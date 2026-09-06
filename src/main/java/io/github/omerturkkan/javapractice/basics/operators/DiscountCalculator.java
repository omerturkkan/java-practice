package io.github.omerturkkan.javapractice.basics.operators;

public class DiscountCalculator {
    public static void main(String[] args) {
        double price = 250.0;
        boolean isMember = true;
        int purchaseCount = 5;
        boolean hasCoupon = false;

        double memberDiscount = isMember ? 10 : 0;
        double loyaltyDiscount = isMember && purchaseCount >= 5 ? 5 : 0;
        double couponDiscount = hasCoupon || purchaseCount >= 10 ? 3 : 0;

        double totalDiscountRate = memberDiscount + loyaltyDiscount + couponDiscount;
        double discountAmount = price * totalDiscountRate / 100;
        double finalPrice = price - discountAmount;

        boolean freeShipping = finalPrice > 200 && isMember;
        double shippingCost = freeShipping ? 0 : 14.99;
        double orderTotal = finalPrice + shippingCost;

        System.out.printf("Price          : %.2f%n", price);
        System.out.printf("Member         : %s (-%s%%)%n", isMember, memberDiscount);
        System.out.printf("Loyalty        : %s purchases (-%s%%)%n", purchaseCount, loyaltyDiscount);
        System.out.printf("Coupon         : %s (-%s%%)%n", hasCoupon, couponDiscount);
        System.out.printf("Total Discount : %.2f (%.0f%%)%n", discountAmount, totalDiscountRate);
        System.out.printf("Final Price    : %.2f%n", finalPrice);
        System.out.printf("Shipping       : %.2f%n", shippingCost);
        System.out.printf("Order Total    : %.2f%n", orderTotal);
    }
}