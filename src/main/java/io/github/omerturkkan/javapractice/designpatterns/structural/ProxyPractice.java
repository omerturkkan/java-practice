package io.github.omerturkkan.javapractice.designpatterns.structural;

public class ProxyPractice {

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private final String fileName;

        RealImage(String fileName) {
            this.fileName = fileName;
            System.out.println("Loading " + fileName + " from disk...");
        }

        @Override
        public void display() {
            System.out.println("Displaying " + fileName);
        }
    }

    // loads the real image only when it's actually needed
    static class ImageProxy implements Image {
        private final String fileName;
        private RealImage realImage;

        ImageProxy(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image photo = new ImageProxy("holiday.jpg");
        System.out.println("Proxy created, nothing loaded yet");

        photo.display();
        photo.display();
    }
}
