-- ============================================================
-- KÜTÜPHANE SİSTEMİ VERİTABANI
-- Tablolar: student, officer, authors, books, barrow, fine
-- ============================================================

CREATE DATABASE library_system;

-- ============================================================
-- DDL - TABLO OLUŞTURMA
-- ============================================================

CREATE TABLE student (
    student_id  SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    surname     VARCHAR(100) NOT NULL,
    phone       VARCHAR(20)
);

CREATE TABLE officer (
    officer_id  SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    surname     VARCHAR(100) NOT NULL,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL
);

CREATE TABLE authors (
    author_id   SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    surname     VARCHAR(100) NOT NULL
);

CREATE TABLE books (
    book_id     SERIAL PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    author_id   INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

-- status: 'borrowed' | 'returned' | 'overdue'
CREATE TABLE barrow (
    barrow_id   SERIAL PRIMARY KEY,
    student_id  INTEGER NOT NULL,
    officer_id  INTEGER NOT NULL,
    book_id     INTEGER NOT NULL,
    barrow_date DATE    NOT NULL DEFAULT CURRENT_DATE,
    due_date    DATE    NOT NULL,
    return_date DATE,
    status      VARCHAR(20) NOT NULL DEFAULT 'borrowed',
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (officer_id) REFERENCES officer(officer_id),
    FOREIGN KEY (book_id)    REFERENCES books(book_id)
);

-- is_paid: true = ödendi, false = ödenmedi
CREATE TABLE fine (
    fine_id     SERIAL PRIMARY KEY,
    barrow_id   INTEGER        NOT NULL,
    officer_id  INTEGER        NOT NULL,
    amount      NUMERIC(8, 2)  NOT NULL,
    issued_date DATE           NOT NULL DEFAULT CURRENT_DATE,
    is_paid     BOOLEAN        NOT NULL DEFAULT FALSE,
    FOREIGN KEY (barrow_id)  REFERENCES barrow(barrow_id),
    FOREIGN KEY (officer_id) REFERENCES officer(officer_id)
);

-- ============================================================
-- DML - VERİ EKLEME (INSERT)
-- ============================================================

-- student
INSERT INTO student (name, surname, phone) VALUES
    ('Ali',    'Yılmaz',  '05301112233'),
    ('Ayşe',   'Kaya',    '05322223344'),
    ('Mehmet', 'Çelik',   '05333334455'),
    ('Zeynep', 'Arslan',  '05344445566'),
    ('Emre',   'Doğan',   '05355556677');

-- officer
INSERT INTO officer (name, surname, username, password) VALUES
    ('Fatma',  'Şahin',   'fatma.sahin',   'pass1234'),
    ('Hasan',  'Öztürk',  'hasan.ozturk',  'pass5678'),
    ('Merve',  'Erdoğan', 'merve.erdogan',  'pass9012'),
    ('Burak',  'Aydın',   'burak.aydin',   'pass3456'),
    ('Selin',  'Kurt',    'selin.kurt',    'pass7890');

-- authors
INSERT INTO authors (name, surname) VALUES
    ('Orhan',    'Pamuk'),
    ('Sabahattin', 'Ali'),
    ('Yaşar',    'Kemal'),
    ('Halide Edib', 'Adıvar'),
    ('Ahmet Hamdi', 'Tanpınar');

-- books
INSERT INTO books (name, author_id) VALUES
    ('Kar',                         1),
    ('İçimizdeki Şeytan',           2),
    ('İnce Memed',                  3),
    ('Sinekli Bakkal',              4),
    ('Huzur',                       5),
    ('Masumiyet Müzesi',            1),
    ('Kürk Mantolu Madonna',        2);

-- barrow
INSERT INTO barrow (student_id, officer_id, book_id, barrow_date, due_date, return_date, status) VALUES
    (1, 1, 1, '2026-04-01', '2026-04-15', '2026-04-13', 'returned'),
    (2, 2, 3, '2026-04-05', '2026-04-19', NULL,          'borrowed'),
    (3, 1, 5, '2026-04-10', '2026-04-24', '2026-05-01', 'overdue'),
    (4, 3, 2, '2026-04-15', '2026-04-29', NULL,          'borrowed'),
    (5, 2, 4, '2026-04-20', '2026-05-04', '2026-05-04', 'returned'),
    (1, 4, 6, '2026-05-01', '2026-05-15', NULL,          'borrowed');

-- fine (geç iade veya kayıp durumlarda)
INSERT INTO fine (barrow_id, officer_id, amount, issued_date, is_paid) VALUES
    (3, 1, 35.00, '2026-05-02', FALSE),
    (1, 2, 10.00, '2026-04-16', TRUE),
    (5, 3,  5.00, '2026-05-05', FALSE),
    (4, 1, 20.00, '2026-05-06', FALSE),
    (2, 4, 15.00, '2026-05-07', FALSE);

-- ============================================================
-- DML - GÜNCELLEME (UPDATE)
-- ============================================================

-- Öğrencinin telefon numarasını güncelle
UPDATE student SET phone = '05399998877' WHERE student_id = 1;

-- Kitabı iade edildi olarak işaretle ve iade tarihini gir
UPDATE barrow SET return_date = CURRENT_DATE, status = 'returned' WHERE barrow_id = 2;

-- Cezayı ödendi olarak işaretle
UPDATE fine SET is_paid = TRUE WHERE fine_id = 1;

-- Görevlinin şifresini değiştir
UPDATE officer SET password = 'yeniSifre99' WHERE username = 'fatma.sahin';

-- Gecikmiş ödünç almaları 'overdue' yap
UPDATE barrow SET status = 'overdue'
WHERE due_date < CURRENT_DATE AND return_date IS NULL AND status = 'borrowed';

-- ============================================================
-- DML - SİLME (DELETE)
-- ============================================================

-- Ödenen cezayı sil
DELETE FROM fine WHERE is_paid = TRUE AND fine_id = 2;

-- İade edilen ve cezası olmayan ödünç kaydını sil (önce fine kontrol et)
DELETE FROM barrow WHERE barrow_id = 1 AND status = 'returned'
  AND barrow_id NOT IN (SELECT barrow_id FROM fine);

-- Hiç kitabı olmayan bir yazarı sil
DELETE FROM authors WHERE author_id NOT IN (SELECT author_id FROM books);

-- Sisteme kayıtlı eski bir öğrenciyi sil (önce ilişkili barrow/fine yoksa)
DELETE FROM student WHERE student_id = 5
  AND student_id NOT IN (SELECT student_id FROM barrow);

-- Silinecek görevliyi sil (barrow ve fine'da kullanılmıyorsa)
DELETE FROM officer WHERE officer_id = 5
  AND officer_id NOT IN (SELECT officer_id FROM barrow)
  AND officer_id NOT IN (SELECT officer_id FROM fine);

-- ============================================================
-- DML - SORGULAMA (SELECT)
-- ============================================================

-- 1. Tüm kitapları yazar adıyla birlikte listele
SELECT b.book_id, b.name AS kitap, a.name || ' ' || a.surname AS yazar
FROM books b
JOIN authors a ON b.author_id = a.author_id;

-- 2. Şu an ödünçte olan kitaplar (iade edilmemiş)
SELECT s.name || ' ' || s.surname AS ogrenci,
       bk.name                    AS kitap,
       br.barrow_date,
       br.due_date,
       br.status
FROM barrow br
JOIN student s  ON br.student_id = s.student_id
JOIN books   bk ON br.book_id    = bk.book_id
WHERE br.return_date IS NULL;

-- 3. Ödenmemiş cezaları görevli adıyla listele
SELECT f.fine_id,
       s.name || ' ' || s.surname AS ogrenci,
       f.amount,
       f.issued_date,
       f.is_paid
FROM fine f
JOIN barrow br ON f.barrow_id   = br.barrow_id
JOIN student s ON br.student_id = s.student_id
WHERE f.is_paid = FALSE;

-- 4. Her öğrencinin toplam ödünç alma sayısı
SELECT s.name || ' ' || s.surname AS ogrenci, COUNT(br.barrow_id) AS toplam_odunc
FROM student s
LEFT JOIN barrow br ON s.student_id = br.student_id
GROUP BY s.student_id, ogrenci
ORDER BY toplam_odunc DESC;

-- 5. En çok ödünç alınan kitaplar
SELECT bk.name AS kitap, COUNT(br.barrow_id) AS odunc_sayisi
FROM books bk
LEFT JOIN barrow br ON bk.book_id = br.book_id
GROUP BY bk.book_id, bk.name
ORDER BY odunc_sayisi DESC;

-- 6. Gecikmiş iade listesi (bugün itibarıyla)
SELECT s.name || ' ' || s.surname AS ogrenci,
       bk.name                    AS kitap,
       br.due_date,
       CURRENT_DATE - br.due_date AS geciken_gun
FROM barrow br
JOIN student s  ON br.student_id = s.student_id
JOIN books   bk ON br.book_id    = bk.book_id
WHERE br.return_date IS NULL AND br.due_date < CURRENT_DATE;

-- 7. Toplam tahsil edilmemiş ceza tutarı
SELECT SUM(amount) AS tahsil_edilmemis_toplam FROM fine WHERE is_paid = FALSE;
