package com.turkcell;

public class Interfaces {
    public static void main(String[] args) {
       CarRepository carRepo = new PgCarRepository();// sol taraf interface, sağ taraf implementasyon
       // sol tarf => bana carrepository kurallarına uyan bir somut bir cisim ver.
       carRepo.addCar(new Car(true, "BMW"));
    }

}
