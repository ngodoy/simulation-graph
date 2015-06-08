package proyecto_grafo;

import java.awt.Graphics2D;
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
public class Grafo {

    private LinkedList<Arista> aristos;// lista de arista
    private LinkedList<Vertices> lista;// lista de vertices
    private int tipo_grafo;

    public Grafo(int tipo_grafo) {
        this.tipo_grafo = tipo_grafo;
    }

    /**
     * Get the value of tipo_grafo
     *
     * @return the value of tipo_grafo
     */
    public int getTipo_grafo() {
        return tipo_grafo;
    }

    /**
     * Set the value of tipo_grafo
     *
     * @param tipo_grafo new value of tipo_grafo
     */
    public void setTipo_grafo(int tipo_grafo) {
        this.tipo_grafo = tipo_grafo;
    }

    public boolean contains_arista(Object o) {
        return aristos.contains(o);
    }

    public int size_vertice() {
        return lista.size();
    }

    public Vertices get_vertices(int index) {
        return lista.get(index);
    }

    public int indexOf_Vertices(Object o) {
        return lista.indexOf(o);
    }

    public Arista get_arista(int index) {
        return aristos.get(index);
    }

    public int size_arista() {
        return aristos.size();
    }

    /**
     * Get the value of aristos
     *
     * @return the value of aristos
     */
    public LinkedList getAristos() {
        return aristos;
    }

    /**
     * Set the value of aristos
     *
     * @param aristos new value of aristos
     */
    public void setAristos(LinkedList aristos) {
        this.aristos = aristos;

    }

    /**
     * regresa el vertice que esta en el parametro "o"
     * y devueve null sino lo encuentra
     * @return
     */
    public Vertices contains(Point2D o) {
        for (Vertices nodo : lista) {
            if (nodo != null) {
                if (nodo.distance(o) < nodo.getTamaño()) {
                    return nodo;
                }
            }
        }
        return null;
    }

    public Grafo() {
        this.lista = new LinkedList<Vertices>();
        this.aristos = new LinkedList<Arista>();

    }

    public boolean add(Vertices e) {

        return lista.add(e);
    }

    public void addLast(Vertices e) {
        lista.addLast(e);
    }

    /**
     * verificamos los vertices ayacente al
     * @param o removemos todos los caminos hacia este
     * vertice de ser cieto @return verdadero sino falso
     */
    public boolean remove(Object o) {

        Vertices a = (Vertices) o;
        if (lista.remove(a)) {
            System.out.print("elimi en vertices\n");
            for (Vertices v : lista) {
                if (v != null) {
                    int t = v.Existe_adyacencia(a);
                    if (t != -1) {
                        System.out.print("el vertice  " + v.getNombre() + "  es adyacente a el vertice " + a.getNombre() + " en la poscion " + t);
                        Arista b = v.remove_adyacencia(t);
                        this.aristos.remove(b);
                        a.setGrado_menos();
                        this.aristos.removeAll(a.getCamino());
                        return true;
                    }
                }
            }
            a.setGrado_menos();
            this.aristos.removeAll(a.getCamino());
        }
        return false;
    }

    /**
     * Get the value of lista
     *
     * @return the value of lista
     */
    public LinkedList getLista() {
        return lista;
    }

    /**
     * Set the value of lista
     *
     * @param lista new value of lista
     */
    public void setLista(LinkedList lista) {
        this.lista = lista;
    }

    public Object[] toArray() {
        return lista.toArray();
    }

    /**
     * dibula toda la lista de adyacencia
     * @param g2
     */
    public void dibujate(Graphics2D g2) {

        for (Arista arista : aristos) {
            if (arista != null) {
                arista.Dibujate(g2);
            }
        }
        for (Vertices vertice : lista) {
            if (vertice != null) {
                vertice.Dibujate(g2);
            }
        }

    }

    public void addLast(Arista e) {
        aristos.addLast(e);
    }

    public void addFirst(Arista e) {
        aristos.addFirst(e);
    }

    public int indexOf(Object o) {
        return aristos.indexOf(o);
    }

    /**
     * pone todos los vertices como eje de parametro
     * b
     * @param b
     */
    public void vistados(boolean b) {
        for (Vertices v : this.lista) {
            if (v != null) {
                v.setVisitado(b);
            }
        }
    }

    /**
     * busca una arista entre dos vertices sino lo encuentra
     * devuelve una arista null
     * @param nodoAux
     * @param nodoCaminoAux
     * @return
     */
    public Arista arista_entre(Vertices nodoAux, Vertices nodoCaminoAux) {
        int i = 0;
        Arista arista;
        while (i < this.size_arista()) {
            arista = this.get_arista(i);
            if ((this.tipo_grafo == 3) || (this.tipo_grafo == 4)) {
                if ((arista.getOrigen() == nodoAux) &&
                        (arista.getFin() == nodoCaminoAux)) {
                    return arista;
                } else {
                    i++;
                }
            } else {
                if (((arista.getOrigen() == nodoAux) &&
                        (arista.getFin() == nodoCaminoAux)) ||
                        ((arista.getOrigen() == nodoCaminoAux)) &&
                        (arista.getFin() == nodoAux)) {
                    return arista;
                } else {
                    i++;
                }
            }
        }
        return null;
    }

    public ArrayList<String> buscar_nodo(int h) {
        ArrayList<String> vertices = new ArrayList<String>();
        for (Vertices v : this.lista) {
            if (v != null) {
                if (v.getGrado() == h) {
                    vertices.add(v.getNombre());
                }
            }

        }
        return vertices;

    }
}