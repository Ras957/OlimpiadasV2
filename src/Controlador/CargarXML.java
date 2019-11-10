package Controlador;

import java.io.File;
import java.sql.SQLException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Clase para leer nuestro archivo XML con la configuración de la Conexión
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class CargarXML {
    
    /**
     * Crea un objeto Configuracion a partir de un XML
     * @param f
     * @return objeto Configuración
     * @throws JAXBException
     * @throws SQLException
     */
    public static Configuracion unmarshal(File f) throws JAXBException, SQLException{
        Configuracion conf = new Configuracion();
        JAXBContext context = JAXBContext.newInstance(Configuracion.class);
        Unmarshaller um = context.createUnmarshaller();
        conf = (Configuracion) um.unmarshal(f);
        return conf;
    }
    
}












