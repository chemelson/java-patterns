package ru.chemelson.patterns.gof.behavioral;

import java.util.Stack;

public class MementoDemo {

    public static void main(String[] args) {
        var gameManager = new GameManager();
        var game = gameManager.newGame();
        game.printStatistics();
        game.play(1, 5);
        game.printStatistics();
        gameManager.makeSave();
        game.play(1, 10);
        game.printStatistics();
        gameManager.loadLastSave();
        game.printStatistics();
    }

    // Memento - aka snapshot
    record GameSave(int level, int score) {
    }

    // Originator - this class state we can save as memento
    static class Game {
        private int level = 0;
        private int score = 0;

        public void play(int levelIncrement, int scoreIncrement) {
            level += levelIncrement;
            score += scoreIncrement;
        }

        public GameSave save() {
            System.out.println("Saved.");
            return new GameSave(level, score);
        }

        public void load(GameSave save) {
            this.level = save.level;
            this.score = save.score;
            System.out.println("Loaded.");
        }

        public void printStatistics() {
            System.out.println("Level: " + level + ". Score: " + score + ".");
        }
    }

    // Caretaker - this class is responsible for managing mementos
    static class GameManager {
        private Stack<GameSave> saves;
        private Game game;

        public Game newGame() {
            game = new Game();
            saves = new Stack<>();
            System.out.println("New game started!");
            return game;
        }

        public void makeSave() {
            var save = game.save();
            saves.push(save);
        }

        public void loadLastSave() {
            var save = saves.pop();
            game.load(save);
        }
    }

}
