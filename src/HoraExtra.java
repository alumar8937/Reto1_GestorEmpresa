/*
 *@author Pedro Marín Sanchis
 */
public class HoraExtra {

    final private int id_usuario;
    final private String fecha;

    public HoraExtra(int id_usuario, String fecha) {

        this.id_usuario = id_usuario;
        this.fecha = fecha;

    }

    public int getId_usuario() {
        return this.id_usuario;
    }

    public String getFecha() {
        return this.fecha;
    }
}
