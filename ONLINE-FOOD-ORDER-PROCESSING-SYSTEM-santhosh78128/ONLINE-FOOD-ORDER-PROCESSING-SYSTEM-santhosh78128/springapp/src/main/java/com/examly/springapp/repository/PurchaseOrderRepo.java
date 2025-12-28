package com.examly.springapp.repository;
import com.examly.springapp.model.PurchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder,Integer>{
    // 
}
