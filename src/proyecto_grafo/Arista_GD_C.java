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

/**
 * Aristas de grafo dirigido con peso
 */
public class Arista_GD_C extends Arista {

    /**
     * solo resive dos variables y utiliza el constructor por defecto
     *
     * @param origen
     * @param fin
     */
    public Arista_GD_C(Vertices origen, Vertices fin) {
        super(origen, fin);
    }

    /**
     * resive tres variables ademas utiliza
     *
     * @param peso
     * @param origen
     * @param fin
     */
    public Arista_GD_C(int peso, Vertices origen, Vertices fin) {
        super(peso, origen, fin);
        origen.addLast(this);//se le agrega a lista del vertyice esta arista
        fin.setGrado_mas();//se aumenta el grado del vertice

    }

    @Override
    public void Dibujate(Graphics2D g2) {

        int x = (int) (origen.getPosicion().getX() + fin.getPosicion().getX());
        int y = (int) (origen.getPosicion().getY() + fin.getPosicion().getY());
        Point medio = new Point(x / 2, y / 2);
        g2.setPaint(Color.RED);
        g2.draw(new Line2D.Double(origen.getPosicion(), medio));
        g2.setPaint(Color.WHITE);
        g2.drawString("" + this.peso, medio.x, medio.y);
        g2.setPaint(Color.GREEN);
        g2.draw(new Line2D.Double(medio, fin.getPosicion()));

    }

    /**
     *
     */
    @Override
    public void correcto() {
        origen.addLast(this);//se le agrega a lista del vertyice esta arista
        fin.setGrado_mas();//se aumenta el grado del vertice
        try {
            String aux = JOptionPane.showInputDialog(null, "Escriba El numero", "Ingrese El Peso", JOptionPane.INFORMATION_MESSAGE);
            if (aux != null) {
                this.peso = Integer.parseInt(aux);// se le asigna el peso           
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La Entrada No Es Un Numero", "Error", JOptionPane.ERROR_MESSAGE);

            // Discard input or request new input ...
            // clean up if necessary
        }
    }
}
