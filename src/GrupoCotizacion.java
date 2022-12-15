/*
 *@author Pedro Marín Sanchis
 */
public class GrupoCotizacion {
    final private int id;
    private int sueldo_base;

    public GrupoCotizacion(int id, int sueldo_base) {

        this.id = id;
        this.sueldo_base = sueldo_base;

    }

    public int getId() {
        return this.id;
    }

    public int getSueldo_base() {
        return this.sueldo_base;
    }

    public void setSueldo_base(int sueldo_base) {
        this.sueldo_base = sueldo_base;
    }
}