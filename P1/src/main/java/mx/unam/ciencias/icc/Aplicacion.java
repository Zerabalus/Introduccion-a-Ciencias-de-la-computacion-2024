package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase para aplicaciones de la base de datos de estudiantes.
 */
public class Aplicacion {

    /* Modo de la aplicación. */
    private enum Modo {
        /* Modo para guardar. */
        GUARDA(1),
        /* Modo para cargar. */
        CARGA(2);

        /* Código de terminación. */
        private int codigo;

        /* Constructor. */
        private Modo(int codigo) {
            this.codigo = codigo;
        }

        /* Regresa el código. */
        public int getCodigo() {
            return codigo;
        }

        /* Regresa el modo de la bandera. */
        public static Modo getModo(String bandera) {
            switch (bandera) {
            case "-g": return GUARDA;
            case "-c": return CARGA;
            default: throw new IllegalArgumentException(
                "Bandera inválida: " + bandera);
            }
        }
    }

    /* La base de datos. */
    private BaseDeDatosEstudiantes bdd;
    /* La ruta del archivo. */
    private String ruta;
    /* El modo de la aplicación. */
    private Modo modo;

    /**
     * Define el estado inicial de la aplicación.
     * @param bandera la bandera de modo.
     * @param ruta la ruta del archivo.
     * @throws IllegalArgumentException si la bandera no es "-r" o "-g".
     */
    public Aplicacion(String bandera, String ruta) {
        modo = Modo.getModo(bandera);
        this.ruta = ruta;
        bdd = new BaseDeDatosEstudiantes();
    }

    /**
     * Ejecuta la aplicación.
     */
    public void ejecuta() {
        switch (modo) {
        case GUARDA:
            guarda();
            break;
        case CARGA:
            carga();
            break;
        }
    }

    /* Modo de guarda de la aplicación. */
    private void guarda() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        verificaSalida(sc);
        agregaEstudiantes(sc);
        sc.close();
        try {
            BufferedWriter out =
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(ruta)));
            bdd.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.err.printf("No pude guardar en el archivo \"%s\".\n",
                              ruta);
            System.exit(modo.getCodigo());
        }
    }

    /* Verifica que la salida no exista o le permite salir al usuario. */
    private void verificaSalida(Scanner sc) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            System.out.printf("El archivo \"%s\" ya existe y será " +
                              "reescrito.\n¿Desea continuar? (s/n): ", ruta);
            String r = sc.next();
            if (!r.equals("s")) {
                sc.close();
                System.exit(0);
            }
        }
    }

    private void agregaAnimes(Scanner sc) {
         System.out.println("\nDeje el nombre en blanco para " +
                            "parar la captura de animes.");
        Anilist e = null;
        do {
            try {
                e = getAnilist(sc);
                if (e != null)
                    bdd.agregaRegistro(e);
            } catch (InputMismatchException ime) {
                System.err.printf("\nNúmero inválido. Se descartará " +
                                  "este anime.\n");
                sc.next(); // Purgamos la última entrada del usuario.
                continue;
            }
        } while (e != null);
    }

    /* Obtiene un anime de la línea de comandos. */
    private Anilist getAnilist(Scanner sc) {
        System.out.printf("\nNombre   : ");
        String nombre = sc.next();
        if (nombre.equals(""))
            return null;
        System.out.println("Genero   : ");
        String genero = sc.next();
        System.out.printf("Capitulos   : ");
        int cuenta = sc.nextInt();
        System.out.printf("Estreno     : ");
        int estreno = sc.nextInt();
        System.out.printf("Calificacion : ");
        double calificacion = sc.nextDouble();
        
        return new Anilist(nombre, genero, cuenta, calificacion, estreno);
    }

    /* Modo de carga de la aplicación. */
    private void carga() {
        try {
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(ruta)));
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.err.printf("No pude cargar del archivo \"%s\".\n",
                              ruta);
            System.exit(modo.getCodigo());
        }
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        busca(sc);
        sc.close();
    }

    /* Hace la búsqueda. */
    private void busca(Scanner sc) {
        System.out.println("\nDeje el campo en blanco para " +
                           "parar la búsqueda de animes.");
        String c = "X";
        while (!(c = getCampo(sc)).equals("")) {
            Lista l;
            try {
                l = getResultados(c, sc);
            } catch (ExcepcionOpcionInvalida epi) {
                System.out.printf("%s\n", epi.getMessage());
                continue;
            } catch (InputMismatchException ime) {
                System.out.printf("\nValor inválido. Búsqueda descartada.\n");
                sc.next(); // Purgamos la entrada.
                continue;
            }
            Lista.Nodo nodo = l.getCabeza();
            String m = nodo != null ? "" :
                "\nCero registros cazan la búsqueda.";
            System.out.println(m);
            while (nodo != null) {
                System.out.printf("%s\n\n", nodo.get().toString());
                nodo = nodo.getSiguiente();
            }
        }
    }

    /* Regresa el campo. */
    private String getCampo(Scanner sc) {
        System.out.printf("\n¿Por qué campo quiere buscar? (n/c/p/e): ");
        return sc.next();
    }

    /* Regresa los resultados de la búsqueda. */
    private Lista getResultados(String c, Scanner sc) {
        System.out.println();
        switch (c) {
        case "n": return bdd.buscaRegistros(CampoAnilist.NOMBRE,
                                            getValorNombre(sc));
        case "g": return bdd.buscaRegistros(CampoAnilist.GENERO,
                                            getValorGenero(sc));
        case "c": return bdd.buscaRegistros(CampoAnilist.CAPITULOS,
                                            getValorCapitulos(sc));
        case "p": return bdd.buscaRegistros(CampoAnilist.ESTRENO,
                                            getValorEstreno(sc));
        case "e": return bdd.buscaRegistros(CampoAnilist.CALIFICACION,
                                            getValorCalificacion(sc));
        default:
            String m = String.format("El campo '%s' es inválido.", c);
            throw new ExcepcionOpcionInvalida(m);
        }
    }


    private String getValorNombre(Scanner sc) {
        System.out.printf("El nombre debe contener: ");
        return sc.next();
    }
    private Genero getValorGenero(Scanner sc) {
        System.out.printf("El genero debe ser: ");
        return sc.next();
    }

    private Integer getValorCapitulos(Scanner sc) {
        System.out.printf("El número de cuenta debe ser mayor o igual a: ");
        return Integer.valueOf(sc.nextInt());
    }

    private Double getValorPromedio(Scanner sc) {
        System.out.printf("El calificacion debe ser mayor o igual a: ");
        return Double.valueOf(sc.nextDouble());
    }

    private Integer getValorEdad(Scanner sc) {
        System.out.printf("La estreno debe ser mayor o igual a: ");
        return Integer.valueOf(sc.nextInt());
    }
}
