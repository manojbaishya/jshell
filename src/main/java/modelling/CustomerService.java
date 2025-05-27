package modelling;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final Customer customer;
    private final Station station;
    private final List<Transaction> transactions;
    public List<Transaction> getTransactions() { return transactions; }
    public CustomerService(Customer customer, Station station) {
        super();
        this.customer = customer;
        this.station = station;
        this.transactions = new ArrayList<>();
    }
    public BigDecimal purchaseFuel(double quantity) {
        var now = OffsetDateTime.now();
        Optional<FuelPrice> priceQuery = station.getFuelPrices().stream()
                                                .filter(fuelprice -> compareDates(fuelprice, now))
                                                .findFirst();
        if (priceQuery.isPresent()) {
            FuelPrice priceToday = priceQuery.get();
            BigDecimal amount = priceToday.getPrice().multiply(BigDecimal.valueOf(quantity));
            var transaction = new Transaction(now, station, customer, quantity);
            transaction.setAmount(amount);
            transactions.add(transaction);
            return amount;
        } else {
            return BigDecimal.valueOf(0d);
        }
    }
    private boolean compareDates(FuelPrice fuelprice, OffsetDateTime now) {
        return fuelprice.getOnDate().toInstant().atZone(ZoneId.of("UTC")).toLocalDate()
                        .equals(now.toInstant().atZone(ZoneId.of("UTC")).toLocalDate());
    }
    public BigDecimal getTotalPurchasedBill() {
        return transactions.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public double getTotalPurchasedQuantity() {
        return transactions.stream().mapToDouble(Transaction::getQuantity).sum();
    }
}
