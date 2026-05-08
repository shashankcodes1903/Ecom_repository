package com.nie.csd.Services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nie.csd.Repositories.ProductRepository;
import com.nie.csd.exceptions.IdNotPresentException;
import com.nie.csd.models.Products;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    public List<Products> getAllProducts() {
        logger.info("Retrieving all products from the database of collections products");
        return repository.findAll();
    }

    public Products getByProductId(String id) throws IdNotPresentException {
        logger.debug("Retrieving product with id : {} from database "+"of collections products", id);
        return repository.findById(id)
        .orElseThrow( () -> {
            logger.error("Product with id: "+id+" not found in the database of collections products");
            return new IdNotPresentException("Product not found id: "+id);
         }
        );
    }

    public Products addProducts(Products products) {
        return repository.save(products);
    }

    public Products updateProducts(String id, Products product) {
        System.out.println("inside update method of service ");
        Products existingProduct = repository.findById(id).get();
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setTags(product.getTags());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            return repository.save(existingProduct);
        }
        
        return repository.save(product);
        // if the product exists, update it; otherwise, add a new product
        // the id is not being updated here
    }

    public void deleteProducts(String id) {
        Products existingProduct = repository.findById(id).get();
        if (existingProduct != null){
            repository.deleteById(id);
            System.out.println("Product deleted succesfully");
        }
        else{
            System.out.println("Product with id " + id + " not found");
        } 
    }
}