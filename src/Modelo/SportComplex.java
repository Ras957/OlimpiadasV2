package Modelo;

/**
 *  Clase Headquarter que instancia objetos Headquarter
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfrancisidlosrios.es>
 */
public class SportComplex {
    protected Integer id;
    protected String location;
    protected String boss;
    protected Headquarter headquarter;
    protected String tipo;

    /*
    public SportComplex(String location, String boss) {
        this.location = location;
        this.boss = boss;
    }*/

    public SportComplex(String location, String boss, 
            Headquarter headquarter) {
        this.location = location;
        this.boss = boss;
        this.headquarter = headquarter;
    }

    public SportComplex() {
        this.location = "";
        this.boss = "";
        this.headquarter = null;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the boss
     */
    public String getBoss() {
        return boss;
    }

    /**
     * @param boss the boss to set
     */
    public void setBoss(String boss) {
        this.boss = boss;
    }

    /**
     * @return the headquarters
     */
    public Headquarter getHeadquarter() {
        return headquarter;
    }

    /**
     * @param headquarters the headquarters to set
     */
    public void setHeadquarter(Headquarter headquarter) {
        this.headquarter = headquarter;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /*
    @Override
    public String toString(){
        return "ID: "+this.id+" Localización: "+this.location+
                " Jefe "+this.boss+" Id_Sede:"+this.headquarter.getId();
    }*/

    @Override
    public String toString() {
        return this.location+" ("+this.id+")";
    }
    
}


















