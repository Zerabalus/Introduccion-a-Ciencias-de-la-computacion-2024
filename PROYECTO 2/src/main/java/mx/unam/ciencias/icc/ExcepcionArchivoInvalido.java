package mx.unam.ciencias.icc;

/**
 * Excepcion que se lanza cuando una bandera que requiere un argumento no lo recibe 
 * o recibe uno no válido.
 */
public class ExcepcionArchivoInvalido extends RuntimeException {

    /**
     * Constructor vacío.
     */
    public ExcepcionArchivoInvalido() {}

    /**
     * Constructor que acepta un mensaje.
     * @param mensaje Mensaje que se recibe
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