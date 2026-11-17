package io.github.omerturkkan.javapractice.designpatterns.structural;

public class DecoratorPractice {

    interface Coffee {
        String description();

        double price();
    }

    static class SimpleCoffee implements Coffee {
        @Override
        public String description() {
            return "Coffee";
        }

        @Override
        public double price() {
            return 50;
        }
    }

    // wraps another coffee and adds something on top of it
    abstract static class CoffeeDecorator implements Coffee {
        protected final Coffee coffee;

        CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
    }

    static class Milk extends CoffeeDecorator {
        Milk(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String description() {
            return coffee.description() + ", milk";
        }

        @Override
        public double price() {
            return coffee.price() + 10;
        }
    }

    static class Caramel extends CoffeeDecorator {
        Caramel(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String description() {
            return coffee.description() + ", caramel";
        }

        @Override
        public double price() {
            return coffee.price() + 15;
        }
    }

    static void printOrder(Coffee coffee) {
        System.out.printf("%-30s %.2f TL%n", coffee.description(), coffee.price());
    }

    public static void main(String[] args) {
        printOrder(new SimpleCoffee());
        printOrder(new Milk(new SimpleCoffee()));
        printOrder(new Caramel(new Milk(new SimpleCoffee())));
        printOrder(new Milk(new Milk(new SimpleCoffee())));
    }
}
