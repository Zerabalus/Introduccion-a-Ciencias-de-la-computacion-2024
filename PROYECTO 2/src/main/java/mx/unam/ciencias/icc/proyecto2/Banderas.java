package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista; // importa la clase lista
import mx.unam.ciencias.icc.ExcepcionErrorBandera; //improta la excepción de las banderas

/**
 * Clase de las banderas 
 */
public class Banderas {

    // Si el usuario pasa la bandera -o seguida de un identificador, el programa guarda su salida en un archivo.
    private static String banderaO = "-o";
    // Si el usuario pasa la bandera -r, el programa debe imprimir las líneas al revés.
    private static String banderaR = "-r";

    // Arreglo que almacena los argumentos de la terminal
    private String[] identificador;

    /**
     * Constructor que recibe los argumentos de la terminal.
     *
     * @param identificador Los argumentos de la terminal.
     */
    public Banderas(String[] identificador) {
        this.identificador = identificador;
    }

    /**
     * Valida las banderas proporcionadas.
     *
     * @param banderas Lista de banderas proporcionadas.
     * @throws ExcepcionErrorBandera si hay más de una bandera o si se escriben
     * ambas -r y -o.
     */
    public static void validaBanderas(Lista<String> banderas) throws ExcepcionErrorBandera {
        // Contadores para el número de veces que se encuentra cada bandera
        int rRevisa = 0; // Contador para la bandera -r
        int oRevisa = 0; // Contador para la bandera -o

        // Itera sobre cada bandera en la lista de banderas
        for (String bandera : banderas) {
            // Verifica si la bandera actual es -r
            if (bandera.equals("-r")) {
                rRevisa++; // Incrementa el contador de la bandera -r
            } else if (bandera.equals("-o")) { // Verifica si la bandera actual es -o
                oRevisa++; // Incrementa el contador de la bandera -o
            }
        }

        // Verifica si se escribieron más de una bandera en total
        if (rRevisa + oRevisa > 1) {
            // Lanza una excepción indicando que solo se permite una bandera -r o -o
            throw new ExcepcionErrorBandera("Solo se permite una bandera (-r u -o).");
        }
    }

    /**
     * Checa si la bandera para ordenar al revés está en los argumentos.
     *
     * @return {@code true} si está la bandera para ordenar al revés, {@code false}
     *         en otro caso.
     */
    public boolean tieneBanderaAlReves() {
        for (String input : identificador) {
            if (input.equals(banderaR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el valor que sigue de la bandera para guardar en un archivo (-o)
     * @return El nombre del archivo después de la bandera -o.
     * @throws ExcepcionErrorBandera Si la bandera -o no se sigue de un nombre de archivo.
     */
    public String getValorBanderaO() {
        for (int i = 0; i < identificador.length; i++) {
            if (identificador[i].equals(banderaO)) {
                if (i + 1 < identificador.length) {
                    return identificador[i + 1];
                } else {
                    throw new ExcepcionErrorBandera("Error con Bandera -o");
                }
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de archivos de la terminal.
     *
     * @return Lista de nombres de archivos.
     */
    public Lista<String> getNombreArchivos() {
        Lista<String> archivosNombres = new Lista<>();

        for (int i = 0; i < identificador.length; i++) {
            if (!identificador[i].equals(banderaO) && !identificador[i].equals(banderaR)) {
                archivosNombres.agregaFinal(identificador[i]);
            }
        }

        return archivosNombres;
    }

    /**
     * Valida las banderas de entrada.
     *
     * @param banderas Lista de banderas de entrada.
     * @throws ExcepcionErrorBandera si hay más de una bandera o si se proporcionan ambas -r y -o.
     */
    

}
