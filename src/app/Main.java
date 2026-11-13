package app;

import modelo.Moto;
import modelo.Repartidor;
import servicio.DomiService;

public class Main {

    public static void main(String[] args) {
        DomiService servicio = new DomiService();
        Menu menu = new Menu(servicio);
        menu.iniciar();
        Repartidor moto = new Moto("ARM01","pepe");
        moto.getNombre();
    }
}
