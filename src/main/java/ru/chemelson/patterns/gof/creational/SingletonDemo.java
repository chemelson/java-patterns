package ru.chemelson.patterns.gof.creational;

/**
 * There are several ways to implement Singleton in Java, they differ in
 * thread safety and complexity. We will implement the most effective one
 * which uses most evident language features.
 * See others implementations like synchronized accessor, double check lock
 * and on demand holder.
 * And also don't forget about singleton bean scope in Spring, etc.
 */

public class SingletonDemo {

    public static void main(String[] args) {
        var instance = Singleton.INSTANCE.getInstance();
        System.out.println(instance.getInfo());
    }

    public enum Singleton {

        INSTANCE("This can be anything");

        private final String info;

        Singleton(String info) {
            this.info = info;
        }

        public Singleton getInstance() {
            return INSTANCE;
        }

        public String getInfo() {
            return info;
        }

    }

}
