package jshell.modelling;

public class Customer {
    public Customer(String registrationNumber, VehicleType vehicleType) {
        super();
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    private String registrationNumber;
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    private VehicleType vehicleType;
    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
}
