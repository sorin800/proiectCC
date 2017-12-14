
package service;

import dao.StudentDao;
import dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.Student;
import model.User;

/**
 *
 * @author sorin.truica
 */
public class MainService {
 
    private String user = "postgres";
    private String pass = "1234";
    private String url = "jdbc:postgresql://localhost:5432/proiectdb";
    private Connection con;
    
    private MainService(){
        try{
            con = DriverManager.getConnection(url, user, pass);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static final class SingletonHolder{
        
        private static final MainService INSTANCE = new MainService();
        
    }
    
    public static MainService getInstance(){
        return SingletonHolder.INSTANCE;
    }
    
    public User login(String nume, String parola){
        UserDao userDao = new UserDao(con);
        try{
        User user = userDao.findUser(nume);
            if(user !=  null){
                if(user.getParola().equals(parola)){
                    return user;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean inregistrare(User user){
        UserDao userDao = new UserDao(con);
        boolean rez = false;
        try{
         User user2 = userDao.findUser(user.getNume());
        if(user2 == null){
            userDao.adaugaUser(user);
            rez = true;
         }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rez;
    }
    
    public void adaugaStudent(Student s){
        StudentDao studentDao = new StudentDao(con);
        try{
            studentDao.adaugaStudent(s);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public List<Student> getAllStudents(){
        
        StudentDao studentDao = new StudentDao(con);
        try{
            return studentDao.getAllStudents();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return Collections.emptyList();
    }
    
    
    public void stergeStudent(int id){
        StudentDao produsDao = new StudentDao(con);
        try{
            produsDao.stergeStudent(id);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    
}
