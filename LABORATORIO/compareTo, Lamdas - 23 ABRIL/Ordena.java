import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
/**
 * Clase Comparador.
 * Veremos ejemplos de cómo usar compare en Java.
 * Compare sirve para ordenar una lista.
 */
public class Ordena implements Comparator<Estudiante>{
    @Override
    public int compare(Estudiante e1, Estudiante e2){
        return e1.getNombre().compareTo(e2.getNombre());
    }

    public static void main(String[] args){
        Estudiante e1 = new Estudiante("Edrei", "Tellez", 26);
        Estudiante e2 = new Estudiante("Diego", "Lozano", 19);
        Estudiante e3 = new Estudiante("Armando", "Alvarado", 24);

        List<Estudiante> lista = Arrays.asList(e1, e2, e3);
        lista.sort(new Ordena());
        lista.forEach(System.out::println);
    }
}