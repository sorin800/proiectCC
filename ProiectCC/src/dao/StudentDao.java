
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 *
 * @author sorin.truica
 */
public class StudentDao {
    
    private Connection con;
    private PreparedStatement stmt1,stmt2,stmt3;
    
    public StudentDao(Connection con){
        try{
        this.con = con;
        this.stmt1 = con.prepareStatement("INSERT INTO proiect.student (nume, facultate, specializare, email) VALUES (?,?,?,?)");
        this.stmt2 = con.prepareStatement("SELECT * FROM proiect.student");
        this.stmt3 = con.prepareStatement("DELETE FROM proiect.student WHERE id = ?");
         }catch(SQLException e){
                 e.printStackTrace();       
    }
  }
    
    public void adaugaStudent(Student s)throws SQLException{
        stmt1.setString(1, s.getNume());
        stmt1.setString(2, s.getFacultate());
        stmt1.setString(3, s.getSpecializare());
        stmt1.setString(4, s.getEmail());
        stmt1.executeUpdate();
    }
    
    
    public List<Student> getAllStudents() throws SQLException{
        List<Student> studenti = new ArrayList<>();
        ResultSet rs = stmt2.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String nume = rs.getString("nume");
            String facultate = rs.getString("facultate");
            String specializare = rs.getString("specializare");
            String email = rs.getString("email");
            
            studenti.add(new Student(id,nume,facultate,specializare,email));
        }
        return studenti;
    }
    
    public void stergeStudent(int id) throws SQLException{
        stmt3.setInt(1, id);
        stmt3.executeUpdate();
    }
}
