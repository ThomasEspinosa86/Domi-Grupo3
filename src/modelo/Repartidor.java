package modelo;

public abstract class Repartidor implements Calificable {

    private final String codigo;
    private String nombre;
    private boolean disponible;

    private int sumaCalificaciones;
    private int cantidadCalificaciones;

    public Repartidor(String codigo, String nombre) {
        this.codigo = validarTexto(codigo, "El código");
        this.nombre = validarTexto(nombre, "El nombre");
        this.disponible = true;
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

    public abstract class Repartidor implements Calificable {

        private final String codigo;
        private String nombre;
        private boolean disponible;

        private int sumaCalificaciones;
        private int cantidadCalificaciones;

        public Repartidor(String codigo, String nombre) {
            this.codigo = validarTexto(codigo, "El código");
            this.nombre = validarTexto(nombre, "El nombre");
            this.disponible = true;
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

    }}
