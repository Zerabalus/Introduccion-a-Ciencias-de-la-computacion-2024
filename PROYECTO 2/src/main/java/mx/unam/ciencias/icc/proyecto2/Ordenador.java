package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista;

/**
 * Clase que organiza TextoPlano en un orden lexicográfico.
 */
public class Ordenador {

    private Ordenador() {}

    /**
     * Ordena la lista de TextoPlano lexicográficamente.
     *
     * @param lista Lista de TextoPlano a ordenar.quie
     * @return Lista ordenada de TextoPlano.
     */
    public static Lista<TextoPlano> ordena(Lista<TextoPlano> lista) {
        return lista.mergeSort((linea1, linea2) -> linea1.getTextoModificado().compareTo(linea2.getTextoModificado()));
    }

    /**
     * Ordena la lista de TextoPlano lexicográficamente y al reves.
     *
     * @param lista Lista de TextoPlano a ordenar.
     * @return Lista ordenada de TextoPlano al reves.
     */
    public static Lista<TextoPlano> ordenaReversa(Lista<TextoPlano> lista) {
        return ordena(lista).reversa();
    }
}
