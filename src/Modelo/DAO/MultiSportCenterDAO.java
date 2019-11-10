/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.MultiSportCenter;
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
public class MultiSportCenterDAO extends MultiSportCenter{
    
    public static final String INSERT = "INSERT INTO multisportcenter(id_sportcomplex, information) VALUES(?,?)";
    public static final String UPDATE = "UPDATE multisportcenter SET id_sportcomplex=? information=? WHERE id_sportcomplex=?";
    public static final String DELETE = "DELETE FROM multisportcenter WHERE id_sportcomplex=?";
    public static final String GETALL = "SELECT * FROM multisportcenter";
    public static final String GETONE = "SELECT * FROM multisportcenter WHERE id_sportcomplex=?";
    
    public static Conexion con;
    
    public MultiSportCenterDAO(MultiSportCenter msc) throws DAOException {
        super( new SportComplex(msc.getLocation(), msc.getBoss()
                ,msc.getHeadquarter()), msc.getInformation());
        this.id= msc.getId();
        con = Conexion.getInstance();
    }

    public MultiSportCenterDAO(SportComplex sc, String information) 
            throws DAOException {
        super(sc, information);
        this.id = sc.getId();
        con = Conexion.getInstance();
    }

    public MultiSportCenterDAO() throws DAOException {
        con = Conexion.getInstance();
    }
    
    public boolean insertMSC() throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(INSERT);
            stat.setInt(1, this.getId());
            stat.setString(2, this.getInformation());
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
    
    public boolean modifyMSC(Integer id) throws DAOException {
        boolean yes = false;
        PreparedStatement stat = null;
        try{
            stat = con.getMiConexion().prepareStatement(UPDATE);
            stat.setInt(1, this.getId());
            stat.setString(2, this.getInformation());
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
    
    public boolean deleteMSC() throws DAOException {
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
    
    private MultiSportCenter convert(ResultSet rs) throws DAOException{
        MultiSportCenter msc = null;
        try {
            SportComplex sportComplex = 
                    new SportComplexDAO().getComplex(rs.getInt("id_sportcomplex"));
            String information = rs.getString("information");
            msc = new MultiSportCenter(sportComplex, information);
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
        return msc;
    }

    public List<MultiSportCenter> getAllMSC() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<MultiSportCenter> multiSportCenters = new ArrayList<>();
        try{
            stat = con.getMiConexion().prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                multiSportCenters.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return multiSportCenters;
    }

    public MultiSportCenter getMSC(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        MultiSportCenter multiSportCenter = null;
        try{
            stat = con.getMiConexion().prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                multiSportCenter = convert(rs);
            }else{
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            Closer.close(stat, rs);
        }
        return multiSportCenter;
    }
}






















