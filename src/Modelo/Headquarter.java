package Modelo;

/**
 *  Clase Headquarter que instancia objetos Headquarter
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfrancisidlosrios.es>
 */
public class Headquarter {
    protected Integer id;
    protected String name;
    protected float budget;
    protected int numComplexes;

    public Headquarter(String name, float budget) {
        this.name = name;
        this.budget = budget;
        this.numComplexes = 0;
    }

    public Headquarter() {
        this.name = "";
        this.budget = 0;
        this.numComplexes = 0;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the budget
     */
    public float getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(float budget) {
        this.budget = budget;
    }

    public int getNumComplexes() {
        return numComplexes;
    }

    public void setNumComplexes(int numComplexes) {
        this.numComplexes = numComplexes;
    }
    
    @Override
    public String toString(){
        return "ID: "+this.id+", Nombre: "+this.name+", Presupuesto "+
                this.budget+", NumComplejos: "+this.numComplexes;
    }
}













