package servicio;

import modelo.*;

import java.util.*;

public class DomiService {

    private static final double RECARGO_LLUVIA = 1.20;   // +20 %

    private final Map<String, Cliente> clientes = new HashMap<>();
    private final Set<String> barrios = new HashSet<>();
    private final Map<String, Double> distancias = new HashMap<>();
    private final List<Repartidor> repartidores = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private boolean modoLluvia = false;

    /** Resultado de preguntarle a un repartidor cuánto cobra y cuánto se demora. */
    public record Cotizacion(Repartidor repartidor, double costo, double tiempo) { }

    public DomiService() {
        cargarDatosDefault();
    }

    private void cargarDatosDefault() {
        String[] nombres = {"Chapinero", "Teusaquillo", "La Candelaria", "Usaquén",
                "Cedritos", "Fontibón", "Suba", "Kennedy", "Bosa"};
        double[] km = {1.5, 3.0, 4.5, 6.0, 8.0, 11.0, 12.0, 14.0, 18.0};
        for (int i = 0; i < nombres.length; i++) {
            barrios.add(nombres[i]);
            distancias.put(nombres[i], km[i]);
        }

        repartidores.add(new Bici("R-01", "Camila Torres"));
        repartidores.add(new Moto("R-02", "Andrés Mejía"));
        repartidores.add(new Carro("R-03", "Jorge Pardo"));

        registrarCliente("1020304050", "Laura Gómez", "3001234567", "Teusaquillo", "Cra 15 # 45-20");
        registrarCliente("1030405060", "Mateo Rincón", "3109876543", "Usaquén", "Calle 120 # 7-30");
    }


    public void registrarCliente(String cedula, String nombre, String telefono,
                                 String barrio, String direccion) {
        Cliente nuevo = new Cliente(cedula, nombre, telefono, barrio, direccion);
        validarBarrio(nuevo.getBarrio());
        if (clientes.containsKey(nuevo.getCedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con esa cédula.");
        }
        clientes.put(nuevo.getCedula(), nuevo);
    }

    public Collection<Cliente> listarClientes() {
        return clientes.values();
    }

    public Cliente buscarCliente(String cedula) {
        Cliente c = clientes.get(cedula == null ? "" : cedula.trim());
        if (c == null) {
            throw new IllegalArgumentException("No existe un cliente con esa cédula.");
        }
        return c;
    }

    public void editarCliente(String cedula, String telefono, String barrio, String direccion) {
        Cliente c = buscarCliente(cedula);
        if (barrio != null && !barrio.trim().isEmpty()) {
            validarBarrio(barrio.trim());
        }
        if (telefono != null && !telefono.trim().isEmpty()) c.setTelefono(telefono);
        if (barrio != null && !barrio.trim().isEmpty()) c.setBarrio(barrio);
        if (direccion != null && !direccion.trim().isEmpty()) c.setDireccion(direccion);
    }

    public void eliminarCliente(String cedula) {
        Cliente c = buscarCliente(cedula);
        for (Pedido p : pedidos) {
            if (p.getCliente() == c && p.getEstado() == Estado.EN_CAMINO) {
                throw new IllegalArgumentException("No se puede eliminar: el cliente tiene un pedido EN_CAMINO.");
            }
        }
        clientes.remove(c.getCedula());
    }

    private void validarBarrio(String barrio) {
        if (!barrios.contains(barrio)) {
            throw new IllegalArgumentException("El barrio \"" + barrio + "\" no tiene cobertura.");
        }
    }


    public void registrarRepartidor(String codigo, String nombre, String tipo) {

        if(codigo == null){
            codigo = "";
        }
        else{ codigo.trim();
        }
        String cod =codigo;
        for (Repartidor r : repartidores) {
            if (r.getCodigo().equalsIgnoreCase(cod)) {
                throw new IllegalArgumentException("Ya existe un repartidor con ese código.");
            }
        }
        Repartidor nuevo;
        switch (tipo == null ? "" : tipo.trim().toUpperCase()) {
            case "BICI":  nuevo = new Bici(codigo, nombre);  break;
            case "MOTO":  nuevo = new Moto(codigo, nombre);  break;
            case "CARRO": nuevo = new Carro(codigo, nombre); break;

            default: throw new IllegalArgumentException("Tipo de repartidor no válido.");
        }
        repartidores.add(nuevo);
    }

    public List<Repartidor> listarRepartidores() {
        return repartidores;
    }

    public void cambiarDisponibilidad(String codigo) {
        Repartidor r = buscarRepartidor(codigo);
        r.setDisponible(!r.isDisponible());
    }

    private Repartidor buscarRepartidor(String codigo) {
        for (Repartidor r : repartidores) {
            if (r.getCodigo().equalsIgnoreCase(codigo == null ? "" : codigo.trim())) {
                return r;
            }
        }
        throw new IllegalArgumentException("No existe un repartidor con ese código.");
    }


    private double kmDe(Cliente cliente) {
        return distancias.get(cliente.getBarrio());
    }

    public List<Cotizacion> cotizar(String cedula, boolean grande) {
        Cliente cliente = buscarCliente(cedula);
        double km = kmDe(cliente);
        List<Cotizacion> resultado = new ArrayList<>();
        for (Repartidor repeat : repartidores) {
            if (repeat.isDisponible() && repeat.puedeLlevar(km, grande, modoLluvia)) {
                double costo = repeat.calcularCosto(km);
                if (modoLluvia) {
                    costo *= RECARGO_LLUVIA;
                }
                resultado.add(new Cotizacion(repeat, costo, repeat.calcularTiempo(km)));
            }
        }
        return resultado;
    }

    public Pedido crearPedido(String cedula, String descripcion, boolean grande) {
        Cliente cliente = buscarCliente(cedula);
        List<Cotizacion> opciones = cotizar(cedula, grande);
        if (opciones.isEmpty()) {
            throw new IllegalArgumentException("Ningún repartidor disponible puede llevar este pedido.");
        }

        Cotizacion mejor = opciones.get(0);
        for (Cotizacion c : opciones) {
            if (c.costo() < mejor.costo()
                    || (c.costo() == mejor.costo() && c.tiempo() < mejor.tiempo())) {
                mejor = c;
            }
        }

        Pedido pedido = new Pedido(cliente, descripcion, grande, kmDe(cliente),
                mejor.repartidor(), mejor.costo(), mejor.tiempo());
        mejor.repartidor().setDisponible(false);
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    private Pedido buscarPedido(int numero) {
        for (Pedido p : pedidos) {
            if (p.getNumero() == numero) {
                return p;
            }
        }
        throw new IllegalArgumentException("No existe el pedido #" + numero + ".");
    }

    public void entregarPedido(int numero, int calificacion) {
        Pedido p = buscarPedido(numero);
        if (p.getEstado() != Estado.EN_CAMINO) {
            throw new IllegalArgumentException("No se puede entregar el pedido #" + numero
                    + ": ya está " + p.getEstado() + ".");
        }
        p.getRepartidor().recibirCalificacion(calificacion);
        p.entregar();
        p.getRepartidor().setDisponible(true);
    }

    public void cancelarPedido(int numero) {
        Pedido p = buscarPedido(numero);
        p.cancelar();
        p.getRepartidor().setDisponible(true);
    }


    public boolean isModoLluvia() { return modoLluvia; }

    public void setModoLluvia(boolean modoLluvia) { this.modoLluvia = modoLluvia; }

    public String cierreDelDia() {
        Map<Estado, Integer> porEstado = new EnumMap<>(Estado.class);
        for (Estado e : Estado.values()) {
            porEstado.put(e, 0);
        }
        Map<String, Double> ingresosPorTipo = new TreeMap<>();
        for (Repartidor r : repartidores) {
            ingresosPorTipo.putIfAbsent(r.getTipo(), 0.0);
        }

        double ingresos = 0;
        for (Pedido p : pedidos) {
            porEstado.merge(p.getEstado(), 1, Integer::sum);
            if (p.getEstado() == Estado.ENTREGADO) {
                ingresos += p.getCosto();
                ingresosPorTipo.merge(p.getRepartidor().getTipo(), p.getCosto(), Double::sum);
            }
        }

        Repartidor estrella = null;
        for (Repartidor r : repartidores) {
            if (r.getCantidadCalificaciones() > 0
                    && (estrella == null || r.obtenerPromedio() > estrella.obtenerPromedio())) {
                estrella = r;
            }
        }

        StringBuilder construirElString = new StringBuilder();
        construirElString.append("Total de pedidos: ").append(pedidos.size()).append("\n");
        for (Map.Entry<Estado, Integer> e : porEstado.entrySet()) {
            construirElString.append("  ").append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }
        construirElString.append("Ingresos del día: $").append(String.format("%,.0f", ingresos)).append("\n");
        for (Map.Entry<String, Double> e : ingresosPorTipo.entrySet()) {
            construirElString.append("  ").append(e.getKey()).append(": $")
                    .append(String.format("%,.0f", e.getValue())).append("\n");
        }
        construirElString.append("Repartidor estrella: ");
        if (estrella == null) {
            construirElString.append("aún no hay calificaciones");
        } else {
            construirElString.append(estrella.getNombre()).append(" (")
                    .append(String.format("%.1f", estrella.obtenerPromedio())).append(")");
        }
        return construirElString.toString();
    }
}