/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Modelo.Commissioner;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class CommissionerComboView extends Commissioner{

    public CommissionerComboView(Commissioner commissioner) {
        super(commissioner.getDni(),commissioner.getName());
        this.id = commissioner.getId();
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
    
}
