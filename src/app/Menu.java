package app;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Repartidor;
import servicio.DomiService;

import java.util.List;
import java.util.Locale;
import java.util.Map;
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
                System.out.println("Elija una opción: ");
                int opcion=scanner.nextInt();
                salir = ejecutar(opcion);
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
            case 1:
                servicio.registrarCliente();
                break;
            /*case 2:
                servicio.listarClientes();
                break;
            case 3:
                servicio.editarClientes();
                break;
            case 4:
                servicio.eliminarClientes();
                break;
            case 5:
                servicio.registrarRepartidores();
                break;
            case 6:
                servicio.listarRepartidores();
                break;
            case 7:
                servicio.cambiarDisponibilidad();
                break;
            case 8:
                servicio.crearPedido();
                break;
            case 9:
                entregarPedido();
                break;
            case 10:
                cancelarPedido();
                break;
            case 11:
                servicio.listarPedidos();
                break;
            case 12:
                alternarLluvia();
                break;
            case 13:
                cierreDelDia();
                break;
            */
            case 0:
                return true;
            default:
                System.out.println("✖ Opción no válida. Elija un número entre 0 y 13.");
        }
        return false;
    }
}
