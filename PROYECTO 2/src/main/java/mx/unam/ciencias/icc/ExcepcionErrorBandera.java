package mx.unam.ciencias.icc;

public class ExcepcionErrorBandera extends IllegalArgumentException{
    /**
     * Constructor sin parámetros
     */
    public ExcepcionErrorBandera() {}

    /**
     * Constructor que acepta un mensaje.
     * @param mensaje Mensaje que se recibe
     */
    public ExcepcionErrorBandera(String mensaje) {
        super(mensaje);
    }
}
