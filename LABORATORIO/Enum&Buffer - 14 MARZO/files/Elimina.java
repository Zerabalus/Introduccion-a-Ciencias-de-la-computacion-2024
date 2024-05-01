/**
 * Importamos las clases necesarias.
 */
import java.io.File;
/**
 * Clase elimina.
 * Elimina un archivo.
 */
public class Elimina{
    public static void main(String[] args){
        File archivo = new File("MiArchivo.txt");
        if(archivo.delete()){
            System.out.println("El archivo " + archivo.getName() + " ha sido eliminado");
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}