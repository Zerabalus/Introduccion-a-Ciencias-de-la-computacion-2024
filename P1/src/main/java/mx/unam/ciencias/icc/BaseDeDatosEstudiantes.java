package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override 
    public Registro creaRegistro() {
        // Aquí va su código.
        return new Anilist (null, 0, 0.0, 0);
    }
}
