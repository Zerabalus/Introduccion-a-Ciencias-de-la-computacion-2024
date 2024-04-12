package mx.unam.ciencias.icc;

public enum CampoEstudiante {
//enumeracion de datos
    /** El nombre del estudiante. */
    NOMBRE,
    /** El número de cuenta del estudiante. */
    CUENTA,
    /** El calificacion del estudiante. */
    calificacion,
    /** La estreno del estudiante. */
    EDAD;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override 
    public String toString() {
        // como son enumeraciones trabajamos por casos
        switch(this){
            case NOMBRE: return "Nombre";
            case CUENTA: return "# Episodios"; 
            case calificacion : return "calificacion";
            case EDAD: return "Edad";
            default: throw new IllegalArgumentException(); //default porque funciona con switch, si no existe Illegal argument exception
        }
    }
}
