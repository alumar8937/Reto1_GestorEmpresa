import java.util.Scanner;
import java.util.ArrayList;

// Author: Pedro Marín Sanchis
public class GestorEmpresa {

    private static Scanner inputValue = new Scanner(System.in);
    private static ArrayList<Departamento> departamentos = new ArrayList<Departamento>(1);
    private static ArrayList<Empleado> empleados = new ArrayList<Empleado>(1);

    private static Boolean condicionDeSalida = false;

    public static void main(String[] args) { // Author: Raúl Simarro Navarro

        while (!condicionDeSalida) {

            mostrarMenu();

        }

        inputValue.close();

    }

    private static void limpiarPantalla(){ // Author: Raúl Simarro Navarro
        System.out.print("\n".repeat(50));
    }

    private static void mostrarError(String textoError) { // Author: Raúl Simarro Navarro
        limpiarPantalla();
        System.out.print("[Error]: " + textoError + "\nPulsa ENTER para continuar...");
        inputValue.nextLine();
        limpiarPantalla();
    }

    private static void mostrarMenu() { // Author: Raúl Simarro Navarro

        mostrarError("Prueba de error!");
        System.out.println("| 0.- Salir |1.-|2.-|3.-|4.-|5.-|");
        condicionDeSalida = true;

    }



}
