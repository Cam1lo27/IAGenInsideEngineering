package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

public class PremiumPricing implements PricingStrategy {

    private static final double DISCOUNT = 0.20;

    @Override
    public double calculate(double subtotal) {
        return subtotal * (1 - DISCOUNT);
    }

    @Override
    public String getMembershipLabel() { return "Premium"; }

    @Override
    public int getDiscountPercent() { return (int)(DISCOUNT * 100); }
}