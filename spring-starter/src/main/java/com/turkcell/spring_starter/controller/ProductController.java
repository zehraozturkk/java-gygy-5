package com.turkcell.spring_starter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.ProductCreateRespone;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.model.Product;


@RestController //Bu class bi rest controllerdır. içini uygulama başladığında tara, http _> functi. tanımlarını al.

@RequestMapping("/api/product") //localhost:80808/api/product -> bu url'e istek atıldığında bu class çalışsın
public class ProductController {

    //Inmemory database
    //geçici hafıza 
   private List<Product> productList = new ArrayList<>();


   @GetMapping()
   public List<Product> getAllProducts(){
    return productList;

   }

   @GetMapping("/{id}")
   public Product getProductById(@PathVariable int id){ 
    // listeden id == product.getId() olanı bul ve döndür, yoksa null döndür
        return productList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
   }

   // bu şu an create etmenin en best practice yönü.  ProductCreateRespone updatete kullanamayız o zaman ProductUpdate response yaparız.
   @PostMapping  //create edilen create ettiğini geri döner
   public ProductCreateRespone createProduct(@RequestBody ProductForCreateDto product){

    //dışardan bi productfotdto alıyorsun ama veritaabanaı hala Product ile çalışlyorsun, o yüzden dto'yu producta çevirmen lazım

    //TRANSFER -> bu sisteme mapping denir. iu an manuel mapping yaptık. 
        Product newProduct = new Product();
        newProduct.setId(productList.size() + 1); //id'yi otomatik olarak atıyoruz, gerçek bir uygulamada veritabanı bunu yapar
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());

        productList.add(newProduct);


        //domain nesnesi olan product'ı dto'ya çeviriyoruz, bu da manuel mapping oluyor
        ProductCreateRespone response = new ProductCreateRespone();
        response.setId(newProduct.getId());
        response.setName(newProduct.getName());
        response.setPrice(newProduct.getPrice());
        return response;
   }

   @PutMapping
   public void updateProduct(@RequestBody Product product){
    Product existingProduct = productList.stream().filter(p -> p.getId() == product.getId()).findFirst().orElseThrow();
    existingProduct.setName(product.getName());
    existingProduct.setPrice(product.getPrice());
   }

   @DeleteMapping("/{id}")
   public void deleteProduct(@PathVariable int id){
       productList.removeIf(p -> p.getId() == id);
   }
}