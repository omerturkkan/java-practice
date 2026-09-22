package io.github.omerturkkan.javapractice.oop.inheritance;

public class VehicleDemo {
    public static void main(String[] args) {
        Vehicle plain = new Vehicle("Generic", 2015);
        Car car = new Car("Toyota", 2021, 4);
        Motorcycle motorcycle = new Motorcycle("Honda", 2019, false);
        Motorcycle sidecar = new Motorcycle("Ural", 1998, true);

        plain.accelerate(50);
        car.accelerate(50);
        motorcycle.accelerate(50);      // overridden: 50 becomes 100
        sidecar.accelerate(30);

        System.out.println(plain.describe());
        System.out.println(car.describe());
        System.out.println(motorcycle.describe());
        System.out.println(sidecar.describe());

        System.out.printf("%nWheels - car: %d, motorcycle: %d, sidecar: %d%n",
                car.wheelCount(), motorcycle.wheelCount(), sidecar.wheelCount());

        // A subclass instance is also an instance of its parent
        System.out.printf("car instanceof Vehicle : %b%n", car instanceof Vehicle);
        System.out.printf("plain instanceof Car   : %b%n", plain instanceof Car);
    }
}
