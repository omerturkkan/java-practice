package io.github.omerturkkan.javapractice.controlflow.conditionals;

public class ShippingCostCalculator {
    public static void main(String[] args) {
        String[] zones = {"LOCAL", "DOMESTIC", "INTERNATIONAL", "DOMESTIC", "MARS"};
        double[] weightsKg = {0.8, 4.5, 12.0, 25.0, 1.0};
        double[] orderTotals = {120.0, 499.0, 850.0, 1500.0, 50.0};
        boolean[] isPremiumMember = {false, true, false, true, false};

        for (int i = 0; i < zones.length; i++) {
            String zone = zones[i];
            double weight = weightsKg[i];
            double orderTotal = orderTotals[i];
            boolean premium = isPremiumMember[i];

            double baseRate = switch (zone) {
                case "LOCAL" -> 15.0;
                case "DOMESTIC" -> 35.0;
                case "INTERNATIONAL" -> 120.0;
                default -> -1;
            };

            if (baseRate < 0) {
                System.out.printf("Order #%d : unsupported zone '%s'%n%n", i + 1, zone);
                continue;
            }

            double weightFee;
            if (weight <= 1) {
                weightFee = 0;
            } else if (weight <= 5) {
                weightFee = 10;
            } else if (weight <= 20) {
                weightFee = 30;
            } else {
                weightFee = 30 + (weight - 20) * 4;
            }

            double shipping = baseRate + weightFee;
            String note;

            if (premium) {
                if (zone.equals("INTERNATIONAL")) {
                    shipping *= 0.5;
                    note = "premium 50% off";
                } else {
                    shipping = 0;
                    note = "premium free shipping";
                }
            } else if (orderTotal >= 1000) {
                shipping = 0;
                note = "free over 1000";
            } else if (orderTotal >= 500) {
                shipping *= 0.8;
                note = "20% off over 500";
            } else {
                note = "standard";
            }

            System.out.printf("Order #%d%n", i + 1);
            System.out.printf("  Zone     : %s%n", zone);
            System.out.printf("  Weight   : %.1f kg%n", weight);
            System.out.printf("  Total    : %.2f%n", orderTotal);
            System.out.printf("  Premium  : %b%n", premium);
            System.out.printf("  Shipping : %.2f (%s)%n%n", shipping, note);
        }
    }
}
