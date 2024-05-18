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
     * @param lineas Lista de TextoPlano a imprimir o guardar.
     * @param salida Nombre del archivo de salida. Si es null, imprime en consola.
     */
    public static void regresaSalida(Lista<TextoPlano> lineas, String salida) {
        // Si no se proporciona un archivo de salida, imprime las líneas en la consola
        if (salida == null)
            for (TextoPlano linea : lineas)
                System.out.println(linea.toString());
        else
            try {
                // Intenta guardar las líneas en el archivo de salida
                Archivos.guardar(salida, lineas);
            } catch(IOException ioe) {
                // Manejo de errores en caso de problemas al guardar
                System.out.printf("\nSe produjo un fallo al intentar almacenar la salida.");
                System.exit(1);
            }
    }

    /**
     * Ordena las líneas utilizando el orden lexicográfico.
     *
     * @param lineas Lista de TextoPlano a ordenar.
     * @param reversa Indica si se debe ordenar en forma inversa.
     * @return Lista ordenada de TextoPlano.
     */
    private static Lista<TextoPlano> ordena(Lista<TextoPlano> linea, boolean reversa) {
        return reversa ?
                Ordenador.ordenaReversa(linea) :
                Ordenador.ordena(linea);
    }

    /**
     * Lee la(s) entrada(s) y regresa una lista con las líneas normalizadas.
     *
     * @param fuentesEntrada Lista de nombres de archivos de entrada.
     * @return Lista de TextoPlano generada a partir de las entradas.
     */
    private static Lista<TextoPlano> leeLineas(Lista<String> fuentesEntrada) {
        Lista<BufferedReader> entradas = null;
        Lista<TextoPlano> lineas = new Lista<>();

        try {
            // Obtiene los BufferedReader de las fuentes de entrada
            entradas = Archivos.obtenerEntradas(fuentesEntrada);
            // Lee las líneas de las entradas y las normaliza
            lineas = Archivos.procesarEntradas(entradas);
            // Cierra los BufferedReader
            Archivos.cerrarArchivos(entradas);
        } catch(IOException ioe) {
            // Manejo de errores en caso de problemas al leer la entrada
            Archivos.cerrarArchivos(entradas);
            System.out.println("Se presentó un error al leer la entrada.");
            System.exit(1);
        }

        return lineas;
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args Argumentos de terminal.
     */
    public static void main(String[] args) {
        // Analiza los argumentos recibidos.
        Banderas identificador = new Banderas(args);
        boolean reversa = identificador.tieneBanderaAlReves();
        String archivoSalida = null;
        try {
            // Intenta obtener el nombre del archivo de salida de las banderas
            archivoSalida = identificador.obtenerValorBanderaO();
        } catch(IllegalArgumentException iae) {
            // Manejo de errores en caso de problemas con las banderas
            System.out.println("\nEl argumento de la bandera -o debe ir seguido de un " +
                                "nombre de archivo en el cual escribir.\n" +
                                "Uso: java -jar proyecto1.jar -o <archivo>");
            System.exit(1);
        }

        // Lee las líneas de las fuentes de entrada
        Lista<TextoPlano> lineas = leeLineas(identificador.obtenerFuentesEntrada());
        // Ordena las líneas
        Lista<TextoPlano> ordenadas = ordena(lineas, reversa);
        // Imprime o guarda las líneas ordenadas según el archivo de salida proporcionado
        regresaSalida(ordenadas, archivoSalida);
    }
}
