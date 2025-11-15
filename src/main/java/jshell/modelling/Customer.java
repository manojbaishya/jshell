package jshell.modelling;

import org.jspecify.annotations.NonNull;

public class Customer {
    public Customer(@NonNull String name, @NonNull String registrationNumber, @NonNull VehicleType vehicleType) {
        super();
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    @NonNull
    private final String name;
    public @NonNull String getName() { return name; }

    @NonNull
    private String registrationNumber;
    public @NonNull String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(@NonNull String registrationNumber) { this.registrationNumber = registrationNumber; }

    @NonNull
    private VehicleType vehicleType;
    public @NonNull VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(@NonNull VehicleType vehicleType) { this.vehicleType = vehicleType; }

    @Override
    public String toString() {
        return "Customer{name='%s', registrationNumber='%s', vehicleType=%s}".formatted(name, registrationNumber, vehicleType);
    }
}
