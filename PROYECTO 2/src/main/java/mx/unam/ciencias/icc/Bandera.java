package mx.unam.ciencias.icc;

public class Bandera {
    private String[] args;

    public Bandera(String[] args) {
        this.args = args;
    }

    public boolean BanderaInversaR() {
        for (String arg : args) {
            if ("-r".equals(arg)) return true;
        }
        return false;
    }

    public boolean BanderaO() {
        for (String arg : args) {
            if ("-o".equals(arg)) return true;
        }
        return false;
    }

    public int indiceBanderaO() {
        for (int i = 0; i < args.length; i++) {
            if ("-o".equals(args[i])) return i;
        }
        return -1;
    }
}
