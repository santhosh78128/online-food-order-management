package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.PurchaseOrder;
import com.examly.springapp.repository.PurchaseOrderRepo;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderRepo repo;

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchase(@RequestBody PurchaseOrder purchase){
        return new ResponseEntity<>(repo.save(purchase), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getPurchase(){
        List<PurchaseOrder> purchase = repo.findAll();
        if(purchase.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }  
        return ResponseEntity.status(HttpStatus.OK).body(purchase);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable int id) {
        PurchaseOrder order = repo.findById(id).orElse(null);
        if(order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable int id, @RequestBody PurchaseOrder purchase) {
        PurchaseOrder existing = repo.findById(id).orElse(null);
        if(existing != null) {
            existing.setOrderNumber(purchase.getOrderNumber());
            existing.setOrderDate(purchase.getOrderDate());
            existing.setStatus(purchase.getStatus());
            existing.setSupplier(purchase.getSupplier());
            repo.save(existing);
            return ResponseEntity.status(HttpStatus.OK).body(existing);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}