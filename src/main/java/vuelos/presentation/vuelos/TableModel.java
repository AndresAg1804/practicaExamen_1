package vuelos.presentation.vuelos;

import vuelos.logic.Vuelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    List<Vuelo> rows;
    int[] cols;

    public TableModel(int[] cols, List<Vuelo> rows){
        this.cols=cols;
        this.rows=rows;
        initColNames();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int col){
        return colNames[cols[col]];
    }

    public Class<?> getColumnClass(int col){
        switch (cols[col]){
            default: return super.getColumnClass(col);
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int col) {
        Vuelo sucursal = rows.get(row);
        switch (cols[col]){
            case NUMERO: return sucursal.getNumero();
            case ORIGEN: return sucursal.getOrigen().getNombre();
            case DESTINO: return sucursal.getDestino().getNombre();
            case SALIDA: return sucursal.getSalida();
            case LLEGADA: return sucursal.getLlegada();
            default: return "";
        }
    }

    public Vuelo getRowAt(int row) {
        return rows.get(row);
    }

    public static final int NUMERO=0;
    public static final int ORIGEN=1;
    public static final int DESTINO=2;
    public static final int SALIDA=3;
    public static final int LLEGADA=4;

    String[] colNames = new String[6];
    private void initColNames(){
        colNames[NUMERO]= "Numero";
        colNames[ORIGEN]= "Origen";
        colNames[DESTINO]= "Destino";
        colNames[SALIDA]= "Salida";
        colNames[LLEGADA]= "Llegada";
    }

}
