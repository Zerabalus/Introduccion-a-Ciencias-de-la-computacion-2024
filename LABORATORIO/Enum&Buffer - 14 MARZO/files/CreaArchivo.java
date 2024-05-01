/**
 * Importamos los paquetes necesarios de Java.
 */
import java.io.File;
import java.io.IOException;
/**
 * Clase CreaArchivo.
 * Creamos un archivo desde cero.
 * Hacemos uso de excepciones.
 */
public class CreaArchivo{
    public static void main(String[] args){
        /**
         * Hacemos uso de excepciones por si ocurre un 
         * error en la creación de nuestro archivo.
         */
        try{
            File fichero = new File("MiArchivo.txt");
            /**
             * El método createNewFile() devuelve un valor booleano.
             * True si ha sido creado y False si ya existe.
             */
            if(fichero.createNewFile()){
                /**
                 * Usamos el método getName() de la clase File para
                 * saber el nombre del archivo o fichero.
                 */
                System.out.println("Tu archivo ha sido creado. " + fichero.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch(IOException ioe){
            System.out.println("Ha ocurrido un error");
            ioe.printStackTrace();
            /**
             * Es un método de la clase throwable de Java que imprime el throwable 
             * junto con otros detalles como el número de línea y el nombre de la 
             * clase donde ocurrió la excepción.
             */
        }
    }
}