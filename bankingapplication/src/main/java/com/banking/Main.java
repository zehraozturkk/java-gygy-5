package com.banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Customer> customers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            System.out.println("\n--- BANKA SİSTEMİ ---");
            System.out.println("1- Müşteri Ol (Hesap otomatik 0 TL ile açılır)");
            System.out.println("2- Para Yatır");
            System.out.println("3- Para Çek");
            System.out.println("4- Hesap Özeti Görüntüle");
            System.out.println("5- Veritabanını Gör (Tüm Müşteriler)");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    musteriOl();
                    break;
                case 2:
                    parayatir();
                    break;
                case 3:
                    paracek();
                    break;
                case 4:
                    hesapOzetiGoster();
                    break;
                case 5:
                    veritabaniListele();
                    break;
                case 0:
                    running = false;
                    System.out.println("Sistemden çıkılıyor...");
                    break;
                default:
                    System.out.println("Hatalı seçim, tekrar deneyin.");
            }
        }
    }

    public static void musteriOl() {
        System.out.print("Adınız: ");
        String name = scanner.next();
        System.out.print("Soyadınız: ");
        String surname = scanner.next();
        
        String newId = "c" + (customers.size() + 1);
        
        Customer newCustomer = new Customer(name, surname, name.toLowerCase() + "@bank.com", "1234", newId);
        customers.add(newCustomer);
        
        System.out.println("Kayıt Başarılı! Atanan Müşteri ID: " + newId);
    }

    public static void parayatir() {
        System.out.print("Müşteri ID: ");
        String id = scanner.next();
        System.out.print("Yatırılacak Tutar: ");
        double amount = scanner.nextDouble();
        
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                c.getAccount().setBalance(c.getAccount().getBalance() + amount);
                System.out.println("Para yatırıldı. Güncel Bakiye: " + c.getAccount().getBalance() + " TL");
                return;
            }
        }
        System.out.println("Üzgünüz, bu ID ile eşleşen bir müşteri bulunamadı.");
    }

    public static void paracek() {
        System.out.print("Müşteri ID: ");
        String id = scanner.next();
        System.out.print("Çekilecek Tutar: ");
        double amount = scanner.nextDouble();
        
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                if (c.getAccount().getBalance() >= amount) {
                    c.getAccount().setBalance(c.getAccount().getBalance() - amount);
                    System.out.println("Para çekildi. Güncel Bakiye: " + c.getAccount().getBalance() + " TL");
                } else {
                    System.out.println("Yetersiz bakiye. Mevcut Bakiye: " + c.getAccount().getBalance() + " TL");
                }
                return;
            }
        }
        System.out.println("Üzgünüz, bu ID ile eşleşen bir müşteri bulunamadı.");
    }

    public static void hesapOzetiGoster() {
        System.out.print("Bilgilerini görmek istediğiniz Müşteri ID girin: ");
        String id = scanner.next();
        
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                System.out.println("\n--- " + c.getName() + " " + c.getSurname() + " ---");
                System.out.println("Hesap No: " + c.getAccount().getAccountNumber());
                System.out.println("Güncel Bakiye: " + c.getAccount().getBalance() + " TL");
                return;
            }
        }
        System.out.println("Üzgünüz, bu ID ile eşleşen bir müşteri bulunamadı.");
    }

    public static void veritabaniListele() {
        if (customers.isEmpty()) {
            System.out.println("Sistemde henüz kayıtlı müşteri yok.");
            return;
        }
        System.out.println("\n--- SİSTEMDEKİ TÜM MÜŞTERİLER ---");
        System.out.printf("%-10s | %-15s | %-10s%n", "ID", "AD SOYAD", "BAKİYE");
        System.out.println("------------------------------------------");
        for (Customer c : customers) {
            System.out.printf("%-10s | %-15s | %-10s TL%n", 
                c.getCustomerId(), 
                c.getName() + " " + c.getSurname(), 
                c.getAccount().getBalance());
        }
    }
}