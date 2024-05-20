package mx.unam.ciencias.icc.proyecto2;

/**PARA ESTA CLASE SE QUE HAY MANERAS MAS SENCILLAS DE HACERLO
 PORQUE ANDUVE INVESTIGANDO Y SEGUN SE PODIA CON NORMALIZER HASHMAP Y OTROS IMPORTS
 PERO SEGUN LAS INSTRUCCIONES NO SE PODIAN IMPORTAR Y NO LO VIMOS EN CLASE POR LO DEL PARO T.T
 ENTONCES LO HICE ASI: 
*/

/**
 * Clase que modifica el texto/archivo de entrada.
 */
public class TextoPlano {

    private String textoOriginal;
    private String textoPlano;

    public TextoPlano(String textoOriginal) {
        
        this.textoOriginal = textoOriginal;
        // Ponemos el texto/caracteres "planos" elimina los acentos y diacríticos, 
        //luego lo convertimos a minúsculas y
        // eliminamos cualquier carácter que no sea una letra.
        this.textoPlano = eliminarAcentos(textoOriginal).toLowerCase().replaceAll("[^a-z]", "");
    }

    // Método para eliminar acentos y diacríticos de los caracteres Unicode
    private String eliminarAcentos(String texto) {
        String textoModificado = "";
        // Iteramos sobre cada carácter del texto original
        for (char c : texto.toCharArray()) {
            // Normalizamos y concatenamos el carácter modificado al nuevo texto
            textoModificado += textoPlano(c);
        }
        return textoModificado;
    }

    // Método para textoPlano caracteres con diacríticos
    private char textoPlano(char c) {
        switch (c) {
            // Vocales con acentos
            case 'á': case 'à': case 'â': case 'ä': case 'å':
            case 'Á': case 'À': case 'Â': case 'Ä': case 'Å':
                return 'a';
            case 'é': case 'è': case 'ê': case 'ë':
            case 'É': case 'È': case 'Ê': case 'Ë':
                return 'e';
            case 'í': case 'ì': case 'î': case 'ï':
            case 'Í': case 'Ì': case 'Î': case 'Ï':
                return 'i';
            case 'ó': case 'ò': case 'ô': case 'ö': case 'ø':
            case 'Ó': case 'Ò': case 'Ô': case 'Ö': case 'Ø':
                return 'o';
            case 'ú': case 'ù': case 'û': case 'ü':
            case 'Ú': case 'Ù': case 'Û': case 'Ü':
                return 'u';

            // Ñ 
            case 'ñ': case 'Ñ':
                return 'n';
            //Demás...    
        
            case 'ç': case 'Ç':
                return 'c';
            case 'ß':
                return 's';
            case 'ÿ':
                return 'y';
            case 'æ':
                return 'a';
            case 'œ':
                return 'o';

            // Para cualquier otro devuelve el mismo
            default:
                return c;
        }
    }

    // Método que devuelve el texto modificado ya plano
    public String getTextoModificado() {
        return textoPlano;
    }

    // Representación en cadena que evuelve el texto original
    @Override
    public String toString() {
        return textoOriginal;
    }
}
