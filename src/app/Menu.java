package app;

import modelo.Cliente;
import modelo.Pedido;
import servicio.DomiService;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {

    private final DomiService servicio;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(DomiService servicio) {
        this.servicio = servicio;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            try {
                int opcion = leerEntero("Elija una opción: ");
                salir = ejecutar(opcion);
            } catch (NumberFormatException e) {
                // Va ANTES que IllegalArgumentException porque es una hija de ella
                System.out.println("✖ Debe escribir un número.");
            } catch (IllegalArgumentException e) {
                System.out.println("✖ " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Entrada cerrada. Saliendo...");
                salir = true;
            } catch (Exception e) {
                System.out.println("✖ Ocurrió un error inesperado: " + e.getMessage());
            }
        }
        System.out.println("Gracias por usar Domi. ¡Hasta pronto!");
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("══════════════════ DOMI · OPERADOR ══════════════════");
        if (servicio.isModoLluvia()) {
            System.out.println("   🌧  MODO LLUVIA ACTIVO");
        }
        System.out.println("  CLIENTES        1. Registrar     2. Listar");
        System.out.println("                  3. Editar        4. Eliminar");
        System.out.println("  REPARTIDORES    5. Registrar     6. Listar");
        System.out.println("                  7. Cambiar disponibilidad");
        System.out.println("  PEDIDOS         8. Crear pedido (cotiza y asigna)");
        System.out.println("                  9. Entregar     10. Cancelar");
        System.out.println("                 11. Listar pedidos");
        System.out.println("  OPERACIÓN      12. Activar / desactivar lluvia");
        System.out.println("                 13. Cierre del día");
        System.out.println("   0. Salir");
        System.out.println("═════════════════════════════════════════════════════");
    }

    private boolean ejecutar(int opcion) {
        switch (opcion) {
            case 1:  registrarCliente(); break;
            case 2:  mostrarLista(servicio.listarClientes(), "No hay clientes registrados."); break;
            case 3:  editarCliente(); break;
            case 4:  eliminarCliente(); break;
            case 5:  registrarRepartidor(); break;
            case 6:  mostrarLista(servicio.listarRepartidores(), "No hay repartidores registrados."); break;
            case 7:  cambiarDisponibilidad(); break;
            case 8:  crearPedido(); break;
            case 9:  entregarPedido(); break;
            case 10: cancelarPedido(); break;
            case 11: mostrarLista(servicio.listarPedidos(), "No hay pedidos."); break;
            case 12: alternarLluvia(); break;
            case 13: System.out.println(servicio.cierreDelDia()); break;
            case 0:  return true;
            default: System.out.println("✖ Opción no válida. Elija un número entre 0 y 13.");
        }
        return false;
    }

    // ---------- Opciones ----------

    private void registrarCliente() {
        String cedula = leerTexto("Cédula: ");
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        String barrio = leerTexto("Barrio: ");
        String direccion = leerTexto("Dirección: ");
        servicio.registrarCliente(cedula, nombre, telefono, barrio, direccion);
        System.out.println("✔ Cliente registrado.");
    }

    private void editarCliente() {
        String cedula = leerTexto("Cédula del cliente: ");
        servicio.buscarCliente(cedula); // si no existe, falla ya, antes de pedir más datos
        System.out.println("(Deje vacío lo que no quiera cambiar)");
        String telefono = leerTexto("Nuevo teléfono: ");
        String barrio = leerTexto("Nuevo barrio: ");
        String direccion = leerTexto("Nueva dirección: ");
        servicio.editarCliente(cedula, telefono, barrio, direccion);
        System.out.println("✔ Cliente actualizado.");
    }

    private void eliminarCliente() {
        servicio.eliminarCliente(leerTexto("Cédula del cliente: "));
        System.out.println("✔ Cliente eliminado.");
    }

    private void registrarRepartidor() {
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        String tipo = leerTexto("Tipo (Bici / Moto / Carro): ");
        servicio.registrarRepartidor(codigo, nombre, tipo);
        System.out.println("✔ Repartidor registrado.");
    }

    private void cambiarDisponibilidad() {
        servicio.cambiarDisponibilidad(leerTexto("Código del repartidor: "));
        System.out.println("✔ Disponibilidad actualizada.");
    }

    private void crearPedido() {
        String cedula = leerTexto("Cédula del cliente: ");
        Cliente cliente = servicio.buscarCliente(cedula);
        String descripcion = leerTexto("Descripción del pedido: ");
        boolean grande = leerTexto("¿Es un pedido grande? (s/n): ").equalsIgnoreCase("s");

        System.out.println("Cotizando pedido para " + cliente.getNombre() + " · " + cliente.getBarrio());
        for (DomiService.Cotizacion c : servicio.cotizar(cedula, grande)) {
            System.out.println("  " + c.repartidor().getCodigo() + " " + c.repartidor().getNombre()
                    + " (" + c.repartidor().getTipo() + ")  $" + String.format("%,.0f", c.costo())
                    + "  " + String.format("%.1f", c.tiempo()) + " min");
        }

        Pedido p = servicio.crearPedido(cedula, descripcion, grande);
        System.out.println("✔ Pedido #" + p.getNumero() + " asignado a " + p.getRepartidor().getNombre()
                + " (" + p.getRepartidor().getTipo() + ") · " + p.getEstado());
    }

    private void entregarPedido() {
        int numero = leerEntero("Número de pedido: ");
        int calificacion = leerEntero("Calificación del cliente (1 a 5): ");
        servicio.entregarPedido(numero, calificacion);
        System.out.println("✔ Pedido entregado.");
    }

    private void cancelarPedido() {
        servicio.cancelarPedido(leerEntero("Número de pedido: "));
        System.out.println("✔ Pedido cancelado.");
    }

    private void alternarLluvia() {
        servicio.setModoLluvia(!servicio.isModoLluvia());
        System.out.println(servicio.isModoLluvia() ? "🌧 Modo lluvia ACTIVADO." : "☀ Modo lluvia desactivado.");
    }

    // ---------- Utilidades de lectura ----------

    private void mostrarLista(Collection<?> lista, String mensajeVacio) {
        if (lista.isEmpty()) {
            System.out.println(mensajeVacio);
            return;
        }
        for (Object o : lista) {
            System.out.println(o);
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    // Lee como texto y convierte: evita la trampa del buffer de nextInt()
    private int leerEntero(String mensaje) {
        return Integer.parseInt(leerTexto(mensaje).trim());
    }
}