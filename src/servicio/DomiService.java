package servicio;

import modelo.Bici;
import modelo.Carro;
import modelo.Cliente;
import modelo.Dron;
import modelo.Moto;
import modelo.Pedido;
import modelo.Repartidor;

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


}