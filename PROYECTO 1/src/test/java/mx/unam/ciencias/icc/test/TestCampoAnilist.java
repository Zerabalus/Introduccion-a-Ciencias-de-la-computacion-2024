package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.CampoAnilist;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la enumeración {@link CampoAnilist}.
 */
public class TestCampoAnilist {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    
    @Test public void testToString() {
        String s = CampoAnilist.NOMBRE.toString();
        Assert.assertTrue(s.equals("Nombre"));
        String s1 = CampoAnilist.GENERO.toString();
        Assert.assertTrue(s1.equals("Genero"));
        s = CampoAnilist.CAPITULOS.toString();
        Assert.assertTrue(s.equals("Capitulos"));
        s = CampoAnilist.ESTRENO.toString();
        Assert.assertTrue(s.equals("Estreno"));
        s = CampoAnilist.CALIFICACION.toString();
        Assert.assertTrue(s.equals("Calificacion"));
    }
}
