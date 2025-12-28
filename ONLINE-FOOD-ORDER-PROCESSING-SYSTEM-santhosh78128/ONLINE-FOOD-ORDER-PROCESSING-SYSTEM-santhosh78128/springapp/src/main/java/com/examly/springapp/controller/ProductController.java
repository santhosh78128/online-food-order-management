package com.examly.springapp.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepo;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepo repo;
    
    @PostMapping
    public ResponseEntity<Product> createProdcut(@RequestBody Product product){
        try{
            repo.save(product);
            return new ResponseEntity<>(product,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products = repo.findAll();
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
        public ResponseEntity<Product> getbyId(@PathVariable int id)
        {
            Product a=repo.findById(id).orElse(null);
            if(a==null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return new ResponseEntity<>(a,HttpStatus.OK);
        }
            @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product){
        Product existing = repo.findById(id).orElse(null);
        if(existing !=null)
        {
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setProductName(product.getProductName());
        existing.setStockQuantity(product.getStockQuantity());
        repo.save(existing);
        return ResponseEntity.status(HttpStatus.OK).body(existing);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        if(repo.existsById(id)){
            repo.deleteById(id);
        }
        return "record deleted successfully";
    }
}


    
    


