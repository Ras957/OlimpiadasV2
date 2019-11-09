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
import Modelo.DAO.HeadquarterDAO;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.DAO.SportCenterDAO;
import Modelo.DAO.SportComplexDAO;
import Modelo.Equipment;
import Modelo.Headquarter;
import Modelo.MultiSportCenter;
import Modelo.SportCenter;
import Modelo.SportComplex;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import Modelo.auxiliary.DNIException;
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
    public static void main(String[] args) throws DNIException {
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
//            Headquarter hq = new Headquarter("Valencia", 5000000);
//            hq.setId(2);
//            SportComplex o = new SportComplex("plaza prueba", "Ruperto",hq);
//            SportComplexDAO dao = new SportComplexDAO(o);
              SportComplexDAO dao = new SportComplexDAO();
//            if (dao.insertComplex()) {
//                System.out.println("El complejo se ha generado con ID " + dao.getId());
//            }
//            if (dao.deleteComplex()) {
//                System.out.println("El complejo se ha borrado con ID " + dao.getId());
//            }
//            o.setId(dao.getId());
            SportComplex o2 = dao.getComplex(24);
//            System.out.println(o);
//            System.out.println(o2);
//            List<SportComplex> hqs = dao.getAllComplex();
//            System.out.println(hqs+"\n");
//            SportCenter sc = new SportCenter(o,"tenis","Mucho espacio");
//            SportCenterDAO scDAO = new SportCenterDAO(sc);
//            if (scDAO.insertSportCenter()) {
//                System.out.println("El unideportivo se ha generado con ID " + scDAO.getId());
//            }
//            if (scDAO.deleteSportCenter()) {
//                System.out.println("El unideportivo se ha borrado con ID " + scDAO.getId());
//            }
            MultiSportCenter msc = new MultiSportCenter(o2,"Mucho espacio");
            MultiSportCenterDAO mscDAO = new MultiSportCenterDAO(msc);
            if (mscDAO.insertMSC()) {
                System.out.println("El polideportivo se ha generado con ID " + mscDAO.getId());
            }
            msc.setId(mscDAO.getId());
//            if (mscDAO.deleteMSC()) {
//                System.out.println("El polideportivo se ha borrado con ID " + mscDAO.getId());
//            }
//            msc.setId(mscDAO.getId());
//            System.out.println(msc);
//            List<MultiSportCenter> mscs = mscDAO.getAllMSC();
//            System.out.println(mscs+"\n");
//            Equipment equip = new Equipment("espada");
//            EquipmentDAO daoE = new EquipmentDAO(equip);
//            if (daoE.insertEquipment()) {
//                System.out.println("El material se ha generado con ID"+daoE.getId());
//            }
//            if (daoE.deleteEquipment()) {
//                System.out.println("El material se ha borrado con ID"+daoE.getId());
//            }
//            Equipment equip2 = daoE.getEquipment(2);
//            System.out.println(equip2);
//            List<Equipment> equips = daoE.getAllEquipment();
//            System.out.println(equips);
//            Commissioner com = new Commissioner("aaaaa","aaaaa");
//            CommissionerDAO daoC = new CommissionerDAO(com);
//            if (daoC.insertCommissioner()) {
//                System.out.println("El material se ha generado con ID"+daoC.getId());
//            }
//            if (daoC.deleteCommissioner()) {
//                System.out.println("El material se ha borrado con ID"+daoC.getId());
//            }
//            Commissioner com2 = daoC.getCommissioner(2);
//            System.out.println(com2);
//            List<Commissioner> coms = daoC.getAllCommissioner();
//            System.out.println(coms);
            Area area = new Area ("sitio","aaaa",msc);
            AreaDAO daoA = new AreaDAO(area);
            if (daoA.insertArea()) {
                System.out.println("Area insertada con ID"+daoA.getId());
            }
            if (daoA.deleteArea()) {
                System.out.println("Area borrada con ID"+daoA.getId());
            }
            Area area2 = daoA.getArea(1);
            System.out.println(area2);
            List<Area> areas = daoA.getAllArea();
            System.out.println(areas);
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


























