import java.util.Arrays;
import java.util.List;
import java.util.Collections;
/**
 * Clase Lambdas.
 * Ejemplo de uso de lambdas para ordenamientos.
 */
public class Lambdas{
    public static void main(String[] args){
        Estudiante e1 = new Estudiante("Edrei", "Tellez", 22);
        Estudiante e2 = new Estudiante("Diego", "Lozano", 19);
        Estudiante e3 = new Estudiante("Armando", "Alvarado", 24);
        Estudiante e4 = new Estudiante("X", "Alcantara", 20);


        List<Estudiante> lista = Arrays.asList(e1, e2, e3, e4);
        /**
         * Aquí usamos las lambdas.
         * Ordenamos por el parámetro nombre.
         */
        //lista.sort((ea,eb)->ea.getNombre().compareTo(eb.getNombre()));
        //lista.forEach(System.out::println);
        /**
         * Ordenamos por apellido.
         */
        // lista.sort((ea,eb)->ea.getApellido().compareTo(eb.getApellido()));
        // lista.forEach(System.out::println);
        /**
         * ¿Cómo ordenamos por edad?
         */
        lista.sort((ea,eb)->ea.getEdad().compare(eb.getEdad()));
        lista.forEach(System.out::println);
        
    }
}