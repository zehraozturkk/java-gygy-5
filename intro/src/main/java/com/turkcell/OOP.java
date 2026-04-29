package com.turkcell;

public class OOP {

    public static void main(String[] args) {
        Car car1 = new Car(true, "Honda"); //Car sınıfnından bir nesene oluşturduk. new -> yeni bir instance oluşturma keywordü.

        car1.setYear(2020);  // buna set işlemi denir(değer atama işlemi)
        car1.setModel("Civic");
        car1.setBrand("Honda");
        car1.setPrice(25000.0);

        System.out.println(car1.getPrice()); // get işlemi. price değeri private olduğu için doğrudan erişemeyiz, onun yerine getPrice fonksiyonu ile erişiriz. 

        System.out.println(car1.getBrand()); // buna get işlemi denir(değer okuma işlemi)

        String[] specs = {"ABS", "Airbag", "Sunroof"};
        car1.setSpechs(specs); // spechs özelliği private olduğu için doğrudan erişemeyiz, getter-setter ile erişilir.
        
        String[] x = car1.getSpechs(); // getSpechs() fonksiyonu, spechs dizisinin bir kopyasını döndürür. bu sayede orijinal diziye yapılan değişiklikler, x dizisine yansımayacaktır. bu da encapsulation prensibine uygun bir şekilde veri güvenliği sağlar.
        x[0] = "ESP"; // x dizisinin ilk elemanını değiştirdik, ancak bu değişiklik car1 nesnesinin spechs dizisine yansımayacaktır.

        System.out.println(car1.getSpechs()[0]); // car1 nesnesinin spechs dizisinin ilk elemanını yazdırır, bu da "ABS" olacaktır.
       
    }

}
