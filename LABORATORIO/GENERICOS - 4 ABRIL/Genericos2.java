/**
 * Clase genérica.
 * Ejemplo de como creamos objetos genéricos
 * con un cierto tipo de dato.
 */
public class Genericos2<T> {
    /**
     * Declaramos un objeto de tipo T.
     */
    T obj;
    /**
     * Constructor único.
     */
    Genericos2(T obj){ 
        this.obj = obj; 
    }
    /**
     * Método que nos regresa el tipo de Objeto.
     */
    public T getObject(){ 
        return this.obj; 
    }
    /**
     * Método main.
     */
    public static void main(String[] args){
        /**
         * Creamos una instancia de tipo Integer.
         */
        Genericos2<Integer> g1 = new Genericos2<>(1000);
        System.out.println(g1.getObject());
 
        /**
         * Creamos una instancia de tipo String.
         */
        Genericos2<String> g2 = new Genericos2<String>("ICC");
        System.out.println(g2.getObject());
    }
}