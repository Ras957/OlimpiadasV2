/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Commissioner;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;

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

    public CommissionerDAO() throws DAOException {
         con = Conexion.getInstance();
    }
    
    
}




