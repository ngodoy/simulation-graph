/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_grafo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import javax.swing.JOptionPane;

public class Arista_GND_C extends Arista {

    /**
     * constructor
     *
     * @param origen
     * @param fin
     */
    public Arista_GND_C(Vertices origen, Vertices fin) {
        super(origen, fin);
    }

    /**
     * constructor
     *
     * @param peso
     * @param origen
     * @param fin
     */
    public Arista_GND_C(int peso, Vertices origen, Vertices fin) {
        super(peso, origen, fin);
        origen.addLast(this);
        fin.addLast(this);
        origen.setGrado_mas();// se aumenta el grado del vertice
        fin.setGrado_mas();//se aumenta el grado del vertice
    }

    /**
     * dibuja una linea con su peso en los vertices
     *
     * @param g2
     */
    @Override
    public void Dibujate(Graphics2D g2) {
        int x = (int) (origen.getPosicion().getX() + fin.getPosicion().getX());
        int y = (int) (origen.getPosicion().getY() + fin.getPosicion().getY());
        Point medio = new Point(x / 2, y / 2);
        g2.setPaint(Color.WHITE);
        g2.drawString("" + this.peso, medio.x, medio.y);
        g2.setPaint(color);
        g2.draw(new Line2D.Double(origen.getPosicion(), fin.getPosicion()));
    }

    /**
     * solo agrega un peso
     */
    @Override
    public void correcto() {
        origen.addLast(this);
        fin.addLast(this);
        origen.setGrado_mas();
        fin.setGrado_mas();
        try {
            String aux = JOptionPane.showInputDialog(null, "Escriba El numero", "Ingrese El Peso", JOptionPane.INFORMATION_MESSAGE);
            if (aux != null) {
                this.peso = Integer.parseInt(aux);// se le asigna el peso           
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La Entrada No Es Un Numero", "Error", JOptionPane.ERROR_MESSAGE);

            // JOptionPane.showInputDialog(null, "Error", "la entrada no es un numero",JOptionPane.ERROR_MESSAGE );
            // Discard input or request new input ...
            // clean up if necessary
        }
    }

}
