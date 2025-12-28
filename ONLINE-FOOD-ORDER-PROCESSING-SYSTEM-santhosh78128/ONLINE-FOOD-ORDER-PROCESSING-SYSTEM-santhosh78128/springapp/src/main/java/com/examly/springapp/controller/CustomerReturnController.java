package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.CustomerReturn;
import com.examly.springapp.repository.CustomerReturnRepo;

@RestController
@RequestMapping("/api/customer-returns")
public class CustomerReturnController {
    @Autowired
    private CustomerReturnRepo repo;
    
    @PostMapping
    public ResponseEntity<CustomerReturn> createCustomerReturn(@RequestBody CustomerReturn customerReturn) {
        return new ResponseEntity<>(repo.save(customerReturn), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<CustomerReturn>> getAllCustomerReturns() {
        List<CustomerReturn> returns = repo.findAll();
        if(returns.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(returns);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomerReturn> getCustomerReturnById(@PathVariable int id) {
        CustomerReturn customerReturn = repo.findById(id).orElse(null);
        if(customerReturn == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerReturn);
    }
}
