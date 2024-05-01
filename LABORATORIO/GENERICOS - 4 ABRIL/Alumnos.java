/**
 * Clase Alumnos.
 * Definimos las características de un alumno.
 */
public class Alumnos{
    /**
     * ATRIBUTOS de un alumno.
     */
    /**
     * Nombre del alumno.
     */
    private String nombre;
    /**
     * Número de cuenta.
     */
    private int cuenta;
    /**
     * Promedio.
     */
    private double promedio;
    /**
     * CONSTRUCTOR de la clase Alumno.
     * @param nombre el nombre del alumno.
     * @param cuenta número de cuenta del alumno.
     * @param promedio promedio del alumno.
     */
    public Alumnos(String nombre, int cuenta, double promedio){
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.promedio = promedio; 
    }
    /**
     * Método toString().
     * @return devuelve una representación en cadena del alumno.
     */
    public String toString(){
        return "El alumno: " + nombre +
                " con numero de cuenta: " + cuenta +
                " tiene promedio de: " + promedio;
    }
}