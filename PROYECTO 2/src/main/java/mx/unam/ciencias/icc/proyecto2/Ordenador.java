package mx.unam.ciencias.icc.proyecto2;

/**
 * Clase encargada de ordenar listas de TextoPlano de manera lexicográfica.
 */
public class Ordenador {

    // Constructor privado para evitar instanciación.
    private Ordenador() {}

    /**
     * Ordena la lista de TextoPlano de manera lexicográfica.
     *
     * @param lista Lista de TextoPlano a ordenar.
     * @return Lista ordenada de TextoPlano.
     */
    public static Lista<TextoPlano> ordena(Lista<TextoPlano> lista) {
        return lista.mergeSort((linea1, linea2) ->
                linea1.obtenerTextoPlano().compareTo(linea2.obtenerTextoPlano()));
    }

    /**
     * Ordena la lista de TextoPlano de manera lexicográfica en orden inverso.
     *
     * @param lista Lista de TextoPlano a ordenar.
     * @return Lista ordenada de TextoPlano en orden inverso.
     */
    public static Lista<TextoPlano> ordenaReversa(Lista<TextoPlano> lista) {
        return ordena(lista).reversa();
    }
}