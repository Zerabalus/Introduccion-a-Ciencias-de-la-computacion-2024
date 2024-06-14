package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase abstracta para bases de datos genéricas. Provee métodos para agregar y
 * eliminar registros, y para guardarse y cargarse de una entrada y salida
 * dados. Además, puede hacer búsquedas con valores arbitrarios sobre los campos
 * de los registros.
 *
 * Las clases que extiendan a BaseDeDatos deben implementar el método {@link
 * #creaRegistro}, que crea un registro genérico en blanco.
 *
 * Las modificaciones a la base de datos son notificadas a los escuchas {@link
 * EscuchaBaseDeDatos}.
 *
 * @param <R> El tipo de los registros, que deben implementar la interfaz {@link
 * Registro}.
 * @param <C> El tipo de los campos de los registros, que debe ser una
 * enumeración {@link Enum}.
 */
public abstract class BaseDeDatos<R extends Registro<R, C>, C extends Enum> {

    /* Lista de registros en la base de datos. */
    private Lista<R> registros;
    /* Lista de escuchas de la base de datos. */
    private Lista<EscuchaBaseDeDatos<R>> escuchas;

    /**
     * Constructor único.
     */
    public BaseDeDatos() {
        // Aquí va su código.
        registros = new Lista<R>();
        escuchas = new Lista<>();
    }

    /**
     * Regresa el número de registros en la base de datos.
     * @return el número de registros en la base de datos.
     */
    public int getNumRegistros() {
        // Aquí va su código.
        return registros.getLongitud();
    }

    /**
     * Regresa una lista con los registros en la base de datos. Modificar esta
     * lista no cambia a la información en la base de datos.
     * @return una lista con los registros en la base de datos.
     */
    public Lista<R> getRegistros() {
        // Aquí va su código.
        return registros.copia();
    }

    /**
     * Agrega el registro recibido a la base de datos. Los escuchas son
     * notificados con {@link EscuchaBaseDeDatos#baseDeDatosModificada} con el
     * evento {@link EventoBaseDeDatos#REGISTRO_AGREGADO}.
     * @param registro el registro que hay que agregar a la base de datos.
     */
    public void agregaRegistro(R registro) {
        // Aquí va su código.
        registros.agregaFinal(registro);
        for (EscuchaBaseDeDatos<R> escucha : escuchas) {
            escucha.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_AGREGADO, registro, null);
        }
    }

    /**
     * Elimina el registro recibido de la base de datos. Los escuchas son
     * notificados con {@link EscuchaBaseDeDatos#baseDeDatosModificada} con el
     * evento {@link EventoBaseDeDatos#REGISTRO_ELIMINADO}.
     * @param registro el registro que hay que eliminar de la base de datos.
     */
    public void eliminaRegistro(R registro) {
        // Aquí va su código.
        registros.elimina(registro);
        for (EscuchaBaseDeDatos<R> escucha : escuchas) {
            escucha.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_ELIMINADO, registro, null);
        }
    }

    /**
     * Modifica el primer registro en la base de datos para que sea idéntico al
     * segundo. Antes de modificar el registro, los escuchas son notificados con
     * {@link EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#REGISTRO_MODIFICADO} y las versiones original y
     * modificada del registro. Si el primer registro no está en la base de
     * datos, ésta no es modificada y no se notifica de nada a los escuchas.
     * @param registro1 un registro igual al que hay que modificar en la base de
     *                  datos.
     * @param registro2 el registro con los nuevos valores.
     * @throws IllegalArgumentException si registro1 o registro2 son
     *         <code>null</code>.
     */
    public void modificaRegistro(R registro1, R registro2) {
        // Aquí va su código.
        if (registro1 == null || registro2 == null)
            throw new IllegalArgumentException();
        if (registros.contiene(registro1)) {
            for (R r : registros) {
                if (r.equals(registro1)) {
                    for (EscuchaBaseDeDatos<R> escucha : escuchas) {
                        escucha.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_MODIFICADO, r, registro2);
                    }
                    r.actualiza(registro2);
                    break;
                }
            }
        }
    }

    /**
     * Limpia la base de datos. Los escuchas son notificados con {@link
     * EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#BASE_LIMPIADA}
     */
    public void limpia() {
        // Aquí va su código.
        registros.limpia();
        for (EscuchaBaseDeDatos<R> escucha : escuchas) {
            escucha.baseDeDatosModificada(EventoBaseDeDatos.BASE_LIMPIADA, null, null);
        }
    }

    /**
     * Guarda todos los registros en la base de datos en la salida recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
        // Aquí va su código.
        for (R r : registros) {
            try {
                out.write(r.seria());
            } catch (IOException ioe) {
                throw new IOException();
            }
        }
    }

    /**
     * Carga los registros de la entrada recibida en la base de datos. Si antes
     * de llamar el método había registros en la base de datos, estos son
     * eliminados. Los escuchas son notificados con {@link
     * EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#BASE_LIMPIADA}, y por cada registro cargado con {@link
     * EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#REGISTRO_AGREGADO}.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
        // Aquí va su código.
        registros.limpia();

        for (EscuchaBaseDeDatos<R> escucha : escuchas) {
            escucha.baseDeDatosModificada(EventoBaseDeDatos.BASE_LIMPIADA, null, null);
        }
        String linea = in.readLine();
        try {
            try {
                while (linea != null) {
                    String[] campos = linea.split("\t");
                    R r = creaRegistro();

                    if (campos.length == 4) {
                        r.deseria(linea);
                    } else {
                        break;
                    }
                    registros.agregaFinal(r);

                    linea = in.readLine();
                }
                for (R r : registros) {
                    for (EscuchaBaseDeDatos<R> escucha : escuchas) {
                        escucha.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_AGREGADO, r, null);
                    }
                }
            } catch (IOException e) {
                throw new IOException("Error de lectura");
            }
        } catch (IOException ioe) {
            throw new IOException(ioe.getMessage());
        }
    }

    /**
     * Busca registros por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param valor el valor a buscar.
     * @return una lista con los registros tales que casan el campo especificado
     *         con el valor dado.
     * @throws IllegalArgumentException si el campo no es de la enumeración
     *         correcta.
     */
    public Lista<R> buscaRegistros(C campo, Object valor) {
        // Aquí va su código.
        Lista<R> lista = new Lista<R>();
        for (R r : registros) {
            if (r.casa(campo, valor)) {
                lista.agregaFinal(r);
            }
        }
        return lista;
    }

    /**
     * Crea un registro en blanco.
     * @return un registro en blanco.
     */
    public abstract R creaRegistro();

    /**
     * Agrega un escucha a la base de datos.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaBaseDeDatos<R> escucha) {
        // Aquí va su código.
        escuchas.agregaFinal(escucha);
    }

    /**
     * Elimina un escucha de la base de datos.
     * @param escucha el escucha a eliminar.
     */
    public void eliminaEscucha(EscuchaBaseDeDatos<R> escucha) {
        // Aquí va su código.
        escuchas.elimina(escucha);
    }
}
