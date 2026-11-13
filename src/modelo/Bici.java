package modelo;

public class Bici extends Repartidor {

    private static final double TARIFA_BASE = 2000;
    private static final double VALOR_KM = 800;
    private static final double VELOCIDAD_KMH = 15;
    private static final double DISTANCIA_MAXIMA = 5;

    public Bici(String codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public double calcularCosto(double km) {
        return TARIFA_BASE + km * VALOR_KM;
    }

    @Override
    public double calcularTiempo(double km) {
        return km * 60 / VELOCIDAD_KMH;
    }

    @Override
    public boolean puedeLlevar(double km, boolean esgrande, boolean lluvia) {
        return !esgrande && !lluvia && km <= DISTANCIA_MAXIMA;
    }

    @Override
    public String getTipo() {
        return "Bici";
    }
}
