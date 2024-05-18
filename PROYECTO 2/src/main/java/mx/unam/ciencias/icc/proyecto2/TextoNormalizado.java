package mx.unam.ciencias.icc.proyecto2;

import java.text.Normalizer;

/**
 * Clase que representa un texto normalizado a partir de un texto de entrada.
 */
public class TextoNormalizado {

    // Texto de entrada original.
    private String textoEntrada;
    // Texto normalizado resultante.
    private String textoNormalizado;

    /**
     * Constructor de la clase. Normaliza el texto de entrada.
     *
     * @param textoEntrada El texto de entrada que se normalizará.
     */
    public TextoNormalizado(String textoEntrada) {
        this.textoEntrada = textoEntrada;

        // Normaliza el texto utilizando Normalizer y lo convierte a minúsculas.
        String normalizado = Normalizer.normalize(textoEntrada, Normalizer.Form.NFD);
        normalizado = normalizado.toLowerCase().replaceAll("[^a-z]", "");

        this.textoNormalizado = normalizado;
    }

    /**
     * Obtiene el texto normalizado.
     *
     * @return El texto normalizado.
     */
    public String obtenerTextoNormalizado() {
        return textoNormalizado;
    }

    /**
     * Representación en cadena del objeto. Devuelve el texto original.
     *
     * @return El texto original.
     */
    @Override
    public String toString() {
        return textoEntrada;
    }
}
