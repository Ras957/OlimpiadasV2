/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcomplex;

import Modelo.Headquarter;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class HeadquarterComboView  extends Headquarter{
    
    public HeadquarterComboView(Headquarter headquarter) {
        super(headquarter.getName(), headquarter.getBudget());
        this.id = headquarter.getId();
    }
    
    @Override
    public String toString() {
        return this.getName()+" (ID: "+this.getId()+")";
    }
    
    
    
}









