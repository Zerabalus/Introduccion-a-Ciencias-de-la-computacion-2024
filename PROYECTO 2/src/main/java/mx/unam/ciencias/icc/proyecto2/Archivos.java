package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.Lista; //Importa la clase lista
import mx.unam.ciencias.icc.ExcepcionArchivoInvalido; // Importa la excepcion archivo invalido

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * Clase que maneja la lectura y escritura de archivos.
 */

public class Archivos {

    private Archivos() {  

    }
    /**
     * Obtiene los BufferedReader de los archivos de entrada.
     *
     * @param listaNombres Lista de nombres de archivos de entrada.
     * @return BufferedReader para los archivos.
     * @throws ExcepcionArchivoInvalido si hay un problema al abrir un archivo.
     */
    public static Lista<BufferedReader> getArchivosEntrada(Lista<String> listaNombres) throws ExcepcionArchivoInvalido {
        Lista<BufferedReader> entradas = new Lista<BufferedReader>();

        // Si no hay archivos de entrada especificados, usa la entrada
        if (listaNombres.esVacia()) {
            entradas.agregaFinal(new BufferedReader(new InputStreamReader(System.in)));
            return entradas;
        }

        // Intenta abrir cada archivo de entrada y agrega sus BufferedReader a lista.
        for (String nombres : listaNombres) {
            try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(nombres)));

                // Crea un objeto File para el archivo actual.
                File archivo = new File(listaNombres.getUltimo());
                // Verifica si el archivo no existe.
                if (!archivo.exists()) {
                    // Si el archivo no existe, lo crea.
                    archivo.createNewFile();
                }
                entradas.agregaFinal(entrada);

                // Cierra los archivos abiertos si ocurre un error y lanza una excepción.
            } catch (IOException ioe) {
                cierraBufferArchivos(entradas);
                throw new ExcepcionArchivoInvalido("Hubo un problema con el archivo");
            }
        }

        return entradas;
    }

    /**
     * Lee y vuelve texto plano las líneas de los BufferedReader 
     *
     * @param entradas Lista de BufferedReader de se leen las líneas
     * @return Lista de TextoPlano con las líneas planas
     * @throws ExcepcionArchivoInvalido si hay un error al leer las líneas de entrada
     */

    public static Lista<TextoPlano> modificaLineasEntrada(Lista<BufferedReader> entradas)
            throws IOException {
        Lista<TextoPlano> lineasModificadas = new Lista<TextoPlano>();

        String linea;
        // Lee las líneas de cada BufferedReader y las agrega a la lista como
        // Texto Plano.
        for (BufferedReader input : entradas)

            while ((linea = input.readLine()) != null)
                lineasModificadas.agregaFinal(new TextoPlano(linea));

        return lineasModificadas;
    }

    /**
     * Cierra los BufferedReader de la lista proporcionada.
     *
     * @param entradas Lista de BufferedReader que se deben cerrar.
     */

     public static void cierraBufferArchivos(Lista<BufferedReader> entradas) {
        if (entradas == null)
            return;
        
        for (BufferedReader entrada : entradas) {

        // Intenta cerrar los BufferedReader y atrapa posibles excepciones. 

        try {
                entrada.close();
            } catch (IOException ioe) {}
            
        }
    }

    /**
     * Guarda las líneas planas en un archivo de salida.
     *
     * @param nombres Nombre del archivo de salida.
     * @param lineas Lista de TextoPlano que se debe guardar.
     * @throws ExcepcionArchivoInvalido si hay un problema al escribir en el archivo de salida.
     */

     public static void guarda(String nombres, Lista<TextoPlano> lineas) throws ExcepcionArchivoInvalido {
        BufferedWriter salida = null;
        try {
            salida = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(nombres)));
            for (TextoPlano linea : lineas)
                salida.write(linea.toString() + "\n");
        } catch (IOException ioe) {
            throw new ExcepcionArchivoInvalido("Se produjo un error al intentar escribir en el archivo de salida.");
        } finally {
            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
