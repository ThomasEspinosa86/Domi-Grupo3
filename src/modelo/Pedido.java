package modelo;

public class Pedido {

    private static int contador = 1;

    private final int numero;
    private final Cliente cliente;
    private final String descripcion;
    private final boolean grande;
    private final double km;
    private final Repartidor repartidor;
    private final double costo;
    private final double tiempo;
    private Estado estado;

    public Pedido(Cliente cliente, String descripcion, boolean grande, double km,
                  Repartidor repartidor, double costo, double tiempo) {
        if (cliente == null || repartidor == null) {
            throw new IllegalArgumentException("El pedido necesita cliente y repartidor.");
        }
        this.cliente = cliente;
        this.descripcion = Validador.textoObligatorio(descripcion, "descripción");
        this.grande = grande;
        this.km = km;
        this.repartidor = repartidor;
        this.costo = costo;
        this.tiempo = tiempo;
        this.estado = Estado.EN_CAMINO;
        this.numero = contador++;   // solo avanza si todo lo anterior salió bien
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public String getDescripcion() { return descripcion; }
    public boolean isGrande() { return grande; }
    public double getKm() { return km; }
    public Repartidor getRepartidor() { return repartidor; }
    public double getCosto() { return costo; }
    public double getTiempo() { return tiempo; }
    public Estado getEstado() { return estado; }

    // Solo se puede salir de EN_CAMINO; los otros dos estados son finales.
    public void entregar() {
        validarEnCamino("entregar");
        estado = Estado.ENTREGADO;
    }

    public void cancelar() {
        validarEnCamino("cancelar");
        estado = Estado.CANCELADO;
    }

    private void validarEnCamino(String accion) {
        if (estado != Estado.EN_CAMINO) {
            throw new IllegalArgumentException("No se puede " + accion + " el pedido #" + numero
                    + ": ya está " + estado + ".");
        }
    }

    @Override
    public String toString() {
        return "#" + numero + " | " + cliente.getNombre()
                + " | " + repartidor.getNombre() + " (" + repartidor.getTipo() + ")"
                + " | $" + String.format("%,.0f", costo)
                + " | " + String.format("%.1f", tiempo) + " min"
                + " | " + estado;
    }
}