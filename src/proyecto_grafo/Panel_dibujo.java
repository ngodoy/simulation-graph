package proyecto_grafo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


import java.util.ArrayList;

import java.util.LinkedList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import proyecto_grafo.Arista;

/**
 *
 * @author  Administrador
 */
public class Panel_dibujo extends javax.swing.JPanel {

    private int contador_v;
    private Boolean drawing;
    private JTextArea area_text;
    private Vertices seleccion;
    private Vertices ini;
    private Vertices fin;
    private Grafo grafo;
    private boolean moviendo;

    /**
     * Get the value of grafo
     *
     * @return the value of grafo
     */
    public Grafo getGrafo() {
        return grafo;
    }

    /**
     * Set the value of grafo
     *
     * @param grafo new value of grafo
     */
    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;

        this.repaint();

    }

    /**
     * Get the value of drawing
     *
     * @return the value of drawing
     */
    public Boolean getDrawing() {
        return drawing;
    }

    /**
     * Set the value of drawing
     *
     * @param drawing new value of drawing
     */
    public void setDrawing(Boolean drawing) {
        this.drawing = drawing;
    }

    /**
     * Get the value of selecion
     *
     * @return the value of selecion
     */
    public Vertices getSelecion() {
        return seleccion;
    }

    /**
     * Set the value of selecion
     *
     * @param selecion new value of selecion
     */
    public void setSelecion(Vertices selecion) {
        this.seleccion = selecion;
    }

    /** Creates new form Panel_dibujo */
    public Panel_dibujo() {
        //setBackground(Color.WHITE);
        this.setTipo_grafo(1);
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        grafo.dibujate(g2);
    }

    public void eliminar(KeyEvent evt) {
        if (127 == evt.getKeyCode()) {
            if (this.seleccion != null) {
                grafo.remove(seleccion);
            }
        }
        this.repaint();
    }
    private int tipo_grafo;

    /**
     * Get the value of tipo_grafo
     *
     * @return the value of tipo_grafo
     */
    public int getTipo_grafo() {
        return grafo.getTipo_grafo();
    }

    /**
     * Set the value of tipo_grafo  *
     * @param tipo_grafo new value of tipo_grafo
     */
    public void setTipo_grafo(int tipo_grafo) {
        this.tipo_grafo = tipo_grafo;
        grafo = new Grafo();
        grafo.setTipo_grafo(tipo_grafo);
    }

    /**
     * elige el vertices y llama la funcion recursiva de profundidad
     */
    public void recorrido_profundida() {
        int numero = elija_vertices();
        if (numero != -1) {
            grafo.vistados(false);
            area_text.setText("");
            recorrido_profundida(grafo.get_vertices(numero));
        }

    }

    /**
     * inicializamos un contador de vertices en cero
     * y visitamos los nodos.. si falta un nodo por visitar
     * el grafo no es conexo...
     */
    public void conexo() {
        int numero = elija_vertices();
        if (numero != -1) {
            grafo.vistados(false);
            area_text.setText("");
            contador_v = 0;
            realizarAnchura(grafo.get_vertices(numero));
            if (contador_v == grafo.size_vertice()) {
                JOptionPane.showMessageDialog(null, "El Grafo es Conexo ", "Es Conexo ?", JOptionPane.WARNING_MESSAGE, null);

            } else {
                JOptionPane.showMessageDialog(null, "El Grafo no es Conexo ", "Es Conexo ?", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }

    /**
     * elige el vertice y luego llama a  la funcion excenricidadd de ese vertice
     * el cual es privado....
     */
    public void exentricidad() {
        int numero = elija_vertices();
        if (numero != -1) {
            grafo.vistados(false);
            area_text.setText("");
            contador_v = 0;
            realizarAnchura(grafo.get_vertices(numero));
            if (contador_v == grafo.size_vertice()) {
                int exc = this.excentricidad(grafo.get_vertices(numero), grafo);

                JOptionPane.showMessageDialog(null, "El Vertices tiene Exentricidad " + exc, "Execentricidad ?", JOptionPane.WARNING_MESSAGE, null);

            } else {
                JOptionPane.showMessageDialog(null, "El Vertices tiene Exentricidad 0", "Execentricidad?", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }

    /**
     * recorremos secuencialmente el grafo
     * y buscamos el nodo o vertice con mayor excentricidad.
     */
    public void diametro_grafo() {

        grafo.vistados(false);
        area_text.setText("");
        contador_v = 0;
        realizarAnchura(grafo.get_vertices(0));
        if (contador_v == grafo.size_vertice()) {
            int exc = this.excentricidad(grafo.get_vertices(0), grafo);
            for (int i = 1; i < grafo.size_vertice(); i++) {
                int aux = this.excentricidad(grafo.get_vertices(i), grafo);
                if (exc < aux) {
                    exc = aux;
                }

            }
            JOptionPane.showMessageDialog(null, "El Vertices tiene Exentricidad " + exc, "Execentricidad ?", JOptionPane.WARNING_MESSAGE, null);

        } else {
            JOptionPane.showMessageDialog(null, "El Vertices tiene Exentricidad ", "Execentricidad?", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    /**
     * solol busca los camino que existen en nodo 
     * ó vertices ademas me dice si existe un camino si ono
     */
    public void camino_entre_nodo() {
        if (grafo.size_vertice() > 1) {
            int numero = elija_vertices();
            if (numero != -1) {
                int numero2 = elija_vertices();
                if (numero2 != -1) {
                    Camino camino = new Camino(grafo);
                    Vertices inicio = grafo.get_vertices(numero);
                    camino.dijkstra(inicio);
                    boolean sigue = true;
                    Vertices nodoAux = grafo.get_vertices(numero2);

                    this.area_text.setText("");
                    if (nodoAux.getPadre() == null) {
                        this.area_text.setText("No existe camino \n entre Estos Vertices");
                        return;
                    }
                    while (sigue) {
                        if (sigue) {
                            this.area_text.setText(nodoAux.getNombre() + "  " +
                                    nodoAux.getDistancia() + "\n" + area_text.getText());
                            Vertices padre = nodoAux.getPadre();
                            nodoAux = padre;
                            sigue = (!inicio.equals(nodoAux));
                        }
                    }
                }
            }
        }

    }

    public void nodo_con_grado() {
        String input = (String) JOptionPane.showInputDialog(null, "Elija el Grado", "Grado de los Vertices", JOptionPane.QUESTION_MESSAGE);
        int h = Integer.parseInt(input);
        this.area_text.setText("");
        ArrayList<String> nombres = grafo.buscar_nodo(h);
        for (String n : nombres) {
            if (n != null) {
                this.area_text.append(n + "\n");
            }

        }
    }

    public void existen_ciclo() {
        int h = elija_vertices();
        if (h != -1) {
            grafo.vistados(false);
            area_text.setText("");
            Vertices nodo = grafo.get_vertices(h);
            nodo.setVisitado(true);
            asd(nodo);
        }

    }

    private void asd(Vertices e) {
        // area_text.append(e.getNombre() + "\n");
        LinkedList<Arista> h = (LinkedList<Arista>) e.camino_clone();
        while (!h.isEmpty()) {
            ArrayList<String> nombre = new ArrayList<String>();
            nombre.add(e.getNombre());
            int i = verificar_ciclo(h.removeFirst().inverso(e), nombre);
            if (i != 1) {
                for (String n : nombre) {
                    if (n != null) {
                        area_text.append(n + "\n");
                    }

                }
                return;
            }
        }
    }

    private int verificar_ciclo(Vertices nodo, ArrayList<String> nombre) {
        if (nodo != null) {
            if (!nodo.getVisitado()) {
                nombre.add(nodo.getNombre());
                LinkedList<Arista> h = (LinkedList<Arista>) nodo.camino_clone();
                while (!h.isEmpty()) {
                    int i = verificar_ciclo(h.removeFirst().inverso(nodo), nombre);
                    if (i != 1) {
                        return -1;
                    }
                }

            } else {
                nombre.add(nodo.getNombre());
                return -1;
            }
        }
        return 1;
    }

    /**
     * solo busca en todo el grafo la excentriciadad mayor..
     * usado variaa estructuras repetivas
     * @param vertices
     * @param grafo
     * @return
     */
    private int excentricidad(Vertices vertices, Grafo grafo) {
        int excentricidad = vertices.getExecentriciada();
        Camino camino = new Camino(grafo);
        camino.dijkstra(vertices);
        int i = 0;
        Vertices nodoAux;
        int distancia = -1;
        while (i < grafo.size_vertice()) {
            nodoAux = grafo.get_vertices(i);
            if (nodoAux.getDistancia() > distancia) {
                excentricidad = nodoAux.getDistancia();
                vertices.setExecentriciada(excentricidad);
                distancia = excentricidad;
            }
            i++;
        }

        return excentricidad;
    }

    /**
     * metodo que elige el vertice y llama 
     * al recorrido en anchura de ese vertice..
     */
    public void recorrido_anchura() {
        int h = elija_vertices();
        if (h != - 
            1) {
            grafo.vistados(false);
            area_text.setText("");
            realizarAnchura(grafo.get_vertices(h));
        }

    }

    /**
     * metodo recursivo que una pila para 
     * recorrer en profundidad los vertices de un grafo...
     * @param nodo
     */
    private void recorrido_profundida(Vertices nodo) {

        if (nodo != null) {
            if (!nodo.getVisitado()) {
                area_text.append(nodo.getNombre() + "\n");
                nodo.setVisitado(true);
                LinkedList<Arista> h = (LinkedList<Arista>) nodo.camino_clone();
                while (!h.isEmpty()) {
                    recorrido_profundida(h.removeFirst().inverso(nodo));
                }

            }
        }
    }

    /**
     * estee l recorrido en ancura y usa una cola 
     * oara recorrer el grafo.
     * @param e
     */
    private void realizarAnchura(Vertices e) {
        LinkedList<Vertices> cola = new LinkedList<Vertices>();	// Cola FIFO (Primero en entrar --> primero en salir)
        LinkedList<Arista> hijos;	// Lista para contener todos los hijos de actual       
        Vertices v = null;	// Uno de los estados hijo
        e.setVisitado(true);
        cola.addLast(e); // mete estado e en la cola
        while (!cola.isEmpty()) {
            contador_v++;
            Vertices actual = cola.removeFirst();
            area_text.append(actual.getNombre() + "\n");
            hijos = actual.getCamino();
            for (int i = 0; i < hijos.size(); i++) {
                v = hijos.get(i).inverso(actual);
                if (!v.getVisitado()) {
                    v.setVisitado(true);
                    cola.addLast(v);
                }

            }
        }
    }

    /**
     * este metodo utiliza un Jopciont panel para
     * seleccionar el vertices y regresa el indicador 
     * de la posicion de la lista
     * @return
     */
    private int elija_vertices() {
        if (grafo.size_vertice() == 0) {
            return -1;
        }

        ArrayList<String> nombres = new ArrayList<String>();
        for (int i = 0; i < grafo.size_vertice(); i++) {
            String v = grafo.get_vertices(i).getNombre();
            nombres.add(v);
        }

        String input = (String) JOptionPane.showInputDialog(null, "Elija el vertice",
                "Los Vertices Del Grafo", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                nombres.toArray(), // Array of choices
                nombres.get(
                0)); // Initial choice
        int h = nombres.indexOf(input);
        return h;
    }

    /**
     * Este metodo escribe en el text area
     */
    private void escrive() {
        if (seleccion != null) {
            this.area_text.setText("" + seleccion.getNombre() + "\nTiene " + seleccion.getGrado() + " grado");
        }



    }

    /**
     * Get the value of area_text
     *
     * @return the value of area_text
     */
    public JTextArea getArea_text() {
        return area_text;
    }

    /**
     * Set the value of area_text
     *
     * @param area_text new value of area_text
     */
    public void setArea_text(JTextArea area_text) {
        this.area_text = area_text;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_vertices(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                add_aristas(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Mover_vertice(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kay_elimina(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * En este evento creamos los aristas y movemos los vertices 
     * @param evt
     */
private void Mover_vertice(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mover_vertice
    if (SwingUtilities.isLeftMouseButton(evt)) {
        if (seleccion == null) {
            seleccion = grafo.contains(evt.getPoint());

        } else {
            seleccion.setPosicion(evt.getPoint());
        }

    } else if (SwingUtilities.isRightMouseButton(evt)) {
        if (moviendo) {
            fin = grafo.contains(evt.getPoint());
            if (fin != null) {

                if (!fin.equals(ini)) {
                    switch (tipo_grafo) {
                        case 1:
                            Arista_GND_S camino = new Arista_GND_S(ini, fin);
                            if (!grafo.contains_arista(camino)) {
                                this.grafo.addLast(camino);
                                camino.correcto();
                            }

                            break;

                        case 2:
                            Arista_GND_C camino_2 = new Arista_GND_C(ini, fin);
                            if (!grafo.contains_arista(camino_2)) {
                                this.grafo.addLast(camino_2);
                                camino_2.correcto();
                            }

                            break;
                        case 3:
                            Arista_GD_S camino_3 = new Arista_GD_S(ini, fin);
                            if (!grafo.contains_arista(camino_3)) {
                                this.grafo.addLast(camino_3);
                                camino_3.correcto();
                            }

                            break;
                        case 4:
                            Arista_GD_C camino_4 = new Arista_GD_C(ini, fin);
                            if (!grafo.contains_arista(camino_4)) {
                                this.grafo.addLast(camino_4);
                                camino_4.correcto();
                            }

                            break;

                    }

                }
            }
        }

    }
    repaint();
}//GEN-LAST:event_Mover_vertice

    /**
     * añadimos en el grafo un vertice
     * @param evt
     */
private void add_vertices(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_vertices
    if (SwingUtilities.isLeftMouseButton(evt)) {
        if (seleccion != null) {
            seleccion.setSelecion(false);
        }

        seleccion = null;
        seleccion =
                grafo.contains(evt.getPoint());
        if (seleccion != null) {
            escrive();
            seleccion.setSelecion(true);
        } else {

            String nombre = JOptionPane.showInputDialog(this, "Escriva el nombre del vertices", "Nombre del Vertice", JOptionPane.QUESTION_MESSAGE);
            if (nombre != null) {
                grafo.add(new Vertices(nombre, evt.getPoint()));
            }

        }

    }
    repaint();
}//GEN-LAST:event_add_vertices

    /**
     * comoenzamos a mover el mause para añadir en la lista
     * @param evt
     */
private void add_aristas(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_aristas

    if (SwingUtilities.isRightMouseButton(evt)) {
        ini = grafo.contains(evt.getPoint());
        if (ini != null) {
            moviendo = true;
        } else {
            moviendo = false;
        }

    }

    repaint();
}//GEN-LAST:event_add_aristas
    /**
     * escucha del teclado para eliminar la lista seleccionada
     * @param evt
     */
private void kay_elimina(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kay_elimina
    this.eliminar(evt);
}//GEN-LAST:event_kay_elimina
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
