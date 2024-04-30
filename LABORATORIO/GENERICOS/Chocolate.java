/**
 * Clase Chocolate.
 * Almacena datos del chocolate como marca y precio.
 */
public class Chocolate{
    /**
     * ATRIBUTOS de un chocolate.
     */
    /**
     * Marca del chocolate.
     */
    private String marca;
    /**
     * Precio del chocolate.
     */
    private double precio;
    /**
     * CONSTRUCTOR de la clase Chocolate.
     * @param marca la marca del chocolate.
     * @param precio el precio del chocolate.
     */
    public Chocolate(String marca, double precio){
        this.marca = marca;
        this.precio = precio;
    }
    /**
     * Método toString()
     * @return devuelve una representación en cadena de un chocolate.
     */
    public String toString(){
        return "La marca es: " + marca + " con precio de: " + precio;
    }
}