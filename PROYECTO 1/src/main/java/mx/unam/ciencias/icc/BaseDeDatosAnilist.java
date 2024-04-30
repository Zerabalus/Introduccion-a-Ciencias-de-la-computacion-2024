package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de animes.
 */
public class BaseDeDatosAnilist extends BaseDeDatos {

    @Override 
    public Registro creaRegistro() {
        // Aquí va su código.
        return new Anilist (null, null, 0, 0, 0.0);
    }
}
