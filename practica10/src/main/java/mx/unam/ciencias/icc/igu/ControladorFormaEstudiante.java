package mx.unam.ciencias.icc.igu;

/**
 * Clase abstracta para controladores del contenido de diálogo con formas con
 * datos de estudiantes que se aceptan o rechazan.
 */
public abstract class ControladorFormaEstudiante extends ControladorForma {

    /** El valor del nombre. */
    protected String nombre;
    /** El valor del número de cuenta. */
    protected int cuenta;
    /** El valor del promedio. */
    protected double promedio;
    /** El valor de la edad. */
    protected int edad;

    /**
     * Verifica que el nombre sea válido.
     * @param nombre el nombre a verificar.
     * @return <code>true</code> si el nombre es válido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaNombre(String nombre) {
        // Aquí va su código.
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        if (nombre.length() < 1) {
            return false;
        }
        
        for (char c : nombre.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Verifica que el número de cuenta sea válido.
     * @param cuenta el número de cuenta a verificar.
     * @return <code>true</code> si el número de cuenta es válido;
     *         <code>false</code> en otro caso.
     */
    protected boolean verificaCuenta(String cuenta) {
        // Aquí va su código.
        if (cuenta == null || cuenta.isEmpty()) {
            return false;
        }
    
        for (char c : cuenta.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
    
        try {
            long cuentaLong = Long.parseLong(cuenta);
            return cuentaLong >= 1000000 && cuentaLong <= 999999999;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica que el promedio sea válido.
     * @param promedio el promedio a verificar.
     * @return <code>true</code> si el promedio es válido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaPromedio(String promedio) {
        // Aquí va su código.
        if (promedio == null || promedio.isEmpty()) {
            return false;
        }
        
        try {
            double promedioDouble = Double.parseDouble(promedio);
            return promedioDouble >= 0.0 && promedioDouble <= 10.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica que la edad sea válida.
     * @param edad la edad a verificar.
     * @return <code>true</code> si la edad es válida; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaEdad(String edad) {
        // Aquí va su código.
        try {
            int edadInt = Integer.parseInt(edad);
            return edadInt >= 13 && edadInt <= 99;
        } catch (NumberFormatException e) {
            return false;
        }
        
    }
}