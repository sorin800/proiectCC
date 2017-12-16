
package worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 *
 * @author sorin.truica
 */
public class Worker {
   
    public static void main(String [] args){
        try{
      Jedis redis = connectToRedis("127.0.0.1");
      Connection dbConn = connectToDB("jdbc:postgresql:////localhost:5432/proiectdb");
        System.out.println("Inserting data");
        while(true){
            stergeDate(dbConn);
            insertUser(dbConn);
            break;
        }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    
    static Jedis connectToRedis(String host){
        Jedis conn = new Jedis(host);
        while(true){
            try{
                conn.keys("*");
                
                break;
            }catch(JedisConnectionException e){
                System.err.println("Waiting for redis");
                sleep(1000);
            }
        }
        System.err.println("Connected to redis");
        return conn;
        
    }
    static void insertUser(Connection dbConn) throws SQLException{
        PreparedStatement insert1 = dbConn.prepareStatement("INSERT INTO proiect.student (nume, facultate, specializare, email) VALUES ('Sorin','UPB','SSA','sorin@gmail.com')");
        PreparedStatement insert2 = dbConn.prepareStatement("INSERT INTO proiect.student (nume, facultate, specializare, email) VALUES ('Ionel','UPB','SSA','ionel@gmail.com')");
        try{
            insert1.executeUpdate();
            insert2.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("Datele au fost introduse!");
    }
    
    static void stergeDate(Connection dbConn) throws SQLException{
        
        PreparedStatement delete = dbConn.prepareStatement("DELETE FROM proiect.student");
        try{
        delete.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("Datele au fost sterse!");
    }
    static Connection connectToDB(String host){
        
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            
            String url = "jdbc:postgresql://localhost:5432/proiectdb";
            
            while(conn == null){
                try{
                    conn = DriverManager.getConnection(url,"postgres","1234");
                }catch(SQLException e){
                    System.err.println("Waiting for db");
                    sleep(1000);
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        System.err.println("Connected to db");
        return conn;
    }
    
    static void sleep(long duration){
        try{
                Thread.sleep(duration);
        } catch(InterruptedException e){
            System.exit(1);
        }
    }
}
