package ru.chemelson.patterns.gof.structural;

public class DecoratorDemo {

    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        pizza = new CheeseDecorator(pizza);
        pizza = new PepperoniDecorator(pizza);

        System.out.println("Cost: " + pizza.getCost());
        System.out.println("Description: " + pizza.getDescription());
    }

    // Abstract component
    interface Pizza {
        double getCost();

        String getDescription();
    }

    // Concrete component we will decorate
    static class PlainPizza implements Pizza {
        @Override
        public double getCost() {
            return 10.0;
        }

        @Override
        public String getDescription() {
            return "Plain Pizza";
        }
    }

    // Abstract decorator
    static abstract class ToppingDecorator implements Pizza {
        protected Pizza pizza;

        public ToppingDecorator(Pizza pizza) {
            this.pizza = pizza;
        }

        @Override
        public double getCost() {
            return pizza.getCost();
        }

        @Override
        public String getDescription() {
            return pizza.getDescription();
        }
    }

    // First decorator
    static class CheeseDecorator extends ToppingDecorator {
        public CheeseDecorator(Pizza newPizza) {
            super(newPizza);
        }

        public double getCost() {
            return pizza.getCost() + 2.5;
        }

        public String getDescription() {
            return pizza.getDescription() + ", Cheese";
        }
    }

    // Second decorator
    static class PepperoniDecorator extends ToppingDecorator {
        public PepperoniDecorator(Pizza newPizza) {
            super(newPizza);
        }

        public double getCost() {
            return pizza.getCost() + 3.0;
        }

        public String getDescription() {
            return pizza.getDescription() + ", Pepperoni";
        }
    }

}
