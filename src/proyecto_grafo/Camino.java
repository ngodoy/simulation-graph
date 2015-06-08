
package proyecto_grafo;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Clase que especifica el camino entre nodos o aristas
 */
public class Camino {

    Grafo gr;
    Vector camino = new Vector();
    Vector distanciaCamino;
    Vector padre;

    /**
     * Constructor de Camino
     * @param g Grafo sobre el que se desea conocer el camino
     */
    public Camino(Grafo g) {
        this.gr = g;
    }

    /** 
     * M�todo que devuelve el camino
     * @return Camino
     */
    public Vector darCamino() {
        return camino;
    }

    /**
     * M�todo que implementa el algoritmo de Dijkstra
     * @param nodo Nodo del que se desea conocer camino m�nimo
     */
    public void dijkstra(Vertices nodo) {
        int i;
        LinkedList<Vertices> colaNodos = new LinkedList<Vertices>();
        LinkedList<Arista> adyacentesNodos = new LinkedList<Arista>();
        Vertices nodoAux;
        Arista arista;
        int peso;

        int infinito = Integer.MAX_VALUE;
        LinkedList<Vertices> nodos = this.gr.getLista();
        gr.vistados(false);
        for (i = 0; i < nodos.size(); i++) {
            nodoAux = nodos.get(i);
            nodoAux.setPadre(null);
            if (nodoAux.equals(nodo)) {
                nodoAux.setDistancia(0);
            } else {
                nodoAux.setDistancia(infinito);
            }
            // Se encolan los nodos del grafo
        }

        colaNodos = nodos;
        
        boolean sigue = true;
        while (sigue) {
            nodoAux = extraer_Minimo(colaNodos);
            sigue = colaNodos.contains(nodoAux);
            if (sigue) {
                 adyacentesNodos = nodoAux.getCamino();
               i = 0;
                while (i < adyacentesNodos.size()) {
                    Vertices nodoCaminoAux = new Vertices();
                    nodoCaminoAux = adyacentesNodos.get(i).inverso(nodoAux);
                    arista = this.gr.arista_entre(nodoAux, nodoCaminoAux);
                     if (arista != null) {
                        peso = arista.getPeso();
                         if (nodoCaminoAux.getDistancia() > (nodoAux.getDistancia() + peso)) {
                            int k = 0;
                            Vertices n = new Vertices();
                            while (k < colaNodos.size()) {
                                n = colaNodos.get(k);
                                if (n.equals(nodoCaminoAux)) {
                                    k = colaNodos.size();
                                }
                                k++;
                            }
                            nodoCaminoAux.setDistancia(nodoAux.getDistancia() + peso);
                            nodoCaminoAux.setPadre(nodoAux);
                            n.setDistancia(nodoAux.getDistancia() + peso);

                            this.camino.add(nodoCaminoAux);
                            }
                    }
                    i++;
                }
            }
        }    
    }

    /**
     * M�todo que extrae el nodoCamino con m�nima distancia de una cola dada, de la que borra dicho elemento
     * @param colaNodos Cola de nodoCaminos
     * @return El nodoCamino con m�nima distancia
     */
    private Vertices extraer_Minimo(LinkedList<Vertices> colaNodos) {
        int i = 0;
        int distMinima = Integer.MAX_VALUE;
        Vertices nodoCamino = new Vertices();
        Vertices nodoCaminoAux;

        while (i < colaNodos.size()) {
            nodoCaminoAux = colaNodos.get(i);          
            if ((!nodoCaminoAux.getVisitado()) && (nodoCaminoAux.getDistancia() < distMinima)) {
                nodoCamino = nodoCaminoAux;
                distMinima = nodoCaminoAux.getDistancia();
            }
            i++;
        }
        if (colaNodos.contains(nodoCamino)) {
            nodoCamino.setVisitado(true);
        }
      return nodoCamino;
    }
}
