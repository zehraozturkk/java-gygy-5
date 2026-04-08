package com.turkcell;

public class Main {
    public static void main(String[] args) {
    

    // karar blokları ve döngüler

    // her koşul bloğu yalnızca 1 scope çalıştırır.
    // kod yukarıdan aşşağı okunur. 
        int age = 26;

        if (age >= 18) {
            System.out.println("You are an adult.");
        }
        else if (age == 18){
                System.out.println("You are a minor.");
        }
        else {
            System.out.println("You are not an adult.");
        }

        String name = "zehra";
        if (name.equals("zehra")) {
            System.out.println("Hello, Zehra!");
        }
        
        calculateGrade(58, "Zehra");
        calculateGrade(60);  // method overloading sayesinde sadece grade girerek de hesaplama yapabiliriz. burada default olarak öğrenci ismini verdik.

        String result1 = calculateGrade2(34, "ali");
        System.out.println(result1);


        double result = sum(10.4, 45.8);
        System.out.println("Sum: " + result);




    }


    //methodlar, berlirli bir görevi yerine getirmek için kullanılan kod bloklarıdır.
    //erişim belirteci= public,i private, protected
    //static veya boş
    // dönüş tipi= void veya herhangi bir veri tipi = void
    // method ismi 
    public static void calculateGrade(int grade, String name) {

        
        if (grade >= 90) {
            System.out.println(name + " A"); // konsola yazar
        } else if (grade >= 80) {   
            System.out.println(name + " B");
        } else if (grade >= 70) {
            System.out.println(name +" C");
        } else if (grade >= 60) {
            System.out.println(name +" D");
        } else {
            System.out.println(name +" F");
        }



    }

    // method overloading, aynı isimde ancak farklı parametre listesine sahip birden fazla method tanımlama tekniğidir.
    // burada bunu yapma sebibimiz tek fonksiyona default bir şekilde parametre koyamamızdır. burada artık sadce grade girerek hesaplanan bir calculaterımız var.

    public static void calculateGrade(int grade) {
       calculateGrade(grade, "öğrenci");
    }


    // dönüş tipi= void veya herhangi bir veri tipi = void ==boş demektir. dönüş tipi yok.
    // void yerine dönüş tipini değiştirebiliriz ve burada return kullanabilriz.

    public static String calculateGrade2(int grade, String name) {

        
        if (grade >= 90) {
            return name + " A";
        } else if (grade >= 80) {   
            return name + " B";
        } else if (grade >= 70) {
            return name + " C"; 
        } else if (grade >= 60) {
            return name + " D";
        } else {
            return name + " F";
        }

    }


    // type türü yanına konulan ... (double...) => bu bir array gibi davranır yani i,sediğiniz kadar değer koy diyoruz
    public static double sum(double... numbers) { // varargs denir bu kullanıma fonk. çağırırken istediğimiz kadar sayı verebiliriz.
        double total = 0;
        for (double num: numbers){
            total += num;
        }
        return total;
    }
}