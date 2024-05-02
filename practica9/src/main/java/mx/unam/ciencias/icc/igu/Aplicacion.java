package mx.unam.ciencias.icc.igu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Clase para aplicaciones con interfaz gráfica de la base de datos.
 */
public class Aplicacion extends Application {

    /* Vista de la interfaz estudiantes. */
    private static final String INTERFAZ_ESTUDIANTES_FXML =
        "fxml/interfaz-estudiantes.fxml";
    /* Ícono de la Facultad de Ciencias. */
    private static final String ICONO_CIENCIAS =
        "icons/ciencias.png";

    /**
     * Inicia la aplicación.
     * @param escenario la ventana principal de la aplicación.
     * @throws Exception si algo sale mal.
     */
    @Override public void start(Stage escenario) throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        String url = cl.getResource(ICONO_CIENCIAS).toString();
        escenario.getIcons().add(new Image(url));
        escenario.setTitle("Administrador de Estudiantes");

        FXMLLoader cargador;
        cargador = new FXMLLoader(cl.getResource(INTERFAZ_ESTUDIANTES_FXML));
        BorderPane cristal = (BorderPane)cargador.load();
        ControladorInterfazEstudiantes controlador = cargador.getController();
        controlador.setEscenario(escenario);

        Scene escena = new Scene(cristal);
        escenario.setScene(escena);
        escenario.setOnCloseRequest(e -> controlador.salir(e));
        escenario.show();
    }
}
