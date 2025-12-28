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

import com.examly.springapp.model.Supplier;
import com.examly.springapp.repository.SupplierRepo;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierRepo repo;

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier){
        try{
            return new ResponseEntity<>(repo.save(supplier),HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Supplier>>getAllSupplier()
    {
          List<Supplier> suppliers = repo.findAll();
          if(suppliers.isEmpty())
          {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
          }
          return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable int id)
    {
        Supplier a=repo.findById(id).orElse(null);
        if(a==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(a);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable int id,@RequestBody Supplier supplier){
        Supplier existing = repo.findById(id).orElse(null);
        if(existing!=null){
            existing.setAddress(supplier.getAddress());
            existing.setContactNumber(supplier.getContactNumber());
            existing.setEmail(supplier.getEmail());
            existing.setSupplierName(supplier.getSupplierName());
            repo.save(existing);
            return ResponseEntity.status(HttpStatus.OK).body(existing);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable int id){
        //comment
    }
}

