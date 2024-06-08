package ru.chemelson.patterns.gof.behavioral;

public class MediatorDemo {

    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        FirstComponent first = new FirstComponent();
        SecondComponent second = new SecondComponent();

        mediator.setFirstComponent(first);
        mediator.setSecondComponent(second);

        first.setMediator(mediator);
        second.setMediator(mediator);

        first.sendMessage("Hola!");
    }

    interface Mediator {
        void sendMessage(String message, Component component);
    }

    interface Component {
        void setMediator(Mediator mediator);

        void sendMessage(String message);

        void receiveMessage(String message);
    }

    static class FirstComponent implements Component {
        private Mediator mediator;

        @Override
        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }

        @Override
        public void sendMessage(String message) {
            mediator.sendMessage(message, this);
        }

        @Override
        public void receiveMessage(String message) {
            System.out.println("First component received: " + message);
        }
    }

    static class SecondComponent implements Component {
        private Mediator mediator;

        @Override
        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }

        @Override
        public void sendMessage(String message) {
            mediator.sendMessage(message, this);
        }

        @Override
        public void receiveMessage(String message) {
            System.out.println("Second component received: " + message);
        }
    }

    static class ConcreteMediator implements Mediator {
        private FirstComponent firstComponent;
        private SecondComponent secondComponent;

        public void setFirstComponent(FirstComponent firstComponent) {
            this.firstComponent = firstComponent;
        }

        public void setSecondComponent(SecondComponent secondComponent) {
            this.secondComponent = secondComponent;
        }

        @Override
        public void sendMessage(String message, Component component) {
            if (component.equals(firstComponent) && secondComponent != null) {
                secondComponent.receiveMessage(message);
            } else if (component.equals(secondComponent) && firstComponent != null) {
                firstComponent.receiveMessage(message);
            }
        }
    }

}
