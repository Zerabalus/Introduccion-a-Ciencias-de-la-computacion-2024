/**
 * Importamos las clases necesarias.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Clase que lee un archivo.
 * Usamos BufferedReader.
 */
public class EjemploBuffer{
    public static void main(String[] args) throws Exception{
        BufferedReader br = null;
        try{
            String linea;
            br = new BufferedReader(new FileReader("hola.txt"));
            while((linea = br.readLine()) != null){
                System.out.println(linea);
            }
            br.close();
        } catch (IOException ioe){
            System.out.println("Ocurrió un error");
            ioe.printStackTrace();
        }
    }    
}