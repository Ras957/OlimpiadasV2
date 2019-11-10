/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Area;
import Modelo.MultiSportCenter;
import Modelo.auxiliary.Closer;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *  Clase AreaDAO que hereda de Area y sirve para comunicarse con la base de datos
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class AreaDAO extends Area{
    
    public static final String INSERT = "INSERT INTO area(id_multisportcenter,location,sport) VALUES(?,?,?)";
    public static final String UPDATE = "UPDATE area SET id_multisportcenter=?, location=?, sport=? WHERE id=?";
    public static final String DELETE = "DELETE FROM area WHERE id=?";
    public static final String GETALL = "SELECT * FROM area";
    public static final String GETONE = "SELECT * FROM area WHERE id=?";
    public static final String GETALL_BY_MSC = "SELECT * FROM area WHERE id_multisportcenter=?";
    
    public static Conexion con;
    
    public AreaDAO(Area a) throws DAOException{
        super(a.getLocation(), a.getSport(), a.getMsc());
        this.id = a.getId();
        con = Conexion.getInstance();
    }

    public AreaDAO(String location, String sport, MultiSportCenter msc)
            throws DAOException{
        super(location, sport, msc);
        con = Conexion.getInstance();
    }

    public AreaDAO() throws DAOException {
        con = Conexion.getInstance();
    }
    
    
    
    public boolean insertArea() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.getMiConexion().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setInt(1, this.getMsc().getId());
            stat.setString(2, this.getLocation());
            stat.setString(3, this.getSport());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
                yes=true;
            } else {
                throw new DAOException("No puedo asignar ID a esta Area");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return yes;
    }
    
    public boolean modifyArea() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try {
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setInt(1, this.getMsc().getId());
            stat.setString(2, this.getLocation());
            stat.setString(3, this.getSport());
            stat.setInt(4, this.getId());
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
    
    public boolean deleteArea() throws DAOException {
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
    
    private Area convert(ResultSet rs) throws DAOException {
        Area area = null;
        try {
            Integer id = rs.getInt("id");
            Integer id_multisportcenter = rs.getInt("id_multisportcenter");
            String location = rs.getString("location");
            String sport = rs.getString("sport");
            MultiSportCenter msc = new MultiSportCenterDAO().getMSC(id_multisportcenter);
            area = new Area(location, sport, msc);
            area.setId(id);
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
        return area;
    }
    
    public List<Area> getAllArea() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Area> areas = new ArrayList<>();
        try {
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                areas.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return areas;
    }

    public Area getArea(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Area area = null;
        if (id != 0) {
            try {
                stat = con.getMiConexion().prepareStatement(GETONE);
                stat.setInt(1, id);
                rs = stat.executeQuery();
                if (rs.next()) {
                    area = convert(rs);
                } else {
                    throw new DAOException("No se ha encontrado ese registro");
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            } finally {
                Closer.close(stat, rs);
            }
        }
        return area;
    }
    
    public List<Area> getAreaByMSC(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Area> areas = new ArrayList<>();
        try {
            stat = con.getMiConexion().prepareStatement(GETALL_BY_MSC);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                areas.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return areas;
    }
}









