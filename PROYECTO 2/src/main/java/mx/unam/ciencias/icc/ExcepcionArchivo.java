package mx.unam.ciencias.icc;

/**
 * Excepcion que se lanza cuando una bandera que debe recibir un argumento no lo
 * recibe o recibe uno inválido.
 */
public class ExcepcionArchivo extends RuntimeException {

    /**
     * Constructor que no recibe parámetros
     */
    public ExcepcionArchivo() {}

    /**
     * Constructor que recibe un mensaje.
     * @param mensaje Mensaje recibido
     */
    public ExcepcionArchivo(String mensaje) {
        super(mensaje);
    }
}