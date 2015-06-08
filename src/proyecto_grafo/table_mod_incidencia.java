/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_grafo;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.DefaultListModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrador
 */
public class table_mod_incidencia extends DefaultListModel implements TableModel {

    private ArrayList<String> columnNames;

    // constructor
    public table_mod_incidencia() {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Vertices/Aristas");

    }

    /**
     * remueve todos los elementos tanto
     * fila como columnas
     */
    @Override
    public void removeAllElements() {
        columnNames.clear();
        this.columnNames.add("Vertices/Aristas");
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
            } else {
                return rowData.getArry(column - 1);
            }
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
                columnNames.set(row, nombre);
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

    public void trimToSize_columnNames() {
        columnNames.trimToSize();
    }

    public <T> T[] toArray_columnNames(T[] a) {
        return columnNames.toArray(a);
    }

    public Object[] toArray_columnNames() {
        return columnNames.toArray();
    }

    public int size_columnNames() {
        return columnNames.size();
    }

    public String set_columnNames(int index, String element) {
        return columnNames.set(index, element);
    }

    public boolean remove_columnNames(Object o) {
        return columnNames.remove(o);
    }

    public String remove_columnNames(int index) {
        return columnNames.remove(index);
    }

    public int lastIndexOf_columnNames(Object o) {
        return columnNames.lastIndexOf(o);
    }

    public boolean isEmpty_columnNames() {
        return columnNames.isEmpty();
    }

    public int indexOf_columnNames(Object o) {
        return columnNames.indexOf(o);
    }

    public String get_columnNames(int index) {
        return columnNames.get(index);
    }

    public void ensureCapacity_columnNames(int minCapacity) {
        columnNames.ensureCapacity(minCapacity);
    }

    public boolean contains_columnNames(Object o) {
        return columnNames.contains(o);
    }

    public Object clone_columnNames() {
        return columnNames.clone();
    }

    public void clear_columnNames() {
        columnNames.clear();
    }

    public boolean addAll_columnNames(int index, Collection<? extends String> c) {
        return columnNames.addAll(index, c);
    }

    public boolean addAll_columnNames(Collection<? extends String> c) {
        return columnNames.addAll(c);
    }

    public void add_columnNames(int index, String element) {
        columnNames.add(index, element);
    }

    public boolean add_columnNames(String e) {
        return columnNames.add(e);
    }
}
