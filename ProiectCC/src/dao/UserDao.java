
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author sorin.truica
 */
public class UserDao {
    
    private Connection con;
    private PreparedStatement stmt1, stmt2;
  
    
    public UserDao(Connection con){
        try{
        this.con = con;
        this.stmt1 = con.prepareStatement("SELECT * FROM proiect.user WHERE nume = ? ");
        this.stmt2 = con.prepareStatement("INSERT INTO proiect.user (nume, parola) VALUES(?,?) ");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public User findUser(String username) throws SQLException{
        stmt1.setString(1, username);
        User user = null;
        try(ResultSet rs = stmt1.executeQuery()){
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setNume(rs.getString("nume"));
                user.setParola(rs.getString("parola"));
            }
        }
        return user;
    }
    
   public void adaugaUser(User user) throws SQLException{
        stmt2.setString(1, user.getNume());
        stmt2.setString(2, user.getParola());
        stmt2.executeUpdate();
        
    }
    
}
