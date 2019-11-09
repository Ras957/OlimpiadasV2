/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Area;
import Modelo.Commissioner;
import Modelo.DAO.AreaDAO;
import Modelo.DAO.CommissionerDAO;
import Modelo.DAO.EquipmentDAO;
import Modelo.DAO.EventDAO;
import Modelo.DAO.HeadquarterDAO;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.DAO.SportCenterDAO;
import Modelo.DAO.SportComplexDAO;
import Modelo.Equipment;
import Modelo.Event;
import Modelo.Headquarter;
import Modelo.MultiSportCenter;
import Modelo.SportCenter;
import Modelo.SportComplex;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import Modelo.auxiliary.DNIException;
import Vista.MainMenu;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class OlympicsV2 {

    public static Conexion con;
    public static MainMenu menu;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DNIException {
        try {
            con = Conexion.getInstance();

            if (con.conectar()) {
                System.out.println("Conexion establecida con la base de datos");
            }
            menu = new MainMenu();
            menu.setVisible(true);
            //con.cerrarConexion();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

}

