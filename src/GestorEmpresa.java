// Author: Pedro Marín Sanchis

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
public class GestorEmpresa {

    final private static Scanner inputValue = new Scanner(System.in);

    final private static String delimitador_CSV = ";";
    private static ArrayList<Departamento> departamentos = new ArrayList<>(1);
    private static ArrayList<Empleado> empleados = new ArrayList<>(1);

    private static Boolean condicionDeSalida = false;

    public static void main(String[] args) { // Author: Raúl Simarro Navarro

        while (!condicionDeSalida) {

            limpiarPantalla();
            mostrarMenu();
            limpiarPantalla();

       }

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

    private static File escogerArchivo(boolean avisarSiExiste, String mensaje) { // Author: Pedro Marín Sanchis

        boolean entradaCorrecta = false;
        File archivo = null;

        while (!entradaCorrecta) {

            limpiarPantalla();
            System.out.print(mensaje);
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

    private static void cargarEmpleados(DocumentoCSV Datos_Empresa, DocumentoCSV Datos_Personales) { // Author: Pedro Marín Sanchis

        ArrayList<Empleado> lista = new ArrayList<>(1);

        if (Datos_Personales.obtenerCantidadDeCampos() > 0 && Datos_Empresa.obtenerCantidadDeCampos() > 0) {

            for (int i = 1; i < Datos_Personales.obtenerCantidadDeCampos() - 1; i++) {

                int id_usuario = Integer.parseInt(Datos_Personales.obtenerValor(i,0));
                int id_departamento = Integer.parseInt(Datos_Empresa.obtenerValor(i,1));
                String NIF = Datos_Personales.obtenerValor(i, 1);
                String nombre = Datos_Personales.obtenerValor(i, 2);
                String apellido1 = Datos_Personales.obtenerValor(i, 3);
                String apellido2 = Datos_Personales.obtenerValor(i, 4);
                int num_SegSocial = 0;
                String antiguedad = Datos_Empresa.obtenerValor(i, 2);
                String cat_GrupoProfesional = Datos_Empresa.obtenerValor(i, 3);
                int grupo_Cotizacion = Integer.parseInt(Datos_Empresa.obtenerValor(i, 4));
                String email = Datos_Empresa.obtenerValor(i, 5);

                lista.add(new Empleado(id_usuario, id_departamento, NIF, nombre, apellido1, apellido2, num_SegSocial, antiguedad, cat_GrupoProfesional, grupo_Cotizacion, email));

            }

            empleados = lista;

        } else {

            mostrarError("Los archivos no son válidos.");

        }

    }

    private static void cargarDepartamentos(DocumentoCSV Departamento) { // Author: Pedro Marín Sanchis

        ArrayList<Departamento> lista = new ArrayList<>(1);

        if (Departamento.obtenerCantidadDeCampos() > 0) {

            for (int i = 1; i < Departamento.obtenerCantidadDeCampos(); i++) {

                lista.add(new Departamento(Integer.parseInt(Departamento.obtenerValor(i, 0)), Departamento.obtenerValor(i, 1)));

            }

            departamentos = lista;

        } else {

            mostrarError("Los archivos no son válidos.");

        }

    }

    private static DocumentoCSV crearDepartamentoCSV() throws IOException { // Author: Pedro Marín Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        if (departamentos.size() > 0) {

            for (Departamento i: departamentos) {
                documento.anyadirTuple(new String[]{String.valueOf(i.getId()), i.getNombre()});
            }

        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

}
