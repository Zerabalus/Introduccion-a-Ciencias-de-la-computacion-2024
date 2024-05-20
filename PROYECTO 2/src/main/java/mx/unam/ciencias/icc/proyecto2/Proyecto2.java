package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista; //Importa la clase lista
import mx.unam.ciencias.icc.ExcepcionArchivoInvalido; // Importa la excepcion archivo invalido
import mx.unam.ciencias.icc.ExcepcionErrorBandera;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase principal del proyetco que se encarga de leer, procesar y escribir líneas de texto
 */
public class Proyecto2 {

    /**
     * Imprime la salida en la terminal o la guarda en un archivo si se proporciona un nombre de archivo.
     *
     * @param lineas Lista de TextoPlano que se imprimirá o guardará.
     * @param salida Nombre del archivo de salida. Si es null, se imprime en la terminal.
     */
    public static void returnSalida(Lista<TextoPlano> lineas, String salida) {

        // Si no se proporciona un archivo de salida imprime las líneas en la terminal
        if (salida == null) {
            for (TextoPlano string : lineas) {
                System.out.println(string.toString());
            }
        } 
        
        else
            try {

                // Intenta guardar las líneas en el archivo de salida
                Archivos.guarda(salida, lineas);
            } catch (IOException ioe) {
                // Manejo de errores en caso de problemas al guardar
                System.out.printf("\nSe produjo un fallo al intentar almacenar la salida.");
                System.exit(1);
            }
    }

    /**
     * Ordena las líneas lexicográficamente
     *
     * @param string  Lista de TextoPlano a ordenar.
     * @param reversa Indica si se debe ordenar en forma inversa.
     * @param string referencia a las lineas
     * @return Lista ordenada de TextoPlano.
     */
    private static Lista<TextoPlano> ordena(Lista<TextoPlano> string, boolean reversa) {
        return reversa ? Ordenador.ordenaReversa(string) : Ordenador.ordena(string);
    }

    /**
     * Lee las líneas de las fuentes de entrada y las devuelve como una lista de TextoPlano.
     *
     * @param fuentesEntrada Lista de nombres de archivos de entrada.
     * @return Lista de TextoPlano generada a partir de las entradas
     * @throws ExcepcionArchivoInvalido Si hay un error al cerrar el archivo
     */


     private static Lista<TextoPlano> leeLineas(Lista<String> fuentesEntrada) {
        Lista<BufferedReader> entradas = null;
        Lista<TextoPlano> lineas = new Lista<>();
    
        try {
            // Obtiene los BufferedReader de las entradas
            entradas = Archivos.getArchivosEntrada(fuentesEntrada);
            // Lee las líneas de las entradas y las vuelve texto plano
            lineas = Archivos.modificaLineasEntrada(entradas);
        } catch (IOException ioe) {
            // en caso de errores al leer la entrada:
            System.out.println("Error al leer la entrada: " + ioe.getMessage());
            System.exit(1);
        } finally {
            // Cierra los BufferedReader viendo que no son nulos
            if (entradas != null) {
                Archivos.cierraBufferArchivos(entradas);
            }
        }
    
        return lineas;
    }
    /**
     * Main del proyecto
     *
     * @param args Argumentos de terminal.
     */
    public static void main(String[] args) {
        Lista<String> argumentos = new Lista<>();
        for (String arg : args) {
            argumentos.agregaFinal(arg);
        }
    
        // Checa si -r y -o estan a la vez
        try {
            Banderas.validaBanderas(argumentos);
        } catch (ExcepcionErrorBandera e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    
        Banderas identificador = new Banderas(args);
        boolean reversa = identificador.tieneBanderaAlReves();
        String archivoSalida = null;
        try {
            archivoSalida = identificador.getValorBanderaO();
        } catch (IllegalArgumentException ioe) {
            System.out.println("\nEl argumento de la bandera -o debe ir seguido de un " +
                    "nombre de archivo en el cual escribir.\n" +
                    "Uso: java -jar proyecto2.jar -o <archivo>,\n" + 
                    "ejemplo para más archivos:java -jar target/proyecto2.jar <archivo1> <archivo2> -r -o"+
                    " <prueba2archivos> \n" + 
                                                    "");
            System.exit(1);
        }
    
        // lee las lineas ordena e imprime
        Lista<TextoPlano> lineas = leeLineas(identificador.getNombreArchivos());
        Lista<TextoPlano> ordenadas = ordena(lineas, reversa);
        returnSalida(ordenadas, archivoSalida);
    }
    
}
