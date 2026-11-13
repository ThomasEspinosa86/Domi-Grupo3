package modelo;

public class Moto extends Repartidor {
    public Moto(String codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public double calcularCosto(double km) {
        return 0;
    }

    @Override
    public double calcularTiempo(double km) {
        return 0;
    }

    @Override
    public boolean puedeLlevar(double km, boolean esGrande, boolean lluvia) {
        return false;
    }

    @Override
    public String getTipo() {
        return "";
    }

    @Override
    public String textoValidar(String valor) {
        if (valor.trim().length() <= 0) {
            return valor;
        } else {
            return valor;
        }
    };
}
