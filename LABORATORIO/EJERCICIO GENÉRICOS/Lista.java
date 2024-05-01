import java.util.Iterator;    //Iterator para recorrer la lista.
import java.util.ArrayList;   //ArrayList para crear listas.
/**
 * Clase genérica Lista.
 * Para la lista de compras
 */
public class Lista<T>{
    private ArrayList<T> lista = new ArrayList<>();
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
    public static void main(String[] args) {

        // Ropa que compró Ana
        Ropa<String> blusa = new Ropa<>("Blusa", "Mediana", "300", "Rojo", "Zahara");
        Ropa<String> pantalon1 = new Ropa<>( "Pantalon","38",  "450", "Azul", "Levi");
        Ropa<String> pantalon2 = new Ropa<>("Pantalon", "34", "550", "Azul", "Oggi Jeans");
        Ropa<String> camisa = new Ropa<>("Camisa", "Chica", "383", "Blanco", "Hollister");

        // Ropa del segundo cliente para la otra lista
        Ropa<String> pantalon3 = new Ropa<>("Pantalon", "36", "1900", "Negro", "Toskovat");
        Ropa<String> pantalon4 = new Ropa<>("Pantalon", "32", "22650", "Gris", "Loro Piana");
        Ropa<String> camisa1 = new Ropa<>("Camisa","Mediana", "3400", "Blanco", "Calvin Klein");
        Ropa<String> camisa2 = new Ropa<>("Camisa", "Grande", "14800", "Negro", "Yves Saint Laurent");

        // Crea la lista
        Lista<Ropa<String>> listaAna = new Lista<>();
        listaAna.add(blusa);
        listaAna.add(pantalon1);
        listaAna.add(pantalon2);
        listaAna.add(camisa);

        
        //Lista de ana con iteradores
        System.out.println("Lista de compras de Ana:");
        Iterator<Ropa<String>> iteratorAna = listaAna.getElementos().iterator();
        while (iteratorAna.hasNext()) {
            Ropa<String> prendaAna = iteratorAna.next();
            System.out.println(prendaAna.toString());
        }

         Lista<Ropa<String>> listaOtroCliente = new Lista<>();
        listaOtroCliente.add(pantalon3);
        listaOtroCliente.add(pantalon4);
        listaOtroCliente.add(camisa1);
        listaOtroCliente.add(camisa2);

        // foreach
        System.out.println("\nLista de compras del segundo cliente:");
        for (Ropa<String> prenda : listaOtroCliente.getElementos()) {
            System.out.println(prenda.toString());
         }
    }
}