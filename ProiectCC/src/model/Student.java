
package model;

/**
 *
 * @author sorin.truica
 */
public class Student {
    
    private int id;
    private String nume;
    private String facultate;
    private String specializare;
    private String email;

    public Student(int id, String nume, String facultate, String specializare, String email) {
        this.id = id;
        this.nume = nume;
        this.facultate = facultate;
        this.specializare = specializare;
        this.email = email;
    }

    public Student(String nume, String facultate, String specializare, String email) {
        this.nume = nume;
        this.facultate = facultate;
        this.specializare = specializare;
        this.email = email;
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

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString(){
        return id + " | " + nume + " | " +  facultate + " | " + email;
    }
    
}
