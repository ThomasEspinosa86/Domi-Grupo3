package modelo;

public class Pedido extends Repartidor {
    private String numero;
    private String cliente;
    private String descripcion;
    private boolean tamanio;
    private int kilometros;
    private String repartidor;
    private double costo;
    private int tiempo;
    private String estado;
    public enum estados{
        Encamino,
        Entregado,
        Cancelado
    }


    public Pedido(String codigo, String nombre, String numero, String cliente, String descripcion, boolean tamanio, int kilometros, String repartidor, double costo, int tiempo,String estado) {
        super(codigo, nombre);
        this.numero = numero;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.tamanio = tamanio;
        this.kilometros = kilometros;
        this.repartidor = repartidor;
        this.costo = costo;
        this.tiempo = tiempo;
        this.estado=estado;
    }

    public Pedido(String codigo, String nombre) {
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
        return "";
    }
}
