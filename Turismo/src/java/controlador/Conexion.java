package controlador;


import java.sql.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    
    Connection connection=null;
    ResultSet rs = null;
    Statement stmt = null;
    
    public Conexion(String BD){
        String url="jdbc:mysql://localhost:3306/"+BD;
        String usuario="root";
        String passw="1234";
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(url,usuario,passw);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public void CloseConnection() throws SQLException{
        connection.close();
    }
    
    public int ExecuteQuery(String Query, Connection con) throws SQLException{
        stmt = (Statement) con.createStatement();
        if(stmt.executeUpdate(Query) != 0){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    public ResultSet Result_Set(String Query, Connection con) throws SQLException{
        stmt = (Statement) con.createStatement();
        if(stmt != null){
            rs = stmt.executeQuery(Query);
            }
        return rs;
    }
}

