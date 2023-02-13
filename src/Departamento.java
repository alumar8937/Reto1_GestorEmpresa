/**
 * Clase que define un departamento, con id y nombre.
 * 
 * @author Pedro Marín Sanchis 
 * @version V.1
 * @since 18/12/2022
 */
public class Departamento {

    final private int id;
    private String nombre;

    /**
     * Constructor de Departamento
     * @param id
     * @param nombre
     */
    public Departamento(int id, String nombre) {

        this.id = id;
        this.nombre = nombre;

    }

    /**
     * <pre>
     * Este método devuelve un array de Strings donde cada valor representa diferente información del departamento.
     * [0] : ID
     * [1] : Nombre
     * </pre>
     * @return String[]
     */
    public String[] toStringArray() {
        return new String[]{String.valueOf(this.id), this.nombre};
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece la variable nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
