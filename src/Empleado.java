// Author: Pedro Marín Sanchis

public class Empleado {
    final private int id_usuario;
    private String NIF;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int num_SegSocial;
    private String antiguedad;
    private String cat_GrupoProfesional;
    private int grupo_Cotizacion;
    private String email;

    public Empleado(int id_usuario, String NIF, String nombre, String apellido1, String apellido2, int num_SegSocial, String antiguedad, String cat_GrupoProfesional, int grupo_Cotizacion, String email) {

        this.id_usuario = id_usuario;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNIF() {
        return NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public int getNum_SegSocial() {
        return num_SegSocial;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public int getGrupo_Cotizacion() {
        return grupo_Cotizacion;
    }

    public String getEmail() {
        return email;
    }

    /*public void setId_usuario(int id_usuario) { // No se debe cambiar la ID del usuario, es la clave principal de la base de datos.
        this.id_usuario = id_usuario;
    }*/

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setNum_SegSocial(int num_SegSocial) {
        this.num_SegSocial = num_SegSocial;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public void setCat_GrupoProfesional(String cat_GrupoProfesional) {
        this.cat_GrupoProfesional = cat_GrupoProfesional;
    }

    public void setGrupo_Cotizacion(int grupo_Cotizacion) {
        this.grupo_Cotizacion = grupo_Cotizacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
