package mx.unam.ciencias.icc;

import java.util.InputMismatchException;

/**
 * Clase para excepciones de opciones inválidas.
 */
public class ExcepcionOpcionInvalida extends InputMismatchException {

    /**
     * Constructor vacío.
     */
    public ExcepcionOpcionInvalida() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionOpcionInvalida(String mensaje) {
        super(mensaje);
    }
}
