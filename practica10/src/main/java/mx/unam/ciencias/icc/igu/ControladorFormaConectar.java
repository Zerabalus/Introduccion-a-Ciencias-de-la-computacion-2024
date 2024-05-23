package mx.unam.ciencias.icc.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Clase para el controlador del diálogo para conectar al servidor.
 */
public class ControladorFormaConectar extends ControladorForma {

    /* El campo verificable para la dirección. */
    @FXML private EntradaVerificable entradaDireccion;
    /* El campo verificable para el puerto. */
    @FXML private EntradaVerificable entradaPuerto;

    /* La dirección. */
    private String direccion;
    /* El puerto. */
    private int puerto;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaDireccion.setVerificador(s -> verificaDireccion(s));
        entradaPuerto.setVerificador(p -> verificaPuerto(p));

        entradaDireccion.textProperty().addListener(
            (o, v, n) -> conexionValida());
        entradaPuerto.textProperty().addListener(
            (o, v, n) -> conexionValida());
    }

    /* Manejador para cuando se activa el botón conectar. */
    @FXML private void conectar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Determina si los campos son válidos. */
    private void conexionValida() {
        boolean s = entradaDireccion.esValida();
        boolean p = entradaPuerto.esValida();
        botonAceptar.setDisable(!s || !p);
    }

    /* Verifica que la dirección sea válido. */
    private boolean verificaDireccion(String s) {
        if (s == null || s.isEmpty())
            return false;
        direccion = s;
        return true;
    }

    /* Verifica que el puerto sea válido. */
    private boolean verificaPuerto(String p) {
        if (p == null || p.isEmpty())
            return false;
        try {
            puerto = Integer.parseInt(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (puerto < 1025 || puerto > 65535)
            return false;
        return true;
    }

    /**
     * Regresa la dirección del diálogo.
     * @return la dirección del diálogo.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Regresa el puerto del diálogo.
     * @return el puerto del diálogo.
     */
    public int getPuerto() {
        return puerto;
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaDireccion.requestFocus();
    }
}
