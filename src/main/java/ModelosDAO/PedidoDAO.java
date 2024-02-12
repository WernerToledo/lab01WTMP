/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelosDAO;

import Entidades.Pedido;
import db.cn;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ale
 */
public class PedidoDAO {
    private cn CN;
    private Connection con;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    public PedidoDAO() throws ClassNotFoundException {
        this.CN = new cn();
    }
    
    public List<Pedido> getAllPedidos() {
        
        List<Pedido> listaPedidos = new ArrayList<>();   
        String sql = "SELECT Pedidos.ID, Pedidos.Fecha, Pedidos.Total, Pedidos.Estado, Clientes.Nombre AS NombreCliente " +
                     "FROM Pedidos JOIN Clientes ON Pedidos.ID_Cliente = Clientes.ID";
        try {
            PreparedStatement ps = this.CN.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombreCliente = rs.getString("NombreCliente");
                Date fechaPedido = rs.getDate("fecha");
                double montoTotal = rs.getDouble("total");
                String estado = rs.getString("estado");

                // Crear un objeto Pedido con los valores obtenidos de la base de datos
                Pedido pedido = new Pedido(id, fechaPedido, montoTotal, estado, nombreCliente);
                listaPedidos.add(pedido);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
            return null;
        }
    
        return listaPedidos;
    }
    public List<Pedido> getTopPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();

        String sql = "SELECT YEAR(fecha) AS anio, " +
                             "MONTHNAME(fecha) AS mes, " +
                             "COUNT(*) AS total_pedidos " +
                     "FROM pedidos " +
                     "GROUP BY YEAR(fecha), MONTH(fecha) " +
                     "ORDER BY total_pedidos DESC " +
                     "LIMIT 3";

        try {
            PreparedStatement ps = CN.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Obtener los valores de la consulta
                String mes = rs.getString("mes");
                int anio = rs.getInt("anio");
                int totalPedidos = rs.getInt("total_pedidos");

                // Crear un objeto Pedido con los valores obtenidos de la base de datos
                Pedido pedido = new Pedido(totalPedidos, mes, anio);
                listaPedidos.add(pedido);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        }
        return listaPedidos;
    }

    
    public Pedido getPedidoById(int idPedido) {
        Pedido pedido = null;
       String sql = "SELECT Pedidos.ID, Pedidos.Fecha, Pedidos.Total, Pedidos.Estado, Clientes.Nombre AS NombreCliente, Clientes.ID AS idCliente " +
             "FROM Pedidos " +
             "JOIN Clientes ON Pedidos.ID_Cliente = Clientes.ID " +
             "WHERE Pedidos.ID = ?";

        try {
            PreparedStatement ps = CN.getConnection().prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                String nombreCliente = rs.getString("NombreCliente");
                Date fechaPedido = rs.getDate("Fecha");
                double montoTotal = rs.getDouble("Total");
                String estado = rs.getString("Estado");
                int idCliente = rs.getInt("idCliente");

                // Crear un objeto Pedido con los valores obtenidos de la base de datos
                pedido = new Pedido(id, idCliente, fechaPedido, montoTotal, estado, nombreCliente);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el pedido: " + e.getMessage());
        }

        return pedido;
    }
    
    public List<Pedido> getForGraf() {
        List<Pedido> listaPedidos = new ArrayList<>();   
        String sql = "SELECT c.nombre AS NombreCliente, COUNT(p.ID_Cliente) AS CantidadPedidos " +
                     "FROM clientes c " +
                     "JOIN pedidos p ON c.ID = p.id_cliente " +
                     "GROUP BY c.nombre " +
                     "ORDER BY COUNT(p.ID_Cliente) DESC " +
                     "LIMIT 2";

        try {
            PreparedStatement ps = this.CN.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreCliente = rs.getString("NombreCliente");
                int cantidadPedidos = rs.getInt("CantidadPedidos");

                // Crear un objeto Pedido con los valores obtenidos de la base de datos
                Pedido pedido = new Pedido(cantidadPedidos, nombreCliente);
                listaPedidos.add(pedido);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
            return null;
        }

        return listaPedidos;
    }


    
    public boolean insertPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_cliente, total, estado) VALUES (?, ?, ?)";
        boolean inserted = false;

        try {
            Connection con = CN.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer los parámetros en la sentencia SQL
            ps.setInt(1, pedido.getID_Cliente());
            ps.setDouble(2, pedido.getTotal());
            ps.setString(3, "a");
            
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                inserted = true;
            }
            // Cerrar PreparedStatement y Connection
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el pedido: " + e.getMessage());
        }

        return inserted;
    }
    
    public boolean updatePedido(Pedido pedido) {
        String sql = "UPDATE pedidos SET total = ?, fecha = ?, id_cliente = ? WHERE ID = ?";
        boolean updated = false;

        try {
            Connection con = CN.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer los parámetros en la sentencia SQL
            ps.setDouble(1, pedido.getTotal());
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(3, pedido.getID_Cliente());
            ps.setInt(4, pedido.getID());
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                updated = true;
            }
            // Cerrar PreparedStatement y Connection
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el pedido: " + e.getMessage());
        }

        return updated;
    }
    
    public boolean Delete(int idPedido) {
        String sql = "UPDATE pedidos SET estado = ? WHERE ID = ?";
        boolean actualizado = false;

        try {
            Connection con = CN.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer los parámetros en la sentencia SQL
            ps.setString(1, "d"); // Establecer el estado como "d"
            ps.setInt(2, idPedido); // Establecer el ID del pedido

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                actualizado = true;
            }
            // Cerrar PreparedStatement y Connection
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del pedido: " + e.getMessage());
        }

        return actualizado;
    }

    
    
}
