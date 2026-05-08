package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class RentalService {

    private final List<Movie> catalog;

    public RentalService(List<Movie> catalog) {
        this.catalog = catalog;
    }

    public void showCatalog() {
        System.out.println("\n=== Peliculas Disponibles ===");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, catalog.get(i));
        }
    }

    public void processRental(String indices, PricingStrategy pricing) {
        List<Movie> selected = new ArrayList<>();

        for (String raw : indices.split(",")) {
            int idx = Integer.parseInt(raw.trim()) - 1;

            if (idx < 0 || idx >= catalog.size()) {
                System.out.println("  ✖ Numero " + (idx+1) + " fuera de rango. Se omite.");
                continue;
            }

            Movie movie = catalog.get(idx);

            if (!movie.isAvailable()) {
                System.out.println("  ✖ \"" + movie.getTitle() + "\" no esta disponible. Se omite.");
                continue;
            }

            selected.add(movie);
            movie.setAvailable(false);
        }

        if (selected.isEmpty()) {
            System.out.println("\nNo se selecciono ninguna pelicula valida.");
            return;
        }

        new Receipt(selected, pricing).print();
    }
}