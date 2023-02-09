/**
 * 
 * Define una entrada en la tabla de horas extras. Tiene id_usuario y horas.
 * 
 * @author Pedro Marín Sanchis
 * @version V1
 * @since 18/12/2022
 */
public class HoraExtra {

    final private int id_usuario;
    final private int horas;

    /**
     * Constructor de HoraExtra
     * @param id_usuario
     * @param horas
     */
    public HoraExtra(int id_usuario, int horas) {

        this.id_usuario = id_usuario;
        this.horas = horas;

    }

    /**
     * @return id_usuario
     */
    public int getId_usuario() {
        return this.id_usuario;
    }

    /**
     * @return horas
     */
    public int getHoras() {
        return this.horas;
    }
}
