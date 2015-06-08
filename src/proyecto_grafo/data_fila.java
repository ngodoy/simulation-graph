package proyecto_grafo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
public class data_fila {

    public data_fila() {
        this.arry = new int[50];
    }

    public data_fila(String nombre) {
        this.arry = new int[50];
        this.nombre = nombre;
    }
    private int[] arry;

    /**
     * Get the value of arry
     *
     * @return the value of arry
     */
    public int[] getArry() {
        return arry;
    }

    /**
     * Set the value of arry
     *
     * @param arry new value of arry
     */
    public void setArry(int[] arry) {
        this.arry = arry;
    }

    /**
     * Get the value of arry at specified index
     *
     * @param index
     * @return the value of arry at specified index
     */
    public Integer getArry(int index) {
        return this.arry[index];
    }

    /**
     * Set the value of arry at specified index.
     *
     * @param index
     * @param newArry new value of arry at specified index
     */
    public void setArry(int index, int newArry) {
        this.arry[index] = newArry;
    }
    private String nombre;

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
}
