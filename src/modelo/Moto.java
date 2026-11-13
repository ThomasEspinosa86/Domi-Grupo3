package modelo;

public class Moto extends Repartidor {

    private static final double TARIFA_BASE = 3000;
    private static final double VALOR_KM = 1000;
    private static final double VELOCIDAD_KMH = 30;
    private static final double DISTANCIA_MAXIMA = 15;

    public Moto(String codigo, String nombre) {
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
        return !esGrande && km <= DISTANCIA_MAXIMA;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}