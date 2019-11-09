/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Equipment;
import Modelo.auxiliary.Closer;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EquipmentDAO extends Equipment{
    
    public static final String INSERT = "INSERT INTO equipment(name) VALUES(?)";
    public static final String UPDATE = "UPDATE equipment SET name=? WHERE id=?";
    public static final String DELETE = "DELETE FROM equipment WHERE id=?";
    public static final String GETALL = "SELECT * FROM equipment";
    public static final String GETONE = "SELECT * FROM equipment WHERE id=?";
    
    public static Conexion con;

    public EquipmentDAO(Equipment equip) throws DAOException {
        super(equip.getName());
        con = Conexion.getInstance();
    }

    public EquipmentDAO(String name) throws DAOException {
        super(name);
        con = Conexion.getInstance();
    }
    
    public EquipmentDAO() throws DAOException {
         con = Conexion.getInstance();
    }
    
    public boolean insertEquipment() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = con.getMiConexion().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, this.getName());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
                yes=true;
            }else{
                throw new DAOException("No puedo asignar ID a este Equipamiento");
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return yes;
    }
    
    public boolean modifyEquipment() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setString(1, this.getName());
            stat.setInt(2, this.getId());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }else{
                yes=true;
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat);
        }
        return yes;
    }
    
    public boolean deleteEquipment() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(DELETE);
            stat.setInt(1, this.getId());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }else{
                yes=true;
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat);
        }
        return yes;
    }
    
    
    private Equipment convert(ResultSet rs) throws DAOException{
        Equipment equip = null;
        try {
            String name = rs.getString("name");
            equip = new Equipment(name);
            equip.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
        return equip;
    }

    public List<Equipment> getAllEquipment() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Equipment> equipements = new ArrayList<>();
        try{
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                equipements.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return equipements;
    }

    public Equipment getEquipment(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Equipment equip = null;
        try{
            stat = con.getMiConexion().prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                equip = convert(rs);
            }else{
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return equip;
    }
}









