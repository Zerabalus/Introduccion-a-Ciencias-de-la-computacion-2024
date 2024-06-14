package mx.unam.ciencias.icc.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.Registro;

/**
 * Clase abstracta para servidores de bases de datos genéricas.
 */
public abstract class ServidorBaseDeDatos<R extends Registro<R, ?>> {

    /* La base de datos. */
    private BaseDeDatos<R, ?> bdd;
    /* La ruta donde cargar/guardar la base de datos. */
    private String ruta;
    /* El servidor de enchufes. */
    private ServerSocket servidor;
    /* El puerto. */
    private int puerto;
    /* Lista con las conexiones. */
    private Lista<Conexion<R>> conexiones;
    /* Bandera de continuación. */
    private boolean continuaEjecucion;
    /* Escuchas del servidor. */
    private Lista<EscuchaServidor> escuchas;

    /**
     * Crea un nuevo servidor usando la ruta recibida para poblar la base de
     * datos.
     * @param puerto el puerto dónde escuchar por conexiones.
     * @param ruta la ruta en el disco del cual cargar/guardar la base de
     *             datos. Puede ser <code>null</code>, en cuyo caso se usará el
     *             nombre por omisión <code>base-de-datos.bd</code>.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatos(int puerto, String ruta)
        throws IOException {
        // Aquí va su código.
        this.puerto = puerto;
        this.ruta = (ruta != null) ? ruta : "estudiantes.bd";
        servidor = new ServerSocket(puerto);
        conexiones = new Lista<Conexion<R>>();
        escuchas = new Lista<EscuchaServidor>();
        bdd = creaBaseDeDatos();
        carga();
    }

    /**
     * Comienza a escuchar por conexiones de clientes.
     */
    public void sirve() {
        // Aquí va su código.

    continuaEjecucion = true;
       anotaMensaje(String.format("Escuchando en el puerto: %d", this.puerto));
        while(continuaEjecucion){
          try{
            Socket s = this.servidor.accept();
            ConexionServidor<R> conexion = new ConexionServidor<R>(this.bdd, s);
            String hostName = s.getInetAddress().getCanonicalHostName();
           anotaMensaje(String.format("Conexión recibida de: %s.", hostName));
           anotaMensaje(String.format("Serial de conexión: %d", conexion.getSerial()));
            conexion.agregaEscucha( (c, e, m) -> manejaEvento(c, e, m));
            new Thread( () -> conexion.manejaMensajes()).start();
            synchronized(this.conexiones){
              this.conexiones.agregaFinal(conexion);
            }
          }catch(IOException e){
            if(continuaEjecucion){
             anotaMensaje("Error al recibir una conexión.");
            }
          }
        }
       anotaMensaje("Ejecución del servidor ha terminado.");
}

    /**
     * Agrega un escucha de servidor.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaServidor escucha) {
        // Aquí va su código.
        escuchas.agregaFinal(escucha);
    }

    /**
     * Limpia todos los escuchas del servidor.
     */
    public void limpiaEscuchas() {
        // Aquí va su código.
        escuchas.limpia();
    }

    /* Carga la base de datos del disco duro. */
    private void carga() {
        // Aquí va su código.
        try{
           anotaMensaje("Cargando base de datos de %s.\n",ruta);
            BufferedReader in =
              new BufferedReader(
                new InputStreamReader(
                  new FileInputStream(ruta)));
            this.bdd.carga(in);
            in.close();
           anotaMensaje("Base de datos cargada exitosamente de %s.\n", ruta);
          }catch(IOException ioe){
           anotaMensaje("Ocurrió un error al tratar de cargar %s.\n", ruta);
           anotaMensaje("La base de datos estará inicialmente vacía.\n");
          }  
    }

    /* Guarda la base de datos en el disco duro. */
    private synchronized void guarda() {
        // Aquí va su código.
        try{
           anotaMensaje("Guardando base de datos en %s \n", archivo);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new )
                this.bdd.guarda(out);
                out.close();
               anotaMensaje("Base de datos guardada");

        }catch(IOException ioe){
            anotaMensaje("Ocurrió un error al guardar la base de datos. \n");
        }
    }

    /* Recibe los mensajes de la conexión. */
    private void mensajeRecibido(Conexion<R> conexion, Mensaje mensaje) {
        // Aquí va su código.
        if (conexion.isActiva()){
            switch (mensaje) {
                case BASE_DE_DATOS : baseDeDatos(conexion);
                    break;
                case REGISTRO_AGREGADO:
                    registroAlterado(conexion, mensaje);
                    break;
                case REGISTRO_ELIMINADO:
                    registroEliminado(conexion);
                    break;
                case REGISTRO_MODIFICADO:
                    registroModificado(conexion);
                    break;
                case DESCONECTAR:
                    desconecta(conexion);
                    break;
                case DETENER_SERVICIO:
                    detenerServicio();
                    break;
                case GUARDA:
                    guarda();
                    break;
                case ECO:
                    eco(conexion);
                    break;
                case INVALIDO:
                    error(conexion, "Desconectando la conexión %d: Mensaje inválido.");
                    break;

            }    
        }
    }

    /* Maneja el mensaje BASE_DE_DATOS. */
    private void baseDeDatos(Conexion<R> conexion) {
        // Aquí va su código.
        anotaMensaje("Base de datos pedida por %d.", puerto);

        try {
            conexion.enviaMensaje(Mensaje.BASE_DE_DATOS);
            conexion.enviaBaseDeDatos();
        } catch (IOException ioe) {
            anotaMensaje("Error al enviar la base de datos a la conexión %d.",
                    puerto);
        }
    }

    /* Maneja los mensajes REGISTRO_AGREGADO y REGISTRO_MODIFICADO. */
    private void registroAlterado(Conexion<R> conexion, Mensaje mensaje) {
        // Aquí va su código.
        if (mensaje == Mensaje.REGISTRO_AGREGADO) {
            try {
                R registro = conexion.recibeRegistro();
                agregaRegistro(registro);

                anotaMensaje("Registro agregado por %d.", puerto);

                for (Conexion<R> con : conexiones) {
                    if (con == conexion)
                        continue;

                    con.enviaMensaje(Mensaje.REGISTRO_AGREGADO);
                    con.enviaRegistro(registro);
                }
            } catch (IOException ioe) {
                anotaMensaje("Error al agregar registro por la conexión %d.",
                        puerto);
            }
            guarda();
        } else if (mensaje == Mensaje.REGISTRO_ELIMINADO) {
            registroEliminado(conexion);
        }

    }

    /* Maneja el mensaje REGISTRO_MODIFICADO. */
    private void registroModificado(Conexion<R> conexion) {
        // Aquí va su código.
        R r1 = null, r2 = null;
        try {
            r1 = conexion.recibeRegistro();
            r2 = conexion.recibeRegistro();
        } catch (IOException ioe) {
            error(conexion, "Error recibiendo registros");
            return;
        }
        for (Conexion<R> otra : conexiones) {
            if (conexion == otra)
                continue;
            try {
                otra.enviaMensaje(Mensaje.REGISTRO_MODIFICADO);
                otra.enviaRegistro(r1);
                otra.enviaRegistro(r2);
            } catch (IOException ioe) {
                error(otra, "Error recibiendo registro");
        }
    }  

    /* Maneja el mensaje DESCONECTAR. */
    private void desconectar(Conexion<R> conexion) {
        // Aquí va su código.
        desconecta(conexion);
    }
    }

    /* Maneja el mensaje DETENER_SERVICIO. */
    private void detenerServicio() {
        // Aquí va su código.
        continuaEjecucion = false;

        for (Conexion<R> con : conexiones)
            desconecta(con);

        try {
            servidor.close();
        } catch (IOException ioe) {
            anotaMensaje("Error al detener el servidor.");
        }
    }

    /* Maneja el mensaje ECO. */
    private void eco(Conexion<R> conexion) {
        // Aquí va su código.
        anotaMensaje("Solicitud de eco de %d.", puerto);

        try {
            conexion.enviaMensaje(Mensaje.ECO);
        } catch (IOException ioe) {
            anotaMensaje("Error al enviar ECO a la conexión %d.",
                    puerto);
        }
    }

    /* Imprime un mensaje a los escuchas y desconecta la conexión. */
    private void error(Conexion<R> conexion, String mensaje) {
        // Aquí va su código.
        anotaMensaje(mensaje, puerto);
        desconecta(conexion);
    }

    /* Desconecta la conexión. */
    private void desconecta(Conexion<R> conexion) {
        // Aquí va su código.
        conexion.desconecta();
        synchronized (conexiones) {
            conexiones.elimina(conexion);
        }
        anotaMensaje("La conexión %d ha sido desconectada.",
                puerto);
    }

    /* Agrega el registro a la base de datos. */
    private synchronized void agregaRegistro(R registro) {
        // Aquí va su código.
        synchronized (bdd) {
            bdd.agregaRegistro(registro);
        }
    }

    /* Elimina el registro de la base de datos. */
    private synchronized void eliminaRegistro(R registro) {
        // Aquí va su código.
        synchronized (bdd) {
            bdd.eliminaRegistro(registro);
        }
    }

    /* Modifica el registro en la base de datos. */
    private synchronized void modificaRegistro(R registro1, R registro2) {
        // Aquí va su código.
        synchronized (bdd) {
            bdd.modificaRegistro(registro1, registro2);
        }
    }

    /* Procesa los mensajes de todos los escuchas. */
    private void anotaMensaje(String formato, Object ... argumentos) {
        // Aquí va su código.
        for (EscuchaServidor escucha : escuchas)
            escucha.procesaMensaje(formato, argumentos);
    }

    /**
     * Crea la base de datos concreta.
     * @return la base de datos concreta.
     */
    public abstract BaseDeDatos<R, ?> creaBaseDeDatos();
}
