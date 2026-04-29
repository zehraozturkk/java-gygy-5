package com.turkcell;

public class MsCarRepository implements CarRepository {

    public void addCar(Car car) {
        // Microsoft SQL Server veritabanına araba ekleme işlemi burada yapılır.
        System.out.println("MS SQL Server veritabanına araba eklendi: ");
    }

}
