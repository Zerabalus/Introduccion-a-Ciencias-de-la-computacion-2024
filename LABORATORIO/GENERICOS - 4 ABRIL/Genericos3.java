/**
 * Clase genérica.
 * Ejemplo de como crear objetos con dos tipos
 * de datos.
 */
public class Genericos3<T, U>{
    /**
     * Objeto de tipo T.
     */
    T t1;
    /**
     * Objeto de tipo U.
     */
    U u1;  
    /**
     * Constructor único.
     */
    public Genericos3(T t1, U u1){
        this.t1 = t1;
        this.u1 = u1;
    }
    /**
     * Método que imprime el tipo de los objetos.
     */
    public void print(){
        System.out.println(t1);
        System.out.println(u1);
    }
    /**
     * Método main.
     */
    public static void main (String[] args){
        Genericos3 <String, Integer> g1 = 
            new Genericos3<String, Integer>("ICC", 2023);
 
        g1.print();
    }
}