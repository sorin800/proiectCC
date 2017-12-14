
package model;

/**
 *
 * @author sorin.truica
 */
public class User {
    
    private int id;
    private String nume;
    private String parola;

    
    public User(){
        
    }
    
    public User(String nume, String parola){
        this.nume = nume;
        this.parola = parola;
    }
    
    public User(int id, String nume, String parola) {
        this.id = id;
        this.nume = nume;
        this.parola = parola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    
}
