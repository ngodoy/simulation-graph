package proyecto_grafo;

//www.casidiablo.net
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProcesarXML {

    private int representacion;
    private Document dom;

    public ProcesarXML(File archivo) {
        parsearArchivoXml(archivo);
    }
    //Parsea el archivo XML
    private void parsearArchivoXml(File archivo) {
        // 1. Obteher el objeto DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // 2. Usar DocumentBuilderFactory para crear un DocumentBuilder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3. Parsear a partir de un archivo
            dom = db.parse(archivo);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    //Parsea el documento XML y extrae los datos
    public Grafo parsearDocumento() {
        Grafo g = new Grafo();
       // pedimos los nodos con el nombre modo
        
        NodeList nodo = dom.getElementsByTagName("Modo");
        if (nodo != null && nodo.getLength() > 0) {
            obtener_modo(g, (Element) nodo.item(0));
        }
        nodo = dom.getElementsByTagName("Vertice");
        if (nodo != null && nodo.getLength() > 0) {
            // pedimos los nodos con el nombre vertices
            for (int i = 0; i < nodo.getLength(); i++) {
                this.obtener_vertice(g, (Element) nodo.item(i));
            }
        }

        nodo = dom.getElementsByTagName("Arista");
        if (nodo != null && nodo.getLength() > 0) {
            // pedimos los nodos con el nombre arista
            for (int i = 0; i < nodo.getLength(); i++) {
                this.obtener_arista(g, (Element) nodo.item(i));
            }
        }

        return g;
    }

    /**
     * obtemos los datos de la arista 
     * @param g
     * @param hijo
     */
    private void obtener_arista(Grafo g, Element hijo) {
        int origen = Integer.parseInt(this.obtenerTexto(hijo, "Vertice_origen"));
        int finl = Integer.parseInt(this.obtenerTexto(hijo, "Vertice_fin"));
        int peso = Integer.parseInt(this.obtenerTexto(hijo, "Peso"));
        Vertices ini = g.get_vertices(origen);
        Vertices fin = g.get_vertices(finl);
        switch (g.getTipo_grafo()) {
            case 1:
                Arista_GND_S camino = new Arista_GND_S(peso, ini, fin);
                g.addLast(camino);
                break;
            case 2:
                Arista_GND_C camino_2 = new Arista_GND_C(peso, ini, fin);
                g.addLast(camino_2);
                break;
            case 3:
                Arista_GD_S camino_3 = new Arista_GD_S(peso, ini, fin);
                g.addLast(camino_3);
                break;
            case 4:
                Arista_GD_C camino_4 = new Arista_GD_C(peso, ini, fin);
                g.addLast(camino_4);
                break;
        }

    }

    /**
     *    obtemos los datos de la grafo
     * @param g
     * @param elemento
     */
    private void obtener_modo(Grafo g, Element elemento) {
        this.representacion = obtenerEntero(elemento, "Representacion_de_Grafo");
        g.setTipo_grafo(obtenerEntero(elemento, "Tipo_de_Grafo"));

    }

    /**
     *  obtemos los datos del vertice
     * @param g
     * @param hijo
     */
    private void obtener_vertice(Grafo g, Element hijo) {
        String nombre = obtenerTexto(hijo, "Nombre");     
        int x = obtenerEntero(hijo, "Posicion_X");
        int y = obtenerEntero(hijo, "Posicion_Y");
        Point posicion = new Point(x, y);
        Vertices v = new Vertices(nombre, posicion);
        g.addLast(v);
    }
    
    //Devuelve un valor entero del elemento recibido
    private int obtenerEntero(Element elemento, String nombreEtiqueta) {
        return (int) Double.parseDouble(obtenerTexto(elemento, nombreEtiqueta));
    }
    //Devuelve los datos del elemento recibido
    private String obtenerTexto(Element elemento, String nombreEtiqueta) {
        String texto = null;
        NodeList nl = elemento.getElementsByTagName(nombreEtiqueta);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            texto = el.getFirstChild().getNodeValue();
        }
        return texto;
    }

    public int getRepresentacion() {
        return representacion;
    }
}