package mx.unam.ciencias.icc.test;

import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.icc.ExcepcionIndiceInvalido;
import mx.unam.ciencias.icc.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Lista}.
 */
public class TestLista {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Generador de números aleatorios. */
    private Random random;
    /* Número total de elementos. */
    private int total;
    /* La lista. */
    private Lista lista;

    /* Valida una lista. */
    private void validaLista(Lista lista) {
        int longitud = lista.getLongitud();
        String[] arreglo = new String[longitud];
        int c = 0;
        Lista.Nodo nodo = lista.getCabeza();
        while (nodo != null) {
            arreglo[c++] = (String)nodo.get();
            nodo = nodo.getSiguiente();
        }
        Assert.assertTrue(c == longitud);
        c = 0;
        nodo = lista.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(arreglo[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        Assert.assertTrue(c == longitud);
        c = longitud - 1;
        nodo = lista.getRabo();
        while (nodo != null) {
            Assert.assertTrue(arreglo[c--].equals(nodo.get()));
            nodo = nodo.getAnterior();
        }
    }

    /* Convierte un entero en cadena. */
    private String str(int n) {
        return String.valueOf(n);
    }

    /**
     * Crea un generador de números aleatorios para cada prueba, un número total
     * de elementos para nuestra lista, y una lista.
     */
    public TestLista() {
        random = new Random();
        total = 10 + random.nextInt(90);
        lista = new Lista();
    }

    /**
     * Prueba unitaria para {@link Lista#Lista}.
     */
    @Test public void testConstructor() {
        Assert.assertTrue(lista != null);
        Assert.assertTrue(lista.esVacia());
        Assert.assertTrue(lista.getLongitud() == 0);
    }

    /**
     * Prueba unitaria para {@link Lista#getLongitud}.
     */
    @Test public void testGetLongitud() {
        Assert.assertTrue(lista.getLongitud() == 0);
        for (int i = 0; i < total/2; i++) {
            lista.agregaFinal(str(random.nextInt(total)));
            Assert.assertTrue(lista.getLongitud() == i + 1);
        }
        for (int i = total/2; i < total; i++) {
            lista.agregaInicio(str(random.nextInt(total)));
            Assert.assertTrue(lista.getLongitud() == i + 1);
        }
        Assert.assertTrue(lista.getLongitud() == total);
    }

    /**
     * Prueba unitaria para {@link Lista#esVacia}.
     */
    @Test public void testEsVacia() {
        Assert.assertTrue(lista.esVacia());
        lista.agregaFinal(str(random.nextInt(total)));
        Assert.assertFalse(lista.esVacia());
        lista.eliminaUltimo();
        Assert.assertTrue(lista.esVacia());
    }

    /**
     * Prueba unitaria para {@link Lista#agregaFinal}.
     */
    @Test public void testAgregaFinal() {
        try {
            lista.agregaFinal(null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        validaLista(lista);
        lista.agregaFinal("1");
        validaLista(lista);
        Assert.assertTrue(lista.getUltimo().equals("1"));
        lista.agregaInicio("2");
        validaLista(lista);
        Assert.assertFalse(lista.getUltimo().equals("2"));
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaFinal(r);
            validaLista(lista);
            Assert.assertTrue(lista.getUltimo().equals(r));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#agregaInicio}.
     */
    @Test public void testAgregaInicio() {
        try {
            lista.agregaInicio(null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        validaLista(lista);
        lista.agregaInicio("1");
        validaLista(lista);
        Assert.assertTrue(lista.getPrimero().equals("1"));
        lista.agregaFinal("2");
        validaLista(lista);
        Assert.assertFalse(lista.getPrimero().equals("2"));
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaInicio(r);
            validaLista(lista);
            Assert.assertTrue(lista.getPrimero().equals(r));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#inserta}.
     */
    @Test public void testInserta() {
        try {
            lista.inserta(0, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        Assert.assertTrue(lista.esVacia());
        validaLista(lista);
        int ini = random.nextInt(total);
        Lista otra = new Lista();
        for (int i = 0; i < total; i++) {
            otra.agregaInicio(str(ini + i));
            lista.inserta(-1, str(ini + i));
            validaLista(lista);
            Assert.assertTrue(lista.equals(otra));
            Assert.assertTrue(lista.getPrimero().equals(str(ini + i)));
        }
        for (int i = -1; i <= total; i++)
            try {
                lista.inserta(i, null);
                Assert.fail();
            } catch (IllegalArgumentException iae) {}
        lista = new Lista();
        otra = new Lista();
        for (int i = 0; i < total; i++) {
            otra.agregaInicio(str(ini + i));
            lista.inserta(0, str(ini + i));
            validaLista(lista);
            Assert.assertTrue(lista.equals(otra));
            Assert.assertTrue(lista.getPrimero().equals(str(ini + i)));
        }
        lista = new Lista();
        otra = new Lista();
        for (int i = 0; i < total; i++) {
            otra.agregaFinal(str(ini + i));
            lista.inserta(lista.getLongitud(), str(ini + i));
            validaLista(lista);
            Assert.assertTrue(lista.equals(otra));
            Assert.assertTrue(lista.getUltimo().equals(str(ini + i)));
        }
        for (int i = 0; i < total; i++) {
            int m = 1 + random.nextInt(total-2);
            lista = new Lista();
            otra = new Lista();
            for (int j = 0; j < total; j++) {
                otra.agregaFinal(str(ini + j));
                if (j != m)
                    lista.agregaFinal(str(ini + j));
                validaLista(lista);
                validaLista(otra);
            }
            Assert.assertTrue(otra.getLongitud() == lista.getLongitud() + 1);
            lista.inserta(m, str(ini + m));
            validaLista(lista);
            Assert.assertTrue(lista.equals(otra));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#elimina}.
     */
    @Test public void testElimina() {
        lista.elimina(null);
        validaLista(lista);
        Assert.assertTrue(lista.esVacia());
        lista.elimina(str(0));
        validaLista(lista);
        Assert.assertTrue(lista.esVacia());
        lista.agregaFinal("1");
        Assert.assertFalse(lista.esVacia());
        lista.eliminaUltimo();
        Assert.assertTrue(lista.esVacia());
        int d = random.nextInt(total);
        int m = -1;
        for (int i = 0; i < total; i++) {
            lista.agregaInicio(str(d++));
            if (i == total / 2)
                m = d - 1;
        }
        String p = (String)lista.getPrimero();
        String u = (String)lista.getUltimo();
        Assert.assertTrue(lista.contiene(p));
        Assert.assertTrue(lista.contiene(str(m)));
        Assert.assertTrue(lista.contiene(u));
        lista.elimina(p);
        validaLista(lista);
        Assert.assertFalse(lista.contiene(p));
        Assert.assertTrue(lista.getLongitud() == --total);
        lista.elimina(str(m));
        validaLista(lista);
        Assert.assertFalse(lista.contiene(str(m)));
        Assert.assertTrue(lista.getLongitud() == --total);
        lista.elimina(u);
        validaLista(lista);
        Assert.assertFalse(lista.contiene(u));
        Assert.assertTrue(lista.getLongitud() == --total);
        while (!lista.esVacia()) {
            lista.elimina(lista.getPrimero());
            validaLista(lista);
            Assert.assertTrue(lista.getLongitud() == --total);
            if (lista.esVacia())
                continue;
            lista.elimina(lista.getUltimo());
            validaLista(lista);
            Assert.assertTrue(lista.getLongitud() == --total);
        }
        try {
            lista.getPrimero();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        try {
            lista.getUltimo();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        lista.agregaFinal("1");
        lista.agregaFinal("2");
        lista.agregaFinal("3");
        lista.agregaFinal("2");
        lista.elimina("2");
        Assert.assertTrue(lista.get(0).equals("1"));
        Assert.assertTrue(lista.get(1).equals("3"));
        Assert.assertTrue(lista.get(2).equals("2"));
        lista.limpia();
        lista.agregaFinal("1");
        lista.agregaFinal("2");
        lista.agregaFinal("1");
        lista.agregaFinal("3");
        lista.elimina("1");
        Assert.assertTrue(lista.get(0).equals("2"));
        Assert.assertTrue(lista.get(1).equals("1"));
        Assert.assertTrue(lista.get(2).equals("3"));
        lista.limpia();
        lista.agregaFinal("1");
        lista.agregaFinal("2");
        lista.agregaFinal("3");
        lista.elimina("2");
        Assert.assertTrue(lista.get(0).equals("1"));
        Assert.assertTrue(lista.get(1).equals("3"));
    }

    /**
     * Prueba unitaria para {@link Lista#eliminaPrimero}.
     */
    @Test public void testEliminaPrimero() {
        try {
            lista.eliminaPrimero();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = str(random.nextInt(total));
            lista.agregaFinal(a[i]);
        }
        int i = 0;
        int n = total;
        while (!lista.esVacia()) {
            Assert.assertTrue(n-- == lista.getLongitud());
            String k = (String)lista.eliminaPrimero();
            validaLista(lista);
            Assert.assertTrue(k.equals(a[i++]));
        }
        Assert.assertTrue(n == 0);
        try {
            lista.eliminaPrimero();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        lista.agregaFinal("1");
        lista.agregaFinal("2");
        lista.agregaFinal("1");
        lista.agregaFinal("3");
        lista.agregaFinal("1");
        lista.eliminaPrimero();
        Assert.assertTrue(lista.get(0).equals("2"));
        Assert.assertTrue(lista.get(1).equals("1"));
        Assert.assertTrue(lista.get(2).equals("3"));
        Assert.assertTrue(lista.get(3).equals("1"));
    }

    /**
     * Prueba unitaria para {@link Lista#eliminaUltimo}.
     */
    @Test public void testEliminaUltimo() {
        try {
            lista.eliminaUltimo();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = str(random.nextInt(total));
            lista.agregaFinal(a[i]);
        }
        int i = 0;
        int n = total;
        while (!lista.esVacia()) {
            Assert.assertTrue(n-- == lista.getLongitud());
            String k = (String)lista.eliminaUltimo();
            validaLista(lista);
            Assert.assertTrue(k.equals(a[total - ++i]));
        }
        Assert.assertTrue(n == 0);
        try {
            lista.eliminaUltimo();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        lista.agregaFinal("1");
        lista.agregaFinal("2");
        lista.agregaFinal("1");
        lista.agregaFinal("3");
        lista.agregaFinal("1");
        lista.eliminaUltimo();
        Assert.assertTrue(lista.get(0).equals("1"));
        Assert.assertTrue(lista.get(1).equals("2"));
        Assert.assertTrue(lista.get(2).equals("1"));
        Assert.assertTrue(lista.get(3).equals("3"));
    }

    /**
     * Prueba unitaria para {@link Lista#contiene}.
     */
    @Test public void testContiene() {
        String r = str(random.nextInt(total));
        Assert.assertFalse(lista.contiene(r));
        int d = random.nextInt(total);
        int m = -1;
        int n = d - 1;
        for (int i = 0; i < total; i++) {
            lista.agregaFinal(str(d++));
            if (i == total/2)
                m = d - 1;
        }
        Assert.assertTrue(lista.contiene(str(m)));
        Assert.assertTrue(lista.contiene(new String(str(m))));
        Assert.assertFalse(lista.contiene(str(n)));
    }

    /**
     * Prueba unitaria para {@link Lista#reversa}.
     */
    @Test public void testReversa() {
        Lista reversa = lista.reversa();
        Assert.assertTrue(reversa.esVacia());
        Assert.assertFalse(reversa == lista);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        reversa = lista.reversa();
        Assert.assertFalse(lista == reversa);
        Assert.assertTrue(reversa.getLongitud() == lista.getLongitud());
        Lista.Nodo nl = lista.getCabeza();
        Lista.Nodo nr = reversa.getRabo();
        while (nl != null && nr != null) {
            Assert.assertTrue(nl.get().equals(nr.get()));
            nl = nl.getSiguiente();
            nr = nr.getAnterior();
        }
        Assert.assertTrue(nl == null);
        Assert.assertTrue(nr == null);
        validaLista(reversa);
    }

    /**
     * Prueba unitaria para {@link Lista#copia}.
     */
    @Test public void testCopia() {
        Lista copia = lista.copia();
        Assert.assertTrue(copia.esVacia());
        Assert.assertFalse(copia == lista);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        copia = lista.copia();
        Assert.assertFalse(lista == copia);
        Assert.assertTrue(copia.getLongitud() == lista.getLongitud());
        Lista.Nodo nl = lista.getCabeza();
        Lista.Nodo nr = copia.getCabeza();
        while (nl != null && nr != null) {
            Assert.assertTrue(nl.get().equals(nr.get()));
            nl = nl.getSiguiente();
            nr = nr.getSiguiente();
        }
        Assert.assertTrue(nl == null);
        Assert.assertTrue(nr == null);
        validaLista(copia);
    }

    /**
     * Prueba unitaria para {@link Lista#limpia}.
     */
    @Test public void testLimpia() {
        String primero = str(random.nextInt(total));
        lista.agregaFinal(primero);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        String ultimo = str(random.nextInt(total));
        lista.agregaFinal(ultimo);
        Assert.assertFalse(lista.esVacia());
        Assert.assertTrue(primero.equals(lista.getPrimero()));
        Assert.assertTrue(ultimo.equals(lista.getUltimo()));
        Assert.assertFalse(lista.esVacia());
        Assert.assertFalse(lista.getLongitud() == 0);
        lista.limpia();
        validaLista(lista);
        Assert.assertTrue(lista.esVacia());
        Assert.assertTrue(lista.getLongitud() == 0);
        int c = 0;
        Lista.Nodo nodo = lista.getCabeza();
        while (nodo != null) {
            c++;
            nodo = nodo.getSiguiente();
        }
        Assert.assertTrue(c == 0);
        try {
            lista.getPrimero();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        try {
            lista.getUltimo();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
    }

    /**
     * Prueba unitaria para {@link Lista#getPrimero}.
     */
    @Test public void testGetPrimero() {
        try {
            lista.getPrimero();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaInicio(r);
            Assert.assertTrue(lista.getPrimero().equals(r));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#getUltimo}.
     */
    @Test public void testGetUltimo() {
        try {
            lista.getUltimo();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaFinal(r);
            Assert.assertTrue(lista.getUltimo().equals(r));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#get}.
     */
    @Test public void testGet() {
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = str(random.nextInt(total));
            lista.agregaFinal(a[i]);
        }
        for (int i = 0; i < total; i++)
            Assert.assertTrue(lista.get(i).equals(a[i]));
        try {
            lista.get(-1);
            Assert.fail();
        } catch (ExcepcionIndiceInvalido eii) {}
        try {
            lista.get(-2);
            Assert.fail();
        } catch (ExcepcionIndiceInvalido eii) {}
        try {
            lista.get(total);
            Assert.fail();
        } catch (ExcepcionIndiceInvalido eii) {}
        try {
            lista.get(total*2);
            Assert.fail();
        } catch (ExcepcionIndiceInvalido eii) {}
    }

    /**
     * Prueba unitaria para {@link Lista#indiceDe}.
     */
    @Test public void testIndiceDe() {
        String r = str(random.nextInt(total));
        Assert.assertTrue(lista.indiceDe(r) == -1);
        int ini = random.nextInt(total);
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = str(ini + i);
            lista.agregaFinal(a[i]);
        }
        for (int i = 0; i < total; i ++)
            Assert.assertTrue(i == lista.indiceDe(a[i]));
        Assert.assertTrue(lista.indiceDe(str(ini - 10)) == -1);
        String s = (String)lista.getPrimero();
        String t = new String(s);
        Assert.assertTrue(lista.indiceDe(t) == 0);
    }

    /**
     * Prueba unitaria para {@link Lista#toString}.
     */
    @Test public void testToString() {
        Assert.assertTrue(lista.toString().equals("[]"));
        String[] a = new String[total];
        for (int j = 0; j < total; j++) {
            lista.limpia();
            for (int i = 0; i < j; i++) {
                a[i] = str(i);
                lista.agregaFinal(a[i]);
            }
            if (lista.esVacia()) {
                Assert.assertTrue("[]".equals(lista.toString()));
                continue;
            }
            String s = "[";
            for (int i = 0; i < j-1; i++)
                s += String.format("%s, ", a[i]);
            s += String.format("%s]", a[j-1]);
            Assert.assertTrue(s.equals(lista.toString()));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#equals}.
     */
    @Test public void testEquals() {
        Assert.assertFalse(lista.equals(null));
        Lista otra = new Lista();
        Assert.assertTrue(lista.equals(otra));
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaFinal(r);
            otra.agregaFinal(new String(r));
        }
        Assert.assertTrue(lista.equals(otra));
        String u = (String)lista.eliminaUltimo();
        Assert.assertFalse(lista.equals(otra));
        lista.agregaFinal(u + "x");
        Assert.assertFalse(lista.equals(otra));
        Assert.assertFalse(lista.equals(""));
        Assert.assertFalse(lista.equals(null));
    }

    /**
     * Prueba unitaria para {@link Lista#getCabeza}.
     */
    @Test public void testGetCabeza() {
        Assert.assertTrue(lista.getCabeza() == null);
        lista.agregaInicio("2");
        Assert.assertTrue(lista.getCabeza() != null);
        Assert.assertTrue(lista.getRabo() != null);
        Assert.assertTrue(lista.getCabeza().get().equals("2"));
        lista.agregaInicio("1");
        Assert.assertTrue(lista.getCabeza() != null);
        Assert.assertTrue(lista.getCabeza().get().equals("1"));
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaInicio(r);
            Assert.assertTrue(lista.getCabeza().get().equals(r));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#getRabo}.
     */
    @Test public void testGetRabo() {
        Assert.assertTrue(lista.getRabo() == null);
        lista.agregaFinal("1");
        Assert.assertTrue(lista.getCabeza() != null);
        Assert.assertTrue(lista.getRabo() != null);
        Assert.assertTrue(lista.getRabo().get().equals("1"));
        lista.agregaFinal("2");
        Assert.assertTrue(lista.getRabo() != null);
        Assert.assertTrue(lista.getRabo().get().equals("2"));
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaFinal(r);
            Assert.assertTrue(lista.getRabo().get().equals(r));
        }
    }
}
