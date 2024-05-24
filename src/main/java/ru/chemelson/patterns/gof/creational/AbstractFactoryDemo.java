package ru.chemelson.patterns.gof.creational;

public class AbstractFactoryDemo {

    public static void main(String[] args) {
        // You can also combine this pattern with Factory Method to get
        // concrete factories instead of initializing them manually

        CafeFactory coffeeFactory = new CoffeeCafeFactory();
        Drink coffee = coffeeFactory.createDrink();
        Pastry croissant = coffeeFactory.createPastry();
        coffee.serve();
        croissant.serve();

        CafeFactory teaFactory = new TeaCafeFactory();
        Drink tea = teaFactory.createDrink();
        Pastry scone = teaFactory.createPastry();
        tea.serve();
        scone.serve();
    }

    // Abstract product interfaces
    interface Drink {
        void serve();
    }

    interface Pastry {
        void serve();
    }

    // Concrete products
    static class CoffeeDrink implements Drink {
        @Override
        public void serve() {
            System.out.println("Serving Coffee");
        }
    }

    static class CoffeePastry implements Pastry {
        @Override
        public void serve() {
            System.out.println("Serving Croissant");
        }
    }

    static class TeaDrink implements Drink {
        @Override
        public void serve() {
            System.out.println("Serving Tea");
        }
    }

    static class TeaPastry implements Pastry {
        @Override
        public void serve() {
            System.out.println("Serving Scone");
        }
    }

    // Abstract factory interface
    interface CafeFactory {
        Drink createDrink();

        Pastry createPastry();
    }

    // Concrete factories
    static class CoffeeCafeFactory implements CafeFactory {
        @Override
        public Drink createDrink() {
            return new CoffeeDrink();
        }

        @Override
        public Pastry createPastry() {
            return new CoffeePastry();
        }
    }

    static class TeaCafeFactory implements CafeFactory {
        @Override
        public Drink createDrink() {
            return new TeaDrink();
        }

        @Override
        public Pastry createPastry() {
            return new TeaPastry();
        }
    }
}
