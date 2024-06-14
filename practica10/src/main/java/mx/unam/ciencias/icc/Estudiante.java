package mx.unam.ciencias.icc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private final StringProperty nombre;
    /* Número de cuenta. */
    private final IntegerProperty cuenta;
    /* Pormedio del estudiante. */
    private final DoubleProperty promedio;
    /* Edad del estudiante.*/
    private final IntegerProperty edad;

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
        this.nombre = new SimpleStringProperty(nombre);
        // Aquí va su código.
        this.cuenta = new SimpleIntegerProperty(cuenta); 
        this.promedio = new SimpleDoubleProperty(promedio);
        this.edad = new SimpleIntegerProperty(edad);
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty nombreProperty() {
        return this.nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        // Aquí va su código.
        // Para obtener el valor property, usamos get(), 
        //si solo es cuenta no nos va a dar el valor sino la referencia al objeto.
        return cuenta.get(); 
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        // Aquí va su código.
        // Verificamos si el valor de cuenta es negativo
        if (cuenta < 0) {
            // Si es negativo, lanzamos una excepción IllegalArgumentException 
            throw new IllegalArgumentException("El número de cuenta no puede ser negativo.");
        }
        // Si el valor es válido 
        this.cuenta.set(cuenta);
    }

    /**
     * Regresa la propiedad del número de cuenta.
     * @return la propiedad del número de cuenta.
     */
    public IntegerProperty cuentaProperty() {
        // Aquí va su código.
        return cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        // Aquí va su código.
        return promedio.get();
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        // Aquí va su código.
         // Verificamos si el valor de promedio está fuera del rango
         if (promedio < 0.0 || promedio > 10.0) {
            // Si el valor es inválido, lanzamos una excepción IllegalArgumentException 
            throw new IllegalArgumentException();
        }
        // Si el valor es válido
        this.promedio.set(promedio);
    }

    /**
     * Regresa la propiedad del promedio.
     * @return la propiedad del promedio.
     */
    public DoubleProperty promedioProperty() {
        // Aquí va su código.
        return promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        // Aquí va su código.
        return edad.get();
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        // Aquí va su código.
        // Verificamos si el valor de edad es menor a 13, nuetsro rango esta entre 13 y 99
        if (edad < 13) {
            // Si el valor no cuadra lanzamos una excepción IllegalArgumentException 
            throw new IllegalArgumentException();
        }
        // Si el valor es válido
        this.edad.set(edad);
    }

    /**
     * Regresa la propiedad de la edad.
     * @return la propiedad de la edad.
     */
    public IntegerProperty edadProperty() {
        // Aquí va su código.
        return edad;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        // Aquí va su código.
        return String.format(
                "Nombre   : %s\n" +
                "Cuenta   : %09d\n" +
                "Promedio : %2.2f\n" +
                "Edad     : %d",
                getNombre(), getCuenta(), getPromedio(), getEdad());

        //nombre, cuenta, promedio, y edad son propiedades y no variables simples
        //por eso uso get por lo de JAVA FX
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
        return getNombre().equals(estudiante.getNombre()) && getCuenta() == estudiante.getCuenta()
                && getPromedio() == estudiante.getPromedio() && getEdad() == estudiante.getEdad();
    }

    /**
     * Regresa el estudiante seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deseria}.
     * @return la seriación del estudiante en una línea de texto.
     */
    @Override public String seria() {
        // Aquí va su código.
        return String.format("%s\t%d\t%2.2f\t%d\n", getNombre(), getCuenta(), getPromedio(), getEdad());
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
        if (linea == null)
            throw new ExcepcionLineaInvalida();
        // campos es un arreglo de 4 elementos
        String[] campos = linea.trim().split("\t");
        // t es un divisor
        // no es una seriación valida de un estudiante

        try {
            nombre.set (campos[0]);
            cuenta.set (Integer.parseInt(campos[1]));
            promedio.set (Double.parseDouble(campos[2]));
            edad.set (Integer.parseInt(campos[3].replace(
                "\n", "").replace("\r", "")));
            
            //lo cambie porque hacer una asignacion directa a un primitivo o 
            // a cadenas no funciona xon propertys/JavaFX
                
        } catch (Exception e) {
            // excepcion es un objeto (importante, las excepciones son objetps) de tipo
            // ExcepcionLineaInvalida
            throw new ExcepcionLineaInvalida();
        }
    }

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    public void actualiza(Estudiante estudiante) {
        // Aquí va su código.
        if (estudiante == null)
            throw new IllegalArgumentException();

        nombre.set(estudiante.nombre.get());
        cuenta.set(estudiante.cuenta.get());
        promedio.set(estudiante.promedio.get());
        edad.set(estudiante.edad.get());
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
    @Override public boolean casa(CampoEstudiante campo, Object valor) {
        // Aquí va su código.
        if (campo == null)
            throw new IllegalArgumentException();

        if (valor == null)
            return false;

        switch (campo) {
            case NOMBRE:
                if (!(valor instanceof String)) {
                    return false;
                // Si el valor es una cadena vacía, devolvemos false
                } else if (valor.equals("")) {
                    return false;
                // Devuelve true si el nombre contiene el valor como subcadena
                } else {
                    return getNombre().contains((String) valor);
                }
            case CUENTA:
                if (!(valor instanceof Integer)) {
                    return false;
                } else if ((Integer) valor < 0) {
                    return false;
                }
                return getCuenta() >= (Integer) valor;
            case PROMEDIO:
                if (!(valor instanceof Double))
                    return false;
                return getPromedio() >= (Double) valor;
            case EDAD:
                if (!(valor instanceof Integer))
                    return false;
                return getEdad() >= (Integer) valor;
            default:
                return false;
        }
    }
}
