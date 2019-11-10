/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Modelo.Area;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class AreaComboView extends Area{

    public AreaComboView(Area area) {
        super(area.getLocation(), area.getSport(), area.getMsc());
        this.id = area.getId();
    }

    @Override
    public String toString() {
        return this.getLocation()+" (ID:"+this.getId()+")";
    }
    
    
}
