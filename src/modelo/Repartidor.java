package modelo;

import servicio.DomiService;

public abstract class Repartidor implements Calificable {

    private final String codigo;
    private String nombre;
    private boolean disponible;

    private int sumaCalificaciones;
    private int cantidadCalificaciones;

    public Repartidor(String codigo, String nombre) {
        this.codigo=codigo;
        this.nombre = nombre;
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo){
        textoValidar(codigo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getSumaCalificaciones() {
        return sumaCalificaciones;
    }

    public void setSumaCalificaciones(int sumaCalificaciones) {
        this.sumaCalificaciones = sumaCalificaciones;
    }

    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    public void setCantidadCalificaciones(int cantidadCalificaciones) {
        this.cantidadCalificaciones = cantidadCalificaciones;
    }

    public abstract double calcularCosto(double km);
    public abstract double calcularTiempo(double km);
    public abstract boolean puedeLlevar(double km, boolean esGrande, boolean lluvia);
    public abstract String getTipo();

    @Override
    public void recibirCalificacion(int puntos) {
        if (puntos < 1 || puntos > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
        }
        sumaCalificaciones += puntos;
        cantidadCalificaciones++;
    }

    @Override
    public double obtenerPromedio() {
        if (cantidadCalificaciones == 0) {
            return 0;
        }
        return (double) sumaCalificaciones / cantidadCalificaciones;
    }

    public abstract String textoValidar(String valor);
}
