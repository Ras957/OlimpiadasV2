/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Headquarter;
import Modelo.SportComplex;
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
 *  Clase SportComplexDAO que hereda de SportComplex y sirve para 
 * comunicarse con la base de datos
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportComplexDAO extends SportComplex {

    public static final String INSERT = "INSERT INTO SportComplex(location,boss,id_headquarter) VALUES(?,?,?)";
    public static final String UPDATE = "UPDATE SportComplex SET location=?, boss=?, id_headquarter=? WHERE id=?";
    public static final String DELETE = "DELETE FROM SportComplex WHERE id=?";
    public static final String GETALL = "SELECT * FROM SportComplex";
    public static final String GETONE = "SELECT * FROM SportComplex WHERE id=?";

    public static Conexion con;

    public SportComplexDAO(SportComplex sc) throws DAOException {
        super(sc.getLocation(), sc.getBoss(), sc.getHeadquarter());
        this.id = sc.getId();
        con = Conexion.getInstance();
    }

    public SportComplexDAO(String location, String boss, Headquarter headquarter) throws DAOException {
        super(location, boss, headquarter);
        con = Conexion.getInstance();
    }

    public SportComplexDAO() throws DAOException {
        con = Conexion.getInstance();
    }
    
    public boolean insertComplex() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.getMiConexion().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, this.getLocation());
            stat.setString(2, this.getBoss());
            stat.setInt(3, this.getHeadquarter().getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
                yes = true;
            } else {
                throw new DAOException("No puedo asignar ID a este Complejo");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return yes;
    }

    public boolean modifyComplex() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try {
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setString(1, this.getLocation());
            stat.setString(2, this.getBoss());
            stat.setInt(3, this.getHeadquarter().getId());
            stat.setInt(4, this.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }else{
                yes = true;
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat);
        }
        return yes;
    }

    public boolean deleteComplex() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try {
            stat = con.getMiConexion().prepareStatement(DELETE);
            stat.setInt(1, this.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }else{
                yes = true;
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat);
        }
        return yes;
    }

    private SportComplex convert(ResultSet rs) throws DAOException{
        SportComplex sportComplex = null;
        try {
            String location = rs.getString("location");
            String boss = rs.getString("boss");
            Integer idHq = rs.getInt("id_headquarter");
            Headquarter hq = new HeadquarterDAO().getHeadquarter(idHq);
            sportComplex = new SportComplex(location, boss, hq);
            sportComplex.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
        return sportComplex;
    }

    public List<SportComplex> getAllComplex() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<SportComplex> sportComplexes = new ArrayList<>();
        try {
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                sportComplexes.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return sportComplexes;
    }

    public SportComplex getComplex(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        SportComplex sportComplex = null;
        try {
            stat = con.getMiConexion().prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                sportComplex = convert(rs);
            } else {
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return sportComplex;
    }

    static class getComplex extends SportComplex {

        public getComplex(int aInt) {
        }
    }

}















