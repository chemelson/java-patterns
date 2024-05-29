package ru.chemelson.patterns.gof.structural;

public class BridgeDemo {

    public static void main(String[] args) {
        Transmission manual = new ManualTransmission();
        Vehicle car = new Car(manual);
        car.applyTransmission();

        Transmission auto = new AutomaticTransmission();
        Vehicle truck = new Truck(auto);
        truck.applyTransmission();
    }

    // Implementor
    interface Transmission {
        void applyGear();
    }

    // Concrete implementors
    static class ManualTransmission implements Transmission {
        @Override
        public void applyGear() {
            System.out.println("Manual transmission applied");
        }
    }

    static class AutomaticTransmission implements Transmission {
        @Override
        public void applyGear() {
            System.out.println("Automatic transmission applied");
        }
    }

    // Abstraction
    static abstract class Vehicle {
        protected Transmission transmission;

        public Vehicle(Transmission transmission) {
            this.transmission = transmission;
        }

        abstract void applyTransmission();
    }

    // Refined abstractions
    static class Car extends Vehicle {
        public Car(Transmission transmission) {
            super(transmission);
        }

        @Override
        void applyTransmission() {
            transmission.applyGear();
        }
    }

    static class Truck extends Vehicle {
        public Truck(Transmission transmission) {
            super(transmission);
        }

        @Override
        void applyTransmission() {
            transmission.applyGear();
        }
    }

}
