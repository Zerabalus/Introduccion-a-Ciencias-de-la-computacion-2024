package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista;

/**
 * Clase encargada de ordenar listas de TextoNormalizado de manera lexicográfica.
 */
public class OrdenadorLexicografico {

    // Constructor privado para evitar instanciación.
    private OrdenadorLexicografico() {}

    /**
     * Ordena la lista de TextoNormalizado de manera lexicográfica.
     *
     * @param lista Lista de TextoNormalizado a ordenar.
     * @return Lista ordenada de TextoNormalizado.
     */
    public static Lista<TextoNormalizado> ordena(Lista<TextoNormalizado> lista) {
        return lista.mergeSort((linea1, linea2) ->
                linea1.obtenerTextoNormalizado().compareTo(linea2.obtenerTextoNormalizado()));
    }

    /**
     * Ordena la lista de TextoNormalizado de manera lexicográfica en orden inverso.
     *
     * @param lista Lista de TextoNormalizado a ordenar.
     * @return Lista ordenada de TextoNormalizado en orden inverso.
     */
    public static Lista<TextoNormalizado> ordenaReversa(Lista<TextoNormalizado> lista) {
        return ordena(lista).reversa();
    }
}
