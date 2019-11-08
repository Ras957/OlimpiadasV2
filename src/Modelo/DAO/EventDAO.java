/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import static Modelo.DAO.AreaDAO.con;
import Modelo.Event;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EventDAO extends Event{
    
    public static final String INSERT = "INSERT INTO event(name,date,id_sportcomplex,id_area) VALUES(?,?,?,?)";
    public static final String INSERT_CE = "INSERT INTO comissioner_event(id_event,id_comissioner,rol) VALUES(?,?,?)";
    public static final String INSERT_EE = "INSERT INTO equipment_event(id_equipment,id_event) VALUES(?,?)";
    public static final String UPDATE = "UPDATE event SET name=?, date=?, id_sportcomplex=?, id_area=? WHERE id=?";
    public static final String UPDATE_CE = "UPDATE comissioner_event SET id_event=?, id_comissioner=?, rol=? WHERE id=?";
    public static final String UPDATE_EE = "UPDATE equipment_event SET id_equipment=?, id_event=? WHERE id=?";
    public static final String DELETE = "DELETE FROM event WHERE id=?";
    public static final String DELETE_CE = "DELETE FROM comissioner_event WHERE id=?";
    public static final String DELETE_EE = "DELETE FROM equipment_event WHERE id=?";
    public static final String GETALL = "SELECT * FROM event";
    public static final String GETALL_CE = "SELECT * FROM comissioner_event WHERE id_event=?";
    public static final String GETALL_EE = "SELECT * FROM equipment_event WHERE id_event=?";
    public static final String GETONE = "SELECT * FROM event WHERE id=?";
    public static final String GETONE_CE = "SELECT id FROM comissioner_event WHERE id_event=7 AND id_comissioner=1 AND rol like ?";
    //final String GETONE_CE = "SELECT * FROM comissioner_event WHERE id=?";
    public static final String GETONE_EE = "SELECT id FROM equipment_event WHERE id_equipment=? AND id_event=?";
    //final String GETONE_EE = "SELECT * FROM equipment_event WHERE id=?";
    
    public static Conexion con;

    public EventDAO() throws DAOException {
         con = Conexion.getInstance();
    }
    
}




