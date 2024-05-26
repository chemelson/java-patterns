package ru.chemelson.patterns.gof.creational;

public class BuilderDemo {

    public static void main(String[] args) {
        // Using simple builder
        var waiter = new Waiter();
        PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();

        waiter.setPizzaBuilder(hawaiianPizzaBuilder);
        waiter.constructPizza();

        var pizza = waiter.getPizza();
        pizza.showPizza();
        System.out.println();

        // Using chained builder
        var car = new Car.Builder()
                .model("Lambo")
                .color("Red")
                .hp(500)
                .build();
        System.out.println(car);
    }

    // Product
    static class Pizza {
        private String size;

        public void setSize(String size) {
            this.size = size;
        }

        public void setCrust(String crust) {
            this.crust = crust;
        }

        public void setToppings(String toppings) {
            this.toppings = toppings;
        }

        private String crust;
        private String toppings;

        public Pizza(String size, String crust, String toppings) {
            this.size = size;
            this.crust = crust;
            this.toppings = toppings;
        }

        public void showPizza() {
            System.out.println("Pizza Size: " + size + ", Crust: " + crust + ", Toppings: " + toppings);
        }
    }

    // Abstract base builder. This is a very simple builder.
    static abstract class PizzaBuilder {
        protected Pizza pizza;

        public Pizza getPizza() {
            return pizza;
        }

        public void createNewPizzaProduct() {
            pizza = new Pizza("default", "default", "default");
        }

        public abstract void buildSize();

        public abstract void buildCrust();

        public abstract void buildToppings();
    }

    // Concrete builder
    static class HawaiianPizzaBuilder extends PizzaBuilder {

        @Override
        public void buildSize() {
            pizza.setSize("Large");
        }

        @Override
        public void buildCrust() {
            pizza.setCrust("Thin");
        }

        @Override
        public void buildToppings() {
            pizza.setToppings("Ham and Pineapple");
        }
    }

    // Director class to build pizza. Often our code acts as director.
    static class Waiter {
        private PizzaBuilder pizzaBuilder;

        public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
            this.pizzaBuilder = pizzaBuilder;
        }

        public Pizza getPizza() {
            return pizzaBuilder.getPizza();
        }

        public void constructPizza() {
            pizzaBuilder.createNewPizzaProduct();
            pizzaBuilder.buildSize();
            pizzaBuilder.buildCrust();
            pizzaBuilder.buildToppings();
        }
    }

    // Let's implement chained builder we've get used to
    static class Car {
        private final String model;
        private final String color;
        private final int hp;

        private Car(Builder builder) {
            this.model = builder.model;
            this.color = builder.color;
            this.hp = builder.hp;
        }

        public String getModel() {
            return model;
        }

        public String getColor() {
            return color;
        }

        public int getHp() {
            return hp;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "model='" + model + '\'' +
                    ", color='" + color + '\'' +
                    ", hp=" + hp +
                    '}';
        }

        public static class Builder {
            private String model;
            private String color;
            private int hp;

            public Builder model(String model) {
                this.model = model;
                return this;
            }

            public Builder color(String color) {
                this.color = color;
                return this;
            }

            public Builder hp(int hp) {
                this.hp = hp;
                return this;
            }

            public Car build() {
                return new Car(this);
            }
        }
    }
}
