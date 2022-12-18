import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class GestorEmpresa {

    private static Boolean condicionDeSalida = false;
    private static Boolean salir = false;
    final private static Scanner inputValue = new Scanner(System.in);
    final private static String delimitador_CSV = ";";

    private static ArrayList<Categoria> categorias = new ArrayList<>(1); // Categoria.csv
    private static ArrayList<Empleado> empleados = new ArrayList<>(1); // Datos_Empresa.csv + Datos_Personales.csv
    private static ArrayList<Departamento> departamentos = new ArrayList<>(1); // Departamento.csv
    private static ArrayList<GrupoCotizacion> gruposCotizacion = new ArrayList<>(1); // Grupo_Cotizacion.csv
    private static ArrayList<HoraExtra> horasExtras = new ArrayList<>(1);  // Hores_exres.csv

    // CABECERAS CSV

    final private static String cabecera_Categoria = "cat_GrupoProfesional";
    final private static String cabecera_Datos_Empresa = "id_usuario;id_departamento;antiguedad;cat_GrupoProfesional;grupo_Cotizacion;email";
    final private static String cabecera_Datos_Personales = "id_usuario;NIF;Nombre;Apellido1;Apellido2;num_SegSocial";
    final private static String cabecera_Departamento = "id;Nombre";
    final private static String cabecera_Grupo_Cotizacion = "Id;sueldo_base";
    final private static String cabecera_Hores_extres = "id_usuario;hora";

    public static void main(String[] args) { // Author: Raúl Simarro Navarro

        importarDatos();

        while (!condicionDeSalida) {

            limpiarPantalla();
            mostrarMenu();

        }

        exportarDatos();

        inputValue.close();

    }

    private static String leerCadena(String mensaje) { //Author: David Serna
        System.out.println(mensaje);
        return inputValue.nextLine();
    }

    private static int leerEntero(String mensaje) { //Author: David Serna
        System.out.println(mensaje);
        try {
            int entero = inputValue.nextInt();
            inputValue.nextLine();
            return entero;
        }catch(Exception e){
            return 0;
        }
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
                case "Y":
                    limpiarPantalla();
                    return true;
                case "N":
                    limpiarPantalla();
                    return false;
            }
        }

    }

    private static void mostrarMenu() { // Author: Raúl Simarro Navarro, David Serna

        String textoMenu = "1.-Empleados \n2.-Departamentos \n3.-Grupos Cotización \n4.-Coste salarial empresa \n" +
                "5.-Salir del programa \nIntroduzca una opción válida:";

        switch(leerEntero(textoMenu)){
            case 1:
                menuEmpleado();
                break;
            case 2:
                menuDepartamento();
                break;
            case 3:
                menuGruposCot();
                break;
            case 4:
                costeSalarial();
                break;
            case 5:
                condicionDeSalida = true;
                break;
            default:
                mostrarMensaje("Has introducido una opción no válida.");
        }

    }

    private static void menuEmpleado() { //Author: David Serna
        salir = false;
        while(!salir){

            limpiarPantalla();

            String textoMenu = "1.-Consultar datos personales del trabajador por ID \n2.-Consultar datos de empresa del " +
                    "trabajador por ID \n3.-Añadir datos personales y de empresa de un nuevo trabajador " +
                    "\n4.-Modificar datos personales de un empleado por ID. \n5.-Modificar datos de empresa de un empleado por ID" +
                    "\n6.-Eliminar un empleado \n7.-Volver a atras \nIntroduzca una opción válida:";

            switch(leerEntero(textoMenu)){
                case 1:
                    obtenerDatosPersonalesID();
                    break;
                case 2:
                    obtenerDatosEmpresaID();
                    break;
                case 3:
                    agregarDatosUsuario();
                    break;
                case 4:
                    modificarDatosPersonales();
                    break;
                case 5:
                    modificarDatosEmpresa();
                    break;
                case 6:
                    eliminarEmpleado();
                    limpiarPantalla();
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    mostrarMensaje("Has introducido una opción no válida.");
            }
        }
    }

    private static void menuDepartamento() { //Author: David Serna
        salir = false;
        while(!salir){

            limpiarPantalla();

            String textoMenu = "1.-Consultar los datos de los empleados de un departamento " +
                    "\n2.-Consultar la cantidad de horas extras segun departamento \n3.-Agregar departamento"+
                    "\n4.-Modificar departamento \n5.-Eliminar departamento" +
                    "\n6.-Volver a atras \nIntroduzca una opción válida:";

            switch(leerEntero(textoMenu)){
                case 1:
                    consultarEmpleadosDepartamento();
                    break;
                case 2:
                    horasExtraDepartamento();
                    break;
                case 3:
                    agregarDep();
                    break;
                case 4:
                    modificarDep();
                    break;
                case 5:
                    eliminarDep();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    mostrarMensaje("Has introducido una opción no válida.");
            }
        }
    }

    private static void menuGruposCot() { //Author: David Serna
        salir = false;
        while(!salir){

            limpiarPantalla();

            String textoMenu = "1.-Consultar la cantidad de trabajadores pertenecientes a un grupo de cotización" +
                    "\n2.-Consultar la cantidad de horas extras segun grupo de cotización \n3.-Agregar grupo de cotización "+
                    "\n4.-Eliminar grupo de cotización \n5.-Volver a atras \nIntroduzca una opción válida:";

            switch(leerEntero(textoMenu)){
                case 1:
                    mostrarMensaje("En este grupo de cotización hay: " + contarEmpleadosGrupoCot(leerEntero("Introduce la id del grupo de cotización:"))+ " empleados.");
                    break;
                case 2:
                    horasExtraGrupoCotizacion();
                    break;
                case 3:
                    agregarDatosGrupoCotizacion();
                    break;
                case 4:
                    eliminarGrupoCot();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    mostrarMensaje("Has introducido una opción no válida.");
            }
        }
    }

    private static void importarDatos() { // Author: Pedro Marín Sanchis

        try {
            cargarCategorias(new DocumentoCSV(new File("CSV/Categoria.csv"), delimitador_CSV));
            cargarEmpleados(new DocumentoCSV(new File("CSV/Datos_Empresa.csv"), delimitador_CSV), new DocumentoCSV(new File("CSV/Datos_Personales.csv"), delimitador_CSV));
            cargarDepartamentos(new DocumentoCSV(new File("CSV/Departamento.csv"), delimitador_CSV));
            cargarGrupo_Cotizacion(new DocumentoCSV(new File("CSV/Grupo_Cotizacion.csv"), delimitador_CSV));
            cargarHores_extres(new DocumentoCSV(new File("CSV/Hores_extres.csv"), delimitador_CSV));
        } catch (IOException e) {
            mostrarError("No se ha podido cargar.");
        }

    }

    private static void exportarDatos() { // Author: Pedro Marín Sanchis

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

    private static void cargarHores_extres(DocumentoCSV Hores_extres) { // Author: Pedro Marín Sanchis

        ArrayList<HoraExtra> lista = new ArrayList<>(1);

        if (Hores_extres.obtenerCantidadDeCampos() > 0) {

            for (int i = 1; i < Hores_extres.obtenerCantidadDeCampos(); i++) {

                lista.add(new HoraExtra(Integer.parseInt(Hores_extres.obtenerValor(i, 0)), Integer.parseInt(Hores_extres.obtenerValor(i, 1))));

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
                documento.anyadirTuple(new String[]{Integer.toString(i.getId_usuario()), Integer.toString(i.getHoras())});
            }

        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

    private static void obtenerDatosPersonalesID() { // Author: David Serna

        int id_pedido;

        try {
            id_pedido = leerEntero("Introduzca la id del empleado:");


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

        try {
            id_solicitada = leerEntero("Introduce la id del empleado");

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
        try {
            departamento = leerCadena("Introduzca el nombre del departamento:");

            for (Departamento dep : departamentos){
                if(departamento.equalsIgnoreCase(dep.getNombre())){
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
        int costeTotal = 0;
        try {
            for(GrupoCotizacion coste : gruposCotizacion) {
                for (Empleado empleado : empleados) {
                    if (coste.getId() == empleado.getGrupo_Cotizacion()) {
                        costeTotal = costeTotal + coste.getSueldo_base();
                    }
                }
            }
            mostrarMensaje("Coste salarial de la empresa: " + costeTotal + " €");
        } catch (Exception e) {
            mostrarError("Error");
        }
    }

    private static void horasExtraGrupoCotizacion() { //Author: Pedro Marín Sanchis

        int horasTotales = 0;
        int id = leerEntero("Introduzca la ID del grupo de cotización:");
        for (GrupoCotizacion i : gruposCotizacion) {

            if(i.getId() == id) {

                for(Empleado empleado : empleados) {
                    if(id == empleado.getGrupo_Cotizacion()){
                        for(HoraExtra hora : horasExtras) {
                            if (empleado.getId_usuario() == hora.getId_usuario()) {
                                horasTotales = horasTotales + hora.getHoras();
                            }
                        }
                    }
                }

            }
        }
            mostrarMensaje("Horas extras del grupo de cotizacion " + id + ": " + horasTotales);

    }

    private static void horasExtraDepartamento() { //Author: Javier Blasco
        String departamento;
        int id_dep = 0;
        int horasTotales = 0;
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
                            horasTotales = horasTotales + hora.getHoras();
                        }
                    }
                }
            }
            mostrarMensaje("Horas extras del departamento " + departamento + " " + horasTotales);
        } catch (Exception e) {
            mostrarError("Entrada no válida.");
        }
    }

    private static void agregarDatosUsuario(){ //Author: David Serna
        try {
            boolean check = false;
            int id_departamento = leerEntero("Introduzca la id del departamento del empleado: ");
            String NIF = leerCadena("Introduzca el NIF del empleado: ").toUpperCase();
            String nombre = leerCadena("Introduzca el nombre del empleado: ").toUpperCase();
            String apellido1 = leerCadena("Introduzca el primer apellido del empleado: ").toUpperCase();
            String apellido2 = leerCadena("Introduzca el segundo apellido del empleado: ").toUpperCase();
            int num_SegSocial = leerEntero("Introduzca el numero de la SS del empleado: ");
            String antiguedad = leerCadena("Introduzca la fecha en la que empezo a trabajar el empleado (Ejemplo: dd/mm/year): ");
            String cat_GrupoProfesional = leerCadena("Introduzca la categoria del grupo profesional del empleado: ");
            int grupo_Cotizacion = leerEntero("Introduzca el grupo de cotización del empleado: ");
            String email = leerCadena("Introduzca el email del usuario: ");


            if(!mostrarPeticionDeConfirmacion("NIF del empleado: " + NIF +
                    "\nNombre del empleado: " + nombre +
                    "\nPrimer apellido del empleado: " + apellido1 +
                    "\nSegundo apellido del empleado: " + apellido2 +
                    "\nNúmero de la Seguridad Social del empleado: " + num_SegSocial +
                    "\nID del departamento: " + id_departamento +
                    "\nAntiguedad del empleado: " + antiguedad +
                    "\nCategoría profesional al que pertence: " + cat_GrupoProfesional +
                    "\nGrupo de cotización al que pertenece: " + grupo_Cotizacion +
                    "\nEmail del empleado: " + email)) {
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

            horasExtras.add(new HoraExtra(indicemax + 1, 0));
        } catch (Exception e) {

            mostrarError("Entrada no válida.");

        }

    }

    private static void agregarDatosGrupoCotizacion() { //Author: David Serna
        try {
            boolean check = false;
            int sueldo_grupcot = leerEntero("Introduzca el sueldo del base del grupo cotización: ");


            if(!mostrarPeticionDeConfirmacion("Sueldo base del grupo_cot: " + sueldo_grupcot)) {
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

    private static void modificarDatosEmpresa() { //Author: Javier Blasco
        try {
            int id_usuario = leerEntero("Introduce la id del empleado al que quieres modificar.");
            for (Empleado empleado : empleados) {
                if(id_usuario == empleado.getId_usuario()){
                    int id_departamento = leerEntero("Introduzca la id del departamento del empleado: ");
                    String antiguedad = leerCadena("Introduzca la fecha en la que empezo a trabajar el empleado (Ejemplo: dd/mm/year): ");
                    String cat_GrupoProfesional = leerCadena("Introduzca la categoria del grupo profesional del empleado: ");
                    int grupo_Cotizacion = leerEntero("Introduzca el grupo de cotización del empleado: ");
                    String email = leerCadena("Introduzca el email del usuario: ");
                    empleado.setId_departamento(id_departamento);
                    empleado.setAntiguedad(antiguedad);
                    empleado.setCat_GrupoProfesional(cat_GrupoProfesional);
                    empleado.setGrupo_Cotizacion(grupo_Cotizacion);
                    empleado.setEmail(email);
                }
            }
        }catch (Exception e){
            mostrarError("Esta id no existe.");
        }
    }

    private static void modificarDatosPersonales() { //Author: Javier Blasco
        try {
            int id_usuario = leerEntero("Introduce la id del empleado al que quieres modificar.");
            for (Empleado empleado : empleados) {
                if(id_usuario == empleado.getId_usuario()){
                    String NIF = leerCadena("Introduzca el NIF del empleado: ").toUpperCase();
                    String nombre = leerCadena("Introduzca el nombre del empleado: ").toUpperCase();
                    String apellido1 = leerCadena("Introduzca el primer apellido del empleado: ").toUpperCase();
                    String apellido2 = leerCadena("Introduzca el segundo apellido del empleado: ").toUpperCase();
                    int num_SegSocial = leerEntero("Introduzca el numero de la SS del empleado: ");
                    empleado.setNIF(NIF);
                    empleado.setNombre(nombre);
                    empleado.setApellido1(apellido1);
                    empleado.setApellido2(apellido2);
                    empleado.setNum_SegSocial(num_SegSocial);
                }
            }
        }catch (Exception e){
            mostrarError("Esta id no existe.");
        }
    }
    
    private static void eliminarEmpleado() { // Author: Pedro Marín Sanchis

        int id = obtenerIDconNIF();

        for(int i = 0; i < empleados.size(); i++) {
            
            if (empleados.get(i).getId_usuario() == id) {

                empleados.remove(i);

                for (int j = 0; j < horasExtras.size(); j++) {

                    if (horasExtras.get(j).getId_usuario() == id) {
                        horasExtras.remove(j);
                        return;
                    }

                }

            }
            
        }
        
    }

    private static int obtenerIDconNIF() { // Author: Pedro Marín Sanchis

        String NIF = leerCadena("Introduzca el NIF del empleado a eliminar: ");

        for (Empleado i: empleados) {
            if (i.getNIF().equalsIgnoreCase(NIF)) {
                return i.getId_usuario();
            }
        }
            return 0;
    }

    private static void eliminarGrupoCot() { //Author: David Serna
        int indice_grupocot = 0;
        int grupo_cot = leerEntero("Introduzca la id del grupo de cotización que desea eliminar:");

        if (!comprobarGrupoCot(grupo_cot)) {
            mostrarMensaje("La id introducida no existe.");
            return;
        }

        int contar_empleados = contarEmpleadosGrupoCot(grupo_cot);

        if (contar_empleados != 0){
            mostrarMensaje("Tienes " + contar_empleados + " empleados en el grupo de cotización "
                    + grupo_cot + ", mientras tengas empleados no puedes eliminar el grupo de coti" +
                    "zación.");
            return;

        }

        for (GrupoCotizacion grupocot : gruposCotizacion){
            if(grupocot.getId() == grupo_cot){
                indice_grupocot = gruposCotizacion.indexOf(grupocot);
            }
        }
        gruposCotizacion.remove(indice_grupocot);
        mostrarMensaje("Se ha eliminado el grupo de cotización correctamente.");
    }

    private static int contarEmpleadosGrupoCot(int grupo_cot) { //Author: David Serna
        int contar_empleados = 0;
        for(Empleado empleado : empleados){
            if(empleado.getGrupo_Cotizacion() == grupo_cot){
                contar_empleados++;
            }
        }
        return contar_empleados;
    }

    private static boolean comprobarGrupoCot(int grupo_cot) { //Author: David Serna
        try {

            for(GrupoCotizacion grupCot : gruposCotizacion){

                if (grupCot.getId() == grupo_cot) {
                    return true;
                }

            }
            return false;

        }catch (Exception e){
            mostrarError("Esta id no existe.");
            return false;
        }
    }

    private static void agregarDep() { // Author: Pedro Marín Sanchis

        int id = leerEntero("Introduzca la ID del departamento:");

        if (!comprobarDep(id)) {

            departamentos.add(new Departamento(id, leerCadena("Introduzca el nombre del nuevo departamento:")));

        } else {

            mostrarError("No se ha encontrado el departamento.");

        }

    }

    private static void modificarDep() { //Author: Pedro Marín Sanchis

        int id = leerEntero("Introduzca la ID del departamento:");

        if (comprobarDep(id)) {

            for (Departamento i: departamentos) {

                if (id == i.getId()) {

                    i.setNombre(leerCadena("Introduzca el nuevo nombre de departamento:"));

                }

            }

        } else {

            mostrarError("No se ha encontrado el departamento.");

        }

    }

    private static void eliminarDep() { //Author: David Serna
        int indice_dep = 0;
        String departamento = leerCadena("Introduzca el nombre del departamento que desea eliminar:");
        int id_departamento =convertirNombreDepIdDep(departamento);
        if (id_departamento == 0){
            return;
        }


        if (!comprobarDep(id_departamento)) {
            mostrarMensaje("El departamento introducido no existe.");
            return;
        }


        int contar_empleados = contarEmpleadosDep(id_departamento);

        if (contar_empleados != 0){
            mostrarMensaje("Tienes " + contar_empleados + " empleados en el departamento "
                    + departamento + ", mientras tengas empleados no puedes eliminar el departamento " +
                    "reasigna los empleados a otro departamento antes de eliminarlo.");
            return;

        }

        for (Departamento dep : departamentos){
            if(dep.getId() == id_departamento){
                indice_dep = departamentos.indexOf(dep);
            }
        }
        departamentos.remove(indice_dep);
        mostrarMensaje("Se ha eliminado el departamento correctamente.");

    }
    private static int convertirNombreDepIdDep(String departamento) { //Author: David Serna
        for (Departamento dep : departamentos){
            if(dep.getNombre().equalsIgnoreCase(departamento)){
                return dep.getId();
            }
        }
        System.out.println("No existe el departamento");
        return 0;
    }

    private static int contarEmpleadosDep(int id_departamento) { //Author: David Serna
        int contar_empleados = 0;
        for(Empleado empleado : empleados){
            if(empleado.getId_departamento() == id_departamento){
                contar_empleados++;
            }
        }
        return contar_empleados;
    }

    private static boolean comprobarDep(int id_departamento) { //Author: David Serna
        try {

            for(Departamento departamento : departamentos){

                if (departamento.getId() == id_departamento) {
                    return true;
                }

            }
            return false;

        }catch (Exception e){
            mostrarError("Esta id no existe.");
            return false;
        }
    }
}
