/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;


import Modelo.Area;
import Modelo.DAO.AreaDAO;
import Modelo.auxiliary.DAOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class AreaComboModel extends DefaultComboBoxModel<AreaComboView>{

    private AreaDAO areas;
    
    private List<Area> list;

    public AreaComboModel(AreaDAO areas) {
        this.areas = areas;
        this.list = new ArrayList<>();
    }
    public void update() throws DAOException{
        if (areas != null) {
            list = areas.getAllArea();
            removeAllElements();
            for (Area a : list) {
                addElement( new AreaComboView(a));
            }
        }
        
    }
    
    public void update(int n) throws DAOException{
        if (areas != null) {
            list = areas.getAreaByMSC(n);
            removeAllElements();
            for (Area a : list) {
                addElement( new AreaComboView(a));
            }
        } 
    }
    
    
    
}
