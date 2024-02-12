/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;

/**@author reqp*/
public class cn {
    private Connection con;
    
    public cn() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/semana04","root","");
            
        }catch (Exception e){
            System.err.println("Error"+e);
        }
    }
    
    public Connection getConnection() {
        return con;
    }
}

