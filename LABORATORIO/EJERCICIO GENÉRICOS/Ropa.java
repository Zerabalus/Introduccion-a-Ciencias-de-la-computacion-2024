/**
 * Clase Ropa.
 * Definimos las características de la ropa de la tienda.
 * 
 * @param <T>      se queda a definir.
 * @param elemento el elemento a mostrar.
 */
public class Ropa<T> {

    /**
     * ATRIBUTOS de la ropa.
     */
    /*Tipo de ropa*/
    private T tipo;
    /* Talla */
    private T talla;
    /* Precio */
    private T precio;
    /* Color */
    private T color;
    /* Marca */
    private T marca;

    /**
     * CONSTRUCTOR de la clase ropa.
     * @param tipo   El tipo de ropa que es (camisa, pantalon, etc.)
     * @param talla
     * @param precio
     * @param color
     * @param marca
     */
    public Ropa(T tipo,T talla, T precio, T color, T marca) {
        
        this.tipo = tipo;
        this.talla = talla;
        this.precio = precio;
        this.color = color;
        this.marca = marca;
    }

    /**
     * Método toString().
     * 
     * @return devuelve una representación en cadena de la ropa.
     */

    public String toString() {
        return ("La prenda es "
                + tipo + 
                " marca " +
                marca +
                " talla " +
                talla +
                " color " +
                color +
                " con precio de " +
                precio);
    }

}
