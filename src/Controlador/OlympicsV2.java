/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.auxiliary.Conexion;
import Modelo.auxiliary.DAOException;
import Modelo.auxiliary.DNIException;
import Vista.MainMenu;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class OlympicsV2 {

    public static Conexion con;
    public static MainMenu menu;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DNIException {
        try {
            con = Conexion.getInstance();

            if (con.conectar()) {
                System.out.println("Conexion establecida con la base de datos");
            }
            menu = new MainMenu();
            menu.setVisible(true);
            //con.cerrarConexion();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

}





