package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.model.Product;


@RestController //Bu class bi rest controllerdır. içini uygulama başladığında tara, http _> functi. tanımlarını al.

@RequestMapping("/api/product") //localhost:80808/api/product -> bu url'e istek atıldığında bu class çalışsın
public class ProductController {
    //kullanıcı ne zaman api/product alanına istek atarsa -> cevap bu fonk. dönen cevap olsun
    ///api/product -> sayHi(); matchle
    @GetMapping("") // localhost:8080/api/product -> sayHi() çalışsın
    public String sayHi(String name, int age){
        return "Hi " + name +  " yaşınız: " + age;
    }

    @GetMapping("hello/{name}") // localhost:8080/api/product/hello/ali -> sayHello() çalışsın, bu şekilde çok kullanılmaz.
    public String sayHello(@PathVariable String name){
        return "Hello " + name;
    }

    @PostMapping
    public String add(@RequestBody Product product){
        return product.getId() + " id'li" + product.getName()+ "ürün eklendi";
    }

}
