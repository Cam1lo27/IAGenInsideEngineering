package eci.edu.byteProgramming.ejercicio.paper.Ejercicio1;

public abstract class Movie {

    private final String title;
    private final double price;
    private boolean available;

    public Movie(String title, double price, boolean available) {
        this.title     = title;
        this.price     = price;
        this.available = available;
    }

    public abstract String getType();

    public String  getTitle()    { return title; }
    public double  getPrice()    { return price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("[%s] %s - $%,.0f - %s",
                getType(), title, price,
                available ? "Disponible" : "No disponible");
    }
}