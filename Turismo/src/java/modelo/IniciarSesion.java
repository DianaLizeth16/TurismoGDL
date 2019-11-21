package modelo;

import controlador.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IniciarSesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IniciarSesion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Conexion conexion = new Conexion("turismo");
            Connection conn = conexion.getConnection();
            ResultSet rs = null;

            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            String query = "SELECT * FROM turista WHERE usuario = '"+usuario+"' AND password = '"+password+"'";  

            rs = conexion.Result_Set(query, conn);
            
            if(rs.next()){
                response.sendRedirect("http://localhost:8080/Turismo/inicio");
            }
            else{
                RequestDispatcher rd; 
                rd = request.getRequestDispatcher("/index.html");
                rd.forward(request, response);
            }
            
        }catch(SQLException e){
            
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
