/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Modelo.Equipment;
import java.util.Objects;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EquipmentComboView extends Equipment{

    public EquipmentComboView(Equipment equip) {
        super(equip.getName());
        this.id = equip.getId();
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
    
}
