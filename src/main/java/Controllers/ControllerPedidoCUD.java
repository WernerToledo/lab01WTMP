/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Entidades.Cliente;
import Entidades.Pedido;
import ModelosDAO.ClienteDAO;
import ModelosDAO.PedidoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ale
 */
@WebServlet(name = "ControllerPedidoCUD", urlPatterns = {"/ControllerPedidoCUD"})
public class ControllerPedidoCUD extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerPedidoCUD</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerPedidoCUD at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ControllerPedido controllerPedido = new ControllerPedido();
        List<Cliente> lCliente = new ArrayList<>();
        Pedido pedido = new Pedido();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        
        if (id > 0 && action.equals("update")) {
            try {
                ClienteDAO clienteDAO = new ClienteDAO();
                lCliente = clienteDAO.getAll();
                if (lCliente != null) {
                    request.setAttribute("lCliente", lCliente);
                }
                
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedido = pedidoDAO.getPedidoById(id);
                
                request.setAttribute("pedido", pedido);
            } catch (Exception e) {
            }
            request.setAttribute("id", id);
            RequestDispatcher dispatcher=request.getRequestDispatcher("FormPedido.jsp");
            dispatcher.forward(request,response); 
        }
        else if (id > 0 && action.equals("delete")) {
            try {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.Delete(id);
                
                controllerPedido.doGet(request, response);
            } catch (Exception e) {
            }
        }
        else{
            try {
                ClienteDAO clienteDAO = new ClienteDAO();
                lCliente = clienteDAO.getAll();
                if (lCliente != null) {
                    request.setAttribute("lCliente", lCliente);
                }
                
            } catch (Exception e) {
            }
            request.setAttribute("id", id);
            RequestDispatcher dispatcher=request.getRequestDispatcher("FormPedido.jsp");
            dispatcher.forward(request,response); 
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Pedido pedido;
        ControllerPedido controllerPedido = new ControllerPedido();
        
        int id = Integer.parseInt(request.getParameter("id"));
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        double total = Double.parseDouble(request.getParameter("total"));
        
        if (id > 0) {
            try {
                pedido = new Pedido(id, id_cliente, total);
                PedidoDAO pedidoDAO = new PedidoDAO();
                
                pedidoDAO.updatePedido(pedido);
                controllerPedido.doGet(request, response);
            } catch (Exception e) {
            }
            
            controllerPedido.doGet(request, response);
        }
        else{
            try {
                PedidoDAO pedidoDAPO = new PedidoDAO();
                pedido = new Pedido(id_cliente, total);
                pedidoDAPO.insertPedido(pedido);
                controllerPedido.doGet(request, response);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
