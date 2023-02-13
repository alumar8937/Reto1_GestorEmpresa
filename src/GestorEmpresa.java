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

    /**
     * Metodo main donde va mostrando el menu y limpia la pantalla siempre que la condicion de salida no sea true.
     * Inicialmente importa los datos.
     * En el caso de que sea true esportara los datos.
     * {@link GestorEmpresa#importarDatos()}.
     * {@link GestorEmpresa#limpiarPantalla}.
     * {@link GestorEmpresa#mostrarMenu()}.
     * {@link GestorEmpresa#exportarDatos()}.
     * @param args
     */
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

    /**
     * En este metodo mostrara un mensaje por pantalla y pedira al usuario una opcion en el caso de ser Y devolvera
     * true en el caso contrario N false.
     * {@link GestorEmpresa#limpiarPantalla()}.
     * @param mensaje
     * @return boolean
     */
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

    /**
     * Este metodo es el encargado de mostrar el menu y permite la seleccion de cada opcion con un pequeño texto
     * informando de lo que realizara al elegir cada una de las opciones.
     * Este menu se ha construido a partir de varios submenus.
     * {@link GestorEmpresa#leerEntero}
     * {@link GestorEmpresa#menuEmpleado()}
     * {@link GestorEmpresa#menuDepartamento()}
     * {@link GestorEmpresa#menuGruposCot()}
     * {@link GestorEmpresa#costeSalarial}
     * {@link GestorEmpresa#mostrarMensaje}
     */
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
                    agregarDepartamento();
                    break;
                case 4:
                    modificarDepartamento();
                    break;
                case 5:
                    eliminarDepartamento();
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
                    mostrarMensaje("En este grupo de cotización hay: " + contarEmpleadosGrupoCotizacion(leerEntero("Introduce la id del grupo de cotización:"))+ " empleados.");
                    break;
                case 2:
                    horasExtraGrupoCotizacion();
                    break;
                case 3:
                    agregarDatosGrupoCotizacion();
                    break;
                case 4:
                    eliminarGrupoCotizacion();
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

            mostrarError("Los archivos no son válidos.");

        }

    }

    /**
     * Método que crea un documento CSV con las categorias
     * @return devuelve el documento CSV
     * @throws IOException
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Método que crea un documento CSV con los datos empresariales del empleado
     * @return devuelve el documento CSV
     * @throws IOException
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Método que crea un documento CSV con los datos personales del empleado
     * @return devuelve el documento CSV
     * @throws IOException
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Método que crea el documento CSV con los departamentos
     * @return devuelve el documento CSV
     * @throws IOException
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Método que crea el documento CSV con los grupos de cotización
     * @return devuelve el documento CSV
     * @throws IOException
     * {@link GestorEmpresa#mostrarError(String)} 
     */
    private static DocumentoCSV crearGrupo_CotizacionCSV() throws IOException { // Author: Pedro Marín Sanchis

        DocumentoCSV documento = new DocumentoCSV("", delimitador_CSV);

        documento.anyadirTuple(cabecera_Grupo_Cotizacion, delimitador_CSV);

        if (departamentos.size() > 0) {

            for (GrupoCotizacion grupoCotizacion: gruposCotizacion) {
                documento.anyadirTuple(new String[]{Integer.toString(grupoCotizacion.getId()), Integer.toString(grupoCotizacion.getSueldo_base())});
            }

        } else {

            mostrarError("Los archivos no son válidos.");

        }

        return documento;

    }

    /**
     * Método que crea el documento CSV de las horas extras
     * @return devuelve el documento CSV
     * @throws IOException
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Métoodo que saca los datos personales de un empleado según su ID
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#mostrarMensaje(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Métoodo que saca los datos empresariales de un empleado según su ID
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#mostrarMensaje(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Método que consulta la información de los empleados de un departamento en específico
     * {@link GestorEmpresa#mostrarError(String)}
     * {@link GestorEmpresa#leerCadena(String)}
     * {@link GestorEmpresa#mostrarMensaje(String)}
     */
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

    /**
     * Método que calcula el coste salarial de la empresa
     * {@link GestorEmpresa#mostrarMensaje(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
    private static void costeSalarial() { //Author: Javier Blasco
        int costeTotal = 0;
        try {
            for(GrupoCotizacion grupoCotizacion : gruposCotizacion) {
                for (Empleado empleado : empleados) {
                    if (grupoCotizacion.getId() == empleado.getGrupo_Cotizacion()) {
                        costeTotal = costeTotal + grupoCotizacion.getSueldo_base();
                    }
                }
            }
            mostrarMensaje("Coste salarial de la empresa: " + costeTotal + " €");
        } catch (Exception e) {
            mostrarError("Error");
        }
    }

    /**
     * Método que devuelve las horas extras de un grupo de cotización
     * {@link GestorEmpresa#mostrarMensaje(String)}
     */
    private static void horasExtraGrupoCotizacion() { //Author: Pedro Marín Sanchis

        int horasTotales = 0;
        int id = leerEntero("Introduzca la ID del grupo de cotización:");
        for (GrupoCotizacion grupoCotizacion : gruposCotizacion) {

            if(grupoCotizacion.getId() == id) {

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

    /**
     * Método que devuelve las horas extras de un departamento
     * {@link GestorEmpresa#mostrarError(String)}
     * {@link GestorEmpresa#mostrarMensaje(String)}
     */
    private static void horasExtraDepartamento() { //Author: Javier Blasco
        String departamento;
        int id_departamento = 0;
        int horasTotales = 0;
        System.out.println("Introduzca el nombre del departamento:");
        try {
            departamento = inputValue.nextLine().toUpperCase();

            for (Departamento dep : departamentos){
                if(departamento.equals(dep.getNombre())){
                    id_departamento = dep.getId();
                }
            }

            for(Empleado empleado : empleados){
                if(id_departamento == empleado.getId_departamento()){
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

    /**
     * Método que agrega un usuario mediante la solicitud de los datos personales y de empresa de este
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#leerCadena(String)}
     * {@link GestorEmpresa#mostrarPeticionDeConfirmacion(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
    private static void agregarDatosUsuario(){ //Author: David Serna
        try {
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

    /**
     * Método que agrega un grupo de cotización nuevo
     * {@link GestorEmpresa#mostrarPeticionDeConfirmacion(String)}
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
    private static void agregarDatosGrupoCotizacion() { //Author: David Serna
        try {
            int sueldo_grupcotizacion = leerEntero("Introduzca el sueldo del base del grupo cotización: ");


            if(!mostrarPeticionDeConfirmacion("Sueldo base del grupo_cot: " + sueldo_grupcotizacion)) {
                return;
            }

            int indicemax = 0;
            for(GrupoCotizacion grupoCotizacion: gruposCotizacion){
                if(grupoCotizacion.getId() > indicemax){
                    indicemax = grupoCotizacion.getId();
                }
            }

            gruposCotizacion.add(new GrupoCotizacion(indicemax + 1, sueldo_grupcotizacion));


        } catch (Exception e) {

            mostrarError("Entrada no válida.");

        }
    }

    /**
     * Método que modifica los datos empresariales de un empleado
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#leerCadena(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Método que modifica los datos personal de un empleado
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#leerCadena(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
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

    /**
     * Método que elimina un empleado si es posible
     * {@link GestorEmpresa#obtenerIDconNIF()}
     */
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

    /**
     * Método que obtiene el id del empleado mediante el NIF de este
     * @return devuelve el ID del empleado
     * {@link GestorEmpresa#leerCadena(String)}
     */
    private static int obtenerIDconNIF() { // Author: Pedro Marín Sanchis

        String NIF = leerCadena("Introduzca el NIF del empleado a eliminar: ");

        for (Empleado i: empleados) {
            if (i.getNIF().equalsIgnoreCase(NIF)) {
                return i.getId_usuario();
            }
        }
            return 0;
    }

    /**
     * Método que elimina un grupo de cotización si es posible
     * {@link GestorEmpresa#mostrarMensaje(String)}
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#comprobarGrupoCotizacion(int)}
     * {@link GestorEmpresa#contarEmpleadosGrupoCotizacion(int)}
     */
    private static void eliminarGrupoCotizacion() { //Author: David Serna
        int indice_grupocot = 0;
        int grupo_cotizacion = leerEntero("Introduzca la id del grupo de cotización que desea eliminar:");

        if (!comprobarGrupoCotizacion(grupo_cotizacion)) {
            mostrarMensaje("La id introducida no existe.");
            return;
        }

        int contar_empleados = contarEmpleadosGrupoCotizacion(grupo_cotizacion);

        if (contar_empleados != 0){
            mostrarMensaje("Tienes " + contar_empleados + " empleados en el grupo de cotización "
                    + grupo_cotizacion + ", mientras tengas empleados no puedes eliminar el grupo de coti" +
                    "zación.");
            return;

        }

        for (GrupoCotizacion grupocot : gruposCotizacion){
            if(grupocot.getId() == grupo_cotizacion){
                indice_grupocot = gruposCotizacion.indexOf(grupocot);
            }
        }
        gruposCotizacion.remove(indice_grupocot);
        mostrarMensaje("Se ha eliminado el grupo de cotización correctamente.");
    }

    /**
     * Método que cuenta los empleados en un grupo de cotizacion
     * @param grupo_cotizacion
     * @return devuelve la cantidad de empleados en el grupo de cotización
     */
    private static int contarEmpleadosGrupoCotizacion(int grupo_cotizacion) { //Author: David Serna
        int contar_empleados = 0;
        for(Empleado empleado : empleados){
            if(empleado.getGrupo_Cotizacion() == grupo_cotizacion){
                contar_empleados++;
            }
        }
        return contar_empleados;
    }

    /**
     * Método que comprueba si el grupo de cotización existe
     * @param grupo_cotizacion
     * @return devuelve true o false dependiendo de si existe el grupo de cotización
     * {@link GestorEmpresa#mostrarError(String)}
     */
    private static boolean comprobarGrupoCotizacion(int grupo_cotizacion) { //Author: David Serna
        try {

            for(GrupoCotizacion grupoCotizacion : gruposCotizacion){

                if (grupoCotizacion.getId() == grupo_cotizacion) {
                    return true;
                }

            }
            return false;

        }catch (Exception e){
            mostrarError("Esta id no existe.");
            return false;
        }
    }

    /**
     * Método que agrega un departamento
     * {@link GestorEmpresa#comprobarDepartamento(int)}
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
    private static void agregarDepartamento() { // Author: Pedro Marín Sanchis

        int id = leerEntero("Introduzca la ID del departamento:");

        if (!comprobarDepartamento(id)) {

            departamentos.add(new Departamento(id, leerCadena("Introduzca el nombre del nuevo departamento:")));

        } else {

            mostrarError("No se ha encontrado el departamento.");

        }

    }

    /**
     * Método que modifica un departamento
     * {@link GestorEmpresa#comprobarDepartamento(int)}
     * {@link GestorEmpresa#leerCadena(String)}
     * {@link GestorEmpresa#leerEntero(String)}
     * {@link GestorEmpresa#mostrarError(String)}
     */
    private static void modificarDepartamento() { //Author: Pedro Marín Sanchis

        int id = leerEntero("Introduzca la ID del departamento:");

        if (comprobarDepartamento(id)) {

            for (Departamento i: departamentos) {

                if (id == i.getId()) {

                    i.setNombre(leerCadena("Introduzca el nuevo nombre de departamento:"));

                }

            }

        } else {

            mostrarError("No se ha encontrado el departamento.");

        }

    }

    /**
     * Método que elimina un departamento
     * {@link GestorEmpresa#convertirNombreDepartamentoIdDepartamento(String)}
     * {@link GestorEmpresa#comprobarDepartamento(int)}
     * {@link GestorEmpresa#mostrarMensaje(String)}
     * {@link GestorEmpresa#leerCadena(String)}
     * {@link GestorEmpresa#contarEmpleadosDepartamento(int)}
     */
    private static void eliminarDepartamento() { //Author: David Serna
        int indice_dep = 0;
        String departamento = leerCadena("Introduzca el nombre del departamento que desea eliminar:");
        int id_departamento = convertirNombreDepartamentoIdDepartamento(departamento);
        if (id_departamento == 0){
            return;
        }


        if (!comprobarDepartamento(id_departamento)) {
            mostrarMensaje("El departamento introducido no existe.");
            return;
        }


        int contar_empleados = contarEmpleadosDepartamento(id_departamento);

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

    /**
     * Método que convierte un nombre de departamento a una id
     * @param departamento
     * @return la id del departamento que coincide con el nombre de este si existe
     */
    private static int convertirNombreDepartamentoIdDepartamento(String departamento) { //Author: David Serna
        for (Departamento dep : departamentos){
            if(dep.getNombre().equalsIgnoreCase(departamento)){
                return dep.getId();
            }
        }
        System.out.println("No existe el departamento");
        return 0;
    }

    /**
     *Método que cuenta la cantidad de empleados por departamento
     * @param id_departamento
     * @return la cantidad de empleados en ese departamento
     */
    private static int contarEmpleadosDepartamento(int id_departamento) { //Author: David Serna
        int contar_empleados = 0;
        for(Empleado empleado : empleados){
            if(empleado.getId_departamento() == id_departamento){
                contar_empleados++;
            }
        }
        return contar_empleados;
    }

    /**
     * Método que comprueba si una id de departamento existe
     * {@link GestorEmpresa#mostrarError(String)}
     * @param id_departamento
     * @return devuelve verdadero o falso dependiendo de si existe
     */
    private static boolean comprobarDepartamento(int id_departamento) { //Author: David Serna
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
