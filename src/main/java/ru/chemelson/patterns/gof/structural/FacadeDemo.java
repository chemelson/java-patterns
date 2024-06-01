package ru.chemelson.patterns.gof.structural;

public class FacadeDemo {

    public static void main(String[] args) {
        var computer = new Computer();
        computer.startComputer();
    }

    static class CPU {
        void initialize() {
            System.out.println("CPU init");
        }
    }

    static class Memory {
        void load(long position, String data) {
            System.out.println("Loading " + data + " into memory at position " + position + ".");
        }
    }

    static class HardDrive {
        String readBootSector() {
            return "Boot sector data";
        }
    }

    static class OperatingSystem {
        String loadKernel() {
            return "OS kernel data";
        }
    }

    // This is actually a Facade
    static class Computer {
        private CPU cpu = new CPU();
        private Memory memory = new Memory();
        private HardDrive hardDrive = new HardDrive();
        private OperatingSystem operatingSystem = new OperatingSystem();

        void startComputer() {
            String bootSector = hardDrive.readBootSector();
            String osData = operatingSystem.loadKernel();
            memory.load(0, bootSector);
            memory.load(1024, osData);
            cpu.initialize();
        }
    }
}
