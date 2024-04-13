package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.CampoEstudiante;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la enumeración {@link CampoEstudiante}.
 */
public class TestCampoEstudiante {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoEstudiante#toString}.
     */
    @Test public void testToString() {
        String s = CampoEstudiante.NOMBRE.toString();
        Assert.assertTrue(s.equals("Nombre"));
        String s1 = CampoEstudiante.Genero.toString();
        Assert.assertTrue(s1.equals("Genero"));
        s = CampoEstudiante.CUENTA.toString();
        Assert.assertTrue(s.equals("# Episodios"));
        s = CampoEstudiante.calificacion.toString();
        Assert.assertTrue(s.equals("calificacion"));
        s = CampoEstudiante.EDAD.toString();
        Assert.assertTrue(s.equals("Edad"));
    }
}
