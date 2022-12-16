/*
 *@author Pedro Marín Sanchis
 */
public class HoraExtra {

    final private int id_usuario;
    final private int horas;

    public HoraExtra(int id_usuario, int horas) {

        this.id_usuario = id_usuario;
        this.horas = horas;

    }

    public int getId_usuario() {
        return this.id_usuario;
    }

    public int getFecha() {
        return this.horas;
    }
}
