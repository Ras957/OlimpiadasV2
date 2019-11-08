/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.SportCenter;
import Modelo.SportComplex;
import Modelo.auxiliary.Closer;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportCenterDAO extends SportCenter{
    
    public static final String INSERT = "INSERT INTO sportcenter(id_sportcomplex,sport,information) VALUES(?,?,?)";
    public static final String UPDATE = "UPDATE sportcenter SET id_sportcomplex=? sport=?, information=? WHERE id_sportcomplex=?";
    public static final String DELETE = "DELETE FROM sportcenter WHERE id_sportcomplex=?";
    public static final String GETALL = "SELECT * FROM sportcenter";
    public static final String GETONE = "SELECT * FROM sportcenter WHERE id_sportcomplex=?";
    
    public static Conexion con;
    
    public SportCenterDAO(SportCenter sc) throws DAOException {
        super(new SportComplex(sc.getLocation(), sc.getBoss(),
                sc.getHeadquarter()),sc.getSport(),sc.getInformation());
        con = Conexion.getInstance();
    }

    public SportCenterDAO(SportComplex sc, String sport, 
            String information) throws DAOException {
        super(sc, sport, information);
        con = Conexion.getInstance();
    }

    public SportCenterDAO() throws DAOException {
        con = Conexion.getInstance();
    }
    
    public boolean insertSportCenter() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(INSERT);
            stat.setInt(1, this.getId());
            stat.setString(2, this.getSport());
            stat.setString(3, this.getInformation());
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
    
    public boolean modifySportCenter(Integer id) throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setInt(1, this.getId());
            stat.setString(2, this.getSport());
            stat.setString(4, this.getInformation());
            stat.setInt(3, id);
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
    
    public boolean deleteSportCenter() throws DAOException {
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
    
    private SportCenter convert(ResultSet rs) throws DAOException{
        SportCenter sportCenter = null;
        try {
            SportComplex sportComplex = 
                    new SportComplexDAO().getComplex(rs.getInt("id_sportcomplex"));
            String sport = rs.getString("sport");
            String location = rs.getString("information");
            sportCenter = new SportCenter(sportComplex, sport, location);
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
        return sportCenter;
    }

    public List<SportCenter> getAllSportCenter() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<SportCenter> sportCenters = new ArrayList<>();
        try{
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                sportCenters.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return sportCenters;
    }

    public SportCenter getSportCenter(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        SportCenter sportCenter = null;
        try{
            stat = con.getMiConexion().prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                sportCenter = convert(rs);
            }else{
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return sportCenter;
    }
}















