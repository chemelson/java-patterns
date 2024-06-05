package ru.chemelson.patterns.gof.behavioral;

public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        var technicalSupport = new TechnicalSupportHandler();
        var billingSupport = new BillingSupportHandler();
        var generalSupport = new GeneralSupportHandler();

        technicalSupport.setNextHandler(billingSupport);
        billingSupport.setNextHandler(generalSupport);

        technicalSupport.handleRequest(QueryType.TECHNICAL, "I can't connect to the internet.");
        technicalSupport.handleRequest(QueryType.BILLING, "I have a question about my invoice.");
        technicalSupport.handleRequest(QueryType.GENERAL, "Thank you for your service.");
    }

    enum QueryType {
        TECHNICAL, BILLING, GENERAL
    }

    // Abstract handler
    static abstract class SupportHandler {
        protected SupportHandler nextHandler;

        public void setNextHandler(SupportHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        public abstract void handleRequest(QueryType queryType, String message);
    }

    static class TechnicalSupportHandler extends SupportHandler {
        @Override
        public void handleRequest(QueryType queryType, String message) {
            if (queryType == QueryType.TECHNICAL) {
                System.out.println("Technical Support: Handling query - " + message);
            } else if (nextHandler != null) {
                nextHandler.handleRequest(queryType, message);
            }
        }
    }

    static class BillingSupportHandler extends SupportHandler {
        @Override
        public void handleRequest(QueryType queryType, String message) {
            if (queryType == QueryType.BILLING) {
                System.out.println("Billing Support: Handling query - " + message);
            } else if (nextHandler != null) {
                nextHandler.handleRequest(queryType, message);
            }
        }
    }

    static class GeneralSupportHandler extends SupportHandler {
        @Override
        public void handleRequest(QueryType queryType, String message) {
            if (queryType == QueryType.GENERAL) {
                System.out.println("General Support: Handling query - " + message);
            } else if (nextHandler != null) {
                nextHandler.handleRequest(queryType, message);
            }
        }
    }
}
