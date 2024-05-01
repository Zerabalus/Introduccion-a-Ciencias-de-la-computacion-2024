/**
 * Importamos las clases necesarias.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Clase Lee.
 * Lee un archivo con el uso de la clase Scanner.
 */
public class Lee{
    public static void main(String[] args){
        try{
            File fichero = new File("MiArchivo.txt");
            Scanner sc = new Scanner(fichero);
            /**
             * hasNextLine() método de la clase Scanner.
             * True si hay otra línea en la entrada del scanner. 
             * Puede bloquearse mientras espera una entrada. 
             * El scanner no avanza más allá de ninguna entrada.
             */
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                System.out.println(linea);
            }
            sc.close();
            /**
             * Pedimos información de nuestro archivo.
             */
            if(fichero.exists()){
                System.out.println("Nombre: " + fichero.getName());
                System.out.println("Ruta: " + fichero.getPath());
                System.out.println("¿Puedo leerlo?: " + fichero.canRead());
                System.out.println("¿Puedo escribir en él?: " + fichero.canWrite());
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch(FileNotFoundException fne){
            System.out.println("Ha ocurrido un error.");
            fne.printStackTrace();
        }
    }
}