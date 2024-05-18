package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista;

/**
 * Clase encargada de gestionar los argumentos de la línea de comandos.
 */
public class Banderas {

    // Bandera para la opción de guardar en archivo.
    private static String banderaO = "-o";
    // Bandera para la opción de ordenar en reversa.
    private static String banderaR = "-r";

    // Arreglo que almacena los argumentos de la línea de comandos.
    private String[] input;

    /**
     * Constructor de la clase. Recibe los argumentos de la línea de comandos.
     *
     * @param input Los argumentos de la línea de comandos.
     */
    public Banderas(String[] input) {
        this.input = input;
    }

    /**
     * Verifica si la bandera de reversa está presente en los argumentos.
     *
     * @return {@code true} si la bandera de reversa está presente, {@code false} en otro caso.
     */
    public boolean tieneBanderaReversa() {
        for (String cmd : input) {
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
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(banderaO)) {
                if (input.length > i + 1) {
                    return input[i + 1];
                } else {
                    throw new IllegalArgumentException("Bandera -o tiene que ir con un archivo.");
                }
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de fuentes de entrada desde los argumentos de la línea de comandos.
     *
     * @return Lista de nombres de archivos como fuentes de entrada.
     */
    public Lista<String> obtenerFuentesEntrada() {
        Lista<String> archivosEntrada = new Lista<>();

        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(banderaO)) {
                i++; // Saltar al siguiente argumento después de la bandera -o.
            } else if (!input[i].equals(banderaR)) {
                archivosEntrada.agregaFinal(input[i]);
            }
        }

        return archivosEntrada;
    }
}
