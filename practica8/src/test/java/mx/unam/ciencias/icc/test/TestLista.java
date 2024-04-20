package mx.unam.ciencias.icc.test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.icc.ExcepcionIndiceInvalido;
import mx.unam.ciencias.icc.IteradorLista;
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
    private Lista<String> lista;

    /* Clase para probar estabilidad de MergeSort. */
    private class Par implements Comparable<Par> {

        /* Valor del par.*/
        private int valor;
        /* Etiqueta del par.*/
        private int etiqueta;

        /* Constructor. */
        public Par(int valor, int etiqueta) {
            this.valor = valor;
            this.etiqueta = etiqueta;
        }

        /* Regresa el valor. */
        public int getValor() {
            return valor;
        }

        /* Regresa la etiqueta. */
        public int getEtiqueta() {
            return etiqueta;
        }

        /* Compara dos pares. */
        @Override public int compareTo(Par par) {
            return valor - par.valor;
        }
    }

    /* Valida una lista. */
    private void validaLista(Lista<String> lista) {
        int longitud = lista.getLongitud();
        String[] arreglo = new String[longitud];
        int c = 0;
        for (String n : lista)
            arreglo[c++] = n;
        Assert.assertTrue(c == longitud);
        c = 0;
        IteradorLista<String> i = lista.iteradorLista();
        while (i.hasNext())
            Assert.assertTrue(arreglo[c++].equals(i.next()));
        Assert.assertTrue(c == longitud);
        c = longitud - 1;
        i.end();
        while (i.hasPrevious())
            Assert.assertTrue(arreglo[c--].equals(i.previous()));
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
        lista = new Lista<String>();
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
        Lista<String> otra = new Lista<String>();
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
        lista = new Lista<String>();
        otra = new Lista<String>();
        for (int i = 0; i < total; i++) {
            otra.agregaInicio(str(ini + i));
            lista.inserta(0, str(ini + i));
            validaLista(lista);
            Assert.assertTrue(lista.equals(otra));
            Assert.assertTrue(lista.getPrimero().equals(str(ini + i)));
        }
        lista = new Lista<String>();
        otra = new Lista<String>();
        for (int i = 0; i < total; i++) {
            otra.agregaFinal(str(ini + i));
            lista.inserta(lista.getLongitud(), str(ini + i));
            validaLista(lista);
            Assert.assertTrue(lista.equals(otra));
            Assert.assertTrue(lista.getUltimo().equals(str(ini + i)));
        }
        for (int i = 0; i < total; i++) {
            int m = 1 + random.nextInt(total-2);
            lista = new Lista<String>();
            otra = new Lista<String>();
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
        String p = lista.getPrimero();
        String u = lista.getUltimo();
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
            String k = lista.eliminaPrimero();
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
            String k = lista.eliminaUltimo();
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
        Lista<String> reversa = lista.reversa();
        Assert.assertTrue(reversa.esVacia());
        Assert.assertFalse(reversa == lista);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        reversa = lista.reversa();
        Assert.assertFalse(lista == reversa);
        Assert.assertTrue(reversa.getLongitud() == lista.getLongitud());
        IteradorLista<String> il = lista.iteradorLista();
        IteradorLista<String> ir = reversa.iteradorLista();
        ir.end();
        while (il.hasNext() && ir.hasPrevious())
            Assert.assertTrue(il.next().equals(ir.previous()));
        Assert.assertFalse(il.hasNext());
        Assert.assertFalse(ir.hasPrevious());
        validaLista(reversa);
    }

    /**
     * Prueba unitaria para {@link Lista#copia}.
     */
    @Test public void testCopia() {
        Lista<String> copia = lista.copia();
        Assert.assertTrue(copia.esVacia());
        Assert.assertFalse(copia == lista);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        copia = lista.copia();
        Assert.assertFalse(lista == copia);
        Assert.assertTrue(copia.getLongitud() == lista.getLongitud());
        Iterator<String> il = lista.iterator();
        Iterator<String> ic = copia.iterator();
        while (il.hasNext() && ic.hasNext())
            Assert.assertTrue(il.next().equals(ic.next()));
        Assert.assertFalse(il.hasNext());
        Assert.assertFalse(ic.hasNext());
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
        for (String n : lista)
            c++;
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
        Lista<String> otra = new Lista<String>();
        Assert.assertTrue(lista.equals(otra));
        for (int i = 0; i < total; i++) {
            String r = str(random.nextInt(total));
            lista.agregaFinal(r);
            otra.agregaFinal(new String(r));
        }
        Assert.assertTrue(lista.equals(otra));
        String u = lista.eliminaUltimo();
        Assert.assertFalse(lista.equals(otra));
        lista.agregaFinal(u + "x");
        Assert.assertFalse(lista.equals(otra));
        Assert.assertFalse(lista.equals(""));
        Assert.assertFalse(lista.equals(null));
    }

    /**
     * Prueba unitaria para la implementación {@link Iterator#hasNext} a través
     * del método {@link Lista#iterator}.
     */
    @Test public void testIteradorHasNext() {
        Iterator<String> iterador = lista.iterator();
        Assert.assertFalse(iterador.hasNext());
        lista.agregaFinal("-1");
        iterador = lista.iterator();
        Assert.assertTrue(iterador.hasNext());
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(i));
        iterador = lista.iterator();
        for (int i = 0; i < total; i++)
            iterador.next();
        Assert.assertTrue(iterador.hasNext());
        iterador.next();
        Assert.assertFalse(iterador.hasNext());
    }

    /**
     * Prueba unitaria para la implementación {@link Iterator#next} a través del
     * método {@link Lista#iterator}.
     */
    @Test public void testIteradorNext() {
        Iterator<String> iterador = lista.iterator();
        try {
            iterador.next();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(i));
        iterador = lista.iterator();
        for (int i = 0; i < total; i++)
            Assert.assertTrue(iterador.next().equals(str(i)));
        try {
            iterador.next();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
    }

    /**
     * Prueba unitaria para la implementación {@link IteradorLista#hasPrevious}
     * a través del método {@link Lista#iteradorLista}.
     */
    @Test public void testIteradorHasPrevious() {
        IteradorLista<String> iterador = lista.iteradorLista();
        Assert.assertFalse(iterador.hasPrevious());
        lista.agregaFinal("-1");
        iterador = lista.iteradorLista();
        iterador.next();
        Assert.assertTrue(iterador.hasPrevious());
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(i));
        iterador = lista.iteradorLista();
        iterador.next();
        Assert.assertTrue(iterador.hasPrevious());
        iterador.previous();
        Assert.assertFalse(iterador.hasPrevious());
        iterador.end();
        Assert.assertTrue(iterador.hasPrevious());
    }

    /**
     * Prueba unitaria para la implementación {@link IteradorLista#previous} a
     * través del método {@link Lista#iteradorLista}.
     */
    @Test public void testIteradorPrevious() {
        IteradorLista<String> iterador = lista.iteradorLista();
        try {
            iterador.previous();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(i));
        iterador = lista.iteradorLista();
        iterador.end();
        for (int i = 0; i < total; i++)
            Assert.assertTrue(iterador.previous().equals(str(total - i - 1)));
        try {
            iterador.previous();
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
    }

    /**
     * Prueba unitaria para la implementación {@link IteradorLista#start} a
     * través del método {@link Lista#iteradorLista}.
     */
    @Test public void testIteradorStart() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(i));
        IteradorLista<String> iterador = lista.iteradorLista();
        while (iterador.hasNext())
            iterador.next();
        Assert.assertTrue(iterador.hasPrevious());
        iterador.start();
        Assert.assertFalse(iterador.hasPrevious());
        Assert.assertTrue(iterador.hasNext());
        Assert.assertTrue(iterador.next().equals("0"));
    }

    /**
     * Prueba unitaria para la implementación {@link IteradorLista#end} a través
     * del método {@link Lista#iteradorLista}.
     */
    @Test public void testIteradorEnd() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(i));
        IteradorLista<String> iterador = lista.iteradorLista();
        iterador.end();
        Assert.assertFalse(iterador.hasNext());
        Assert.assertTrue(iterador.hasPrevious());
        Assert.assertTrue(iterador.previous().equals(str(total - 1)));
    }


    /**
     * Prueba unitaria para {@link Lista#mergeSort}.
     */
    @Test public void testMergeSort() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        Lista<String> ordenada = lista.mergeSort((a, b) -> a.compareTo(b));
        Assert.assertFalse(lista == ordenada);
        Assert.assertTrue(lista.getLongitud() == ordenada.getLongitud());
        for (String e : lista)
            Assert.assertTrue(ordenada.contiene(e));
        String a = ordenada.getPrimero();
        for (String e : ordenada) {
            Assert.assertTrue(a.compareTo(e) <= 0);
            a = e;
        }
        validaLista(ordenada);
        /* Prueba estabilidad. */
        total = 100 + total * 10;
        int c = 0;
        int m = 7 + random.nextInt(20);
        Lista<Par> pares = new Lista<Par>();
        for (int i = 0; i < total; i++) {
            int v = ((i % m) == 0) ? m : random.nextInt(total);
            pares.agregaFinal(new Par(v, i));
        }
        pares = Lista.mergeSort(pares);
        Par u = null;
        for (Par par : pares) {
            if (u == null) {
                u = par;
                continue;
            }
            Assert.assertTrue(u.getValor() <= par.getValor());
            if (u.getValor() == par.getValor())
                Assert.assertTrue(u.getEtiqueta() < par.getEtiqueta());
            u = par;
        }
    }

    /**
     * Prueba unitaria para {@link Lista#mergeSort(Lista)}.
     */
    @Test public void testMergeSortLista() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        Lista<String> ordenada = Lista.mergeSort(lista);
        Assert.assertFalse(lista == ordenada);
        Assert.assertTrue(lista.getLongitud() == ordenada.getLongitud());
        for (String e : lista)
            Assert.assertTrue(ordenada.contiene(e));
        String a = ordenada.getPrimero();
        for (String e : ordenada) {
            Assert.assertTrue(a.compareTo(e) <= 0);
            a = e;
        }
        validaLista(ordenada);
        /* Prueba estabilidad. */
        total = 100 + total * 10;
        int c = 0;
        int m = 7 + random.nextInt(20);
        Lista<Par> pares = new Lista<Par>();
        for (int i = 0; i < total; i++) {
            int v = ((i % m) == 0) ? m : random.nextInt(total);
            pares.agregaFinal(new Par(v, i));
        }
        pares = Lista.mergeSort(pares);
        Par u = null;
        for (Par par : pares) {
            if (u == null) {
                u = par;
                continue;
            }
            Assert.assertTrue(u.getValor() <= par.getValor());
            if (u.getValor() == par.getValor())
                Assert.assertTrue(u.getEtiqueta() < par.getEtiqueta());
            u = par;
        }
    }

    /**
     * Prueba unitaria para {@link Lista#busquedaLineal}.
     */
    @Test public void testBusquedaLineal() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        lista = lista.mergeSort((a, b) -> a.compareTo(b));
        String m = lista.get(total/2);
        Assert.assertTrue(lista.busquedaLineal(m, (a, b) -> a.compareTo(b)));
        String o = " ";
        Assert.assertFalse(lista.busquedaLineal(o, (a, b) -> a.compareTo(b)));
        o = "z";
        Assert.assertFalse(lista.busquedaLineal(o, (a, b) -> a.compareTo(b)));
    }

    /**
     * Prueba unitaria para {@link Lista#busquedaLineal(Lista,Comparable)}.
     */
    @Test public void testBusquedaLinealLista() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(str(random.nextInt(total)));
        lista = Lista.mergeSort(lista);
        String m = lista.get(total/2);
        Assert.assertTrue(Lista.busquedaLineal(lista, m));
        String o = " ";
        Assert.assertFalse(Lista.busquedaLineal(lista, o));
    }
}
