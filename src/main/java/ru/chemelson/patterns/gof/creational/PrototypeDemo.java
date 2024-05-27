package ru.chemelson.patterns.gof.creational;

public class PrototypeDemo {

    public static void main(String[] args) {
        Car basicCar = new BasicCar();
        Car customerCar = (Car) basicCar.clone();

        customerCar.customize("Red", "Sunroof");
    }

    // Prototype
    static abstract class Car implements Cloneable {
        protected String model;
        protected String color;

        public abstract void customize(String color, String accessories);

        // Consider using shallow or deep copy
        @Override
        protected Object clone() {
            Object clone;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
            return clone;
        }
    }

    static class BasicCar extends Car {

        public BasicCar() {
            model = "Basic";
            color = "White";
        }

        @Override
        public void customize(String color, String accessories) {
            this.color = color;
            System.out.println("Car customized with color " + color + " and accessories " + accessories);
        }
    }

}
