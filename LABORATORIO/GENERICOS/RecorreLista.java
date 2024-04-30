import java.util.ArrayList;
import java.util.List;

public class RecorreLista{
    /**
     * Método que recorre una lista
     * Imprime los elementos de la lista.
     * Usamos una estructura que se llama foreach.
     */
    public static void muestra(List<?> l){
        for(Object elemento : l){
            System.out.println(elemento);
        }
    }
    public static void main(String[] args){
        List<String> lista = new ArrayList<String>();
        lista.add("Nando");
        lista.add("Neri");
        lista.add("Lalo");
        lista.add("Diego");
        lista.add("Dona");
        lista.add("Alondra");
        lista.add("Demian");
        lista.add("Adrián");
        lista.add("Edrei");
        lista.add("Memo" + "\n");
        muestra(lista);

        List<Integer> lista2 = new ArrayList<>();
        lista2.add(2);
        lista2.add(3);
        lista2.add(123);
        muestra(lista2);
    }
}