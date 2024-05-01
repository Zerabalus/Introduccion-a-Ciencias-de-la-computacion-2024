import java.util.List;
import java.util.Arrays;

public class Estudiante{
    private String nombre;
    private String apellido;
    private int edad;

    public Estudiante(String nombre, String apellido, int edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return nombre;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String toString(){
        return "Nombre: " + nombre + " Apellido: " + apellido + " Edad: " + edad + "\n";
    }

    public static void main(String[] args){
        Estudiante e1 = new Estudiante("Edrei", "Tellez", 22);
        Estudiante e2 = new Estudiante("Diego", "Lozano", 19);
        Estudiante e3 = new Estudiante("Armando", "Alvarado", 23);

        List<Estudiante> lista = Arrays.asList(e1, e2, e3);
        System.out.println(lista);
    }
}