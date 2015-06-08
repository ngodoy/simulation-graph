/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_grafo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;


public class Arista_GD_S extends Arista {

    /**
     * Constructor normal..
     * @param origen
     * @param fin
     */
    public Arista_GD_S(Vertices origen, Vertices fin) {
        super(origen, fin);
    }

    /**
     * resive tres variables
     * @param peso
     * @param origen
     * @param fin
     */
    public Arista_GD_S(int peso, Vertices origen, Vertices fin) {
        super(peso, origen, fin);
        origen.addLast(this);//se le agrega a lista del vertyice esta arista
        fin.setGrado_mas();//se aumenta el grado del vertice
    }

    /**
     * dibuja una linea entre dos vertices..
     * @param g2
     */
    @Override
    public void Dibujate(Graphics2D g2) {
        int x = (int) (origen.getPosicion().getX() + fin.getPosicion().getX());
        int y = (int) (origen.getPosicion().getY() + fin.getPosicion().getY());
        Point medio = new Point(x / 2, y / 2);
        g2.setPaint(Color.RED);
        g2.draw(new Line2D.Double(origen.getPosicion(), medio));
        g2.setPaint(Color.GREEN);
        g2.draw(new Line2D.Double(medio, fin.getPosicion()));
    }

    @Override
    public void correcto() {
        origen.addLast(this);//se le agrega a lista del vertyice esta arista
        fin.setGrado_mas();//se aumenta el grado del vertice
    }
}
