/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcenter;

import Modelo.SportComplex;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportComplexComboView extends SportComplex {

    public SportComplexComboView(SportComplex sportComplex) {
        super(sportComplex.getLocation(), sportComplex.getBoss(), 
                sportComplex.getHeadquarter());
        this.id = sportComplex.getId();
    }

    @Override
    public String toString() {
        return this.getLocation()+" (ID:"+this.getId()+")";
    }
    
    
    
}







