package mx.unam.ciencias.icc;

public enum CampoAnilist {
//enumeracion de datos
    NOMBRE,
    GENERO,
    CAPITULOS,
    ESTRENO,
    CALIFICACION,

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
            case GENERO: return "# Episodios"; 
            case CAPITULOS : return "Capitulos";
            case ESTRENO: return "Edad";
            case CALIFICACION: return "Calificacion";
            default: throw new IllegalArgumentException(); 
            //default porque funciona con switch, si no existe Illegal argument exception
        }
    }
}
