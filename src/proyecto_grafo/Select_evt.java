package proyecto_grafo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Administrador
 */
public class Select_evt implements ListSelectionListener {

    private Table_mod Matriz_V;
    private JList list_V;
    private JLabel Text;
    private int n;

    public Select_evt(Table_mod Matriz_V, JList list_V, JLabel Text) {
        this.Matriz_V = Matriz_V;
        this.list_V = list_V;
        this.Text = Text;
    }

    public void valueChanged(ListSelectionEvent e) {
        int i = list_V.getSelectedIndex();
        if (i != -1) {

            data_fila vertices = (data_fila) Matriz_V.getElementAt(i);
            if (vertices != null) {
                Text.setText("Vertice " + vertices.getNombre() + "\n");
                int k[] = vertices.getArry();


            }
        }
    }
}

