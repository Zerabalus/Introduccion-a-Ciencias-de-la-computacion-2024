package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad.
 */
public class Estudiante {

    /* Nombre del estudiante. */
    private String nombre;
    /* Número de cuenta. */
    private int cuenta;
    /* Promedio del estudiante.  aqui había un typo en la palabra promedio */
    private double promedio;
    /* Edad del estudiante.*/
    private int edad;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        this.nombre    = nombre;
        // Aquí va su código.
        this.cuenta    = cuenta; 
        this.promedio  = promedio;
        this.edad      = edad;
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        // Aquí va su código.
        return cuenta;
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        // Aquí va su código.
        this.cuenta = cuenta;
    }
    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        // Aquí va su código.
        return promedio;
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        // Aquí va su código.
        this.promedio = promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        // Aquí va su código.
        return edad;
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        // Aquí va su código.
        this.edad = edad;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
   
    public String toString() {
        return "Nombre   : " + nombre + "\n" +
               "Cuenta   : " + String.format("%09d", cuenta) + "\n" +
               "Promedio : " + String.format("%2.2f", promedio) + "\n" +
               "Edad     : " + edad;
    }

    /**
     * Nos dice si el estudiante recibido es igual al que manda llamar el
     * método.
     * @param estudiante el estudiante con el cual comparar.
     * @return <code>true</code> si el estudiante recibido tiene las mismas
     *         propiedades que el estudiante que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    public boolean equals(Estudiante estudiante) {
        if(estudiante == null)
            return false;
        // Aquí va su código.
        else if(this.toString().equals(estudiante.toString()))
            return true;
        return false;
    }
}
