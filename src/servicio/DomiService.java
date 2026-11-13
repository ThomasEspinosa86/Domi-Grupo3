package servicio;

import app.Menu;
import modelo.Moto;
import modelo.Repartidor;
import modelo.Cliente;
import modelo.Pedido;

import java.util.*;

public class DomiService {

    private static final double RECARGO_LLUVIA = 1.20;   // +20 %

    private final Map<String, Cliente> clientes = new HashMap<>();
    private final Set<String> barrios = new HashSet<>();
    private final Map<String, Double> distancias = new HashMap<>();
    private final List<Repartidor> repartidores = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private boolean modoLluvia = false;

    Scanner scanner = new Scanner(System.in);
    private void CargarDatosDefault(){
        String[] nombresBarrios = {"Chapinero",
                "Teusaquillo", "La Candelaria", "Usaquén",
                "Cedritos", "Fontibón", "Suba", "Kennedy", "Bosa"
        };
        double[] valoresDistancia = {1.5, 3.0, 4.5, 6.0, 8.0, 11.0, 12.0, 14.0, 18.0};
        for (int i = 0; i < nombresBarrios.length; i++){
            barrios.add(nombresBarrios[i]);
            distancias.put(nombresBarrios[i], valoresDistancia[i]);
        }
    }
    public Map<String, Cliente> listarClientes() {
        for (String clave : clientes.keySet()) {
            System.out.println(clave);
        }
        return null;
    }
    public Map<String, Cliente> registrarCliente(){
        System.out.println("ingrese los datos del nuevo cliente en este orden \n Barrio: \n Cedula: \n Telefono \n Nombre:");
        Cliente pregunta= new Cliente(scanner.nextLine(),scanner.nextLine(),scanner.nextLine(),scanner.nextLine());
        clientes.put("cliente",pregunta);
        return clientes;
    }

    public static void editarClientes() {


    }

    public Map<String, Cliente> eliminarClientes() {
        return null;
    }

    public List<Repartidor> listarRepartidores() {
        for (Repartidor lenguaje : repartidores) {
            System.out.println(lenguaje);
        }
        return null;
    }
    public List<Repartidor> registrarRepartidores() {
        return repartidores;
    }

    public List<Pedido> crearPedido() {

        return pedidos;
    }
    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    public List<Pedido> cacelarPedido() {
        return pedidos;
    }


    public Set<String> getBarrios() {
        return barrios;
    }

    public Map<String, Double> getDistancias() {
        return distancias;
    }


    public static void cambiarDisponibilidad(){

    }

    public boolean isModoLluvia() {
        return modoLluvia;
    }

    public void setModoLluvia(boolean modoLluvia) {
        this.modoLluvia = modoLluvia;
    }


}