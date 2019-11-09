package Modelo.DAO;

import Modelo.Area;
import Modelo.Commissioner;
import Modelo.Equipment;
import Modelo.Event;
import Modelo.SportComplex;
import Modelo.auxiliary.Closer;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import Modelo.auxiliary.DNIException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
    
    public EventDAO(Event e) throws DAOException{
        super(e.getName(), e.getComplex(), e.getDate(), e.getArea(), 
                e.getEquip(), e.getCommissioners());
        con = Conexion.getInstance();
    }

    public EventDAO(String name, Date date, SportComplex complex, Area area) 
            throws DAOException {
        super(name, date, complex, area);
        con = Conexion.getInstance();
    }

    public EventDAO(String name, SportComplex complex, Date date, Area area, 
            List<Equipment> equip, HashMap<Commissioner, String> commissioners)
            throws DAOException {
        super(name, complex, date, area, equip, commissioners);
        con = Conexion.getInstance();
    }
    

    public EventDAO() throws DAOException {
        con = Conexion.getInstance();
    }
    
    public boolean insertEvent() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.getMiConexion().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, this.getName());
            stat.setDate(2, (java.sql.Date) this.getDate());
            stat.setInt(3, this.getComplex().getId());
            if (this.getArea() != null) {
                stat.setInt(4, this.getArea().getId());
            } else {
                stat.setNull(4, Types.INTEGER);
            }
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
                yes= true;
            } else {
                throw new DAOException("No puedo asignar ID a este Evento");
            }
            if (this.getEquip() != null) {
                for (Equipment e : this.getEquip()) {
                    stat = con.getMiConexion().prepareStatement(INSERT_EE);
                    stat.setInt(1, e.getId());
                    stat.setInt(2, this.getId());
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
            if (this.getCommissioners() != null) {
                for (Entry<Commissioner,String> c : this.getCommissioners().entrySet()) {
                    stat = con.getMiConexion().prepareStatement(INSERT_CE);
                    stat.setInt(1, this.getId());
                    stat.setInt(2, c.getKey().getId());
                    stat.setString(3, c.getValue());
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return yes;
    }
    
    public boolean modifyEvent() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try {
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setString(1, this.getName());
            stat.setDate(2, (java.sql.Date) this.getDate());
            stat.setInt(3, this.getComplex().getId());
            stat.setInt(4, this.getArea().getId());
            stat.setInt(5, this.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }else{
                yes=true;
            }
            if (this.getEquip() != null) {
                for (Equipment e : this.getEquip()) {
                    stat = con.getMiConexion().prepareStatement(UPDATE_EE);
                    stat.setInt(1, e.getId());
                    stat.setInt(2, this.getId());
                    //id de la tupla equipement_event
                    stat.setInt(3, getIdEquEve(e.getId(), this.getId()));
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
            if (this.getCommissioners() != null) {
                for (Entry<Commissioner,String> c : this.getCommissioners().entrySet()) {
                    stat = con.getMiConexion().prepareStatement(UPDATE_CE);
                    stat.setInt(1, this.getId());
                    stat.setInt(2, c.getKey().getId());
                    stat.setString(3, c.getValue());
                    //id de la tupla comissioner_event
                    stat.setInt(4, getIdComEve(this.getId(), c.getKey().getId(), c.getValue()));
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat);
        }
        return yes;
    }
    
    public boolean deleteEvent() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try {
            stat = con.getMiConexion().prepareStatement(DELETE);
            stat.setInt(1, this.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }else{
                yes=true;
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat);
        }
        return yes;
    }
    
    private Event convert(ResultSet rs) throws DAOException, DNIException {
        Event event = null;
        try {
            String name = rs.getString("name");
            java.sql.Date date = (java.sql.Date) rs.getDate("date");
            SportComplex sportComplex =
                    new SportComplexDAO().getComplex(rs.getInt("id_sportcomplex"));
            //area es distinto porque puede ser null, eso se controla en AreaDAO.getArea()
            Area area = new AreaDAO().getArea(rs.getInt("id_area"));
            event = new Event(name, date, sportComplex, area);
            event.setId(rs.getInt("id"));
            PreparedStatement stat = con.getMiConexion().prepareStatement(GETALL_EE);
            stat.setInt(1, event.getId());
            rs = stat.executeQuery();
            while (rs.next()) {
                event.getEquip().add(new EquipmentDAO().getEquipment(rs.getInt("id_equipment")));
            }
            stat = con.getMiConexion().prepareStatement(GETALL_CE);
            stat.setInt(1, event.getId());
            rs = stat.executeQuery();
            while (rs.next()){
                event.getCommissioners().put(
                        new CommissionerDAO().getCommissioner(rs.getInt("id_comissioner")),
                        rs.getString("rol"));
            }
            Closer.close(stat);
        } catch (SQLException ex) {
           throw new DAOException("Error en SQL", ex);
        }
        return event;
    }
    
    public List<Event> getAllEvents() throws DAOException, DNIException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Event> events = new ArrayList<>();
        try {
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                events.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return events;
    }

    public Event getEvent(Integer id) throws DAOException, DNIException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Event event = null;
        try {
            stat = con.getMiConexion().prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                event = convert(rs);
            } else {
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return event;
    }
    
    public int getIdComEve(int id_event, int id_comissioner, String rol) throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        int id = 0;
        try{
            stat = con.getMiConexion().prepareStatement(GETONE_CE);
            stat.setInt(1, id_event);
            stat.setInt(2, id_comissioner);
            stat.setString(3, rol);
            rs = stat.executeQuery();
            id = rs.getInt("id");
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally {
            Closer.close(stat, rs);
        }
        return id;
    }
    
    public int getIdEquEve(int id_equip, int id_event) throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        int id = 0;
        try{
            stat = con.getMiConexion().prepareStatement(GETONE_EE);
            stat.setInt(1, id_equip);
            stat.setInt(2, id_event);
            rs = stat.executeQuery();
            id = rs.getInt("id");
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally {
            Closer.close(stat, rs);
        }
        return id;
    }
    
}







