package ru.chemelson.patterns.gof.structural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyweightDemo {

    public static void main(String[] args) {
        ParticleSystem system = new ParticleSystem();
        system.addParticle(0, 0, 1, 1, 60, "SmokeTexture", "Circle", "Gray");
        system.addParticle(10, 10, 2, 2, 60, "SmokeTexture", "Circle", "Gray");
        system.simulate();
    }

    // This class implements Flyweight itself and FlyweightFactory at once
    static class ParticleType {
        private static Map<String, ParticleType> cache = new HashMap<>();
        private String texture, shape, color;

        private ParticleType(String texture, String shape, String color) {
            this.texture = texture;
            this.shape = shape;
            this.color = color;
        }

        public static ParticleType getParticleType(String texture, String shape, String color) {
            String key = texture + shape + color;
            if (!cache.containsKey(key)) {
                cache.put(key, new ParticleType(texture, shape, color));
            }
            return cache.get(key);
        }

        public String getTexture() {
            return texture;
        }

        public String getShape() {
            return shape;
        }

        public String getColor() {
            return color;
        }
    }

    // This class uses Flyweight instances
    static class Particle {
        private float x, y, velocityX, velocityY;
        private int lifespan;
        private ParticleType type;

        public Particle(float x, float y, float velocityX, float velocityY, int lifespan, ParticleType type) {
            this.x = x;
            this.y = y;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.lifespan = lifespan;
            this.type = type;
        }

        public void update() {
            x += velocityX;
            y += velocityY;
            lifespan--;
        }

        public void draw() {
            System.out.println("Drawing particle at (" + x + ", " + y + ") with texture: " + type.getTexture());
        }
    }

    // This is where we get Flyweight instance and inject it
    static class ParticleSystem {
        private List<Particle> particles = new ArrayList<>();

        public void addParticle(float x, float y, float velocityX, float velocityY, int lifespan, String texture, String shape, String color) {
            ParticleType type = ParticleType.getParticleType(texture, shape, color);
            particles.add(new Particle(x, y, velocityX, velocityY, lifespan, type));
        }

        public void simulate() {
            for (Particle particle : particles) {
                particle.update();
                particle.draw();
            }
        }
    }

}
