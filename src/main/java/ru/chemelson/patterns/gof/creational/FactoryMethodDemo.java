package ru.chemelson.patterns.gof.creational;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        ShapeFactory factory = new DefaultShapeFactory();

        Shape hexagon = factory.getShape("hexagon");
        hexagon.draw();

        Shape pentagon = factory.getShape("pentagon");
        pentagon.draw();
    }

    // Absract base class
    static abstract class Shape {
        abstract void draw();
    }

    // Concrete class 1
    static class Hexagon extends Shape {
        @Override
        void draw() {
            System.out.println("Hexagon");
        }
    }

    // Concrete class 2
    static class Pentagon extends Shape {
        @Override
        void draw() {
            System.out.println("Pentagon");
        }
    }

    // Factory interface
    interface ShapeFactory {
        Shape getShape(String type);
    }

    // Concrete factory implementation
    static class DefaultShapeFactory implements ShapeFactory {
        @Override
        public Shape getShape(String type) {
            return switch (type) {
                case "hexagon" -> new Hexagon();
                case "pentagon" -> new Pentagon();
                default -> throw new IllegalArgumentException("unknown type");
            };
        }
    }

}
