/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_grafo;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;


public class Arista_GND_S extends Arista {

    /**
     * constructor normal
     * @param origen
     * @param fin
     */
    public Arista_GND_S(Vertices origen, Vertices fin) {
        super(origen, fin);
    }

    /**
     * constructor
     * @param peso
     * @param origen
     * @param fin
     */
    public Arista_GND_S(int peso, Vertices origen, Vertices fin) {
        super(peso, origen, fin);
        origen.addLast(this);
        fin.addLast(this);
        origen.setGrado_mas();// se aumenta el grado del vertice
        fin.setGrado_mas();//se aumenta el grado del vertice
    }

    // solo dibuja una linea
    @Override
    public void Dibujate(Graphics2D g2) {       
        g2.setPaint(color);
        g2.draw(new Line2D.Double(origen.getPosicion(), fin.getPosicion()));
    }

    @Override
    public void correcto() {
        origen.addLast(this);
        fin.addLast(this);
        origen.setGrado_mas();
        fin.setGrado_mas();
    }
}
