
package proyecto_grafo;

import java.io.*;

/**
 * Clase que especifica el filtro de ficheros aceptados por el programa
 */
public class FiltroFichero extends javax.swing.filechooser.FileFilter {

    /** 
     * Constructor de FiltroFichero vacio
     */
    public FiltroFichero() {
    }

    /** 
     * M�todo que indica ficheros de que tipo se aceptan 
     * @return Se acepta el fichero si termina en ".xml"
     */
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".xml");
    }

    /** 
     * M�todo que indica la descripci�n de los fichero que se aceptan
     * @return Descripci�n del tipo de ficheros aceptados
     */
    public String getDescription() {
        return " lenguaje de marcas ampliable (*.xml)";
    }
}