package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

public class BasicPricing implements PricingStrategy {

    @Override
    public double calculate(double subtotal) { return subtotal; }

    @Override
    public String getMembershipLabel() { return "Basica"; }

    @Override
    public int getDiscountPercent() { return 0; }
}