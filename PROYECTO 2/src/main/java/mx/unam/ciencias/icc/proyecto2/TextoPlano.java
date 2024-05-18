package mx.unam.ciencias.icc.proyecto2;

/**
 * Clase que modifica el texto/archivo de entrada.
 */
public class TextoPlano {

    // Texto original.
    private String textoOriginal;
    // Texto modificado
    private String TextoPlano;

    /**
     * Constructor que modifica el texto
     *
     * @param textoOriginal El texto que se modificará.
     */
    public TextoPlano(String textoOriginal) {
        this.textoOriginal = textoOriginal;

        // ELimina acentos modificando el texto y lo convierte a minúsculas.
        String plano = eliminarAcentos(textoOriginal).toLowerCase().replaceAll("[^a-z]", "");

        this.TextoPlano = plano;
    }

    /**
     * Elimina los acentos de un texto.
     *
     * @param texto El texto con acentos.
     * @return El texto sin acentos.
     */
    private String eliminarAcentos(String texto) {
        char[] caracteres = texto.toCharArray();

        for (int i = 0; i < caracteres.length; i++) {
            switch (caracteres[i]) {
                case 'á': case 'Á': caracteres[i] = 'a'; break;
                case 'é': case 'É': caracteres[i] = 'e'; break;
                case 'í': case 'Í': caracteres[i] = 'i'; break;
                case 'ó': case 'Ó': caracteres[i] = 'o'; break;
                case 'ú': case 'Ú': caracteres[i] = 'u'; break;
                case 'ñ': case 'Ñ': caracteres[i] = 'n'; break;
                default: break;
            }
        }

        return new String(caracteres);
    }

    /**
     * Obtiene el texto plano.
     *
     * @return El texto plano.
     */
    public String getTextoModificado() {
        return TextoPlano;
    }

    /**
     * Representación en cadena del objeto. Devuelve el texto original.
     *
     * @return El texto original.
     */
    @Override
    public String toString() {
        return textoOriginal;
    }
}
