package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

import java.util.List;
import java.util.Scanner;

public class VideoClub {

    public static void main(String[] args) {

        List<Movie> catalog = List.of(
                MovieFactory.create("fisica",  "Interstellar", 8_000, true),
                MovieFactory.create("fisica",  "El Padrino",   7_000, false),
                MovieFactory.create("digital", "Inception",    5_000, true),
                MovieFactory.create("digital", "Matrix",       6_000, true)
        );

        RentalService service = new RentalService(catalog);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bienvenido al Videoclub de Don Mario ===");
        System.out.println("Seleccione su membresia:");
        System.out.println("  1. Basica");
        System.out.println("  2. Premium (20% descuento)");
        System.out.print("Opcion: ");

        String opt = scanner.nextLine().trim();
        PricingStrategy pricing = opt.equals("2") ? new PremiumPricing() : new BasicPricing();

        service.showCatalog();

        System.out.print("\nSeleccione peliculas (numeros separados por coma): ");
        String selection = scanner.nextLine().trim();

        service.processRental(selection, pricing);
        scanner.close();
    }
}