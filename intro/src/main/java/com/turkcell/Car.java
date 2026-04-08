package com.turkcell;

//subclass
public class Car extends Vehicle { // Car sınıfı, Vehicle sınıfından miras alır. Car sınıfı, Vehicle sınıfının tüm özelliklerini ve davranışlarını miras alır ve ayrıca kendine özgü özellikler ekleyebilir.
    private boolean isElectric;
   
    //Referans tipler için getter-setter kullanımı
    private String[] spechs;

    //Constructor
    public Car(boolean isElectric, String brand) {
        System.out.println("Car constructor çalıştı");
        this.setElectric(isElectric);
        super.setBrand(brand); //kalıtım aldığımız sınıfı temsil eder 
    }

    public Car(){
        System.out.println("Car constructor çalıştı");
    }

    public void setSpechs(String[] spechs) {
        this.spechs = spechs;
    }

    //clone -> değerlerini al, referansını alma

    public String[] getSpechs() {
        return this.spechs.clone(); // clone() metodu, bir dizinin kopyasını oluşturur ve referansını döndürür.
        //  bu sayede orijinal diziye yapılan değişiklikler, kopya diziye yansımayacaktır. bu da encapsulation prensibine uygun bir şekilde veri güvenliği sağlar.
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

}
