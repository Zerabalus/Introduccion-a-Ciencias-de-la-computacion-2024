package mx.unam.ciencias.icc;

/**
 * Clase que representa un texto plano a partir de un texto de entrada.
 */
public class TextoPlano {

    // Texto de entrada original.
    private String textoEntrada;
    // Texto normalizado resultante.
    private String textoPlano;

    /**
     * Constructor de la clase. Normaliza el texto de entrada.
     *
     * @param textoEntrada El texto de entrada que se normalizará.
     */
    public TextoPlano(String textoEntrada) {
        this.textoEntrada = textoEntrada;
        this.textoPlano = cambiarTexto(textoEntrada);
    }

    /**
     * Normaliza el texto de entrada eliminando acentos y convirtiendo a minúsculas.
     *
     * @param texto El texto a normalizar.
     * @return El texto normalizado.
     */
    private String cambiarTexto(String texto) {
        char[] acentos = { 'á', 'é', 'í', 'ó', 'ú' };
        char[] sinAcentos = { 'a', 'e', 'i', 'o', 'u' };

        char[] textoArray = texto.toLowerCase().toCharArray();
        char[] resultadoArray = new char[textoArray.length];

        int resultadoIndex = 0;
        for (char c : textoArray) {
            boolean encontrado = false;
            for (int j = 0; j < acentos.length; j++) {
                if (c == acentos[j]) {
                    resultadoArray[resultadoIndex++] = sinAcentos[j];
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado && c >= 'a' && c <= 'z') {
                resultadoArray[resultadoIndex++] = c;
            }
        }

        return new String(resultadoArray, 0, resultadoIndex);
    }

    /**
     * Obtiene el texto normalizado.
     *
     * @return El texto normalizado.
     */
    public String obtenerTextoPlano() {
        return textoPlano;
    }
}   