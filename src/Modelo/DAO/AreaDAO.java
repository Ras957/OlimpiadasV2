/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Area;
import Modelo.MultiSportCenter;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class AreaDAO extends Area{
    
    public static final String INSERT = "INSERT INTO area(id_multisportcenter,location,sport) VALUES(?,?,?)";
    public static final String UPDATE = "UPDATE area SET id_multisportcenter=?, location=?, sport=? WHERE id=?";
    public static final String DELETE = "DELETE FROM area WHERE id=?";
    public static final String GETALL = "SELECT * FROM area";
    public static final String GETONE = "SELECT * FROM area WHERE id=?";
    
    public static Conexion con;

    public AreaDAO(String location, String sport, MultiSportCenter msc){
        super(location, sport, msc);
    }
    
    public void insert() throws DAOException {
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
            } else {
                throw new DAOException("No puedo asignar ID a esta Area");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    
    
    
    
    
    
}




