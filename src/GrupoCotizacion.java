/**
 * 
 * Define un grupo de cotización, con id y sueldo_base.
 * 
 * @author Pedro Marín Sanchis
 * @version V.1
 * @since 18/12/2022
 */
public class GrupoCotizacion {
    final private int id;
    private int sueldo_base;

    /**
     * Constructor de GrupoCotizacion
     * @param id
     * @param sueldo_base
     */
    public GrupoCotizacion(int id, int sueldo_base) {

        this.id = id;
        this.sueldo_base = sueldo_base;

    }

    /**
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return sueldo_base
     */
    public int getSueldo_base() {
        return this.sueldo_base;
    }

    /**
     * Establece la variable sueldo base
     * @param sueldo_base
     */
    public void setSueldo_base(int sueldo_base) {
        this.sueldo_base = sueldo_base;
    }
}