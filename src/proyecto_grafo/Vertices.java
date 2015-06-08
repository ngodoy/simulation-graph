package proyecto_grafo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
public class Vertices {

    private int execentriciada;
    private Vertices padre;//algorit de discra 
    private int distancia;////algorit de discra 
    private final int tamaño = 25;
    private Point2D posicion;
    private LinkedList<Arista> camino;
    private String nombre;
    private int grado;
    private Boolean visitado;

    public Vertices() {
            camino = new LinkedList<Arista>();
    }

    /**
     * Get the value of visitado
     *
     * @return the value of visitado
     */
    public Boolean getVisitado() {
        return visitado;
    }

    /**
     * Set the value of visitado
     *
     * @param visitado new value of visitado
     */
    public void setVisitado(Boolean visitado) {
        this.visitado = visitado;
    }

    public int size() {
        return camino.size();
    }

    public void setGrado_menos() {
        this.grado--;
    }

    public void setGrado_mas() {
        this.grado++;
    }

    /**
     * Get the value of grado
     *
     * @return the value of grado
     */
    public int getGrado() {
        return grado;
    }

    /**
     * Set the value of grado
     *
     * @param grado new value of grado
     */
    public void setGrado(int grado) {
        this.grado = grado;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // constructor
    public Vertices(String nombre, Point2D posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.grado = 0;
        camino = new LinkedList<Arista>();
        selecion = false;
        this.visitado = false;
    }

    public Object[] toArray() {
        return camino.toArray();
    }

    /**
     * verifica la distacia de un punto al vertice
     * @param pt
     * @return
     */
    public double distance(Point2D pt) {
        return posicion.distance(pt);
    }

    /**
     * Get the value of tamaño
     *
     * @return the value of tamaño
     */
    public int getTamaño() {
        return tamaño;
    }

    //constructor
    public Vertices(Point2D posicion) {
        this.posicion = posicion;
        camino = new LinkedList<Arista>();
        selecion = false;
    }
   
    private final Color color = Color.BLUE;

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the value of camino
     *
     * @return the value of camino
     */
    public LinkedList<Arista> getCamino() {
        return camino;
    }

    public boolean remove(Object o) {
        return camino.remove(o);
    }

    /**
     * Set the value of camino
     *
     * @param camino new value of camino
     */
    public void setCamino(LinkedList camino) {
        this.camino = camino;
    }

    public void addLast(Arista e) {
        camino.addLast(e);
    }

    /**
     * Get the value of posicion
     *
     * @return the value of posicion
     */
    public Point2D getPosicion() {
        return posicion;

    }

    /**
     * Set the value of posicion
     *
     * @param posicion new value of posicion
     */
    public void setPosicion(Point2D posicion) {
        this.posicion = posicion;
    }

    // dibuja el vertice o el nodo
    public void Dibujate(Graphics2D g2) {
        g2.setPaint(color);
        g2.fill(new Ellipse2D.Double(posicion.getX() - tamaño / 2, posicion.getY() - tamaño / 2, tamaño, tamaño));
        if (selecion) {
            g2.setPaint(Color.red);
            g2.draw(new Ellipse2D.Double(posicion.getX() - tamaño / 2, posicion.getY() - tamaño / 2, tamaño, tamaño));
        }

    }

    /**
     * elimina toda las adyacencias del vertice es decir las aristas
     */
    public void remove_camino() {
        Vertices ver;
        for (Arista eliminar : camino) {
            if (eliminar != null) {
                ver = eliminar.inverso(this);
                ver.remove(eliminar);
            }
        }
    }

    /**
     * ubica el vertice en esa posicion
     * @param point
     * @param anterior
     */
    public void setPosicion(Point point, Point2D anterior) {
        posicion.setLocation(posicion.getX() + point.getX() - anterior.getX(), posicion.getY() + point.getY() - anterior.getY());
    }
    private Boolean selecion;

    /**
     * Get the value of selecion
     *
     * @return the value of selecion
     */
    public Boolean getSelecion() {
        return selecion;
    }

    /**
     * Set the value of selecion
     *
     * @param selecion new value of selecion
     */
    public void setSelecion(Boolean selecion) {
        this.selecion = selecion;
    }

    /**
     * verifica la adyacencia con el vertice
     * @param vertices
     * @return
     */
    public int adyacencia(Vertices vertices) {
        Vertices aux;
        for (Arista v : this.camino) {
            if (v != null) {
                aux = v.inverso(this);
                if (aux != null) {
                    if (aux.equals(vertices)) {
                        int i = camino.indexOf(v);
                        return camino.get(i).peso;
                    }
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * devuelve -1 si no es adyacente lo sontrario si lo es.
     * @param vertices
     * @return
     */
    public int Existe_adyacencia(Vertices vertices) {
        Vertices aux;
        for (Arista v : this.camino) {
            if (v != null) {
                aux = v.inverso(this);
                if (aux != null) {
                    if (aux.equals(vertices)) {
                        return camino.indexOf(v);

                    }
                }
            }
        }
        return -1;
    }

    public Arista remove_adyacencia(int index) {
        return camino.remove(index);
    }

    public Object camino_clone() {
        return camino.clone();
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Vertices getPadre() {
        return padre;
    }

    public void setPadre(Vertices padre) {
        this.padre = padre;
    }
    
    public int getExecentriciada() {
        return execentriciada;
    }

    public void setExecentriciada(int execentriciada) {
        this.execentriciada = execentriciada;
    }

}
