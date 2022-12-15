// Author: Pedro Marín Sanchis

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
public class GestorEmpresa {

    final private static Scanner inputValue = new Scanner(System.in);
    private static ArrayList<Departamento> departamentos = new ArrayList<>(1);
    private static ArrayList<Empleado> empleados = new ArrayList<>(1);

    private static Boolean condicionDeSalida = false;

    public static void probarClaseCSV() { // Author: Pedro Marín Sanchis

        DocumentoCSV document = null;
        try {
            document = new DocumentoCSV(new File("CSV/Datos_Empresa.csv"), ";");
            System.out.println(document.obtenerValor(4,5));
            document.cambiarValor(4,5,"Jose");
            System.out.println(document.obtenerValor(4,5));
            System.out.println(document.obtenerContenidoTextoPlano());
            exportarTexto(escogerArchivo(true), document.obtenerContenidoTextoPlano());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) { // Author: Raúl Simarro Navarro

        while (!condicionDeSalida) {

            limpiarPantalla();
            mostrarMenu();
            limpiarPantalla();

       }

        probarClaseCSV();

        inputValue.close();

    }

    private static void limpiarPantalla(){ // Author: Raúl Simarro Navarro
        System.out.print("\n".repeat(50));
    }

    private static void mostrarError(String mensaje) { // Author: Raúl Simarro Navarro
        limpiarPantalla();
        System.out.print("[Error]: " + mensaje + "\nPulsa ENTER para continuar...");
        inputValue.nextLine();
        limpiarPantalla();
    }

    private static boolean mostrarPeticionDeConfirmacion(String mensaje) { // Author: Raúl Simarro Navarro, Pedro Marín Sanchis

        while (true) {
            limpiarPantalla();
            System.out.print("[Aviso]: " + mensaje + "\n¿Deseas continuar? [Y/N]: ");
            switch (inputValue.nextLine().toUpperCase()) {
                case "Y": return true;
                case "N": return false;
            }
            limpiarPantalla();
        }

    }

    private static void mostrarMenu() { // Author: Raúl Simarro Navarro

        System.out.println("| 0.- Salir |1.-|2.-|3.-|4.-|5.-|");
        condicionDeSalida = true;

    }

    private static File escogerArchivo(boolean avisarSiExiste) { // Author: Pedro Marín Sanchis

        boolean entradaCorrecta = false;
        File archivo = null;

        while (!entradaCorrecta) {

            limpiarPantalla();
            System.out.print("Escribe la ruta del archivo: ");
            archivo = new File(inputValue.nextLine());
            if (archivo.exists() && avisarSiExiste) {

                if (mostrarPeticionDeConfirmacion("Este archivo ya existe, en caso de continuar será reemplazado.")) {

                    entradaCorrecta = true;

                }

            } else {

                entradaCorrecta = true;

            }

        }

        return archivo;

    }

    private static void exportarTexto(File destino, String contenido) { // Author: Pedro Marín Sanchis

        try {
            FileWriter writer = new FileWriter(destino);
            writer.write(contenido);
            writer.close();
        } catch (IOException e) {
            mostrarError("Error al exportar!");
        }

    }

}
