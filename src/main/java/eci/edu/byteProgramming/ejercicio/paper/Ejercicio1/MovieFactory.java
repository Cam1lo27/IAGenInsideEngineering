package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

public class MovieFactory {

    private MovieFactory() {}

    public static Movie create(String type, String title,
                               double price, boolean available) {
        return switch (type.toLowerCase()) {
            case "fisica"  -> new PhysicalMovie(title, price, available);
            case "digital" -> new DigitalMovie(title, price, available);
            default -> throw new IllegalArgumentException(
                    "Tipo desconocido: " + type);
        };
    }
}