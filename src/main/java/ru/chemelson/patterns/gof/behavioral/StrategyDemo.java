package ru.chemelson.patterns.gof.behavioral;

public class StrategyDemo {

    public static void main(String[] args) {
        var travelPlan = new TravelPlan();

        travelPlan.setTransportationStrategy(new AirTravel());
        travelPlan.setAccommodationStrategy(new HotelStay());
        travelPlan.setActivityStrategy(new AdventureSports());
        System.out.println(travelPlan.generatePlan());

        System.out.println("Another plan:");

        // Let's use another strategy for transport
        travelPlan.setTransportationStrategy(new TrainTravel());
        System.out.println(travelPlan.generatePlan());
    }

    // Strategy Interfaces
    interface TransportationStrategy {
        String travelPlan();
    }

    interface AccommodationStrategy {
        String stayPlan();
    }

    interface ActivityStrategy {
        String activityPlan();
    }

    // Concrete Strategies
    static class AirTravel implements TransportationStrategy {
        public String travelPlan() {
            return "Travel by air: Fast and convenient for long distances.";
        }
    }

    static class TrainTravel implements TransportationStrategy {
        public String travelPlan() {
            return "Travel by train: if you are afraid of planes.";
        }
    }

    static class HotelStay implements AccommodationStrategy {
        public String stayPlan() {
            return "Stay in a hotel: Enjoy comfort and luxury services";
        }
    }

    static class AdventureSports implements ActivityStrategy {
        public String activityPlan() {
            return "Engage in adventure sports: Exciting and thrilling experiences.";
        }
    }

    // Context Class: Travel Plan
    static class TravelPlan {
        private TransportationStrategy transportationStrategy;
        private AccommodationStrategy accommodationStrategy;
        private ActivityStrategy activityStrategy;

        public void setTransportationStrategy(TransportationStrategy strategy) {
            this.transportationStrategy = strategy;
        }

        public void setAccommodationStrategy(AccommodationStrategy strategy) {
            this.accommodationStrategy = strategy;
        }

        public void setActivityStrategy(ActivityStrategy strategy) {
            this.activityStrategy = strategy;
        }

        public String generatePlan() {
            StringBuilder plan = new StringBuilder();
            if (transportationStrategy != null) {
                plan.append(transportationStrategy.travelPlan()).append("\n");
            }
            if (accommodationStrategy != null) {
                plan.append(accommodationStrategy.stayPlan()).append("\n");
            }
            if (activityStrategy != null) {
                plan.append(activityStrategy.activityPlan()).append("\n");
            }
            return plan.toString();
        }
    }
}
