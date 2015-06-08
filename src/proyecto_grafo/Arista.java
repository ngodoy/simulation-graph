package proyecto_grafo;

import java.awt.Color;
import java.awt.Graphics2D;


public abstract class Arista {
    /**
     * Variables.....
     */
    protected int peso;  
    protected final Color color = Color.red;  
    protected Vertices fin;  
    protected Vertices origen;

    
   /**
    * Constructor de las aristas
    * @param peso
    * @param origen
    * @param fin
    */  
    public Arista(int peso, Vertices origen, Vertices fin) {
        this.peso = peso;
        this.fin = fin;
        this.origen = origen;
    }

   /**
    * Constructor de las aristas
    * @param origen eso kasd 
    * @param fin
    */
    public Arista(Vertices origen, Vertices fin) {
        this.fin = fin;
        this.origen = origen;
        this.peso = 1;
    }

    /**
     * Solo se hace cuando la arista es correcta
     */
    public abstract void correcto();

    /**
     * La forma como se dibuja la srista
     */
    public abstract void Dibujate(Graphics2D g2);

    /**
     * Get the value of fin
     * @return the value of fin
     */
    public Vertices getFin() {
        return fin;
    }

    /**
     * Set the value of fin
     * @param fin new value of fin
     */
    public void setFin(Vertices fin) {
        this.fin = fin;
    }

    /**
     * Get the value of origen
     * @return the value of origen
     */
    public Vertices getOrigen() {
        return origen;
    }

    /**
     * Set the value of origen
     *
     * @param origen new value of origen
     */
    public void setOrigen(Vertices origen) {
        this.origen = origen;
    }

    /**
     * Get the value of peso
     *
     * @return the value of peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Set the value of peso
     *
     * @param peso new value of peso
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Me devuelve el vertice contrario
     * @param otro
     * @return
     */
    public Vertices inverso(Vertices otro) {
        if (origen.equals(otro)) {
            return fin;
        } else if (fin.equals(otro)) {
            return origen;
        }
        return null;
    }

  
/**
 * verifica si las dos aristas son iguales.
 * @param obj
 * @return
 */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Arista other = (Arista) obj;
        if (this.color != other.color && (this.color == null || !this.color.equals(other.color))) {
            return false;
        }
        if (this.fin != other.fin && (this.fin == null || !this.fin.equals(other.fin))) {
            return false;
        }
        if (this.origen != other.origen && (this.origen == null || !this.origen.equals(other.origen))) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.color != null ? this.color.hashCode() : 0);
        hash = 53 * hash + (this.fin != null ? this.fin.hashCode() : 0);
        hash = 53 * hash + (this.origen != null ? this.origen.hashCode() : 0);
        return hash;
    }
/**
 * Se usa para la tabla de la matriz de insidencia
 * @param v
 * @return
 */
    public int cotenido(Vertices v) {
        if (v.equals(this.getOrigen())) {
            return 1;
        }
        if (v.equals(this.getFin())) {
            return -1;
        }
        return 0;
    }
    
    
}
