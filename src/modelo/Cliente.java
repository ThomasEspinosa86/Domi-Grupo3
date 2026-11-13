package modelo;

public class Cliente {

    private final String cedula;   // es la llave del HashMap: no se edita
    private String nombre;
    private String telefono;
    private String barrio;
    private String direccion;

    public Cliente(String cedula, String nombre, String telefono, String barrio, String direccion) {
        this.cedula = Validador.textoObligatorio(cedula, "cédula");
        setNombre(nombre);
        setTelefono(telefono);
        setBarrio(barrio);
        setDireccion(direccion);
    }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getBarrio() { return barrio; }
    public String getDireccion() { return direccion; }

    public void setNombre(String nombre) {
        this.nombre = Validador.textoObligatorio(nombre, "nombre");
    }

    public void setTelefono(String telefono) {
        String t = Validador.textoObligatorio(telefono, "teléfono");
        if (t.length() < 7 || t.length() > 10) {
            throw new IllegalArgumentException("El teléfono debe tener entre 7 y 10 dígitos.");
        }
        for (char c : t.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("El teléfono solo puede contener números.");
            }
        }
        this.telefono = t;
    }


    public void setBarrio(String barrio) {
        this.barrio = Validador.textoObligatorio(barrio, "barrio");
    }

    public void setDireccion(String direccion) {
        this.direccion = Validador.textoObligatorio(direccion, "dirección");
    }

    @Override
    public String toString() {
        return cedula + " | " + nombre + " | Tel: " + telefono + " | " + barrio + " | " + direccion;
    }
}