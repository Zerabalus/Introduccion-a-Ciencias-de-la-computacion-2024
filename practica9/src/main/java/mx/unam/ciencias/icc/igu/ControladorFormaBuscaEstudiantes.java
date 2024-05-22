package mx.unam.ciencias.icc.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoEstudiante;

/**
 * Clase para el controlador del contenido del diálogo para buscar estudiantes.
 */
public class ControladorFormaBuscaEstudiantes
    extends ControladorFormaEstudiante {

    /* El combo del campo. */
    @FXML private ComboBox<CampoEstudiante> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Verifica el valor. */
    private boolean verificaValor(String valor) {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:   return verificaNombre(valor);
        case CUENTA:   return verificaCuenta(valor);
        case PROMEDIO: return verificaPromedio(valor);
        case EDAD:     return verificaEdad(valor);
        default:       return false; // No puede ocurrir.
        }
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case NOMBRE:
            m = "Buscar por nombre necesita al menos un carácter";
            break;
        case CUENTA:
            m = "Buscar por cuenta necesita un número entre " +
                "1000000 y 99999999";
            break;
        case PROMEDIO:
            m = "Buscar por promedio necesita un número entre 0.0 y 10.0";
            break;
        case EDAD:
            m = "Buscar por edad necesita un número entre 13 y 99";
            break;
        }
        return new Tooltip(m);
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
            // Aquí va su código.
            case NOMBRE:   return entradaValor.getText();
            case CUENTA:   return Integer.parseInt(entradaValor.getText());
            case PROMEDIO: return Double.parseDouble(entradaValor.getText());
            case EDAD:     return Integer.parseInt(entradaValor.getText());    
        default:       return entradaValor.getText(); // No puede ocurrir.
        }
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoEstudiante getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
