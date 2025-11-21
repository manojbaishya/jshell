package javakit.modelling;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {
    public Transaction(OffsetDateTime createdOn, Station createdAt, Customer createdBy, double quantityLitres) {
        super();
        this.createdOn = createdOn;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.quantityLitres = quantityLitres;
    }
    private OffsetDateTime createdOn;
    public OffsetDateTime getCreatedOn() { return createdOn; }
    public void setCreatedOn(OffsetDateTime createdOn) { this.createdOn = createdOn; }
    
    private Station createdAt;
    public Station getCreatedAt() { return createdAt; }
    public void setCreatedAt(Station createdAt) { this.createdAt = createdAt; }
    
    private Customer createdBy;
    public Customer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Customer createdBy) { this.createdBy = createdBy; }
    
    private double quantityLitres;
    public double getQuantity() { return quantityLitres; }
    public void setQuantity(double quantity) { this.quantityLitres = quantity; }
    
    private BigDecimal amount;
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
