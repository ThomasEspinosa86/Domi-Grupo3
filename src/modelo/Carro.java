package modelo;

public class Carro extends Repartidor {

    private static final double TARIFA_BASE = 5000;
    private static final double VALOR_KM = 1200;
    private static final double VELOCIDAD_KMH = 25;

    public Carro(String codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public double calcularCosto(double km) {
        return TARIFA_BASE + km * VALOR_KM;
    }

    @Override
    public double calcularTiempo(double km) {
        return km / VELOCIDAD_KMH * 60;
    }

    @Override
    public boolean puedeLlevar(double km, boolean esGrande, boolean lluvia) {
        return true; // sin límite de distancia, lleva grandes y no le afecta la lluvia
    }

    @Override
    public String getTipo() {
        return "Carro";
    }
}