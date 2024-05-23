package mx.unam.ciencias.icc.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import mx.unam.ciencias.icc.Estudiante;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * estudiantes.
 */
public class ControladorFormaEditaEstudiante
    extends ControladorFormaEstudiante {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para el número de cuenta. */
    @FXML private EntradaVerificable entradaCuenta;
    /* La entrada verificable para el promedio. */
    @FXML private EntradaVerificable entradaPromedio;
    /* La entrada verificable para la edad. */
    @FXML private EntradaVerificable entradaEdad;

    /* El estudiante creado o editado. */
    private Estudiante estudiante;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaCuenta.setVerificador(c -> verificaCuenta(c));
        entradaPromedio.setVerificador(p -> verificaPromedio(p));
        entradaEdad.setVerificador(e -> verificaEdad(e));

        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
        entradaCuenta.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
        entradaPromedio.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
        entradaEdad.textProperty().addListener(
            (o, v, n) -> verificaEstudiante());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaEstudiante();
        aceptado = true;
        escenario.close();
    }

    /* Actualiza al estudiante, o lo crea si no existe. */
    private void actualizaEstudiante() {
        if (estudiante != null) {
            estudiante.setNombre(nombre);
            estudiante.setCuenta(cuenta);
            estudiante.setPromedio(promedio);
            estudiante.setEdad(edad);
        } else {
            estudiante = new Estudiante(nombre, cuenta, promedio, edad);
        }
    }

    /**
     * Define el estudiante del diálogo.
     * @param estudiante el nuevo estudiante del diálogo.
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        if (estudiante == null)
            return;
        this.estudiante = new Estudiante(null, 0, 0, 0);
        this.estudiante.actualiza(estudiante);
        entradaNombre.setText(estudiante.getNombre());
        String c = String.format("%09d", estudiante.getCuenta());
        entradaCuenta.setText(c);
        String p = String.format("%2.2f", estudiante.getPromedio());
        entradaPromedio.setText(p);
        entradaEdad.setText(String.valueOf(estudiante.getEdad()));
    }

    /**
     * Regresa el estudiante del diálogo.
     * @return el estudiante del diálogo.
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaNombre.requestFocus();
    }

    /* Verifica que los cuatro campos sean válidos. */
    private void verificaEstudiante() {
        boolean n = entradaNombre.esValida();
        boolean c = entradaCuenta.esValida();
        boolean p = entradaPromedio.esValida();
        boolean e = entradaEdad.esValida();
        botonAceptar.setDisable(!n || !c || !p || !e);
    }

    /**
     * Verifica que el número de cuenta sea válido.
     * @param cuenta el número de cuenta a verificar.
     * @return <code>true</code> si el número de cuenta es válido;
     *         <code>false</code> en otro caso.
     */
    @Override protected boolean verificaCuenta(String cuenta) {
        return super.verificaCuenta(cuenta) &&
            this.cuenta >= 10000000 && this.cuenta < 999999999;
    }

    /**
     * Verifica que el promedio sea válido.
     * @param promedio el promedio a verificar.
     * @return <code>true</code> si el promedio es válido; <code>false</code> en
     *         otro caso.
     */
    @Override protected boolean verificaPromedio(String promedio) {
        return super.verificaPromedio(promedio) &&
            this.promedio >= 0.0 && this.promedio <= 10.0;
    }

    /**
     * Verifica que la edad sea válida.
     * @param edad la edad a verificar.
     * @return <code>true</code> si la edad es válida; <code>false</code> en
     *         otro caso.
     */
    @Override protected boolean verificaEdad(String edad) {
        return super.verificaEdad(edad) &&
            this.edad >= 13 && this.edad <= 99;
    }
}
