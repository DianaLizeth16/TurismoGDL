/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import controlador.Conexion;
import java.io.PrintWriter; 

/**
 *
 * @author Diana
 */
@WebServlet(name = "lugares", urlPatterns = {"/lugares"})
public class lugares extends HttpServlet {

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
            out.println("<title>Servlet lugares</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet lugares at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        //processRequest(request, response);
        try{
            
            Conexion conexion = new Conexion("turismo");
            Connection conn = conexion.getConnection();
            Statement stmt=null;
            
            String nombre;
            String caracteristica;
            String categoria;
            String imagen;
            String recomendacion;
          //  String longitud;
            PrintWriter out = response.getWriter();
            
      
            nombre=request.getParameter("nombre");
            caracteristica=request.getParameter("caracteristica");
            categoria=request.getParameter("categoria");
            imagen=request.getParameter("imagen");
            recomendacion=request.getParameter("recomendacion");
            //longitud=request.getParameter("longitud");
            
            String query = "INSERT INTO locacion(nombreLocacion,caracteristica,categoriaLocacion,imagen,recomendacion) VALUES('"+nombre+"','"+caracteristica+"','"+categoria+"','"+imagen+"','"+recomendacion+"') ";
                        int respuesta = conexion.ExecuteQuery(query, conn);
                        
                        if(respuesta == 1){
                                out.println("<!DOCTYPE html>");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" \n" +
                                "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
                                out.println("<title>Servlet lugares</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<center><div class='alert alert-success' role='alert' style='margin-top: 100px; width: 70%;'>");
                                out.println("<center><h3>El registro ha sido completado con exito</h3></center>");
                                out.println("<br>");
                                out.println("<center><button type='button' class='btn btn-outline-success'><a href='inicio.html'>Aceptar</a></button></center>");
                                out.println("</div></center>");
                                out.println("</body>");
                                out.println("</html>");
                        }
        //stmt=conn.createStatement();
      //  stmt.executeUpdate("INSERT INTO locacion(nombreLocacion,caracteristica,categoriaLocacion,imagen,latitud,longitud)VALUES('"+nombre+"','"+caracteristica+"','"+categoria+"','"+imagen+"','"+latitud+"','"+longitud+"')");
      
        //stmt.executeUpdate("Insert into cliente(nombre,domicilio)values('"+nombre+"','"+domicilio+"')");
      //out.println("SE HA AÑADIDO EXITOSAMENTE");
            
            
        }catch(Exception e){
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
