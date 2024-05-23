package mx.unam.ciencias.icc;

/**
 * Clase para excepciones de líneas inválidas.
 */
public class ExcepcionLineaInvalida extends IllegalArgumentException {

    /**
     * Constructor vacío.
     */
    public ExcepcionLineaInvalida() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionLineaInvalida(String mensaje) {
        super(mensaje);
    }
}
