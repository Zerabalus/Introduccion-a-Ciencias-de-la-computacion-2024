package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;

import mx.unam.ciencias.icc.Anilist;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosAnilist;
import mx.unam.ciencias.icc.CampoAnilist;
import mx.unam.ciencias.icc.Anilist;
import mx.unam.ciencias.icc.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class TestBasedeDatosAnilist {

     /** Expiración para que ninguna prueba tarde más de 5 segundos. */
     @Rule public Timeout expiracion = Timeout.seconds(5);

     /* Generador de números aleatorios. */
     private Random random;
     /* Base de datos de Anilist. */
     private BaseDeDatosAnilist bdd;
     /* Número total de animes. */
     private int total;

     /* Enumeración espuria. */
    private enum X {
        /* Campo espurio. */
        A;
    }

    public void TestBaseDeDatosAnilist() {
        random = new Random();
        bdd = new BaseDeDatosAnilist();
        total = 2 + random.nextInt(100);
    }

    @Test public void testConstructor() {
        Lista animes = bdd.getRegistros();
        Assert.assertFalse(animes == null);
        Assert.assertTrue(animes.getLongitud() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testGetNumRegistros() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        for (int i = 0; i < total; i++) {
            Anilist e = TestAnilist.animeAleatorio();
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Lista l = bdd.getRegistros();
        Lista r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Anilist[] animes = new Anilist[total];
        for (int i = 0; i < total; i++) {
            animes[i] = TestAnilist.animeAleatorio();
            bdd.agregaRegistro(animes[i]);
        }
        l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(animes[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        l.elimina(animes[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    //////////////////////////////////////////////////////////////////////
    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Anilist e = TestAnilist.animesAleatorio();
            Assert.assertFalse(bdd.getRegistros().contiene(e));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            Lista l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Anilist e = TestAnilist.animesAleatorio(ini + i);
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Anilist e = (Anilist)bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
            Anilist e = TestAnilist.animesAleatorio();
            bdd.agregaRegistro(e);
        }
        Lista registros = bdd.getRegistros();
        Assert.assertFalse(registros.esVacia());
        Assert.assertFalse(registros.getLongitud() == 0);
        bdd.limpia();
        registros = bdd.getRegistros();
        Assert.assertTrue(registros.esVacia());
        Assert.assertTrue(registros.getLongitud() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Anilist e = TestAnilist.estudianteAleatorio();
            bdd.agregaRegistro(e);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        Lista l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Anilist e = (Anilist)nodo.get();
            String el = String.format("%s\t%d\t%2.2f\t%d",
                                      e.getNombre(),
                                      e.getGenero(),
                                      e.getCapitulos(),
                                      e.getEstreno(),
                                      e.getCalificacion());
            Assert.assertTrue(lineas[c++].equals(el));
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Anilist[] animes = new Anilist[total];
        for (int i = 0; i < total; i++) {
            animes[i] = TestAnilist.animesAleatorio(ini + i);
            entrada += String.format("%s\t%d\t%2.2f\t%d\n",
                                     animes[i].getNombre(),
                                     animes[i].getCuenta(),
                                     animes[i].getPromedio(),
                                     animes[i].getEdad());
            bdd.agregaRegistro(animes[i]);
        }
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
        int c = 0;
        Lista l = bdd.getRegistros();
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(animes[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        entrada = String.format("%s\t%d\t%2.2f\t%d\n",
                                animes[0].getNombre(),
                                animes[0].getGenero(),
                                animes[0].getCapitulos(),
                                animes[0].getEstreno(),
                                animes[0].getCalificacion());
        entrada += " \n";
        entrada += String.format("%s\t%d\t%2.2f\t%d\n",
                                 animes[1].getNombre(),
                                 animes[1].getGenero(),
                                 animes[1].getCapitulos(),
                                 animes[1].getEstreno(),
                                 animes[1].getCalificacion());
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 1);
        entrada = "";
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosAnilists#creaRegistro}.
     */
    @Test public void testCreaRegistro() {
        Anilist e = (Anilist)bdd.creaRegistro();
        Assert.assertTrue(e.getNombre() == null);
        Assert.assertTrue(e.getGenero() == null);
        Assert.assertTrue(e.getCapitulos() == 0);
        Assert.assertTrue(e.getEstreno() == 0);
        Assert.assertTrue(e.getCalificacion() == 0.0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosAnilists#buscaRegistros}.
     */
    @Test
    public void testBuscaRegistros() {
        Anilist[] anime = new Anilist[total];
        int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Anilist e = new Anilist(String.valueOf(ini + i),
                    ini + i, (i * 10.0) / total, i);
            anime[i] = e;
            bdd.agregaRegistro(e);
        }
  /////////////////////////////////////////////////////////////7
        Anilist animes;
        Lista l;
        Lista.Nodo nodo;
        int i;

        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            animes[i] = anime;

            String nombre = animes.getNombre();
            l = bdd.buscaRegistros(CampoAnilist.NOMBRE, nombre);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(anime));
            nodo = l.getCabeza();
            while (nodo != null) {
                Anilist e = (Anilist)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(nombre) > -1);
                nodo = nodo.getSiguiente();
            }
            int n = nombre.length();
            String bn = nombre.substring(random.nextInt(2),
                                         2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoAnilist.NOMBRE, bn);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(anime));
            nodo = l.getCabeza();
            while (nodo != null) {
                Anilist e = (Anilist)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(bn) > -1);
                nodo = nodo.getSiguiente();
            }

            String genero = anime.getGenero();
            l = bdd.buscaRegistros(CampoAnilist.GENERO, genero);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(anime));
            nodo = l.getCabeza();
            while (nodo != null) {
                Anilist e = (Anilist)nodo.get();
                Assert.assertTrue(e.getGenero().indexOf(genero) > -1);
                nodo = nodo.getSiguiente();
            }

            Integer capitulos = Integer.valueOf(anime.getCapitulos());
            l = bdd.buscaRegistros(CampoAnilist.CUENTA, cuenta);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(animes));
            nodo = l.getCabeza();
            while (nodo != null) {
                Anilist e = (Anilist)nodo.get();
                Assert.assertTrue(e.getCuenta() >= cuenta.intValue());
                nodo = nodo.getSiguiente();
            }
        }    
    }
}
