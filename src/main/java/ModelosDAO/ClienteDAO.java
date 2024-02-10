/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelosDAO;

import db.cn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Entidades.Cliente;
import java.util.ArrayList;

/**
 *
 * @author ale
 */
public class ClienteDAO {
    private cn CN;
    private Connection con;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Cliente> getAll(){
        List<Cliente> listaClientes = new ArrayList<>();
        
        this.sql = "SELECT * FROM cliente";
        try {
            ps=this.CN.getConnection().prepareStatement(this.sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt("ID");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("dirección");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                // Crear un objeto Cliente con los valores obtenidos de la base de datos
                Cliente cliente = new Cliente(id, nombre, direccion, telefono, email);
                listaClientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
         return listaClientes;
    } 
    
    public boolean insert(Cliente cliente){
        String sql = "INSERT INTO clientes (nombre, dirección, telefono, email) VALUES (?, ?, ?, ?)";
        boolean band= false;
        try {
            con = CN.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            int filasAfectadas=ps.executeUpdate();
            if (filasAfectadas > 0) {
                band = true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return band;
    }
}
