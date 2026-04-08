package com.turkcell;

//Araç klasmanına giren nesnelerin ortak özelliklerini 
// ve davranışlarını tanımlayacağımız bir üst sınıf oluşturuyoruz. 
// Car ve Bike sınıfları bu sınıftan miras alarak ortak özelliklere sahip olabilirler.

//superclass
public class Vehicle {
    private String brand;
    private String model;
    private int year;
    private Double price;

     
    //setter methodu. isimlendirme de genellikle set ile başlar.
    public void setPrice(Double price){
        if (price < 0) {
            System.out.println("Fiyat negatif olamaz.");
            return; // negatif fiyat durumunda fonksiyonu sonlandırır.
        }
        this.price = price; // set işlemi
    }


    // getter methodu. isimlendirme de genellikle get ile başlar. private değerlerin okunmasını sağlar.
    public Double getPrice() {
                // get işlemlerini kontrol eden mekanizma
        return this.price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }   

    public void setYear(int year) {
            this.year = year;
        }

}
