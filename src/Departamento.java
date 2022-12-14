// Author: Pedro Marín Sanchis

public class Departamento {

    final private int id;
    final private String nombre;

    public Departamento(int id, String nombre) {

        this.id = id;
        this.nombre = nombre;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
