package mx.unam.ciencias.icc.proyecto2;

/**
 * Excepcion que se lanza cuando una bandera que debe recibir un argumento no lo
 * recibe o recibe uno inválido.
 */
public class ExcepcionArchivoInvalido extends RuntimeException {

    /**
     * Constructor vacío.
     */
    public ExcepcionArchivoInvalido() {}

    /**
     * Constructor que recibe un mensaje.
     * @param mensaje Mensaje recibido
     */
    public ExcepcionArchivoInvalido(String mensaje) {
        super(mensaje);
    }

    /**
     * @param mensaje Mensaje
     * @param c Causa de la excepcion.
     */
    public ExcepcionArchivoInvalido(String mensaje, Throwable e) {
        super(mensaje, e);
    }
}