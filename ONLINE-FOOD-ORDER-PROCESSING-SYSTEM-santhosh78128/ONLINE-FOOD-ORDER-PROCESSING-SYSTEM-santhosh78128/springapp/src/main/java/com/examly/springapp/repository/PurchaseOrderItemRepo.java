package com.examly.springapp.repository;
import com.examly.springapp.model.PurchaseOrderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemRepo extends JpaRepository<PurchaseOrderItem,Integer> {
    List<PurchaseOrderItem> findByPurchaseOrderPurchaseOrderId(int purchaseOrderId);
}
