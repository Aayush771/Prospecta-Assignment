package com.example.question1.Controller;


import com.example.question1.Entity.Product;
import com.example.question1.Exception.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
@RestController
public class ProductController {

	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/products/{category}")
	public ResponseEntity<?> getProductByCatogary(@PathVariable("category") String category){
		String url = "https://fakestoreapi.com/products/category/"+category;

                try {
                        Product[] products = restTemplate.getForObject(url, Product[].class);
		
                        if(products == null || products.length == 0) {
                              throw new ProductNotFoundException("Product not found with category "+category);
                        }
                        return new ResponseEntity<>(Arrays.asList(products), HttpStatus.OK);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: " + e.getMessage());   
                }
		
		
		
	}

	@PostMapping("/products")
	public ResponseEntity<?> saveProductHandler(@RequestBody Product product) {

       String Url = "https://fakestoreapi.com/products";
		
           try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");
                HttpEntity<Product> request = new HttpEntity<>(product, headers);
    
    
                ResponseEntity<Object> response = restTemplate.exchange(
                        Url, 
                        HttpMethod.POST, 
                        request, 
                        Object.class
                );
                 if(response.getBody() == null) {
                     throw new ProductNotFoundException("Unable to add the Product");
                 }
               return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
           } catch (Exception e) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body("Error : " + e.getMessage());
           }
           

	}
	
	
}
