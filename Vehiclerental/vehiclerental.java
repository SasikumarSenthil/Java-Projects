import java.util.*;

// Abstract class
abstract class Vehicle {
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected double rentPerDay;
    protected boolean isRented;

    public Vehicle(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isRented = false;
    }

    public abstract void displayInfo();

    public boolean isAvailable() {
        return !isRented;
    }

    public void rentVehicle() {
        isRented = true;
    }

    public void returnVehicle() {
        isRented = false;
    }

    public double calculateRent(int days) {
        return rentPerDay * days;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}

// Car subclass
class Car extends Vehicle {
    private int seats;

    public Car(String vehicleId, String brand, String model, double rentPerDay, int seats) {
        super(vehicleId, brand, model, rentPerDay);
        this.seats = seats;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car ID: " + vehicleId + " | Brand: " + brand + " | Model: " + model +
                " | Rent/Day: ₹" + rentPerDay + " | Seats: " + seats +
                " | Available: " + (isAvailable() ? "Yes" : "No"));
    }
}

// Bike subclass
class Bike extends Vehicle {
    private int engineCC;

    public Bike(String vehicleId, String brand, String model, double rentPerDay, int engineCC) {
        super(vehicleId, brand, model, rentPerDay);
        this.engineCC = engineCC;
    }

    @Override
    public void displayInfo() {
        System.out.println("Bike ID: " + vehicleId + " | Brand: " + brand + " | Model: " + model +
                " | Rent/Day: ₹" + rentPerDay + " | Engine: " + engineCC + "cc" +
                " | Available: " + (isAvailable() ? "Yes" : "No"));
    }
}

// Truck subclass
class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String vehicleId, String brand, String model, double rentPerDay, double loadCapacity) {
        super(vehicleId, brand, model, rentPerDay);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayInfo() {
        System.out.println("Truck ID: " + vehicleId + " | Brand: " + brand + " | Model: " + model +
                " | Rent/Day: ₹" + rentPerDay + " | Load Capacity: " + loadCapacity + " tons" +
                " | Available: " + (isAvailable() ? "Yes" : "No"));
    }
}

// Rental Service class
class RentalService {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void showAvailableVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                v.displayInfo();
            }
        }
    }

    public Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    public void rentVehicle(String id, int days) {
        Vehicle v = findVehicleById(id);
        if (v != null && v.isAvailable()) {
            v.rentVehicle();
            double cost = v.calculateRent(days);
            System.out.println("\nVehicle rented successfully!");
            System.out.println("Total Rent for " + days + " days: ₹" + cost);
        } else {
            System.out.println("\nVehicle not available or invalid ID.");
        }
    }

    public void returnVehicle(String id) {
        Vehicle v = findVehicleById(id);
        if (v != null && !v.isAvailable()) {
            v.returnVehicle();
            System.out.println("\nVehicle returned successfully!");
        } else {
            System.out.println("\nInvalid Vehicle ID or Vehicle is not rented.");
        }
    }
}

// Main class
public class vehiclerental {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RentalService rentalService = new RentalService();

        // Adding sample vehicles
        rentalService.addVehicle(new Car("C1", "Toyota", "Innova", 1500, 7));
        rentalService.addVehicle(new Bike("B1", "Yamaha", "R15", 800, 150));
        rentalService.addVehicle(new Truck("T1", "Tata", "Ace", 2000, 2.5));

        int choice;
        do {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1. Show Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> rentalService.showAvailableVehicles();
                case 2 -> {
                    System.out.print("Enter Vehicle ID: ");
                    String id = sc.next();
                    System.out.print("Enter No. of Days: ");
                    int days = sc.nextInt();
                    rentalService.rentVehicle(id, days);
                }
                case 3 -> {
                    System.out.print("Enter Vehicle ID to return: ");
                    String id = sc.next();
                    rentalService.returnVehicle(id);
                }
                case 4 -> System.out.println("Thank you for using the Vehicle Rental System!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
