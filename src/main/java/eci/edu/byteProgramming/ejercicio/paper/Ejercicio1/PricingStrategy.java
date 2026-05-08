package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

public interface PricingStrategy {
    double calculate(double subtotal);
    String getMembershipLabel();
    int    getDiscountPercent();
}