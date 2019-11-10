package Modelo.auxiliary;

import Controlador.CargarXML;
import Controlador.Configuracion;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;

/**
 * Clase Conexion que sirve para conectarnos a la Base de Datos.
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class Conexion extends Controlador.Configuracion{
    
    private final static File f = new File("bbdd.xml") ;
    
    private static Conexion instance;
    private Connection miConexion;
    private boolean estado = false;//Estado de la conexión
    
    private Conexion() throws  SQLException, JAXBException{
        Configuracion conf = CargarXML.unmarshal(f);
        this.host=conf.getHost();
        this.name=conf.getName();
        this.login=conf.getLogin();
        this.password=conf.getPassword();
    }
    
    /**
     * Aplicación del método Singleton.
     * @return devuelve la Conexion si ya está creada o una nueva.
     * @throws Modelo.auxiliary.DAOException
     */
    public static Conexion getInstance() throws DAOException {
        try {
            if (instance == null) {
                instance = new Conexion();
            } else if (instance.getMiConexion().isClosed()) {
                instance = new Conexion();
            }
        } catch (SQLException | JAXBException ex) {
           throw new DAOException("Error en la conexion", ex);
        }
        return instance;
    }
    
    /**
     * Método para establecer la conexion con la Base de Datos.
     * @return  devuelve true si se conecta.
     * @throws Modelo.auxiliary.DAOException
     */
    public boolean conectar() throws DAOException {
        try {
            //crearBBDD();
            Class.forName("com.mysql.jdbc.Driver");
            miConexion = DriverManager.getConnection("jdbc:mysql://"+
                    this.host+"/"+this.name+"?autoReconnect=true&amp;useSSL=false", this.login, this.password);
            this.estado=true;
	}catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("No database");
        }
            return this.estado;
	}
    
    public boolean cerrarConexion() throws DAOException {
	boolean seCerro=false;
        try {
            this.miConexion.close();
            seCerro=true;
	}catch(SQLException ex){
           throw new DAOException("Error en la conexion", ex);
	}
	return seCerro;	
    }
    
    public static void crearBBDD(){
        System.out.println("Creando nueva Base de datos...");
        ejecutar("olympics.bat");
    }
    
    public static void ejecutar(String archivo){
        try {
            String comando;
            Process proceso = Runtime.getRuntime().exec(archivo);
            BufferedReader lector = new BufferedReader (new InputStreamReader (proceso.getInputStream()));
            while ((comando = lector.readLine()) != null) {
                System.out.println(comando);
            }
                lector.close();
            }catch (Exception err) {
                err.printStackTrace();
            }
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the miConexion
     */
    public Connection getMiConexion() {
        return miConexion;
    }

    /**
     * @param miConexion the miConexion to set
     */
    public void setMiConexion(Connection miConexion) {
        this.miConexion = miConexion;
    }
}


































