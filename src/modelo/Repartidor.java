package modelo;

public abstract class Repartidor implements Calificable {

    private final String codigo;
    private String nombre;
    private boolean disponible;

    private int sumaCalificaciones;
    private int cantidadCalificaciones;

    public Repartidor(String codigo, String nombre) {
        this.codigo = Validador.textoObligatorio(codigo, "código");
        setNombre(nombre);
        this.disponible = true;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public boolean isDisponible() { return disponible; }
    public int getCantidadCalificaciones() { return cantidadCalificaciones; }

    public void setNombre(String nombre) {
        this.nombre = Validador.textoObligatorio(nombre, "nombre");
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Cada hija responde con su propia tabla (polimorfismo)
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

    @Override
    public String toString() {
        return codigo + " | " + nombre + " | " + getTipo()
                + " | " + (disponible ? "Disponible" : "No disponible")
                + " | Promedio: " + String.format("%.1f", obtenerPromedio());
    }
}