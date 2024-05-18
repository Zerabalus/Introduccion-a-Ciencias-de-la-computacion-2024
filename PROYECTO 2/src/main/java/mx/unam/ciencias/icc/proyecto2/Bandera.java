package mx.unam.ciencias.icc.proyecto2;

public class Bandera {
    private String[] banderas;

    public Bandera(String[] banderas) {
        this.banderas = banderas;
    }

    public boolean BanderaInversaR() {
        for (String b : banderas) {
            if ("-r".equals(b)) return true;
        }
        return false;
    }

    public boolean BanderaO() {
        for (String b : banderas) {
            if ("-o".equals(b)) return true;
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
