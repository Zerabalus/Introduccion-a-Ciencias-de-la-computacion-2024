package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private String nombre;
    /* Número de cuenta. */
    private int cuenta;
    /* Pormedio del estudiante. */
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
        // Aquí va su código.
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.promedio = promedio;
        this.edad = edad;
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        // Aquí va su código.
        return nombre;
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        // Aquí va su código.
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
        if (cuenta >= 0) {
            this.cuenta = cuenta;
        } else {
            throw new IllegalArgumentException("El No. de cuenta no puede ser negativo.");
        }
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
        if (promedio >= 0) {
            this.promedio = promedio;
        } else {
            throw new IllegalArgumentException("El promedio no puede ser negativo.");
        } 
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
        if (edad >= 0) {
            this.edad = edad;
        } else {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        // Aquí va su código.
        String cadena = String.format(
                "Nombre   : %s\n" +
                "Cuenta   : %09d\n" +
                "Promedio : %2.2f\n" +
                "Edad     : %d",
                nombre, cuenta, promedio, edad);
        return cadena;

    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Estudiante))
            return false;
        Estudiante estudiante = (Estudiante)objeto;
        // Aquí va su código.
        if(this.toString().equals(estudiante.toString()))
            return true;
        return false;
    }

    /**
     * Regresa el estudiante seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deseria}.
     * @return la seriación del estudiante en una línea de texto.
     */
    @Override public String seria() {
        // Aquí va su código.
        return(String.format("%s\t%d\t%2.2f\t%d\n",nombre,cuenta,promedio,edad));
    }

    /**
     * Deseria una línea de texto en las propiedades del estudiante. La
     * seriación producida por el método {@link Estudiante#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un estudiante.
     */
    @Override public void deseria(String linea) {
        // Aquí va su código.
         // por si es nula, vacía
         if(linea == null)
         throw new ExcepcionLineaInvalida();
     // campos es un arreglo de 4 elementos
     String[] campos = linea.trim().split("\t");
     // t es un divisor
     // no es una seriación valida de un estudiante
     try{
         nombre = (campos[0]);
         cuenta = Integer.parseInt(campos[1]);
         promedio = Double.parseDouble(campos[2]);
         edad = Integer.parseInt(campos[3]);
     } catch (Exception e) {
         // excepcion es un objeto (importante, las excepciones son objetps) de tipo ExcepcionLineaInvalida
         throw new ExcepcionLineaInvalida();
     }
    }

    /**
     * Nos dice si el estudiante casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override
    public boolean casa(CampoEstudiante campo, Object valor) {
        // Aquí va su código.
        if (!(campo instanceof CampoEstudiante))
            throw new IllegalArgumentException();
        CampoEstudiante c = (CampoEstudiante) campo;
        switch (c) {
            case NOMBRE:
                return casaNombre(valor);
            case CUENTA:
                return casaCuenta(valor);
            case EDAD:
                return casaEdad(valor);
            case PROMEDIO:
                return casaPromedio(valor);
            default:
                return false;
        }
    }

    private boolean casaNombre(Object o) {
        if (!(o instanceof String))
            return false;
        String v = (String) o;
        if (v.isEmpty())
            return false;
        return nombre.indexOf(v) != -1;
    }

    private boolean casaCuenta(Object o) {
        if (!(o instanceof Integer))
            return false;
        Integer v = (Integer) o;
        return cuenta >= v.intValue();
    }

    private boolean casaEdad(Object o) {
        if (!(o instanceof Integer))
            return false;
        Integer v = (Integer) o;
        return edad >= v.intValue();
    }

    private boolean casaPromedio(Object o) {
        if (!(o instanceof Double))
            return false;
        Double v = (Double) o;
        return promedio >= v.doubleValue();
    }


    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    @Override public void actualiza(Estudiante estudiante) {
        // Aquí va su código.
        if (estudiante == null)
            throw new IllegalArgumentException();
        nombre = estudiante.getNombre();
        cuenta = estudiante.getCuenta();
        promedio = estudiante.getPromedio();
        edad = estudiante.getEdad();
    }
}
