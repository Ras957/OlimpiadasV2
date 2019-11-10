package Modelo.auxiliary;

/**
 * Clase para controlar las excepciones cuando se introduce un DNI erróneo
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class DNIException extends Exception{

    public DNIException(String message) {
        super(message);
    }

    public DNIException(String message, Throwable cause) {
        super(message, cause);
    }

    public DNIException(Throwable cause) {
        super(cause);
    }
}
