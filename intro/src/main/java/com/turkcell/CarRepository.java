package com.turkcell;


// interface demek içi bol sadece imzası olan method ve değerler içeren bir yapıdır.
// sistemimde araba veritabanı olarak çalışmak isteyen her nesne bu interface'i implemet etmek zorunda
// implement ediyor ise imzaları birbir uygulamak zorunda
public interface CarRepository {

    // bir car reposu nasıl davranır, ne yapar, nasıl çalışır gibi sorulara cevap verecek net metotları tanımlayacağız. 
    void addCar(Car car); // araba ekleme metodu

}

// böylelikle postgreSQLCarRepository, MySQLCarRepository gibi farklı veritabanları için farklı implementasyonlar yapabiliriz. 
// Bu sayede kodumuz daha esnek ve genişletilebilir olur.
