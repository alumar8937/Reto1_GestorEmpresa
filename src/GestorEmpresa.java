import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
public class GestorEmpresa {

    private static Boolean condicionDeSalida = false;
    final private static Scanner inputValue = new Scanner(System.in);
    final private static String delimitador_CSV = ";";

    private static ArrayList<Categoria> categorias = new ArrayList<>(1); //
    private static ArrayList<Empleado> empleados = new ArrayList<>(1); // Datos_Empresa.csv + Datos_Personales.csv
    private static ArrayList<Departamento> departamentos = new ArrayList<>(1); // Departamento.csv
    private static ArrayList<GrupoCotizacion> gruposCotizacion = new ArrayList<>(1); //
    private static ArrayList<HoraExtra> horasExtras = new ArrayList<>(1);  //

    // CABECERAS CSV

    final private static String cabecera_Categoria = "cat_GrupoProfesional";
    final private static String cabecera_Datos_Empresa = "id_usuario;id_departamento;antiguedad;cat_GrupoProfesional;grupo_Cotizacion;email";
    final private static String cabecera_Datos_Personales = "id_usuario;NIF;Nombre;Apellido1;Apellido2;num_SegSocial";
    final private static String cabecera_Departamento = "id;Nombre";
    final private static String cabecera_Grupo_Cotizacion = "Id;sueldo_base";
    final private static String cabecera_Hores_extres = "id_usuario;data";
    public static void main(String[] args) { // Author: Raúl Simarro Navarro

        // Carga de datos hardcodeada

        try {
            cargarCategorias(new DocumentoCSV(new File("CSV/Categoria.csv"), delimitador_CSV));
            cargarEmpleados(new DocumentoCSV(new File("CSV/Datos_Empresa.csv"), delimitador_CSV), new DocumentoCSV(new File("CSV/Datos_Personales.csv"), delimitador_CSV));
            cargarDepartamentos(new DocumentoCSV(new File("CSV/Departamento.csv"), delimitador_CSV));
            cargarGrupo_Cotizacion(new DocumentoCSV(new File("CSV/Grupo_Cotizacion.csv"), delimitador_CSV));
            cargarHores_extres(new DocumentoCSV(new File("CSV/Hores_extres.csv"), delimitador_CSV));
        } catch (IOException e) {
            mostrarError("No se ha podido cargar.");
        }

        // Prueba de exportado

        try {
            crearCategoriaCSV().exportarComoArchivo(new File("Export/Categoria.csv"));
            crearDatos_EmpresaCSV().exportarComoArchivo(new File("Export/Datos_Empresa.csv"));
            crearDatos_PersonalesCSV().exportarComoArchivo(new File("Export/Datos_Personales.csv"));
            crearDepartamentoCSV().exportarComoArchivo(new File("Export/Departamento.csv"));
            crearGrupo_CotizacionCSV().exportarComoArchivo(new File("Export/Grupo_Cotizacion.csv"));
            crearHores_extresCSV().exportarComoArchivo(new File("Export/Hores_extres.csv"));
        } catch (IOException e) {
            mostrarError("No se ha podido exportar.");
        }

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
        mostrarMensaje("[Error]: " + mensaje);
    }

    private static void mostrarMensaje(String mensaje) { // Author: Raúl Simarro Navarro
        limpiarPantalla();
        System.out.print(mensaje + "\nPulsa ENTER para continuar...");
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

    private static void cargarCategorias(DocumentoCSV Categoria) { // Author: Pedro Marín Sanchis

        ArrayList<Categoria> lista = new ArrayList<>(1);

        if (Categoria.obtenerCantidadDeCampos() > 0) {

            for (int i = 1; i < Categoria.obtenerCantidadDeCampos(); i++) {

                lista.add(new Categoria(Categoria.obtenerValor(i, 0)));

            }

            categorias = lista;

        } else {

            mostrarError("Los archivos no son válidos.");

        }

    }

    private static void cargarEmpleados(DocumentoCSV Datos_Empresa, DocumentoCSV Datos_Personales) { // Author: Pedro Marín Sanchis

        ArrayList<Empleado> lista = new ArrayList<>(1);

        if (Datos_Personales.obtenerCantidadDeCampos() > 0 && Datos_Empresa.obtenerCantidadDeCampos() > 0) {

            for (int i = 1; i < Datos_Personales.obtenerCantidadDeCampos(); i++) {

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

    private static void cargarGrupo_Cotizacion(DocumentoCSV Grupo_Cotizacion) { // Author: Pedro Marín Sanchis

        ArrayList<GrupoCotizacion> lista = new ArrayList<>(1);

        if (Grupo_Cotizacion.obtenerCantidadDeCampos() > 0) {

            for (int i = 1; i < Grupo_Cotizacion.obtenerCantidadDeCampos(); i++) {

                lista.add(new GrupoCotizacion(Integer.parseInt(Grupo_Cotizacion.obtenerValor(i, 0)), Integer.parseInt(Grupo_Cotizacion.obtenerValor(i, 1))));

            }

            gruposCotizacion = lista;

        } else {

            mostrarError("Los archivos no son válidos.");

        }

    }

    private static void cargarHores_extres(DocumentoCSV Hores_extres) {

        ArrayList<HoraExtra> lista = new ArrayList<>(1);

        if (Hores_extres.obtenerCantidadDeCampos() > 0) {

            for (int i = 1; i < Hores_extres.obtenerCantidadDeCampos(); i++) {

                lista.add(new HoraExtra(Integer.parseInt(Hores_extres.obtenerValor(i, 0)), Hores_extres.obtenerValor(i, 1)));

            }

            horasExtras = lista;

        } else {

            mostrarError("Los archivos no son bruh válidos.");

        }

    }

    private static DocumentoCSV crearCategoriaCSV() throws IOException { // Author: Pedro Marín Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        documento.anyadirTuple(cabecera_Categoria, delimitador_CSV);

        if (categorias.size() > 0) {
            for (Categoria i: categorias) {
                documento.anyadirTuple(new String[]{i.getCat_GrupoProfesional()});
            }
        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

    private static DocumentoCSV crearDatos_EmpresaCSV() throws IOException { // Author: Pedro Marin Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        documento.anyadirTuple(cabecera_Datos_Empresa, delimitador_CSV);

        if (empleados.size() > 0) {
            for (Empleado i: empleados) {
                documento.anyadirTuple(new String[]{Integer.toString(i.getId_usuario()), Integer.toString(i.getId_departamento()), i.getAntiguedad(), i.getCat_GrupoProfesional(), Integer.toString(i.getGrupo_Cotizacion()), i.getEmail()});
            }
        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

    private static DocumentoCSV crearDatos_PersonalesCSV() throws IOException { // Author: Pedro Marín Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        documento.anyadirTuple(cabecera_Datos_Personales, delimitador_CSV);

        if (empleados.size() > 0) {
            for (Empleado i: empleados) {
                documento.anyadirTuple(new String[]{Integer.toString(i.getId_usuario()), i.getNIF(), i.getNombre(), i.getApellido1(), i.getApellido2(), Integer.toString(i.getNum_SegSocial())});
            }
        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

    private static DocumentoCSV crearDepartamentoCSV() throws IOException { // Author: Pedro Marín Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        documento.anyadirTuple(cabecera_Departamento, delimitador_CSV);

        if (departamentos.size() > 0) {

            for (Departamento i: departamentos) {
                documento.anyadirTuple(new String[]{Integer.toString(i.getId()), i.getNombre()});
            }

        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

    private static DocumentoCSV crearGrupo_CotizacionCSV() throws IOException { // Author: Pedro Marín Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        documento.anyadirTuple(cabecera_Grupo_Cotizacion, delimitador_CSV);

        if (departamentos.size() > 0) {
            for (GrupoCotizacion i: gruposCotizacion) {
                documento.anyadirTuple(new String[]{Integer.toString(i.getId()), Integer.toString(i.getSueldo_base())});
            }
        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

    private static DocumentoCSV crearHores_extresCSV() throws IOException { // Author: Pedro Marín Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        documento.anyadirTuple(cabecera_Hores_extres, delimitador_CSV);

        if (horasExtras.size() > 0) {
            for (HoraExtra i : horasExtras) {
                documento.anyadirTuple(new String[]{Integer.toString(i.getId_usuario()), i.getFecha()});
            }
        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }
    private static void obtenerDatosPersonalesID() { // Author: David Serna Mateu

        int id_pedido;

        System.out.println("Introduzca la id del empleado:");
        try {
            id_pedido = inputValue.nextInt();
            inputValue.nextLine();


            for(Empleado empleado : empleados){
                if(id_pedido == empleado.getId_usuario()){
                    mostrarMensaje("NIF del empleado: " + empleado.getNIF() +
                            "\nNombre del empleado: " + empleado.getNombre() +
                            "\nPrimer apellido del empleado: " + empleado.getApellido1() +
                            "\nSegundo apellido del empleado: " + empleado.getApellido2() +
                            "\nNúmero de la Seguridad Social del empleado: " + empleado.getNum_SegSocial());
                }
            }
        } catch (Exception e) {
            mostrarError("Entrada no válida.");
        }

    }

    private static void obtenerDatosEmpresaID() { //Author: Javier Blasco
        int id_solicitada;

        System.out.println("Introduce la id del empleado");
        try {
            id_solicitada = inputValue.nextInt();
            inputValue.nextLine();

            for(Empleado empleado : empleados){
                if(id_solicitada == empleado.getId_usuario()){
                    mostrarMensaje("ID del departamento: " + empleado.getId_departamento() +
                            "\nAntiguedad del empleado: " + empleado.getAntiguedad() +
                            "\nCategoría profesional al que pertence: " + empleado.getCat_GrupoProfesional() +
                            "\nGrupo de cotización al que pertenece: " + empleado.getGrupo_Cotizacion() +
                            "\nEmail del empleado: " + empleado.getEmail());
                }
            }
        } catch (Exception e) {
            mostrarError("Entrada no válida.");
        }
    }

    private static void consultarEmpleadosDepartamento() { //Author: David Serna
        String departamento;
        int id_dep = 0;
        System.out.println("Introduzca el nombre del departamento:");
        try {
            departamento = inputValue.nextLine().toUpperCase();

            for (Departamento dep : departamentos){
                if(departamento.equals(dep.getNombre())){
                    id_dep = dep.getId();
                }
            }

            for(Empleado empleado : empleados){
                if(id_dep == empleado.getId_departamento()){
                    mostrarMensaje("NIF del empleado: " + empleado.getNIF() +
                            "\nNombre del empleado: " + empleado.getNombre() +
                            "\nPrimer apellido del empleado: " + empleado.getApellido1() +
                            "\nSegundo apellido del empleado: " + empleado.getApellido2() +
                            "\nNúmero de la Seguridad Social del empleado: " + empleado.getNum_SegSocial());
                }
            }
        } catch (Exception e) {
            mostrarError("Entrada no válida.");
        }

    }

    private static void costeSalarial() { //Author: Javier Blasco
        try {
            for(GrupoCotizacion coste : gruposCotizacion) {
                for (Empleado empleado : empleados) {
                    if (coste.getId() == empleado.getGrupo_Cotizacion()) {
                        int costeTotal = coste.getSueldo_base() + coste.getSueldo_base();
                        mostrarMensaje("Coste salarial de la empresa: " + costeTotal);
                    }
                }
            }
        } catch (Exception e) {
            mostrarError("Error");
        }
    }

   private static void horasEXDepartamento() { //Author: Javier Blasco
        /*String departamento;
        int id_dep = 0;
        System.out.println("Introduzca el nombre del departamento:");
        try {
            departamento = inputValue.nextLine().toUpperCase();

            for (Departamento dep : departamentos){
                if(departamento.equals(dep.getNombre())){
                    id_dep = dep.getId();
                }
            }

            for(Empleado empleado : empleados){
                if(id_dep == empleado.getId_departamento()){
                    for(HoraExtra hora : horasExtras) {
                        if (empleado.getId_usuario() == hora.getId_usuario()) {
                            int horasTotaless = hora.getFecha() + hora.getFecha();
                        }
                    }
                }
            }
        } catch (Exception e) {
            mostrarError("Entrada no válida.");
        }*/
    }

    private static void agregarDatosUsuario(){ //Author: David Serna
        try {
            boolean check = false;
            System.out.println("Introduzca la id del departamento del empleado: ");
            int id_departamento = inputValue.nextInt();
            inputValue.nextLine();
            System.out.println("Introduzca el NIF del empleado: ");
            String NIF = inputValue.nextLine();
            System.out.println("Introduzca el nombre del empleado: ");
            String nombre = inputValue.nextLine();
            System.out.println("Introduzca el primer apellido del empleado: ");
            String apellido1 = inputValue.nextLine();
            System.out.println("Introduzca el segundo apellido del empleado: ");
            String apellido2 = inputValue.nextLine();
            System.out.println("Introduzca el numero de la SS del empleado: ");
            int num_SegSocial = inputValue.nextInt();
            inputValue.nextLine();
            System.out.println("Introduzca la fecha en la que empezo a trabajar el empleado (Ejemplo: dd/mm/year): ");
            String antiguedad = inputValue.nextLine();
            System.out.println("Introduzca la categoria del grupo profesional del empleado: ");
            String cat_GrupoProfesional = inputValue.nextLine();
            System.out.println("Introduzca el grupo de cotización del empleado: ");
            int grupo_Cotizacion = inputValue.nextInt();
            inputValue.nextLine();
            System.out.println("Introduzca el email del usuario: ");
            String email = inputValue.nextLine();

            mostrarMensaje("NIF del empleado: " + NIF +
                    "\nNombre del empleado: " + nombre +
                    "\nPrimer apellido del empleado: " + apellido1 +
                    "\nSegundo apellido del empleado: " + apellido2 +
                    "\nNúmero de la Seguridad Social del empleado: " + num_SegSocial +
                    "\nID del departamento: " + id_departamento +
                    "\nAntiguedad del empleado: " + antiguedad +
                    "\nCategoría profesional al que pertence: " + cat_GrupoProfesional +
                    "\nGrupo de cotización al que pertenece: " + grupo_Cotizacion +
                    "\nEmail del empleado: " + email);

            System.out.println("Los datos son correctos (Y/N):");
            if(inputValue.next().toUpperCase().equals("Y")) {
                check = true;
            }else{
                return;
            }
            int indicemax = 0;
            for(Empleado empleado : empleados){
                if(empleado.getId_usuario() > indicemax){
                    indicemax = empleado.getId_usuario();
                }
            }

            empleados.add(new Empleado(indicemax + 1,id_departamento,NIF,nombre,apellido1,apellido2,num_SegSocial,antiguedad,
                    cat_GrupoProfesional,grupo_Cotizacion,email));


        } catch (Exception e) {

            mostrarError("Entrada no válida.");

        }

    }

    private static void agregarDatosGrupoCotizacion() { //Author: David Serna
        try {
            boolean check = false;
            System.out.println("Introduzca el sueldo del base del grupo cotización: ");
            int sueldo_grupcot = inputValue.nextInt();
            inputValue.nextLine();

            mostrarMensaje("Sueldo base del grupo_cot: " + sueldo_grupcot);

            System.out.println("Los datos son correctos (Y/N):");
            if(inputValue.next().toUpperCase().equals("Y")) {
                check = true;
            }else{
                return;
            }
            int indicemax = 0;
            for(GrupoCotizacion grupo_cot: gruposCotizacion){
                if(grupo_cot.getId() > indicemax){
                    indicemax = grupo_cot.getId();
                }
            }

            gruposCotizacion.add(new GrupoCotizacion(indicemax + 1, sueldo_grupcot));


        } catch (Exception e) {

            mostrarError("Entrada no válida.");

        }
    }

    private static void modificarDatosPersonales() { //Author: Javier Blasco
        try {
            for (Empleado empleado : empleados) {
                empleado.getId_usuario();

            }
        }catch (Exception e){
            System.out.println("Los datos no son validos.");
        }
    }
}
