/**
 * 
 * Define tanto la información personal de un empleado como la informacion.
 * 
 * @author Pedro Marín Sanchis
 * @version V.1
 * @since 18/12/2022
 */

public class Empleado {
    final private int id_usuario;

    private int id_departamento;
    private String NIF;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int num_SegSocial;
    private String antiguedad;
    private String cat_GrupoProfesional;
    private int grupo_Cotizacion;
    private String email;

    /**
     * Constructor de Empleado
     * @param id_usuario
     * @param id_departamento
     * @param NIF
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param num_SegSocial
     * @param antiguedad
     * @param cat_GrupoProfesional
     * @param grupo_Cotizacion
     * @param email
     */
    public Empleado(int id_usuario, int id_departamento, String NIF, String nombre, String apellido1,
                    String apellido2, int num_SegSocial, String antiguedad, String cat_GrupoProfesional,
                    int grupo_Cotizacion, String email) {

        this.id_usuario = id_usuario;
        this.id_departamento = id_departamento;
        this.NIF = NIF;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.num_SegSocial = num_SegSocial;
        this.antiguedad = antiguedad;
        this.cat_GrupoProfesional = cat_GrupoProfesional;
        this.grupo_Cotizacion = grupo_Cotizacion;
        this.email = email;

    }

    /**
     * <pre>
     * Este método devuelve un array de Strings donde cada valor representa diferente información del empleado.
     * [0] : ID
     * [1] : ID Departamento
     * [2] : NIF
     * [3] : Nombre
     * [4] : Primer apellido
     * [5] : Segundo apellido
     * [6] : Número de la Seguridad Social
     * [7] : Antigüedad
     * [8] : Categoría / Grupo Profesional
     * [9] : Grupo de Cotización
     * [10]: Email
     * </pre>
     * @return String[]
     */
    public String[] toStringArray() {
        return new String[]{String.valueOf(this.id_usuario), String.valueOf(this.id_departamento),
                this.NIF, this.nombre, this.apellido1, this.apellido2, String.valueOf(this.num_SegSocial),
                this.antiguedad, this.cat_GrupoProfesional, String.valueOf(this.grupo_Cotizacion), this.email};
    }

    /**
     * @return id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @return id_departamento
     */
    public int getId_departamento() {
        return id_departamento;
    }

    /**
     * @return NIF
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @return apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @return num_SegSocial
     */
    public int getNum_SegSocial() {
        return num_SegSocial;
    }

    /**
     * @return antiguedad
     */
    public String getAntiguedad() {
        return antiguedad;
    }

    /**
     * @return cat_GrupoProfesional
     */
    public String getCat_GrupoProfesional() {
        return cat_GrupoProfesional;
    }

    /**
     * @return grupo_Cotizacion
     */
    public int getGrupo_Cotizacion() {
        return grupo_Cotizacion;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la variable id_departamento
     * @param id_departamento
     */
    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    /**
     * Establece la variable NIF
     * @param NIF
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     * Establece la variable nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la variable apellido1
     * @param apellido1
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Establece la variable apellido2
     * @param apellido2
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Establece la variable num_SegSocial
     * @param num_SegSocial
     */
    public void setNum_SegSocial(int num_SegSocial) {
        this.num_SegSocial = num_SegSocial;
    }

    /**
     * Establece la variable antiguedad
     * @param antiguedad
     */
    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    /**
     * Establece la variable cat_GrupoProfesional
     * @param cat_GrupoProfesional
     */
    public void setCat_GrupoProfesional(String cat_GrupoProfesional) {
        this.cat_GrupoProfesional = cat_GrupoProfesional;
    }

    /**
     * Establece la variable grupo_Cotizacion
     * @param grupo_Cotizacion
     */
    public void setGrupo_Cotizacion(int grupo_Cotizacion) {
        this.grupo_Cotizacion = grupo_Cotizacion;
    }

    /**
     * Establece la variable email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
