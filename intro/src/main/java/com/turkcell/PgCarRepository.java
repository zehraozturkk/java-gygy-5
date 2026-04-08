package com.turkcell;

public class PgCarRepository implements CarRepository {

    public void addCar(Car car) {
        // PostgreSQL veritabanına araba ekleme işlemi burada yapılır.
        System.out.println("PostgreSQL veritabanına araba eklendi: ");
    }

 

}
