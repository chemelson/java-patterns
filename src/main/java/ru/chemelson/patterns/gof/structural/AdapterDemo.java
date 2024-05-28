package ru.chemelson.patterns.gof.structural;

public class AdapterDemo {

    public static void main(String[] args) {
        FrenchSpeaker frenchSpeaker = new FrenchSpeaker();
        Translator translator = new Translator(frenchSpeaker);
        EnglishClient client = new EnglishClient(translator);

        client.express("Hello! Thank you for the meeting.");
    }

    // Adaptee - to this class we will make adapter
    static class FrenchSpeaker {
        public void speakFrench(String message) {
            System.out.println("Speaking in French: " + message);
        }
    }

    // Target interface
    interface EnglishSpeaker {
        void speakEnglish(String message);
    }

    // Adadter
    static class Translator implements EnglishSpeaker {
        private FrenchSpeaker frenchSpeaker;

        public Translator(FrenchSpeaker frenchSpeaker) {
            this.frenchSpeaker = frenchSpeaker;
        }

        @Override
        public void speakEnglish(String message) {
            var frenchMessage = translateToFrench(message);
            frenchSpeaker.speakFrench(frenchMessage);
        }

        private String translateToFrench(String message) {
            // Primitive translation
            return message.replace("Hello", "Bonjour").replace("Thank you", "Merci");
        }
    }

    // Client
    static class EnglishClient {
        private EnglishSpeaker speaker;

        public EnglishClient(EnglishSpeaker speaker) {
            this.speaker = speaker;
        }

        public void express(String message) {
            speaker.speakEnglish(message);
        }
    }
}
