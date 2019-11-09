/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.equipment;

import Modelo.Equipment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import Modelo.DAO.EquipmentDAO;
import Modelo.auxiliary.DAOException;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EquipmentTableModel extends AbstractTableModel{
    
    private EquipmentDAO equipements;
    
    private List<Equipment> datos = new ArrayList<>();

    public EquipmentTableModel(EquipmentDAO equipements) {
        this.equipements = equipements;
    }
    
    public void updateModel() throws DAOException {
        datos = equipements.getAllEquipment();
    }
    
    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column){
            case 0: n = "ID"; break;
            case 1: n = "Nombre"; break;
            //case 2: n = "Mantenimiento"; break;
            default: n = "[no]";
        }
        return n;
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        //return 3;
        return 2;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        Equipment e = datos.get(rowIndex);
        switch (columnIndex){
            case 0: o = e.getId(); break;
            case 1: o = e.getName(); break;
            //case 2: o = e.isMaintenance(); break;
            default: o = "";
        }
        return o;
    }
}
