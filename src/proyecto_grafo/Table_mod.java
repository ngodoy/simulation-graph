package proyecto_grafo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * 
 * @author Administrador
 */
public class Table_mod extends DefaultListModel implements TableModel {

    private ArrayList<String> columnNames;// nombre s de la columna

    public boolean add_columnNames(String e) {
        return columnNames.add(e);
    }

    public boolean removeAll() {
        return columnNames.removeAll(columnNames);
    }
//constructor
    public Table_mod() {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Vertices");
    }

    /**
     * añada un elemento de la tabla tanto fila como columna
     * @param obj
     */
    public void addElement(data_fila obj) {
        columnNames.add(obj.getNombre());
        super.addElement(obj);
    }

    /**
     * remuevo el elmento de la posicion index de la tabla
     * @param index
     */
    @Override
    public void removeElementAt(int index) {
        columnNames.remove(index);
        super.removeElementAt(index);
    }

    /**
     * elimino elemento por elemento
     * tanto la columna como la fila de la tabla
     */
    @Override
    public void removeAllElements() {
        int i = 1;
        while (i < columnNames.size()) {
            columnNames.remove(i);
            i++;
        }
        super.removeAllElements();
    }
    private TableModel tableModel = new AbstractTableModel() {

        @Override
        public String getColumnName(int column) {

            return columnNames.get(column);

        }

        public int getRowCount() {
            return size();
        }

        public int getColumnCount() {
            return columnNames.size();
        }

        public Object getValueAt(int row, int column) {
            data_fila rowData = (data_fila) elementAt(row);
            if (column == 0) {
                return rowData.getNombre();
            }
            return rowData.getArry(column - 1);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        
        @Override
        public void setValueAt(Object value, int row, int column) {
            data_fila rowData = (data_fila) elementAt(row);
            String nombre = (String) value;
            if (column == 0) {
                rowData.setNombre(nombre);
                columnNames.set(row , nombre);
            } else {

                rowData.setArry(column - 1, Integer.parseInt(nombre));
            }

            fireTableCellUpdated(row, column); // table event
            rowChanged(row); // list event
        }
    };

    public void rowChanged(int row) {
        fireContentsChanged(this, row, row);
    }

    // Implement the TableModel interface.
    public int getRowCount() {
        return tableModel.getRowCount();
    }

    public int getColumnCount() {
        return tableModel.getColumnCount();
    }

    public String getColumnName(int columnIndex) {
        return tableModel.getColumnName(columnIndex);
    }

    public Class getColumnClass(int columnIndex) {
        return tableModel.getColumnClass(columnIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return tableModel.isCellEditable(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableModel.getValueAt(rowIndex, columnIndex);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tableModel.setValueAt(aValue, rowIndex, columnIndex);
    }

    public void addTableModelListener(TableModelListener l) {
        tableModel.addTableModelListener(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        tableModel.removeTableModelListener(l);
    }

    @Override
    public Object remove(int index) {
        data_fila h = (data_fila) super.remove(index);
        columnNames.remove(h.getNombre());
        return h;
    }
    
    
}
