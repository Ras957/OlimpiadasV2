/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.area;

import Modelo.MultiSportCenter;
import Modelo.SportComplex;
import java.util.Objects;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
class MultiSportCenterComboView extends MultiSportCenter{
    
    public MultiSportCenterComboView(MultiSportCenter msc){
        super(new SportComplex(msc.getLocation(), msc.getBoss(), 
                msc.getHeadquarter()), msc.getInformation());
        this.id = msc.getId();
    }

    @Override
    public String toString() {
        return this.getLocation()+" (ID:"+this.getId()+")";
    }
    
    
    
}





