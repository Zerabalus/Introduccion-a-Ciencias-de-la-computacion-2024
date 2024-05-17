package mx.unam.ciencias.icc;

public class Bandera {
    private String[] banderas;

    public Bandera(String[] banderas) {
        this.banderas = banderas;
    }

    public boolean BanderaInversaR() {
        for (String arg : banderas) {
            if ("-r".equals(arg)) return true;
        }
        return false;
    }

    public boolean BanderaO() {
        for (String arg : banderas) {
            if ("-o".equals(arg)) return true;
        }
        return false;
    }

    public int indiceBanderaO() {
        for (int i = 0; i < banderas.length; i++) {
            if ("-o".equals(banderas[i])) return i;
        }
        return -1;
    }
}
