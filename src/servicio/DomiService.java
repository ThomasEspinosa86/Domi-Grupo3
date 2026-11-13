package servicio;

import modelo.Repartidor;
import modelo.Cliente;
import modelo.Pedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DomiService {

    private static final double RECARGO_LLUVIA = 1.20;   // +20 %

    private final Map<String, Cliente> clientes = new HashMap<>();
    private final Set<String> barrios = new HashSet<>();
    private final Map<String, Double> distancias = new HashMap<>();
    private final List<Repartidor> repartidores = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private boolean modoLluvia = false;

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public Set<String> getBarrios() {
        return barrios;
    }

    public Map<String, Double> getDistancias() {
        return distancias;
    }

    public List<Repartidor> getRepartidores() {
        return repartidores;
    }
    public List<Repartidor> setRepartidores() {
        return repartidores;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public boolean isModoLluvia() {
        return modoLluvia;
    }

    public void setModoLluvia(boolean modoLluvia) {
        this.modoLluvia = modoLluvia;
    }
}