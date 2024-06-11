package ru.chemelson.patterns.gof.behavioral;

public class StateDemo {

    public static void main(String[] args) {
        var doc = new Document();
        doc.publish();
        doc.approve();
    }

    // State interface
    interface State {
        void publish(Document doc);

        void approve(Document doc);
    }

    // Context
    static class Document {
        private State state;

        public Document() {
            this.state = new Draft();
        }

        private void setState(State state) {
            this.state = state;
        }

        public void publish() {
            state.publish(this);
        }

        public void approve() {
            state.approve(this);
        }
    }

    // Concrete states
    static class Draft implements State {
        @Override
        public void publish(Document doc) {
            System.out.println("Publishing draft, moving to moderation");
            doc.setState(new Moderation());
        }

        @Override
        public void approve(Document doc) {
            System.out.println("Draft cannot be approved directly.");
        }
    }

    static class Moderation implements State {
        public void publish(Document doc) {
            System.out.println("Cannot publish from Moderation without approval.");
        }

        public void approve(Document doc) {
            System.out.println("Approving moderation, moving to published.");
            doc.setState(new Published());
        }
    }

    static class Published implements State {
        public void publish(Document doc) {
            System.out.println("Already published.");
        }

        public void approve(Document doc) {
            System.out.println("Already approved.");
        }
    }
}
