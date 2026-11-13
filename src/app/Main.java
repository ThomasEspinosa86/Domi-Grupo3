package app;

import servicio.DomiService;

public class Main {
    public static void main(String[] args) {
        new Menu(new DomiService()).iniciar();
    }
}