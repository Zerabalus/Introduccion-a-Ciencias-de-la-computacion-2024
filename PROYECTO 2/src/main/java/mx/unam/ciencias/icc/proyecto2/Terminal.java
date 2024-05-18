package mx.unam.ciencias.icc.proyecto2;

public class Terminal {

    private enum Opcion {

        ARCHIVO_GUARDADO("-o"),
        INVERSO("-r");

        private String opcion;

        private Opcion(String opcion) {
            this.opcion = opcion;
        }

        public String getOpcion() {
            return opcion;
        }

    }

    public Terminal(String[] args) {
        try {
            analizarArgs(args);
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error inesperado.");
            System.exit(1);
        }
    }

    public void analizarArgs(
            String[] args) {

        if (args.length == 0) {
            Ordenador.ordenar();
        }

        if (args.length == 1) {
            Ordenador.ordenar(args[0]);
        }

        if (args.length == 2) {
            if (args[0].equals(Opcion.ARCHIVO_GUARDADO.getOpcion())) {
                Ordenador.ordenar(args[1]);
            } else if (args[0].equals(Opcion.INVERSO.getOpcion())) {
                BanderaR.invertirOrden(args[1]);
            } else {
                throw new IllegalArgumentException("Opción inválida.");
            }
        }

        if (args.length > 2) {

            String lastArgument = args[args.length - 1];
            String[] argsWithoutLast = new String[args.length - 1];
            String[] argsWithoutFlag = new String[args.length - 1];
            String[] fileArgs = new String[args.length - 2];

            for (int i = 0; i < args.length - 1; i++) {
                argsWithoutLast[i] = args[i];
            }

            for (int i = 1; i < args.length - 1; i++) {
                argsWithoutFlag[i] = args[i];
            }

            for (int i = 1; i < args.length - 1; i++) {
                fileArgs[i - 1] = args[i];
            }

            if (args[0].equals(Opcion.ARCHIVO_GUARDADO.getOpcion())) {
                Ordenador.ordenar(fileArgs, lastArgument);
            } else if (args[0].equals(Opcion.INVERSO.getOpcion())) {
                BanderaR.invertirOrden(fileArgs, lastArgument);
            } else {
                Ordenador.ordenar(argsWithoutLast, lastArgument);
            }

        }

    }

}