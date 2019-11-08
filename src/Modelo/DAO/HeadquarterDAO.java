/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Headquarter;
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
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class HeadquarterDAO extends Headquarter {

    public static final String INSERT = "INSERT INTO headquarter(name,budget) VALUES(?,?)";
    public static final String UPDATE = "UPDATE headquarter SET name=?, budget=? WHERE id=?";
    public static final String DELETE = "DELETE FROM headquarter WHERE id=?";
    public static final String GETALL = "SELECT * FROM headquarter";
    public static final String GETONE = "SELECT * FROM headquarter WHERE id=?";
    public static final String GET_N_COMPLEX = "SELECT count(id) AS numComplex FROM sportcomplex WHERE id_headquarter=?";

    public static Conexion con;

    public HeadquarterDAO(Headquarter dao) throws DAOException {
        super(dao.getName(), dao.getBudget());
        con = Conexion.getInstance();
    }

    public HeadquarterDAO(String name, float budget) throws DAOException {
        super(name, budget);
        con = Conexion.getInstance();
    }

    public HeadquarterDAO() throws DAOException {
        con = Conexion.getInstance();
    }

    public boolean insertHeadquarter() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.getMiConexion().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, this.getName());
            stat.setFloat(2, this.getBudget());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
                yes=true;
            } else {
                throw new DAOException("No puedo asignar ID a esta Sede");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return yes;
    }

    public boolean modifyHeadquarter() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try {
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setString(1, this.getName());
            stat.setFloat(2, this.getBudget());
            stat.setInt(3, this.getId());
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

    public boolean deleteHeadquarter() throws DAOException {
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

    private Headquarter convert(ResultSet rs) throws DAOException {
        Headquarter headquarter = null;
        try {
            String name = rs.getString("name");
            float budget = rs.getFloat("budget");
            int id = rs.getInt("id");
            headquarter = new Headquarter(name, budget);
            headquarter.setId(id);
            headquarter.setNumComplexes(countComplex(id));
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
        return headquarter;
    }

    public List<Headquarter> getAllHeadquarter() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Headquarter> headquarters = new ArrayList<>();
        try {
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                headquarters.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            Closer.close(stat, rs);
        }
        return headquarters;
    }

    public Headquarter getHeadquarter(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Headquarter headquarter = null;
        try {
            stat = con.getMiConexion().prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                headquarter = convert(rs);
            } else {
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
             Closer.close(stat, rs);
        }
        return headquarter;
    }

    public int countComplex(Integer id) throws DAOException {
        int n = 0;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.getMiConexion().prepareStatement(GET_N_COMPLEX);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                n = rs.getInt("numComplex");
            } else {
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
             Closer.close(stat, rs);
        }
        return n;
    }
}



















