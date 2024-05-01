/**
 * Importamos las clases necesarias.
 */
import java.io.FileWriter;
import java.io.IOException;
/**
 * Clase Escribe.
 * Clase que escribe en un archivo que se ha creado
 * con anterioridad.
 */
public class Escribe{
    public static void main(String[] args){
        /**
         * Hacemos uso de excepciones.
         */
        try{
            FileWriter escribir = new FileWriter("MiArchivo.txt");
            escribir.write("Hola mundo!" + "\n");
            escribir.close();
            System.out.println("Listo.");
        } catch(IOException ioe){
            System.out.println("Ocurrió un error");
            ioe.printStackTrace();
        }
    }
}