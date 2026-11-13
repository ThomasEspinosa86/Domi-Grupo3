package modelo;

public final class Validador {

    private Validador() { }

    public static String textoObligatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " no puede estar vacío.");
        }
        return valor.trim();
    }
}