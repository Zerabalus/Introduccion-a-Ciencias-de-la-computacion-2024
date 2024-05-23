package mx.unam.ciencias.icc.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Clase para diálogos con formas de conexión.
 */
public class DialogoConectar extends Stage {

    /* Vista de la forma para realizar búsquedas de estudiantes. */
    private static final String CONECTAR_FXML =
        "fxml/forma-conectar.fxml";

    /* El controlador. */
    private ControladorFormaConectar controlador;

    /**
     * Define el estado inicial de un diálogo para búsquedas de estudiantes.
     * @param escenario el escenario al que el diálogo pertenece.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoConectar(Stage escenario) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador = new FXMLLoader(
            cl.getResource(CONECTAR_FXML));
        AnchorPane cristal = (AnchorPane)cargador.load();

        setTitle("Conectar a servidor");
        initOwner(escenario);
        initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(cristal);
        setScene(escena);
        controlador = cargador.getController();
        controlador.setEscenario(this);
        setOnShown(w -> controlador.defineFoco());
        setResizable(false);
    }

    /**
     * Nos dice si el usuario activó el botón de aceptar.
     * @return <code>true</code> si el usuario activó el botón de aceptar,
     *         <code>false</code> en otro caso.
     */
    public boolean isAceptado() {
        return controlador.isAceptado();
    }

    /**
     * Regresa la dirección del diálogo.
     * @return la dirección del diálogo.
     */
    public String getDireccion() {
        return controlador.getDireccion();
    }

    /**
     * Regresa el puerto del diálogo.
     * @return el puerto del diálogo.
     */
    public int getPuerto() {
        return controlador.getPuerto();
    }
}
