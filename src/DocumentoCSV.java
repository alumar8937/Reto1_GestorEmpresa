// Author: Pedro Marín Sanchis

/*

DocumentoCSV es una clase para el tratamiento de archivos CSV, como argumentos para el constructor toma
Un File (archivo) y un String (delimitador). El propio constructor genera una estructura tal que:

ArrayList de String arrays. Cada String array es un tuple del archivo CSV, includio el primero, que es
la cabecera. Cada String de un String array es un valor en concreto del archivo CSV, su índice indica
la columna en la que se encuentra.

Por lo tanto podemos acceder a cualquier valor de un archivo CSV mediante dos valores enteros. Su pos-
ición en la fila y su posición en la columna.

 */

import java.io.*;
import java.util.ArrayList;

public class DocumentoCSV {

    final private String delimitador;
    final private ArrayList<String[]> tuples;

    public DocumentoCSV(File archivo, String delimitador) throws IOException {

        this.delimitador = delimitador;
        this.tuples = generarTuples(generarContenidoTextoPlano(archivo));

    }

    public DocumentoCSV(String contenido, String delimitador) throws IOException {

        this.delimitador = delimitador;
        if (contenido.length() > 0) {
            this.tuples = generarTuples(contenido);
        } else {
            this.tuples = new ArrayList<>(1);
        }

    }
    private String generarContenidoTextoPlano(File archivo) throws IOException { // Devuelve el contenido del fichero en un String.

        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        StringBuilder builder = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {

            builder.append(linea).append("\n"); // Realizamos append dos veces ya que estamos intentando evitar la concatenación reiterada de Strings. builder.append(linea + \n) es contraproducente.

        }

        reader.close();

        return builder.toString();

    }

    private ArrayList<String[]> generarTuples(String contenidoTextoPlano) {

        ArrayList<String[]> tuples = new ArrayList<>(1);

        for (String i: contenidoTextoPlano.split("\n")) {

            tuples.add(i.split(delimitador));

        }

        return tuples;

    }

    public void anyadirTuple(String[] tuple) {

        this.tuples.add(tuple);

    }

    public String obtenerValor(int tuple, int columna) { // Devuelve el valor indicado (¡ LOS VALORES DE LA CABECERA DEL CSV SIGUEN ESTANDO !).

        return tuples.get(tuple)[columna];

    }

    public void cambiarValor(int fila, int columna, String valor) { // Devuelve el valor indicado (¡ LOS VALORES DE LA CABECERA DEL CSV SIGUEN ESTANDO !).

        String[] tupleModificado = tuples.get(fila);
        tupleModificado[columna] = valor;
        tuples.set(fila, tupleModificado);

    }

    public int obtenerCantidadDeCampos() { // Devuelve la cantidad de campos (¡ LA CABECERA DEL CSV SIGUE ESTANDO !).

        return tuples.size();

    }

    public String obtenerContenidoTextoPlano() { // Devuelve el documento CSV como un String.

        StringBuilder builder = new StringBuilder();

        for (String[] i: tuples) {

            for (String j: i) {

                builder.append(j).append(delimitador);

            }

            builder.append("\n");

        }

        return builder.toString();

    }

    public void exportarComoArchivo(File destino) throws IOException {

        FileWriter writer = new FileWriter(destino);
        writer.write(this.obtenerContenidoTextoPlano());
        writer.close();

    }

}
