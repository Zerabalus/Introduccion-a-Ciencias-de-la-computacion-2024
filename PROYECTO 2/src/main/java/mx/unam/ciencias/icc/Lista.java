package mx.unam.ciencias.icc;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
            siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            // Aquí va su código.
            return (siguiente != null);
        }

        /* Nos da el elemento siguiente. */
        @Override
        public T next() {
            // Aquí va su código.
            if (!hasNext())
                throw new NoSuchElementException();
            else {
                T elementosig = siguiente.elemento;
                anterior = siguiente;
                siguiente = siguiente.siguiente;
                return elementosig;
            }
        }

        /* Nos dice si hay un elemento anterior. */
        @Override
        public boolean hasPrevious() {
            // Aquí va su código.
            return (anterior != null);
        }

        /* Nos da el elemento anterior. */
        @Override
        public T previous() {
            // Aquí va su código.
            if (!hasPrevious())
                throw new NoSuchElementException();
            else {
                T elementosig = anterior.elemento;
                siguiente = anterior;
                anterior = anterior.anterior;
                return elementosig;
            }
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override
        public void start() {
            // Aquí va su código.
            anterior = null;
            siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override
        public void end() {
            // Aquí va su código.
            siguiente = null;
            anterior = rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();
        Nodo n = new Nodo(elemento);
        if (esVacia()) {
            cabeza = n;
            rabo = cabeza;
            cabeza.anterior = null;
            rabo.siguiente = null;
        } else {
            n.anterior = rabo;
            rabo.siguiente = n;
            rabo = n;
        }
        longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        if (elemento == null) {
            throw new IllegalArgumentException();
        }
        if (esVacia()) {
            cabeza = new Nodo(elemento);
            rabo = cabeza;
            longitud++;
        } else {
            Nodo nuevo = new Nodo(elemento);
            cabeza.anterior = nuevo;
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            longitud++;
        }
    }
    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
        if (elemento == null) {
            throw new IllegalArgumentException();
        }
        if (esVacia()) {
            cabeza = new Nodo(elemento);
            rabo = cabeza;
            longitud++;
        } else if (i <= 0) {
            agregaInicio(elemento);
        } else if (i >= longitud) {
            agregaFinal(elemento);
        } else {
            Nodo n = cabeza;
            for (int j = 0; j < i; j++) {
                n = n.siguiente;
            }
            Nodo nuevo = new Nodo(elemento);
            nuevo.anterior = n.anterior;
            nuevo.siguiente = n;
            n.anterior.siguiente = nuevo;
            n.anterior = nuevo;
            longitud++;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        // Aquí va su código.
        Nodo n = cabeza;
        while (n != null && !n.elemento.equals(elemento)) {
            n = n.siguiente;
        }

        if (n == null) {
            return;
        }

        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            if (cabeza != null) {
                cabeza.anterior = null;
            } else {
                rabo = null;
            }
        } else {
            n.anterior.siguiente = n.siguiente;
            if (n.siguiente != null) {
                n.siguiente.anterior = n.anterior;
            } else {
                rabo = n.anterior;
            }
        }
        longitud--;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        if(esVacia())
            throw new NoSuchElementException();
        Nodo eliminado = cabeza;
        if (longitud == 1)
            limpia();
        else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud--;
        }
        return eliminado.elemento;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if(esVacia()){
            throw new NoSuchElementException();
        }
        Nodo eliminado = rabo;
        if (longitud == 1)
            limpia();
        else {
            rabo = rabo.anterior;
            rabo.siguiente = null;
            longitud--;
        }
        return eliminado.elemento;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {
        // Aquí va su código.
        Nodo n = cabeza;
        while(n != null) {
            if(n.elemento.equals(elemento)) {
                return true;
            }
            n = n.siguiente;
        }
        return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
        Lista<T> lista = new Lista<T>();
        Nodo n = rabo;
        // creo una variable nodo
        while (n != null) {
            // mientra n (nodo) sea distinto de nulo
            lista.agregaFinal(n.elemento);
            n = n.anterior;
        }
        return lista;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
        Nodo nodo = cabeza;
        Lista<T> copiaLista = new Lista<T>();
        if(esVacia()) return copiaLista;
        while(nodo != null){
          copiaLista.agregaFinal(nodo.elemento);
          nodo = nodo.siguiente;
        }
        return copiaLista;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        if (longitud != 0)
            return cabeza.elemento;
        else
            throw new NoSuchElementException();
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        if (longitud != 0)
            return rabo.elemento;
        else
            throw new NoSuchElementException();
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
        if (i < 0 || i >= longitud)
            throw new ExcepcionIndiceInvalido();
        Nodo n = cabeza;
        for (int index = 0; index < i; index++) {
            n = n.siguiente;
        }
        return n.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
        Nodo n = cabeza;
        int indice = 0;
        while (n != null) {
            if (n.elemento == elemento)
                return indice;
            n = n.siguiente;
            indice++;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override 
    public String toString() {
        // Aquí va su código.
        if (esVacia())
            return "[]";
        String s = "[";
        for (int i = 0; i < longitud - 1; i++)
            s += String.format("%s, ", get(i));
        s += String.format("%s]", get(longitud - 1));
        return s;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
        if (lista == null)
            return false;
        else if (lista.getLongitud() != longitud)
            return false;
        else if (lista.getLongitud() == 0 && longitud == 0)
            return true;
        Nodo nodo = cabeza;
        int i = 0;
        while (nodo != null) {
            if (nodo.elemento.equals(lista.get(i)) == false)
                return false;
            nodo = nodo.siguiente;
            i++;
        }
        return true;
        
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        // Aquí va su código.
        return mergeSort(copia(), comparador);
        //regresa la copia de una lista
    }
    
    //método que divide la lista
    private Lista<T> mergeSort(Lista<T> l, Comparator<T> comparador) {
        if (l.esVacia() || l.getLongitud() <= 1) {
            return l; //l de lista
            // revisa si no es nulo o su longitud es menor a cero, 
            // tambien pudo haber sido ==0
        }
        int mitad = l.getLongitud() / 2; //divide la lista
        Lista<T> l1 = new Lista<T>(); //crea una nueva lista
        Lista<T> l2;                  //lista para guardar las mitades
        while (l.getLongitud() != mitad) {
            l1.agregaFinal(l.getPrimero());
            if (l.getLongitud() != 0) //longitud distinta de 0 (null no porque es elemento)
                l.eliminaPrimero(); //elimina el primero para no dejar la lista y desperdiciar memoria
        }
        l2 = l.copia(); 
        return mezcla(mergeSort(l1, comparador), mergeSort(l2, comparador), comparador);
    }

    //método que hace la mezcla de las listas a y b en una lista ordenada
    private Lista<T> mezcla(Lista<T> a, Lista<T> b, Comparator<T> comparador) {
        Lista<T> listaOrdenada = new Lista<T>();
        //crea una nueva lista y la asigna a la lista ordenada
        while (a.cabeza != null && b.cabeza != null) {
            int i = comparador.compare(a.cabeza.elemento, b.cabeza.elemento);
            //comparamos los elementos de a y b y los agregamos a la variable i
            if (i <= 0) { //i es menor o igual a 0
                listaOrdenada.agregaFinal(a.getPrimero()); 
                a.eliminaPrimero();
                //agregamos el primero de a al final de la lista ordenada y la borra
            } else {
                listaOrdenada.agregaFinal(b.getPrimero());
                b.eliminaPrimero(); 
                //igual que anterior pero con b
            }
        }

        //agregamos los elementos restantes de la lista
        while (a.cabeza != null) {
            listaOrdenada.agregaFinal(a.getPrimero());
            a.eliminaPrimero();
        }
        while (b.cabeza != null) {
            listaOrdenada.agregaFinal(b.getPrimero());
            b.eliminaPrimero();
        }
        return listaOrdenada;
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        Nodo n = cabeza;
        while(n != null){
            if(comparador.compare(elemento, n.elemento) == 0) return true;
            n = n.siguiente;
        }
        return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
