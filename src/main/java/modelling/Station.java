package modelling;

import java.util.List;

public class Station {
    public Station(String name, String address, Location location) {
        super();
        this.name = name;
        this.address = address;
        this.location = location;
    }
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    private String address;
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    private Location location;
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    private List<FuelPrice> fuelPrices;
    public List<FuelPrice> getFuelPrices() { return fuelPrices; }
    public void setFuelPrices(List<FuelPrice> fuelPrices) { this.fuelPrices = fuelPrices; }
}
