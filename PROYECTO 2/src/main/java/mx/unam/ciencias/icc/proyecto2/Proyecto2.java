package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase principal del programa que realiza la lectura, procesamiento y escritura de líneas de texto.
 */
public class Proyecto2 {

    /**
     * Imprime la salida en la consola o guarda en un archivo según el argumento proporcionado.
     *
     * @param lineas Lista de TextoNormalizado a imprimir o guardar.
     * @param salida Nombre del archivo de salida. Si es null, imprime en consola.
     */
    public static void regresaSalida(Lista<TextoNormalizado> lineas, String salida) {
        // Si no se proporciona un archivo de salida, imprime las líneas en la consola
        if (salida == null)
            for (TextoNormalizado linea : lineas)
                System.out.println(linea.toString());
        else
            try {
                // Intenta guardar las líneas en el archivo de salida
                LecturaEscritura.guardar(salida, lineas);
            } catch(IOException ioe) {
                // Manejo de errores en caso de problemas al guardar
                System.out.printf("\nSe produjo un fallo al intentar almacenar la salida.");
                System.exit(1);
            }
    }

    /**
     * Ordena las líneas utilizando el orden lexicográfico.
     *
     * @param lineas Lista de TextoNormalizado a ordenar.
     * @param reversa Indica si se debe ordenar en forma inversa.
     * @return Lista ordenada de TextoNormalizado.
     */
    private static Lista<TextoNormalizado> ordena(Lista<TextoNormalizado> linea, boolean reversa) {
        return reversa ?
                OrdenadorLexicografico.ordenaReversa(linea) :
                OrdenadorLexicografico.ordena(linea);
    }

    /**
     * Lee la(s) entrada(s) y regresa una lista con las líneas normalizadas.
     *
     * @param fuentesEntrada Lista de nombres de archivos de entrada.
     * @return Lista de TextoNormalizado generada a partir de las entradas.
     */
    private static Lista<TextoNormalizado> leeLineas(Lista<String> fuentesEntrada) {
        Lista<BufferedReader> entradas = null;
        Lista<TextoNormalizado> lineas = new Lista<>();

        try {
            // Obtiene los BufferedReader de las fuentes de entrada
            entradas = LecturaEscritura.obtenerEntradas(fuentesEntrada);
            // Lee las líneas de las entradas y las normaliza
            lineas = LecturaEscritura.procesarEntradas(entradas);
            // Cierra los BufferedReader
            LecturaEscritura.cerrarArchivos(entradas);
        } catch(IOException ioe) {
            // Manejo de errores en caso de problemas al leer la entrada
            LecturaEscritura.cerrarArchivos(entradas);
            System.out.println("Se presentó un error al leer la entrada.");
            System.exit(1);
        }

        return lineas;
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        // Analiza los argumentos recibidos.
        GestorConsola parametro = new GestorConsola(args);
        boolean reversa = parametro.tieneBanderaReversa();
        String archivoSalida = null;
        try {
            // Intenta obtener el nombre del archivo de salida de las banderas
            archivoSalida = parametro.obtenerValorBanderaGuarda();
        } catch(IllegalArgumentException iae) {
            // Manejo de errores en caso de problemas con las banderas
            System.out.println("\nEl argumento de la bandera -o debe ir seguido de un " +
                                "nombre de archivo en el cual escribir.\n" +
                                "Uso: java -jar proyecto1.jar -o <archivo>");
            System.exit(1);
        }

        // Lee las líneas de las fuentes de entrada
        Lista<TextoNormalizado> lineas = leeLineas(parametro.obtenerFuentesEntrada());
        // Ordena las líneas
        Lista<TextoNormalizado> ordenadas = ordena(lineas, reversa);
        // Imprime o guarda las líneas ordenadas según el archivo de salida proporcionado
        regresaSalida(ordenadas, archivoSalida);
    }
}
