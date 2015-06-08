/*
 * table_incidencia.java
 *
 * Created on 8 de noviembre de 2008, 21:11
 */
package proyecto_grafo;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author  Administrador
 */
public class table_incidencia extends javax.swing.JPanel {

    private table_mod_incidencia dataModel;

    /**
     * elimina la columna "i" de la tabla
     * @param i
     */
    public void elimiar(int i) {
        TableColumnModel modo_coulumna = Matriz_V.getColumnModel();
        TableColumn la_columna = modo_coulumna.getColumn(i);
        Matriz_V.removeColumn(la_columna);

    }

    /**
     * remueve laa columnas de la tabla
     */
    public void eliminar_All() {
        int i = Matriz_V.getColumnCount();
        i--;
        while (i > 0) {
            elimiar(i);
            i--;
        }
        dataModel.removeAllElements();
    }

    public table_mod_incidencia getDataModel() {
        return dataModel;
    }

    
    public void setDataModel() {
        this.dataModel = new table_mod_incidencia();
    }

    // añade una fila a la tabla
    public void add(data_fila v) {
        this.dataModel.addElement(v);
        this.Matriz_V.addNotify();

    }

    /**
     * remueve todos los elementos tanto fila como columna
     */
    public void removeAllElements() {

        int h = Matriz_V.getColumnCount();
        if (h == 1) {
            return;
        }
        System.out.println(h);
        for (int i = 1; i < h; ++i) {
            TableColumnModel modo_coulumna = Matriz_V.getColumnModel();
            TableColumn la_columna = modo_coulumna.getColumn(i);
            Matriz_V.removeColumn(la_columna);

        }
        dataModel.removeAllElements();
    }

    // me da el numero de fila
    public int size_columnNames() {
        return dataModel.size_columnNames();
    }

    // remueve todos los elementos de la tabla "todo"
    public void removeAllElements_coluna() {
        dataModel.removeAllElements();
    }

    // verifica la posicion de la columna en la tabla
    public int indexOf_columnNames(Object o) {
        return dataModel.indexOf_columnNames(o);
    }

    // me da el nombre de la columna
    public String get_columnNames(int index) {
        return dataModel.get_columnNames(index);
    }

    // me da el numero de columnas
    public int getColumnCount() {
        return dataModel.getColumnCount();
    }

    public boolean contains_columnNames(Object o) {
        return dataModel.contains_columnNames(o);
    }

    /**
     * añade una columna a la tabla
     * @param e
     */
    public void add_columnNames(String e) {
        dataModel.add_columnNames(e);
        TableColumn columna = new TableColumn(dataModel.size_columnNames() - 1);
        columna.setResizable(true);
        this.Matriz_V.addColumn(columna);

    }

    public table_incidencia() {
        this.dataModel = new table_mod_incidencia();
        initComponents();
        this.Matriz_V.setModel(dataModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Matriz_V = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Matriz De incidencia"));

        Matriz_V.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Matriz_V.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(Matriz_V);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Matriz_V;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
