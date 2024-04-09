/**
 * Clase que nos dice en que paquetería
 * de Java se encuentran ciertos tipos
 * de datos. 
 */
public class Genericos1{
    /**
     * Método genérico.
     * Nos muestra el tipo de dato.
     * @param <T> se queda a definir.
     * @param elemento el elemento a mostrar.
     */
    public static <T> void muestra(T elemento){
        System.out.println(elemento.getClass().getName()
                           + " = " + elemento);
    }
 
    /**
     * Método main.
     */
    public static void main(String[] args){
        /**
         * Llamamos al método genérico con un tipo de dato.
         * Integer.
         */
        muestra(2023);
        /**
         * Llamamos al método genérico con un tipo de dato.
         * String.
         */
        muestra("ICC");
        /**
         * Llamamos al método genérico con un tipo de dato.
         * Double.
         */
        muestra(9.0);
    }
}