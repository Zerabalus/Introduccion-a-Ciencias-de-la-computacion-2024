package mx.unam.ciencias.icc.red;

import mx.unam.ciencias.icc.Registro;

/**
 * Escucha para eventos de conexiones.
 */
@FunctionalInterface
public interface EscuchaConexion<R extends Registro<R, ?>> {

    /**
     * Notifica de un mensaje que se recibió en la comunicación entre el cliente
     * y el servidor.
     * @param conexion la conexión que recibió el mensaje.
     * @param mensaje el mensaje recibido.
     */
    public void mensajeRecibido(Conexion<R> conexion, Mensaje mensaje);
}
