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
@WebServlet(name = "resenia", urlPatterns = {"/resenia"})
public class resenia extends HttpServlet {
    

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
            out.println("<title>Servlet resenia</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet resenia at " + request.getContextPath() + "</h1>");
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
       // processRequest(request, response);
        PrintWriter out = response.getWriter();
        
        try{
            Conexion conexion = new Conexion("turismo");
            Connection conn = conexion.getConnection();
            
            String resenia;
            String nombre;
            String lugar;
            int calificacion;
            //int idTur = 0;
            //int idLoc;
            
            
            
            lugar=request.getParameter("lugar");
            nombre=request.getParameter("nombre");
            calificacion=Integer.parseInt(request.getParameter("calificacion"));
            resenia=request.getParameter("resenia");
            
                 String consulta1 = "SELECT idTurista FROM turista WHERE usuario = '"+nombre+"'";
                 String consulta2 = "SELECT idLocacion FROM locacion WHERE nombreLocacion = '"+lugar+"'";
              //   out.println(lugar);
                    ResultSet rs=null;
                    ResultSet rs2=null;
                    rs = conexion.Result_Set(consulta1, conn);
                    rs2 = conexion.Result_Set(consulta2, conn);
                    
                         while((rs.next())&&(rs2.next())){ 
                              int idLoc = rs2.getInt("idLocacion");  
                             //   out.println("<h1>El id de la Locacion: "+idLoc+" ya ha sido registrado</h1>");
                                int idTur = rs.getInt("idTurista");  
                              //  out.println("<h1>El id de usuario: "+idTur+" ya ha sido registrado</h1>");
                               String query = "INSERT INTO resenia(reseniaR,calificacion,idTurista,idLocacion) VALUES('"+resenia+"','"+calificacion+"','"+idTur+"','"+idLoc+"') ";
                               int respuesta = conexion.ExecuteQuery(query, conn);
                               
                             // out.println("se añadio correcatmente"); 
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
                               
                               
                        }
                        // out.println(resenia);
                         // out.println("<h1>El nombre de usuario: "+nombre+" ya ha sido registrado</h1>");
                         
                   
       
            
            
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
