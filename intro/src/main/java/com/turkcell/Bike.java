package com.turkcell;

public class Bike extends Vehicle { // Bike sınıfı, Vehicle sınıfından miras alır. Bike sınıfı, Vehicle sınıfının tüm özelliklerini ve davranışlarını miras alır ve ayrıca kendine özgü özellikler ekleyebilir.
    

    private boolean hasIntercom;

    public boolean isHasIntercom() {
        return hasIntercom;
    }

    public void setHasIntercom(boolean hasIntercom) {
        this.hasIntercom = hasIntercom;
    }

}

// Inheritance => kalıtım, bir sınıfın özelliklerini ve davranışlarını başka bir sınıfa aktarma prensibidir. bu sayede kod tekrarını azaltır ve daha düzenli bir yapı sağlar. örneğin Car sınıfı ile Bike sınıfı arasında ortak özellikler olabilir, bu ortak özellikleri bir üst sınıfta tanımlayarak Car ve Bike sınıflarının bu özellikleri miras almasını sağlayabiliriz. bu sayede Car ve Bike sınıflarında ortak özellikleri tekrar tanımlamak zorunda kalmayız.

// ben bir nesnenin tüm özelliklerini taşırken ektra da kendime has özellikler taşıyorum. 

//extends(genişletmek) keywordü ile bir sınıfın başka bir sınıftan miras almasını sağlayabiliriz.
//  örneğin Car sınıfı, Vehicle sınıfından miras alabilir, bu sayede Car sınıfı Vehicle sınıfının tüm özelliklerini ve davranışlarını miras alır ve ayrıca kendine özgü özellikler ekleyebilir.
