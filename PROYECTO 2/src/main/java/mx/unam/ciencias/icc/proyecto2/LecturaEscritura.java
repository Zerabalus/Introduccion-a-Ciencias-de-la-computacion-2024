package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase encargada de manejar la lectura y escritura de archivos.
 */

public class LecturaEscritura {

    // Constructor privado para evitar instancias de la clase.
    private LecturaEscritura() {  

    }
    /**
     * Obtiene los BufferedReader de las fuentes de entrada proporcionadas.
     *
     * @param nombresArchivos Lista de nombres de archivos de entrada.
     * @return Lista de BufferedReader asociados a cada archivo de entrada.
     * @throws IOException si hay un problema al abrir alguno de los archivos.
     */
    public static Lista<BufferedReader> obtenerEntradas(Lista<String> nombresArchivos) throws IOException {
        Lista<BufferedReader> entradas = new Lista<BufferedReader>();

        // Si no se proporciona un archivo de entrada, lee desde la entrada estándar.
        if (nombresArchivos.esVacia()) {
            entradas.agregaFinal(new BufferedReader(
                                    new InputStreamReader(System.in)));
            return entradas;
        }

        // Intenta abrir cada archivo de entrada y agregar su BufferedReader a la lista.
        for (String nombreArchivo : nombresArchivos) {
            try {
                BufferedReader entrada = new BufferedReader(
                                            new InputStreamReader(
                                                new FileInputStream(nombreArchivo)));
                entradas.agregaFinal(entrada);
            } catch (IOException ioe) {
                // Cierra los archivos abiertos en caso de error y lanza una excepción.
                cerrarArchivos(entradas);
                throw new IOException("No se pudo abrir el archivo correctamente.");
            }
        }

        return entradas;
    }

    /**
     * Lee las líneas de los BufferedReader proporcionados y las normaliza.
     *
     * @param entradas Lista de BufferedReader de donde leer las líneas.
     * @return Lista de TextoNormalizado con las líneas normalizadas.
     * @throws IOException si hay un problema al leer las líneas de entrada.
     */

    public static Lista<TextoNormalizado> procesarEntradas(Lista<BufferedReader> entradas) throws IOException {
        Lista<TextoNormalizado> lineasProcesadas = new Lista<TextoNormalizado>();

        String linea;
        // Lee las líneas de cada BufferedReader y las agrega normalizadas a la lista.
        for (BufferedReader entrada : entradas)
            while ((linea = entrada.readLine()) != null)
                lineasProcesadas.agregaFinal(new TextoNormalizado(linea));

        return lineasProcesadas;
    }

    /**
     * Cierra los BufferedReader de la lista proporcionada.
     *
     * @param entradas Lista de BufferedReader que se deben cerrar.
     */

    public static void cerrarArchivos(Lista<BufferedReader> entradas) {
        if (entradas == null)
            return;
        // Intenta cerrar cada BufferedReader y atrapa posibles excepciones.
        for (BufferedReader entrada : entradas)
            try {
                entrada.close();
            } catch(IOException ioe) {
                // Ignora las excepciones de cierre.
            }
    }

    /**
     * Guarda las líneas normalizadas en un archivo de salida.
     *
     * @param nombreArchivo Nombre del archivo de salida.
     * @param lineas Lista de TextoNormalizado que se debe guardar.
     * @throws IOException si hay un problema al escribir en el archivo de salida.
     */

    public static void guardar(String nombreArchivo, Lista<TextoNormalizado> lineas) throws IOException {
        BufferedWriter salida = new BufferedWriter(
                                new OutputStreamWriter(
                                    new FileOutputStream(nombreArchivo)));
        // Escribe cada línea en el archivo de salida.
        for (TextoNormalizado linea : lineas)
            salida.write(linea.toString() + "\n");

        salida.close();
    }
}
