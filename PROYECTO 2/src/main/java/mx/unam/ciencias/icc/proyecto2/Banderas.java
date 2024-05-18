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
     * Checa si la bandera para ordenar al revés está en los argumentos.
     *
     * @return {@code true} si está la bandera para ordenar al revés, {@code false} en otro caso.
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
    public String obtenerValorBanderaGuarda() {
        for (int i = 0; i < identificador.length; i++) {
            if (identificador[i].equals(banderaO)) {
                if (i + 1 < identificador.length) {
                    return identificador[i + 1];
                } else {
                    throw new ExcepcionErrorBandera();
                }
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de fuentes de entrada desde los argumentos de la terminal.
     *
     * @return Lista de nombres de archivos como fuentes de entrada.
     */
    public Lista<String> obtenerFuentesEntrada() {
        Lista<String> archivosEntrada = new Lista<>();

        for (int i = 0; i < identificador.length; i++) {
            if (identificador[i].equals(banderaO)) {
                i++; // Saltar al siguiente argumento después de la bandera -o.
            } else if (!identificador[i].equals(banderaR)) {
                archivosEntrada.agregaFinal(identificador[i]);
            }
        }

        return archivosEntrada;
    }
}
