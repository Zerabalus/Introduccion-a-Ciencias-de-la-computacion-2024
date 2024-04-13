package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase abstracta para bases de datos. Provee métodos para agregar y eliminar
 * registros, y para guardarse y cargarse de una entrada y salida dados. Además,
 * puede hacer búsquedas con valores arbitrarios sobre los campos de los
 * registros.
 *
 * Las clases que extiendan a BaseDeDatos deben implementar el método {@link
 * #creaRegistro}, que crea un registro en blanco.
 */
public abstract class BaseDeDatos {

    //Es una clase abstracta

    /* Lista de registros en la base de datos. */
    private Lista registros;

    /**
     * Constructor único.
     */
    public BaseDeDatos() {
        // Aquí va su código.
        registros = new Lista();
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
    public Lista getRegistros() {
        // Aquí va su código.
        return registros.copia();
    }

    /**
     * Agrega el registro recibido a la base de datos.
     * @param registro el registro que hay que agregar a la base de datos.
     */
    public void agregaRegistro(Registro registro) {
        // Aquí va su código.
        registros.agregaFinal(registro);
    }

    /**
     * Elimina el registro recibido de la base de datos.
     * @param registro el registro que hay que eliminar de la base de datos.
     */
    public void eliminaRegistro(Registro registro) {
        // Aquí va su código.
        registros.elimina(registro);
    }

    /**
     * Limpia la base de datos.
     */
    public void limpia() {
        // Aquí va su código.
        registros.limpia();
    }

    /**
     * Guarda todos los registros en la base de datos en la salida recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
        // Aquí va su código.
         Lista.Nodo n = registros.getCabeza();
         while (n != null) {
            Registro r = (Registro) n.get();
            out.write(r.seria());
            //out.newLine();
            n = n.getSiguiente();
        }
        out.close(); // cierra el buffer
    }

    /**
     * Carga los registros de la entrada recibida en la base de datos. Si antes
     * de llamar el método había registros en la base de datos, estos son
     * eliminados.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
        //Aquí va su código
        registros.limpia(); 
        //l por lista
        String l;
        while ((l = in.readLine()) != null) {
            Registro r = creaRegistro();
            try {
                r.deseria(l);
            } catch (ExcepcionLineaInvalida e) {
                break;
            }
            agregaRegistro (r);
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
    public Lista buscaRegistros(Enum campo, Object valor) {
        // Aquí va su código.
        if(!(campo instanceof CampoAnilist))
            throw new IllegalArgumentException();
        Lista l = new Lista();
        Lista.Nodo n = registros.getCabeza();
        while (n != null) {
            Registro r = (Registro) n.get();
            if (r.casa(campo, valor))
                l.agregaFinal(r);
            n = n.getSiguiente();
        }
        return l;
    }

    /**
     * Crea un registro en blanco.
     * @return un registro en blanco.
     * Clases abstractas
     */
    public abstract Registro creaRegistro();
}
