/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.sql.SQLException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class CargarXML {
    
    public static Configuracion unmarshal(File f) throws JAXBException, SQLException{
        Configuracion conf = new Configuracion();
        JAXBContext context = JAXBContext.newInstance(Configuracion.class);
        Unmarshaller um = context.createUnmarshaller();
        conf = (Configuracion) um.unmarshal(f);
        return conf;
    }
    
}











