package proyecto_grafo;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Administrador
 */
public class ver_bien_list extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        data_fila dato = (data_fila) value;
        return super.getListCellRendererComponent(list, dato.getNombre(), index, isSelected, cellHasFocus);
    }
}
