/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Entidades.Pedido;
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
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author ale
 */
@WebServlet(name = "ControllerGrafAnio", urlPatterns = {"/ControllerGrafAnio"})
public class ControllerGrafAnio extends HttpServlet {

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
            out.println("<title>Servlet ControllerGrafAnio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerGrafAnio at " + request.getContextPath() + "</h1>");
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
           
        
        List<JSONObject> jsonPedidos = new ArrayList<>();

        try {
            // Obtener la lista de pedidos desde la base de datos
            PedidoDAO pedidoDAO = new PedidoDAO();
            List<Pedido> lPedido = pedidoDAO.getTopPedidos();

            // Convertir cada objeto Pedido a JSON
            for (Pedido pedido : lPedido) {
                JSONObject jsonPedido = new JSONObject();
                jsonPedido.put("ID", pedido.getID());
                jsonPedido.put("mes", pedido.getMes());
                jsonPedido.put("anio", pedido.getAnio());
                jsonPedidos.add(jsonPedido);
            }

            // Convertir la lista de JSON a una cadena JSON
            JSONArray jsonArray = new JSONArray(jsonPedidos);
            String jsonString = jsonArray.toString();

            // Establecer el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Escribir la cadena JSON en el cuerpo de la respuesta
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            out.flush();
            

        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        
//        try {
//            PedidoDAO pedidoDAO = new PedidoDAO();
//            List<Pedido> lPedido = pedidoDAO.getTopPedidos();
//            
//            request.setAttribute("lPedido", lPedido);
//            RequestDispatcher dispatcher=request.getRequestDispatcher("Grafico.jsp");
//            dispatcher.forward(request,response); 
//            
//        } catch (Exception e) {
//        }

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
        processRequest(request, response);
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
