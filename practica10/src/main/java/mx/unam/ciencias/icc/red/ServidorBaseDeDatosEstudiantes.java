package mx.unam.ciencias.icc.red;

import java.io.IOException;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosEstudiantes;
import mx.unam.ciencias.icc.CampoEstudiante;
import mx.unam.ciencias.icc.Estudiante;

/**
 * Clase para servidores de bases de datos de estudiantes.
 */
public class ServidorBaseDeDatosEstudiantes
    extends ServidorBaseDeDatos<Estudiante> {

    /**
     * Construye un servidor de base de datos de estudiantes.
     * @param puerto el puerto dónde escuchar por conexiones.
     * @param archivo el archivo en el disco del cual cargar/guardar la base de
     *                datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatosEstudiantes(int puerto, String archivo)
        throws IOException {
        // Aquí va su código.
    }

    /**
     * Crea una base de datos de estudiantes.
     * @return una base de datos de estudiantes.
     */
    @Override public
    BaseDeDatos<Estudiante, CampoEstudiante> creaBaseDeDatos() {
        // Aquí va su código.
    }
}
