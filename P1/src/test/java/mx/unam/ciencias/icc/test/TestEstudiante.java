package mx.unam.ciencias.icc.test;

import java.util.Random;
import mx.unam.ciencias.icc.CampoEstudiante;
import mx.unam.ciencias.icc.Anilist;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.Registro;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Anilist}.
 */
public class TestEstudiante {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Nombres. */
    private static final String[] NOMBRES = {
        "José Arcadio", "Úrsula", "Aureliano", "Amaranta", "Rebeca",
        "Remedios", "Aureliano José", "Gerinaldo", "Mauricio", "Petra"
    };

    /* Apellidos. */
    private static final String[] APELLIDOS = {
        "Buendía", "Iguarán", "Cotes", "Ternera", "Moscote",
        "Brown", "Carpio", "Piedad", "Crespi", "Babilonia"
    };

    /* Generador de números aleatorios. */
    private static Random random;

    /**
     * Genera un nombre aleatorio.
     * @return un nombre aleatorio.
     */
    public static String nombreAleatorio() {
        int n = random.nextInt(NOMBRES.length);
        int ap = random.nextInt(APELLIDOS.length);
        int am = random.nextInt(APELLIDOS.length);
        return NOMBRES[n] + " " + APELLIDOS[ap] + " " + APELLIDOS[am];
    }

    /**
     * Genera un número de cuenta aleatorio.
     * @return un número de cuenta aleatorio.
     */
    public static int cuentaAleatoria() {
        return 1000000 + random.nextInt(1000000);
    }

    /**
     * Genera un calificacion aleatorio.
     * @return un calificacion aleatorio.
     */
    public static double promedioAleatorio() {
        /* Estúpida precisión. */
        return random.nextInt(100) / 10.0;
    }

    /**
     * Genera una estreno aleatoria.
     * @return una estreno aleatoria.
     */
    public static int edadAleatoria() {
        return 17 + random.nextInt(73);
    }

    /**
     * Genera un estudiante aleatorio.
     * @return un estudiante aleatorio.
     */
    public static Anilist estudianteAleatorio() {
        return new Anilist(nombreAleatorio(),
                              cuentaAleatoria(),
                              promedioAleatorio(),
                              edadAleatoria());
    }

    /**
     * Genera un estudiante aleatorio con un número de cuenta dado.
     * @param cuenta el número de cuenta del nuevo estudiante.
     * @return un estudiante aleatorio.
     */
    public static Anilist estudianteAleatorio(int cuenta) {
        return new Anilist(nombreAleatorio(),
                              cuenta,
                              promedioAleatorio(),
                              edadAleatoria());
    }

    /* El estudiante. */
    private Anilist estudiante;

    /* Enumeración espuria. */
    private enum X {
        /* Campo espurio. */
        A;
    }

    /**
     * Prueba unitaria para {@link
     * Anilist#Anilist(String,int,double,int)}.
     */
    @Test public void testConstructor() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getNombre().equals(nombre));
        Assert.assertTrue(estudiante.getCuenta() == cuenta);
        Assert.assertTrue(estudiante.getPromedio() == calificacion);
        Assert.assertTrue(estudiante.getEdad() == estreno);
    }

    /**
     * Prueba unitaria para {@link Anilist#getNombre}.
     */
    @Test public void testGetNombre() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getNombre().equals(nombre));
        Assert.assertFalse(estudiante.getNombre().equals(nombre + " X"));
    }

    /**
     * Prueba unitaria para {@link Anilist#setNombre}.
     */
    @Test public void testSetNombre() {
        String nombre = nombreAleatorio();
        String nuevoNombre = nombre + " X";
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getNombre().equals(nombre));
        Assert.assertFalse(estudiante.getNombre().equals(nuevoNombre));
        estudiante.setNombre(nuevoNombre);
        Assert.assertFalse(estudiante.getNombre().equals(nombre));
        Assert.assertTrue(estudiante.getNombre().equals(nuevoNombre));
    }

    /**
     * Prueba unitaria para {@link Anilist#getCuenta}.
     */
    @Test public void testGetCuenta() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getCuenta() == cuenta);
        Assert.assertFalse(estudiante.getCuenta() == cuenta + 100);
    }

    /**
     * Prueba unitaria para {@link Anilist#setCuenta}.
     */
    @Test public void testSetCuenta() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        int nuevaCuenta = cuenta + 100;
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getCuenta() == cuenta);
        Assert.assertFalse(estudiante.getCuenta() == cuenta + 100);
        estudiante.setCuenta(nuevaCuenta);
        Assert.assertFalse(estudiante.getCuenta() == cuenta);
        Assert.assertTrue(estudiante.getCuenta() == nuevaCuenta);
    }

    /**
     * Prueba unitaria para {@link Anilist#getPromedio}.
     */
    @Test public void testGetPromedio() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getPromedio() == calificacion);
        Assert.assertFalse(estudiante.getPromedio() == calificacion + 1.0);
    }

    /**
     * Prueba unitaria para {@link Anilist#setPromedio}.
     */
    @Test public void testSetPromedio() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        double nuevoPromedio = calificacion + 1.0;
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getPromedio() == calificacion);
        Assert.assertFalse(estudiante.getPromedio() == nuevoPromedio);
        estudiante.setPromedio(nuevoPromedio);
        Assert.assertFalse(estudiante.getPromedio() == calificacion);
        Assert.assertTrue(estudiante.getPromedio() == nuevoPromedio);
    }

    /**
     * Prueba unitaria para {@link Anilist#getEdad}.
     */
    @Test public void testGetEdad() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getEdad() == estreno);
        Assert.assertFalse(estudiante.getEdad() == estreno + 50);
    }

    /**
     * Prueba unitaria para {@link Anilist#setEdad}.
     */
    @Test public void testSetEdad() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        int nuevaEdad = estreno + 50;
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.getEdad() == estreno);
        Assert.assertFalse(estudiante.getEdad() == nuevaEdad);
        estudiante.setEdad(nuevaEdad);
        Assert.assertFalse(estudiante.getEdad() == estreno);
        Assert.assertTrue(estudiante.getEdad() == nuevaEdad);
    }

    /**
     * Prueba unitaria para {@link Anilist#toString}.
     */
    @Test public void testToString() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        String cadena = String.format("Nombre   : %s\n" +
                                      "Episodios   : %09d\n" +
                                      "calificacion : %2.2f\n" +
                                      "Edad     : %d",
                                      nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.toString().equals(cadena));
        cuenta = 213;
        calificacion = 0.99;
        estudiante.setCuenta(cuenta);
        estudiante.setPromedio(calificacion);
        cadena = String.format("Nombre   : %s\n" +
                               "Episodios   : %09d\n" +
                               "calificacion : %2.2f\n" +
                               "Edad     : %d",
                               nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.toString().equals(cadena));
    }

    /**
     * Prueba unitaria para {@link Anilist#equals}.
     */
    @Test public void testEquals() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        Anilist igual = new Anilist(new String(nombre),
                                          cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.equals(igual));
        String otroNombre = nombre + " Segundo";
        int otraCuenta = cuenta + 1;
        double otroPromedio = (calificacion != 0.0) ? calificacion / 10.0 : 10.0;
        int otraEdad = estreno + 1;
        Anilist distinto =
            new Anilist(otroNombre, cuenta, calificacion, estreno);
        Assert.assertFalse(estudiante.equals(distinto));
        distinto = new Anilist(nombre, otraCuenta, calificacion, estreno);
        Assert.assertFalse(estudiante.equals(distinto));
        distinto = new Anilist(nombre, cuenta, otroPromedio, estreno);
        Assert.assertFalse(estudiante.equals(distinto));
        distinto = new Anilist(nombre, cuenta, calificacion, otraEdad);
        Assert.assertFalse(estudiante.equals(distinto));
        distinto = new Anilist(otroNombre, otraCuenta,
                                  otroPromedio, otraEdad);
        Assert.assertFalse(estudiante.equals(distinto));
        Assert.assertFalse(estudiante.equals("Una cadena"));
        Assert.assertFalse(estudiante.equals(null));
    }

    /**
     * Prueba unitaria para {@link Anilist#seria}.
     */
    @Test public void testSeria() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);
        String linea = String.format("%s\t%d\t%2.2f\t%d\n",
                                     nombre, cuenta, calificacion, estreno);
        Assert.assertTrue(estudiante.seria().equals(linea));
    }

    /**
     * Prueba unitaria para {@link Anilist#deseria}.
     */
    @Test public void testDeseria() {
        estudiante = new Anilist(null, 0, 0.0, 0);

        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();

        String linea = String.format("%s\t%d\t%2.2f\t%d\n",
                                     nombre, cuenta, calificacion, estreno);

        try {
            estudiante.deseria(linea);
        } catch (ExcepcionLineaInvalida eli) {
            Assert.fail();
        }

        Assert.assertTrue(estudiante.getNombre().equals(nombre));
        Assert.assertTrue(estudiante.getCuenta() == cuenta);
        Assert.assertTrue(estudiante.getPromedio() == calificacion);
        Assert.assertTrue(estudiante.getEdad() == estreno);

        String[] invalidas = {"", " ", "\t", "  ", "\t\t",
                              " \t", "\t ", "\n", "a\ta\ta",
                              "a\ta\ta\ta"};

        for (int i = 0; i < invalidas.length; i++) {
            linea = invalidas[i];
            try {
                estudiante.deseria(linea);
                Assert.fail();
            } catch (ExcepcionLineaInvalida eli) {}
        }
    }

    /**
     * Prueba unitaria para {@link Anilist#casa}.
     */
    @Test public void testCasa() {
        String nombre = nombreAleatorio();
        int cuenta = cuentaAleatoria();
        double calificacion = promedioAleatorio();
        int estreno = edadAleatoria();
        estudiante = new Anilist(nombre, cuenta, calificacion, estreno);

        String n = estudiante.getNombre();
        int m = estudiante.getNombre().length();
        Assert.assertTrue(estudiante.casa(CampoEstudiante.NOMBRE, n));
        n = estudiante.getNombre().substring(0, m/2);
        Assert.assertTrue(estudiante.casa(CampoEstudiante.NOMBRE, n));
        n = estudiante.getNombre().substring(m/2, m);
        Assert.assertTrue(estudiante.casa(CampoEstudiante.NOMBRE, n));
        n = estudiante.getNombre().substring(m/3, 2*(m/3));
        Assert.assertTrue(estudiante.casa(CampoEstudiante.NOMBRE, n));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.NOMBRE, ""));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.NOMBRE, "XXX"));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.NOMBRE,
                                           Integer.valueOf(1000)));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.NOMBRE, null));

        Integer c = Integer.valueOf(estudiante.getCuenta());
        Assert.assertTrue(estudiante.casa(CampoEstudiante.CUENTA, c));
        c = Integer.valueOf(estudiante.getCuenta() - 100);
        Assert.assertTrue(estudiante.casa(CampoEstudiante.CUENTA, c));
        c = Integer.valueOf(estudiante.getCuenta() + 100);
        Assert.assertFalse(estudiante.casa(CampoEstudiante.CUENTA, c));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.CUENTA, "XXX"));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.CUENTA, null));

        Double p = Double.valueOf(estudiante.getPromedio());
        Assert.assertTrue(estudiante.casa(CampoEstudiante.calificacion, p));
        p = Double.valueOf(estudiante.getPromedio() - 5.0);
        Assert.assertTrue(estudiante.casa(CampoEstudiante.calificacion, p));
        p = Double.valueOf(estudiante.getPromedio() + 5.0);
        Assert.assertFalse(estudiante.casa(CampoEstudiante.calificacion, p));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.calificacion, "XXX"));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.calificacion, null));

        Integer e = Integer.valueOf(estudiante.getEdad());
        Assert.assertTrue(estudiante.casa(CampoEstudiante.EDAD, e));
        e = Integer.valueOf(estudiante.getEdad() - 10);
        Assert.assertTrue(estudiante.casa(CampoEstudiante.EDAD, e));
        e = Integer.valueOf(estudiante.getEdad() + 10);
        Assert.assertFalse(estudiante.casa(CampoEstudiante.EDAD, e));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.EDAD, "XXX"));
        Assert.assertFalse(estudiante.casa(CampoEstudiante.EDAD, null));

        try {
            estudiante.casa(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            estudiante.casa(X.A, estudiante.getNombre());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(estudiante.getCuenta());
            estudiante.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(estudiante.getPromedio());
            estudiante.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(estudiante.getEdad());
            estudiante.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Assert.assertFalse(estudiante.casa(X.A, null));
        } catch (IllegalArgumentException iae) {}
    }

    /* Inicializa el generador de números aleatorios. */
    static {
        random = new Random();
    }
}
