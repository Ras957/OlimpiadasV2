/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Commissioner;
import Modelo.auxiliary.Closer;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import Modelo.auxiliary.DNIException;
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
public class CommissionerDAO extends Commissioner{
    
    public static final String INSERT = "INSERT INTO commissioner(dni,name) VALUES(?,?)";
    public static final String UPDATE = "UPDATE commissioner SET dni=?, name=? WHERE id=?";
    public static final String DELETE = "DELETE FROM commissioner WHERE id=?";
    public static final String GETALL = "SELECT * FROM commissioner";
    public static final String GETONE = "SELECT * FROM commissioner WHERE id=?";
    
    public static Conexion con;

    public CommissionerDAO(Commissioner com) throws DAOException {
        super(com.getDni(), com.getName());
        con = Conexion.getInstance();
    }
    
    public CommissionerDAO(String dni, String name) throws DAOException {
        super(dni, name);
        con = Conexion.getInstance();
    }
    
    public CommissionerDAO() throws DAOException {
        con = Conexion.getInstance();
    }

    public boolean insertCommissioner() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = con.getMiConexion().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, this.getDni());
            stat.setString(2, this.getName());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
                yes=true;
            }else{
                throw new DAOException("No puedo asignar ID a este Comisario");
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return yes;
    }
    
    public boolean modifyCommissioner() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setString(1, this.getDni());
            stat.setString(2, this.getName());
            stat.setInt(3, this.getId());
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
    
    public boolean deleteCommissioner() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(DELETE);
            stat.setInt(1, this.getId());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }else{
                yes = true;
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat);
        }
        return yes;
    }
    
    private Commissioner convert(ResultSet rs) 
            throws  DNIException, DAOException{
        Commissioner commissioner = null;
        try {
            String dni = rs.getString("dni");
            String name = rs.getString("name");
            commissioner = new Commissioner(dni, name);
            commissioner.setId(rs.getInt("id"));
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
        return commissioner;
    }

    public List<Commissioner> getAllCommissioner() throws DAOException, DNIException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Commissioner> comissioners = new ArrayList<>();
        try{
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                try {
                    comissioners.add(convert(rs));
                } catch (DNIException ex) {
                    String dni = rs.getString("dni");
                    throw new DNIException("El DNI "+dni+
                            " no es válido");
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return comissioners;
    }

    public Commissioner getCommissioner(Integer id) throws DAOException, DNIException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Commissioner commissioner = null;
        try{
            stat = con.getMiConexion().prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                try {
                    commissioner = convert(rs);
                } catch (DNIException ex) {
                    String dni = rs.getString("dni");
                    throw new DNIException("El DNI "+dni+
                            " no es válido");
                }
            }else{
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return commissioner;
    }
    
}








