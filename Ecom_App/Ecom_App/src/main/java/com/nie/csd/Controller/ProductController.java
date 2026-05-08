package com.nie.csd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nie.csd.Services.ProductService;
import com.nie.csd.exceptions.IdNotPresentException;
import com.nie.csd.models.Products;


@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    // Return a list of products
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts() {
      
        List<Products> productList = service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    // get a product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getByProductId(@PathVariable("id") String id) throws IdNotPresentException{
	    Products product = service.getByProductId(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
     //.orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // add a new product
    @PostMapping("/products")
    public Products addProducts(@RequestBody Products product) {
        Products newProduct = service.addProducts(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct).getBody();
    }

    // update a product by id; else add a new product
    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateProducts(@PathVariable("id") String id, @RequestBody Products product) {
        System.out.println("inside update of controller");
        // Implementation for updating a product
        Products updatedProduct = service.updateProducts(id, product); // Placeholder return statement
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    // delete a product by id
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable("id") String id) {
        service.deleteProducts(id); 
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
      }
}