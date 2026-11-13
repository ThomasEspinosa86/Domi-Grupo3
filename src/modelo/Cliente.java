package modelo;

public class Cliente {
    private String nombre;
    private String telefono;
    private String cedula;
    private String barrio;

    public Cliente(String barrio, String cedula, String telefono, String nombre) {
        this.barrio = barrio;
        this.cedula = cedula;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}
