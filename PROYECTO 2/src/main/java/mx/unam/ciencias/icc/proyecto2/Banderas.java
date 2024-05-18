package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista;

/**
 * Clase de las banderas 
 */
public class Banderas {

    // Si el usuario pasa la bandera -o seguida de un identificador, el programa guarda su salida en un archivo.
    private static String banderaO = "-o";
    // Si el usuario pasa la bandera -r, el programa debe imprimir las líneas en orden inverso.
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
     * Verifica si la bandera de reversa está presente en los argumentos.
     *
     * @return {@code true} si la bandera de reversa está presente, {@code false} en otro caso.
     */
    public boolean tieneBanderaReversa() {
        for (String cmd : identificador) {
            if (cmd.equals(banderaR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el valor asociado a la bandera de guardar en archivo (-o).
     *
     * @return El nombre del archivo especificado después de la bandera -o.
     * @throws IllegalArgumentException Si la bandera -o no se sigue de un nombre de archivo.
     */
    public String obtenerValorBanderaGuarda() {
        for (int i = 0; i < identificador.length; i++) {
            if (identificador[i].equals(banderaO)) {
                if (identificador.length > i + 1) {
                    return identificador[i + 1];
                } else {
                    throw new IllegalArgumentException("Bandera -o tiene que ir con un archivo.");
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
