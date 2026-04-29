package com.turkcell;

public class Functions {
    public static void main(String[] args) {

        // primitive type
        String name = "Yusuf";
        sayWelcome(name);
        System.out.println("Name in main method: " + name); // çıkıt: 

        // reference type
        int[] numbers = {1, 2, 3, 4, 5};
        sum(numbers);
        System.out.println(numbers[0]);
        
    }

    // pass by value -> değer ile aktar: java'da tüm veri tipleri pass by value'dur. yani bir değişkeni bir metoda gönderdiğimizde, o değişkenin bir kopyası oluşturulur ve metoda gönderilir.
    // bu nedenle, metodun içinde yapılan değişiklikler orijinal değişkeni etkilemez.
    public static void sayWelcome(String name){
        name = "zehra";
        System.out.println("Welcome, " + name + "!");

    }

    // pass by reference -> referans ile aktar: java'da arrayler ve objeler referans tiptir. yani bir array veya obje bir metoda gönderildiğinde, o array veya objenin referansı (adres) gönderilir.
    // bu nedenle, metodun içinde yapılan referans bazlı değişiklikler orijinal array veya objeyi etkiler.
    public static void sum(int[] numbers){
        numbers[0] = 10; // bu değişiklik main methodundaki numbers array'ini etkiler çünkü arrayler referans tiptir ve pass by value'dur. yani numbers değişkeni bir kopya oluşturulur ve metoda gönderilir ancak bu kopya aynı array'i referans eder.
    }
}
