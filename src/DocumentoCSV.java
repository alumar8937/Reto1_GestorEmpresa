import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* DocumentoCSV es una clase para el tratamiento de archivos CSV, como argumentos para el constructor toma
* Un File (archivo) y un String (delimitador). El propio constructor genera una estructura tal que:
*
* ArrayList de String arrays. Cada String array es un tuple del archivo CSV, includio el primero, que es
* la cabecera. Cada String de un String array es un valor en concreto del archivo CSV, su índice indica
* la columna en la que se encuentra.
*
* Por lo tanto podemos acceder a cualquier valor de un archivo CSV mediante dos valores enteros. Su pos-
* ición en la fila y su posición en la columna.
*
* @author Pedro Marín Sanchis
* @version V.1
* @since 18/12/2022
*/
public class DocumentoCSV {

    final private String delimitador;
    final private ArrayList<String[]> tuples;

    /**
     * Constructor de DocumentoCSV a partir de un objeto File
     * @param archivo
     * @param delimitador
     * @throws IOException
     */
    public DocumentoCSV(File archivo, String delimitador) throws IOException {

        this.delimitador = delimitador;
        this.tuples = generarTuples(generarContenidoTextoPlano(archivo));

    }

    /**
     * Construcor de DocumentoCSV a partir de un String
     * @param contenido
     * @param delimitador
     */
    public DocumentoCSV(String contenido, String delimitador) {

        this.delimitador = delimitador;
        if (contenido.length() > 0) {
            this.tuples = generarTuples(contenido);
        } else {
            this.tuples = new ArrayList<>(1);
        }

    }

    /**
     * Obtiene el contenido en texto plano de un archivo y lo devuelve.
     * @param archivo
     * @return String
     * @throws IOException
     */
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

    /**
     * Genera y carga los tuples a partir del conenido en texto plano de un documento csv.
     * @param contenidoTextoPlano
     * @return ArrayList<String[]>
     */
    private ArrayList<String[]> generarTuples(String contenidoTextoPlano) {

        ArrayList<String[]> tuples = new ArrayList<>(1);

        for (String i: contenidoTextoPlano.split("\n")) {

            tuples.add(i.split(delimitador));

        }

        return tuples;

    }

    /**
     * Añade un tuple al documento csv.
     * Un tuple es un Array de Strings, cada valor del Array es uno de los campos del tuple.
     * @param tuple
     */
    public void anyadirTuple(String[] tuple) {

        this.tuples.add(tuple);

    }

    /**
     * Añade un tuple al documento csv. Es necesario especificar el delimitador por el que se dividirán los valores.
     * Un tuple es un Array de Strings, cada valor del Array es uno de los campos del tuple.
     * @param tuple
     * @param delimitador
     */
    public void anyadirTuple(String tuple, String delimitador) {

        anyadirTuple(tuple.split(delimitador));

    }

    /**
     * Devuelve el valor de la celda indicada (¡ LOS VALORES DE LA CABECERA DEL CSV SIGUEN ESTANDO !).
     * @param tuple
     * @param columna
     * @return
     */
    public String obtenerValor(int tuple, int columna) {

        return tuples.get(tuple)[columna];

    }

    /**
     * Cambia el valor de la celda indicada (¡ LOS VALORES DE LA CABECERA DEL CSV SIGUEN ESTANDO !).
     * @param fila
     * @param columna
     * @param valor
     */
    public void cambiarValor(int fila, int columna, String valor) {

        String[] tupleModificado = tuples.get(fila);
        tupleModificado[columna] = valor;
        tuples.set(fila, tupleModificado);

    }

    /**
     * Devuelve la cantidad de campos (¡ LA CABECERA DEL CSV SIGUE ESTANDO !).
     * @return int
     */
    public int obtenerCantidadDeCampos() {

        return tuples.size();

    }

    /**
     * Devuelve el documento CSV como un String.
     * @return String
     */
    public String obtenerContenidoTextoPlano() {

        StringBuilder builder = new StringBuilder();

        for (String[] i: tuples) {

            for (int j = 0; j < i.length - 1; j++) {

                builder.append(i[j]).append(delimitador);

            }

            builder.append(i[i.length-1]);
            builder.append("\n");

        }

        return builder.toString();

    }

    /**
     * Este método exporta el objeto DocumentoCSV como un fichero csv. Dentro de si mismo llama al método obtenerContenidoTextoPlano()
     * @param destino
     * @throws IOException
     */
    public void exportarComoArchivo(File destino) throws IOException {

        FileWriter writer = new FileWriter(destino);
        writer.write(this.obtenerContenidoTextoPlano());
        writer.close();

    }

}
