package io.github.omerturkkan.javapractice.designpatterns.structural;

public class AdapterPractice {

    // The interface our application expects: temperature in Celsius
    interface CelsiusSensor {
        double readCelsius();
    }

    // A third-party class we cannot change: it only reports Fahrenheit
    static class LegacyFahrenheitSensor {
        double getFahrenheit() {
            return 98.6;
        }
    }

    // The adapter: implements the expected interface and wraps the legacy class
    static class FahrenheitToCelsiusAdapter implements CelsiusSensor {
        private final LegacyFahrenheitSensor legacySensor;

        FahrenheitToCelsiusAdapter(LegacyFahrenheitSensor legacySensor) {
            this.legacySensor = legacySensor;
        }

        @Override
        public double readCelsius() {
            return (legacySensor.getFahrenheit() - 32) * 5 / 9;
        }
    }

    // Application code: works only with CelsiusSensor and knows nothing about Fahrenheit
    static void printTemperature(CelsiusSensor sensor) {
        System.out.printf("Temperature: %.1f C%n", sensor.readCelsius());
    }

    public static void main(String[] args) {
        LegacyFahrenheitSensor legacy = new LegacyFahrenheitSensor();
        CelsiusSensor adapted = new FahrenheitToCelsiusAdapter(legacy);

        System.out.println("Legacy raw value: " + legacy.getFahrenheit() + " F");
        printTemperature(adapted);
    }
}
