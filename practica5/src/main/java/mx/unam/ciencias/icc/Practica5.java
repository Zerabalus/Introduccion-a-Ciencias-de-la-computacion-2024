package mx.unam.ciencias.icc;

/**
 * Práctica 5: Excepciones, entrada/salida y enumeraciones.
 */
public class Practica5 {

    /* Código de terminación por error de uso. */
    private static final int ERROR_USO = 1;

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar practica5.jar [-g|-c] <archivo>");
        System.exit(ERROR_USO);
    }

    public static void main(String[] args) {
        if (args.length != 2)
            uso();

        try {
            Aplicacion aplicacion = new Aplicacion(args[0], args[1]);
            aplicacion.ejecuta();
        } catch (IllegalArgumentException iae) {
            uso();
        }
    }
}
