package jshell.modelling;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class FuelPrice {
    public FuelPrice(OffsetDateTime onDate, BigDecimal price) {
        super();
        this.onDate = onDate;
        this.price = price;
    }
    private OffsetDateTime onDate;
    public OffsetDateTime getOnDate() { return onDate; }
    public void setOnDate(OffsetDateTime onDate) { this.onDate = onDate; }
    private BigDecimal price;
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
