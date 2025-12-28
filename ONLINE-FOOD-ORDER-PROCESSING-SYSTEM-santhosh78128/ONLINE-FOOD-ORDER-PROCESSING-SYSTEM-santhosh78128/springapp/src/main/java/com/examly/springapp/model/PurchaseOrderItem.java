package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseOrderItemId;
    
    private int quantity;
    private double unitPrice;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    
    public PurchaseOrderItem() {}
    
    public int getPurchaseOrderItemId() {
        return purchaseOrderItemId;
    }
    
    public void setPurchaseOrderItemId(int purchaseOrderItemId) {
        this.purchaseOrderItemId = purchaseOrderItemId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }
    
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
}
