/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.HeadquarterDAO;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.DAO.SportComplexDAO;
import Modelo.Headquarter;
import Modelo.MultiSportCenter;
import Modelo.SportComplex;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import java.sql.SQLException;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            con = Conexion.getInstance();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        try {
            if (con.conectar()) {
                System.out.println("Conexion establecida con la base de datos");
            }
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        try {
            Headquarter hq = new Headquarter("Valencia", 5000000);
            hq.setId(2);
            SportComplex o = new SportComplex("plaza prueba", "Ruperto",hq);
            SportComplexDAO dao = new SportComplexDAO(o);
            if (dao.insertComplex()) {
                System.out.println("El complejo se ha generado con ID " + dao.getId());
            }
//            if (dao.deleteComplex()) {
//                System.out.println("El complejo se ha borrado con ID " + dao.getId());
//            }
            o = dao.getComplex(8);
            System.out.println(o);
            List<SportComplex> hqs = dao.getAllComplex();
            System.out.println(hqs+"\n");
            MultiSportCenter msc = new MultiSportCenter(o,"Mucho espacio");
            MultiSportCenterDAO mscDAO = new MultiSportCenterDAO(msc);
            if (mscDAO.insertMSC()) {
                System.out.println("El unideportivo se ha generado con ID " + mscDAO.getId());
            }
            if (mscDAO.deleteMSC()) {
                System.out.println("El unideportivo se ha borrado con ID " + mscDAO.getId());
            }
            msc = mscDAO.getMSC(4);
            System.out.println(msc);
            List<MultiSportCenter> mscs = mscDAO.getAllMSC();
            System.out.println(mscs+"\n");
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        try {
            con.cerrarConexion();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

}















