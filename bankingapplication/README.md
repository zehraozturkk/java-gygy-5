# Banking Application

Basit bir Java konsol bankacılık uygulaması.

## Özellikler

- Müşteri kaydı oluşturma
- Para yatırma
- Para çekme
- Hesap özeti görüntüleme
- Tüm müşterileri listeleme

## Sınıf Yapısı

| Sınıf | Açıklama |
|-------|----------|
| `Person` | Temel kişi bilgileri (ad, soyad, email, şifre) |
| `Customer` | `Person`'dan türeyen müşteri sınıfı |
| `Employee` | `Person`'dan türeyen çalışan sınıfı |
| `Account` | Hesap numarası ve bakiye bilgisi |
| `Main` | Uygulamanın giriş noktası, menü yönetimi |

## Çalıştırma

```bash
cd bankingapplication
mvn compile exec:java -Dexec.mainClass="com.banking.Main"
```

## Kullanım

Uygulama başlatıldığında konsol menüsü açılır:

```
--- BANKA SİSTEMİ ---
1- Müşteri Ol
2- Para Yatır
3- Para Çek
4- Hesap Özeti Görüntüle
5- Veritabanını Gör
0- Çıkış
```

Yeni müşteri oluşturulduğunda otomatik olarak 0 TL bakiyeli bir hesap açılır ve sistem tarafından benzersiz bir müşteri ID'si atanır.
