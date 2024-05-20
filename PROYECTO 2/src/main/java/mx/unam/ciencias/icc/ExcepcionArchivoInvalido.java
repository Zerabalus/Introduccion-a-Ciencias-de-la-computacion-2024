package mx.unam.ciencias.icc;

import java.io.IOException;

/**
 * Excepcion que se lanza cuando una bandera que requiere un argumento no lo recibe 
 * o recibe uno no válido.
 */
public class ExcepcionArchivoInvalido extends IOException {

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

}