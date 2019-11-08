/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Equipment;
import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EquipementDAO extends Equipment{
    
    public static final String INSERT = "INSERT INTO equipment(name) VALUES(?)";
    public static final String UPDATE = "UPDATE equipment SET name=? WHERE id=?";
    public static final String DELETE = "DELETE FROM equipment WHERE id=?";
    public static final String GETALL = "SELECT * FROM equipment";
    public static final String GETONE = "SELECT * FROM equipment WHERE id=?";
    
    public static Conexion con;

    public EquipementDAO() throws DAOException {
         con = Conexion.getInstance();
    }
    
}




