/**
 * 
 * Clase que define un departamento, con id y nombre.
 * 
 * @author Pedro Marín Sanchis 
 * @version V.1
 * @since 18/12/2022
 */

public class Departamento {

    final private int id;
    private String nombre;

    public Departamento(int id, String nombre) {

        this.id = id;
        this.nombre = nombre;

    }

    public String[] toStringArray() {
        return new String[]{String.valueOf(this.id), this.nombre};
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
