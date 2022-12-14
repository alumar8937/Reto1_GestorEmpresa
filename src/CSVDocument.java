// Author: Pedro Marín Sanchis

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVDocument {
    final private String contenidoTextoPlano;
    final private int posicion = 0;

    public CSVDocument(File archivo) throws IOException {

        StringBuffer buffer = new StringBuffer();

        FileReader lectorDeArchivos = new FileReader(archivo);
        while (lectorDeArchivos.ready()) {

        }
        lectorDeArchivos.read();

    }

}
