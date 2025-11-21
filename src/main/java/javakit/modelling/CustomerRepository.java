package javakit.modelling;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerRepository {
    private final int upperLimit;
    private List<Customer> customers;
    public CustomerRepository(int upperLimit) { this.upperLimit = upperLimit; }

    private static final Random RAND = new Random();
    public List<Optional<Customer>> getCustomers() {
        var customers = new ArrayList<Optional<Customer>>();
        VehicleType[] vehicleTypes = VehicleType.values();
        var faker = new Faker();
        Customer customer;
        for (int i = 0; i <= upperLimit; i++) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                String name = faker.name().fullName();
                VehicleType vehicleType = vehicleTypes[RAND.nextInt(vehicleTypes.length)];
                String registrationNumber = UUID.randomUUID().toString();
                customer = new Customer(name, registrationNumber, vehicleType);
            } else {
                customer = null;
            }
            customers.add(Optional.ofNullable(customer));
        }

        return customers;
    }
}
