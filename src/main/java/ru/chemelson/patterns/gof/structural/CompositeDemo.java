package ru.chemelson.patterns.gof.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositeDemo {

    public static void main(String[] args) {
        Department developmentDepartment = new Department("Development");
        Department marketingDepartment = new Department("Marketing");

        Employee john = new Employee("John", 40);
        Employee jane = new Employee("Jane", 35);
        Employee mike = new Employee("Mike", 30);

        developmentDepartment.addComponent(john);
        developmentDepartment.addComponent(jane);
        marketingDepartment.addComponent(mike);

        System.out.println("Total Hours in Development Department: " + developmentDepartment.getHours());
        System.out.println("Total Hours in Marketing Department: " + marketingDepartment.getHours());
    }

    // Abstract component which defines interface
    interface OrganizationComponent {
        String getName();

        int getHours();
    }

    // Leaf component
    static class Employee implements OrganizationComponent {
        private final String name;
        private final int hours;

        public Employee(String name, int hours) {
            this.name = name;
            this.hours = hours;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getHours() {
            return hours;
        }
    }

    // Composite component
    static class Department implements OrganizationComponent {
        private final String name;
        private List<OrganizationComponent> components = new ArrayList<>();

        public Department(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getHours() {
            var totalHours = 0;
            for (var component : components) {
                totalHours += component.getHours();
            }
            return totalHours;
        }

        public void addComponent(OrganizationComponent component) {
            components.add(component);
        }
    }
}
