/**
 * Importamos ArrayList para crear listas. 
 */
import java.util.ArrayList;
/**
 * Clase genérica Lista.
 * Vamos a almacenar datos en nuestra lista.
 */
public class Lista<T>{
    /**
     * Declaramos a nuestro atributo.
     * En este caso, una lista.
     */
    private ArrayList<T> lista = new ArrayList<T>();
    /**
     * Método que agrega elementos a la lista.
     */
    public void add(T elemento){
        lista.add(elemento);
    }
    /**
     * Método que nos regresa el contenido de la lista.
     */
    public ArrayList<T> getElementos(){
        return lista;
    }

    /**
     * Método main.
     */
    public static void main(String[] args){
        Lista<Chocolate> listaC = new Lista<>();
        listaC.add(new Chocolate("Ferrero", 60.0));
        System.out.println(listaC.getElementos());

        Lista<Alumnos> listaA = new Lista<>();
        listaA.add(new Alumnos("Luis", 313284643, 9.0));
        System.out.println(listaA.getElementos());
    }
}