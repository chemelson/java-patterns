package ru.chemelson.patterns.gof.behavioral;

public class VisitorDemo {

    public static void main(String[] args) {
        Product food = new Food();
        Product clothing = new Clothing();
        Product electronics = new Electronics();

        DiscountVisitor holidayVisitor = new HolidayDiscountVisitor();
        DiscountVisitor clearanceVisitor = new ClearanceDiscountVisitor();

        food.accept(holidayVisitor);
        clothing.accept(clearanceVisitor);
        electronics.accept(holidayVisitor);
    }

    interface DiscountVisitor {
        void visitFood(Food food);

        void visitClothing(Clothing clothing);

        void visitElectronics(Electronics electronics);
    }

    interface Product {
        void accept(DiscountVisitor visitor);
    }

    static class Food implements Product {
        @Override
        public void accept(DiscountVisitor visitor) {
            visitor.visitFood(this);
        }
    }

    static class Clothing implements Product {
        @Override
        public void accept(DiscountVisitor visitor) {
            visitor.visitClothing(this);
        }
    }

    static class Electronics implements Product {
        @Override
        public void accept(DiscountVisitor visitor) {
            visitor.visitElectronics(this);
        }
    }

    static class HolidayDiscountVisitor implements DiscountVisitor {
        public void visitFood(Food food) {
            System.out.println("Applying holiday discount to food.");
        }

        public void visitClothing(Clothing clothing) {
            System.out.println("Applying holiday discount to clothing.");
        }

        public void visitElectronics(Electronics electronics) {
            System.out.println("Applying holiday discount to electronics.");
        }
    }

    static class ClearanceDiscountVisitor implements DiscountVisitor {
        public void visitFood(Food food) {
            System.out.println("Applying clearance discount to food.");
        }

        public void visitClothing(Clothing clothing) {
            System.out.println("Applying clearance discount to clothing.");
        }

        public void visitElectronics(Electronics electronics) {
            System.out.println("Applying clearance discount to electronics.");
        }
    }
}
