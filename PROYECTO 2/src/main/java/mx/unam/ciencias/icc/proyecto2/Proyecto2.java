package mx.unam.ciencias.icc.proyecto2;

public class Proyecto2 {
    static void app() {
        
        System.out.println("Uso: java -jar proyecto2.jar [opciones]");
        System.out.println("Opciones: ");
        System.out.println("  -r <archivo>, Ordena de forma inversa el archivo.");
        System.out.println(
                "  -o <archivos de entrada separados por espacios> <archivo de salida> Ordena los archivos de entrada y los guarda en un archivo.");
    }

    public static void main(String[] args) {

        try {
            // Aquí va tu lógica principal del programa
        } catch (ExcepcionArchivoInvalido e) {
            System.err.println("Error: Archivo inválido. " + e.getMessage());
        } catch (ExcepcionIndiceInvalido e) {
            System.err.println("Error: Índice inválido. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
