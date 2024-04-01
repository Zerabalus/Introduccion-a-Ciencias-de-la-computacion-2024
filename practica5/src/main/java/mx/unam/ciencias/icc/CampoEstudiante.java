package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Estudiante}.
 */
public enum CampoEstudiante {
//enumeracion de datos
    /** El nombre del estudiante. */
    NOMBRE,
    /** El número de cuenta del estudiante. */
    CUENTA,
    /** El promedio del estudiante. */
    PROMEDIO,
    /** La edad del estudiante. */
    EDAD;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override 
    public String toString() {
        // Aquí va su código. como son enumeraciones trabajamos por casos
        switch(this){
            case NOMBRE: return "Nombre";
            case CUENTA: return "# Cuenta"; //#por el num de cuenta
            case PROMEDIO : return "Promedio";
            case EDAD: return "Edad";
            default: throw new IllegalArgumentException(); //default porque funciona con switch, si no existe Illegal argument exception
        }
    }
}
