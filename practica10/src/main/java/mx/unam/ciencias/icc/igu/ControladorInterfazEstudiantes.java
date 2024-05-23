package mx.unam.ciencias.icc.igu;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.BaseDeDatosEstudiantes;
import mx.unam.ciencias.icc.Estudiante;
import mx.unam.ciencias.icc.EventoBaseDeDatos;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.red.Conexion;
import mx.unam.ciencias.icc.red.Mensaje;

/**
 * Clase para el controlador de la ventana principal de la aplicación.
 */
public class ControladorInterfazEstudiantes {

    /* Opción de menu para cambiar el estado de la conexión. */
    @FXML private MenuItem menuConexion;
    /* Opción de menu para agregar. */
    @FXML private MenuItem menuAgregar;
    /* Opción de menu para editar. */
    @FXML private MenuItem menuEditar;
    /* Opción de menu para eliminar. */
    @FXML private MenuItem menuEliminar;
    /* Opción de menu para buscar. */
    @FXML private MenuItem menuBuscar;
    /* El botón de agregar. */
    @FXML private Button botonAgregar;
    /* El botón de editar. */
    @FXML private Button botonEditar;
    /* El botón de eliminar. */
    @FXML private Button botonEliminar;
    /* El botón de buscar. */
    @FXML private Button botonBuscar;

    /* La tabla. */
    @FXML private TableView<Estudiante> tabla;

    /* La ventana. */
    private Stage escenario;
    /* El modelo de la selección. */
    private TableView.TableViewSelectionModel<Estudiante> modeloSeleccion;
    /* La selección. */
    private ObservableList<TablePosition> seleccion;
    /* Los renglones en la tabla. */
    private ObservableList<Estudiante> renglones;

    /* La base de datos. */
    private BaseDeDatosEstudiantes bdd;
    /* La conexión del cliente. */
    private Conexion<Estudiante> conexion;
    /* Si hay o no conexión. */
    private boolean conectado;

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        renglones = tabla.getItems();
        modeloSeleccion = tabla.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.MULTIPLE);
        seleccion = modeloSeleccion.getSelectedCells();
        ListChangeListener<TablePosition> lcl = c -> cambioSeleccion();
        seleccion.addListener(lcl);
        cambioSeleccion();
        setConectado(false);
        bdd = new BaseDeDatosEstudiantes();
        bdd.agregaEscucha((e, r1, r2) -> eventoBaseDeDatos(e, r1, r2));
    }

    /* Cambioa el estado de la conexión. */
    @FXML private void cambiaConexion(ActionEvent evento) {
        if (!conectado)
            conectar();
        else
            desconectar();
    }

    /**
     * Termina el programa.
     * @param evento el evento que generó la acción.
     */
    @FXML public void salir(Event evento) {
        desconectar();
        Platform.exit();
    }

    /* Agrega un nuevo estudiante. */
    @FXML private void agregaEstudiante(ActionEvent evento) {
        DialogoEditaEstudiante dialogo;
        try {
            dialogo = new DialogoEditaEstudiante(escenario, null);
        } catch (IOException ioe) {
            String mensaje = ("Ocurrió un error al tratar de " +
                              "cargar el diálogo de estudiante.");
            dialogoError("Error al cargar interfaz", mensaje);
            return;
        }
        dialogo.showAndWait();
        tabla.requestFocus();
        if (!dialogo.isAceptado())
            return;
        bdd.agregaRegistro(dialogo.getEstudiante());
        try {
            conexion.enviaMensaje(Mensaje.REGISTRO_AGREGADO);
            conexion.enviaRegistro(dialogo.getEstudiante());
        } catch (IOException ioe) {
            dialogoError("Error con el servidor",
                         "No se pudo enviar un estudiante a agregar.");
        }
    }

    /* Edita un estudiante. */
    @FXML private void editaEstudiante(ActionEvent evento) {
        Estudiante estudiante = renglones.get(seleccion.get(0).getRow());
        DialogoEditaEstudiante dialogo;
        try {
            dialogo = new DialogoEditaEstudiante(escenario, estudiante);
        } catch (IOException ioe) {
            String mensaje = ("Ocurrió un error al tratar de " +
                              "cargar el diálogo de estudiante.");
            dialogoError("Error al cargar interfaz", mensaje);
            return;
        }
        dialogo.showAndWait();
        tabla.requestFocus();
        if (!dialogo.isAceptado())
            return;
        try {
            conexion.enviaMensaje(Mensaje.REGISTRO_MODIFICADO);
            conexion.enviaRegistro(estudiante);
            conexion.enviaRegistro(dialogo.getEstudiante());
        } catch (IOException ioe) {
            dialogoError("Error con el servidor",
                         "No se pudieron enviar estudiantes a modificar.");
        }
        bdd.modificaRegistro(estudiante, dialogo.getEstudiante());
    }

    /* Elimina uno o varios estudiantes. */
    @FXML private void eliminaEstudiantes(ActionEvent evento) {
        int s = seleccion.size();
        String titulo = (s > 1) ?
            "Eliminar estudiantes" : "Eliminar estudiante";
        String mensaje = (s > 1) ?
            "Esto eliminará a los estudiantes seleccionados" :
            "Esto eliminará al estudiante seleccionado";
        String aceptar = titulo;
        String cancelar = (s > 1) ?
            "Conservar estudiantes" : "Conservar estudiante";
        if (!dialogoDeConfirmacion(titulo, mensaje, "¿Está seguro?",
                                   aceptar, cancelar))
            return;
        Lista<Estudiante> aEliminar = new Lista<Estudiante>();
        for (TablePosition tp : seleccion)
            aEliminar.agregaFinal(renglones.get(tp.getRow()));
        modeloSeleccion.clearSelection();
        for (Estudiante estudiante : aEliminar) {
            bdd.eliminaRegistro(estudiante);
            try {
                conexion.enviaMensaje(Mensaje.REGISTRO_ELIMINADO);
                conexion.enviaRegistro(estudiante);
            } catch (IOException ioe) {
                dialogoError("Error con el servidor",
                             "No se pudo enviar un estudiante a eliminar.");
                return;
            }
        }
    }

    /* Busca estudiantes. */
    @FXML private void buscaEstudiantes(ActionEvent evento) {
        DialogoBuscaEstudiantes dialogo;
        try {
            dialogo = new DialogoBuscaEstudiantes(escenario);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            String mensaje = ("Ocurrió un error al tratar de " +
                              "cargar el diálogo de búsqueda.");
            dialogoError("Error al cargar interfaz", mensaje);
            return;
        }
        dialogo.showAndWait();
        tabla.requestFocus();
        if (!dialogo.isAceptado())
            return;

        Lista<Estudiante> resultados =
            bdd.buscaRegistros(dialogo.getCampo(),
                               dialogo.getValor());

        modeloSeleccion.clearSelection();
        for (Estudiante estudiante : resultados)
            modeloSeleccion.select(estudiante);
    }

    /* Muestra un diálogo con información del programa. */
    @FXML private void acercaDe(ActionEvent evento) {
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.initOwner(escenario);
        dialogo.initModality(Modality.WINDOW_MODAL);
        dialogo.setTitle("Acerca de Administrador de Estudiantes");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Aplicación para administrar estudiantes.\n"  +
                               "Copyright © 2022 Facultad de Ciencias, UNAM.");
        dialogo.showAndWait();
        tabla.requestFocus();
    }

    /**
     * Define el escenario.
     * @param escenario el escenario.
     */
    public void setEscenario(Stage escenario) {
        this.escenario = escenario;
    }

    /* Conecta el cliente con el servidor. */
    private void conectar() {
        DialogoConectar dialogo;
        try {
            dialogo = new DialogoConectar(escenario);
        } catch (IOException | IllegalStateException e) {
            String mensaje = ("Ocurrió un error al tratar de " +
                              "cargar el diálogo de conexión.");
            dialogoError("Error al cargar interfaz", mensaje);
            return;
        }
        dialogo.showAndWait();
        tabla.requestFocus();
        if (!dialogo.isAceptado())
            return;
        String direccion = dialogo.getDireccion();
        int puerto = dialogo.getPuerto();
        try {
            Socket enchufe = new Socket(direccion, puerto);
            conexion = new Conexion<Estudiante>(bdd, enchufe);
            new Thread(() -> conexion.recibeMensajes()).start();
            conexion.agregaEscucha((c, m) -> mensajeRecibido(c, m));
            conexion.enviaMensaje(Mensaje.BASE_DE_DATOS);
        } catch (IOException ioe) {
            conexion = null;
            String mensaje =
                String.format("Ocurrió un error al tratar de " +
                              "conectarnos a %s:%d.\n", direccion, puerto);
            dialogoError("Error al establecer conexión", mensaje);
            return;
        }
        setConectado(true);
    }

    /* Desconecta el cliente del servidor. */
    private void desconectar() {
        if (!conectado)
            return;
        setConectado(false);
        conexion.desconecta();
        conexion = null;
        bdd.limpia();
    }

    /* Cambia la interfaz gráfica dependiendo si estamos o no conectados. */
    private void setConectado(boolean conectado) {
        this.conectado = conectado;
        if (conectado)
            menuConexion.setText("Desconectar");
        else
            menuConexion.setText("Conectar...");
        menuAgregar.setDisable(!conectado);
        menuBuscar.setDisable(!conectado);
        botonAgregar.setDisable(!conectado);
        botonBuscar.setDisable(!conectado);
    }

    /* Maneja un evento de cambio en la base de datos. */
    private void eventoBaseDeDatos(EventoBaseDeDatos evento,
                                   Estudiante estudiante1,
                                   Estudiante estudiante2) {
        switch (evento) {
        case BASE_LIMPIADA:
            Platform.runLater(() -> renglones.clear());
            break;
        case REGISTRO_AGREGADO:
            Platform.runLater(() -> agregaEstudiante(estudiante1));
            break;
        case REGISTRO_ELIMINADO:
            Platform.runLater(() -> eliminaEstudiante(estudiante1));
            break;
        case REGISTRO_MODIFICADO:
            Platform.runLater(() -> tabla.sort());
            break;
        }
    }

    /* Actualiza la interfaz dependiendo del número de renglones
     * seleccionados. */
    private void cambioSeleccion() {
        int s = seleccion.size();
        menuEliminar.setDisable(s == 0);
        menuEditar.setDisable(s != 1);
        botonEliminar.setDisable(s == 0);
        botonEditar.setDisable(s != 1);
    }

    /* Crea un diálogo con una pregunta que hay que confirmar. */
    private boolean dialogoDeConfirmacion(String titulo,
                                          String mensaje, String pregunta,
                                          String aceptar, String cancelar) {
        Alert dialogo = new Alert(AlertType.CONFIRMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(mensaje);
        dialogo.setContentText(pregunta);

        ButtonType si = new ButtonType(aceptar);
        ButtonType no = new ButtonType(cancelar, ButtonData.CANCEL_CLOSE);
        dialogo.getButtonTypes().setAll(si, no);

        Optional<ButtonType> resultado = dialogo.showAndWait();
        tabla.requestFocus();
        return resultado.get() == si;
    }

    /* Recibe los mensajes de la conexión. */
    private void mensajeRecibido(Conexion<Estudiante> conexion, Mensaje mensaje) {
        switch (mensaje) {
        case BASE_DE_DATOS:
            baseDeDatos(conexion);
            break;
        case REGISTRO_AGREGADO:
            registroAlterado(conexion, mensaje);
            break;
        case REGISTRO_ELIMINADO:
            registroAlterado(conexion, mensaje);
            break;
        case REGISTRO_MODIFICADO:
            registroModificado(conexion);
            break;
        case DESCONECTAR:
            Platform.runLater(() -> desconectar());
            break;
        case DETENER_SERVICIO:
            // Se ignora.
            break;
        case ECO:
            // Se ignora.
            break;
        case INVALIDO:
            Platform.runLater(() -> dialogoError("Error con el servidor",
                                                 "Mensaje inválido recibido. " +
                                                 "Se finalizará la conexión."));
            break;
        }
    }

    /* Maneja el mensaje BASE_DE_DATOS. */
    private void baseDeDatos(Conexion<Estudiante> conexion) {
        try {
            conexion.recibeBaseDeDatos();
        } catch (IOException ioe) {
            String m = "No se pudo recibir la base de datos. " +
                "Se finalizará la conexión.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
    }

    /* Maneja los mensajes REGISTRO_AGREGADO y REGISTRO_MODIFICADO. */
    private void registroAlterado(Conexion<Estudiante> conexion,
                                  Mensaje mensaje) {
        Estudiante e;
        try {
            e = conexion.recibeRegistro();
        } catch (IOException ioe) {
            String m = "No se pudo recibir un registro. " +
                "Se finalizará la conexión.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        if (mensaje == Mensaje.REGISTRO_AGREGADO)
            bdd.agregaRegistro(e);
        else
            bdd.eliminaRegistro(e);
    }

    /* Maneja el mensaje REGISTRO_MODIFICADO. */
    private void registroModificado(Conexion<Estudiante> conexion) {
        Estudiante e1, e2;
        try {
            e1 = conexion.recibeRegistro();
            e2 = conexion.recibeRegistro();
        } catch (IOException ioe) {
            String m = "No se pudieron recibir registros." +
                "Se finalizará la conexión.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        bdd.modificaRegistro(e1, e2);
    }

    /* Muestra un diálogo de error. */
    private void dialogoError(String titulo, String mensaje) {
        if (conectado)
            desconectar();
        Alert dialogo = new Alert(AlertType.ERROR);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.setOnCloseRequest(e -> renglones.clear());
        dialogo.showAndWait();
        tabla.requestFocus();
    }

    /* Agrega un estudiante a la tabla. */
    private void agregaEstudiante(Estudiante estudiante) {
        renglones.add(estudiante);
        tabla.sort();
    }

    /* Elimina un estudiante de la tabla. */
    private void eliminaEstudiante(Estudiante estudiante) {
        renglones.remove(estudiante);
        tabla.sort();
    }
}
