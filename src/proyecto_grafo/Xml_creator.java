package proyecto_grafo;

//www.casidiablo.net
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.*;
import java.io.StringWriter;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Xml_creator {

    private static final String TAG_GRAFO = "GraFo";
    private static final String TAG_MODO = "Modo";
    private static final String TAG_ID = "id";
    private static final String TAG_VERTICE = "Vertice";
    private static final String TAG_ARISTA = "Arista";
    // Codificacion
    private static final String XML_VERSION = "1.0";
    private static final String XML_ENCODING = "ISO-8859-1";
    private static final String JAVA_ENCODING = "8859_1";
    // Nombre del archivo
    private static final String NOMBRE_ARCHIVO_XML = "Grafo.xml";
    // Objetos
    private Document documentoXML = null;
    private Element descarga = null;

    public Xml_creator() {
        generaDocumentoXML();
    }

    public void generarDocumentoXMLDescarga(Grafo g, int representacion) {
        Element elemento;
        Element item;
        Element tk;

        // 1. Crear elemento
        elemento = documentoXML.createElement(TAG_MODO);
        // 2. Asignar un atributo
        descarga.appendChild(elemento);

        // a. Crear item
        item = documentoXML.createElement("Representacion_de_Grafo");
        // b. Asignar un dato al item
        item.appendChild(documentoXML.createTextNode("" + representacion));
        // c. Aniadir el item
        elemento.appendChild(item);
        // a. Crear item
        item = documentoXML.createElement("Tipo_de_Grafo");
        // b. Asignar un dato al item
        item.appendChild(documentoXML.createTextNode("" + g.getTipo_grafo()));
        // c. Aniadir el item
        elemento.appendChild(item);
        //asdasd

        descarga.appendChild(elemento);
        for (int i = 0; i < g.size_vertice(); i++) {
            Vertices vertice = g.get_vertices(i);
            item = documentoXML.createElement(TAG_VERTICE);
            item.setAttribute(TAG_ID, "" + i);

            tk = documentoXML.createElement("Nombre");
            tk.appendChild(documentoXML.createTextNode(vertice.getNombre()));
            item.appendChild(tk);

            tk = documentoXML.createElement("Posicion_X");
            tk.appendChild(documentoXML.createTextNode("" + vertice.getPosicion().getX()));
            item.appendChild(tk);

            tk = documentoXML.createElement("Posicion_Y");
            tk.appendChild(documentoXML.createTextNode("" + vertice.getPosicion().getY()));
            item.appendChild(tk);

            descarga.appendChild(item);
        }


        for (int j = 0; j < g.size_arista(); j++) {
            Arista arista = g.get_arista(j);
            item = documentoXML.createElement(TAG_ARISTA);
            item.setAttribute(TAG_ID, "" + j);
            tk = documentoXML.createElement("Vertice_origen");
            tk.appendChild(documentoXML.createTextNode("" + g.indexOf_Vertices(arista.getOrigen())));
            item.appendChild(tk);
            tk = documentoXML.createElement("Vertice_fin");
            tk.appendChild(documentoXML.createTextNode("" + g.indexOf_Vertices(arista.getFin())));
            item.appendChild(tk);
            tk = documentoXML.createElement("Peso");
            tk.appendChild(documentoXML.createTextNode("" + arista.peso));
            item.appendChild(tk);
            descarga.appendChild(item);
        }



    }

    private void generaDocumentoXML() {
        try {
            // 1. Crear objeto DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactoryImpl.newInstance();
            // 2. A partir del objeto DocumentBuilderFactory crear un objeto DocumentBuilder 
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            // 3. Generar el documento XML
            documentoXML = docBuilder.newDocument();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        // 4. Crear el elemento "descargas"
        descarga = documentoXML.createElement(TAG_GRAFO);
        // 5. Agregar al documento principal
        documentoXML.appendChild(descarga);
    }

    private String generaTextoXML() {
        StringWriter strWriter = null;
        XMLSerializer seliarizadorXML = null;
        OutputFormat formatoSalida = null;
        try {
            seliarizadorXML = new XMLSerializer();
            strWriter = new StringWriter();
            formatoSalida = new OutputFormat();
            // 1. Establecer el formato
            formatoSalida.setEncoding(XML_ENCODING);
            formatoSalida.setVersion(XML_VERSION);
            formatoSalida.setIndenting(true);
            formatoSalida.setIndent(4);
            // 2. Definir un objeto donde se generara el codigo
            seliarizadorXML.setOutputCharStream(strWriter);
            // 3. Aplicar el formato
            seliarizadorXML.setOutputFormat(formatoSalida);
            // 4. Serializar documento XML
            seliarizadorXML.serialize(documentoXML);
            strWriter.close();
        } catch (IOException ioEx) {
            System.out.println("Error : " + ioEx);
        }
        return strWriter.toString();
    }

    public void guardarDocumentoXML(String texto,File grafo) {
        try {
            OutputStream fout = new FileOutputStream(grafo);
            OutputStream bout = new BufferedOutputStream(fout);
            OutputStreamWriter out = new OutputStreamWriter(bout, JAVA_ENCODING);
            out.write(texto);
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error codificacion");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public String obtenerTextoXML() {
        return generaTextoXML();
    }
}