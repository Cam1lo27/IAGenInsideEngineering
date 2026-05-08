package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

import java.util.List;

public class Receipt {

    private final List<Movie>     rentedMovies;
    private final PricingStrategy pricing;

    public Receipt(List<Movie> rentedMovies, PricingStrategy pricing) {
        this.rentedMovies = rentedMovies;
        this.pricing      = pricing;
    }

    public void print() {
        double subtotal = rentedMovies.stream().mapToDouble(Movie::getPrice).sum();
        double discount = subtotal * pricing.getDiscountPercent() / 100.0;
        double total    = pricing.calculate(subtotal);

        System.out.println("\n--- RECIBO DE ALQUILER ---");
        System.out.println("Cliente: " + pricing.getMembershipLabel());
        System.out.println("Peliculas:");
        for (Movie m : rentedMovies) {
            System.out.printf("  - %s (%s) - $%,.0f%n",
                    m.getTitle(), m.getType(), m.getPrice());
        }
        System.out.printf("Subtotal: $%,.0f%n", subtotal);
        if (pricing.getDiscountPercent() > 0) {
            System.out.printf("Descuento (%d%%): $%,.0f%n",
                    pricing.getDiscountPercent(), discount);
        }
        System.out.printf("Total a pagar: $%,.0f%n", total);
        System.out.println("--------------------------");
        System.out.println("¡Disfrute su pelicula!");
    }
}