package ru.chemelson.patterns.gof.behavioral;

public class CommandDemo {

    public static void main(String[] args) {
        var light = new Light();
        var turnOn = new TurnOnLightCommand(light);
        var turnOff = new TurnOffLightCommand(light);

        var remote = new RemoteControl();
        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }

    // Command interface
    interface Command {
        void execute();
    }

    // Receiver - Light
    static class Light {
        public void turnOn() {
            System.out.println("Light is ON!");
        }

        public void turnOff() {
            System.out.println("Light is OFF!");
        }
    }

    // Concrete commands
    static class TurnOnLightCommand implements Command {
        private Light light;

        public TurnOnLightCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    static class TurnOffLightCommand implements Command {
        private Light light;

        public TurnOffLightCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

    // Invoker
    static class RemoteControl {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        void pressButton() {
            command.execute();
        }
    }
}
