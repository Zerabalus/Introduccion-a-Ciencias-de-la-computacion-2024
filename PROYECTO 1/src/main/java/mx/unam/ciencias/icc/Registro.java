package mx.unam.ciencias.icc;


/**
 * Interfaz para registros. Los registros deben de poder seriarse a y
 * deseriarse de una línea de texto. También deben poder determinar si sus
 * campos casan valores arbitrarios y actualizarse con los valores de otro
 * registro.
 */
public interface Registro {

    /**
     * Regresa el registro seriado en una línea de texto. La línea de texto
     * que este método regresa debe ser aceptada por el método {@link
     * Registro#deseria}.
     * @return la seriación del registro en una línea de texto.
     */
    public String seria();

    /**
     * Deseria una línea de texto en las propiedades del registro. La
     * seriación producida por el método {@link Registro#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un registro.
     */
    public void deseria(String linea);

    /**
     * Nos dice si el registro casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el campo especificado del registro casa el
     *         valor dado, <code>false</code> en otro caso.
     */
    public boolean casa(Enum campo, Object valor);


}
