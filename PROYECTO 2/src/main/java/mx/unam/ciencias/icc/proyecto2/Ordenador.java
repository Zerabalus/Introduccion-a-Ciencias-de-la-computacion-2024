package mx.unam.ciencias.icc.proyecto2;

public interface Ordenador<T> extends Iterable<T> {

    /**
     * Agrega un elemento a la colección.
     * @param elemento el elemento a agregar.
     */
    public void agrega(T elemento);

    /**
     * Elimina un elemento
     * @param elemento el elemento a eliminar
     */
    public void elimina(T elemento);

    /**
     * Nos dice si un elemento está contenido
     * @param elemento el elemento que queremos verificar 
     * @return <code>true</code> si el elemento está contenido 
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento);

    /**
     * Nos dice si el conjunto de elementos en la colección es vacío.
     * @return <code>true</code> si el conjunto de elementos en la colección es
     *         vacío, <code>false</code> en otro caso.
     */
    public boolean esVacio();

    /**
     * Regresa el número de elementos en la colección.
     * @return el número de elementos en la colección.
     */
    public int getElementos();
}
