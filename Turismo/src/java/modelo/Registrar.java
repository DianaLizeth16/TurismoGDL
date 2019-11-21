package modelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;

@WebServlet(name = "Registrar", urlPatterns = {"/Registrar"})
public class Registrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registrar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registrar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{
            Conexion conexion = new Conexion("turismo");
            Connection conn = conexion.getConnection();
            int idTurista = 0;        
            String nom = null;
            int age = 0;
            String prosed = null;
            String user = null;
            String passw = null;
            
            if(conn != null){
                String nombre = request.getParameter("nombre");
                String edad = request.getParameter("edad");
                String procedencia = request.getParameter("procedencia");
                String fInicial = request.getParameter("inicial");
                String fFinal = request.getParameter("final");
                String username = request.getParameter("user");
                String password = request.getParameter("password");
                String comfirm = request.getParameter("confirm");
                                
                if(password.equals(comfirm)){
                    String consulta = "SELECT * FROM turista WHERE usuario = '"+username+"'";
                    ResultSet rs = null;
                    rs = conexion.Result_Set(consulta, conn);
     
                    if(rs.next()){
                        while(rs.next()){
                            idTurista = rs.getInt(1);        
                            nom = rs.getString(2);
                            age = rs.getInt(3);
                            prosed = rs.getString(4);
                            user = rs.getString(5);
                            passw = rs.getString(6);
                        }
                        out.println("<!DOCTYPE html>");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" \n" +
                                "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
                                out.println("<title>Servlet lugares</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<center><div class='alert alert-danger' role='alert' style='margin-top: 100px; width: 70%;'>");
                                out.println("<center><h3>Este nombre de usuario ya ha sido registrado</h3></center>");
                                out.println("<br>");
                                out.println("<center><button type='button' class='btn btn-outline-danger'><a href='registro.html'>Aceptar</a></button></center>");
                                out.println("</div></center>");
                                out.println("</body>");
                                out.println("</html>");
                        
                    }
                    else{                   
                        String query = "INSERT INTO turista(nombreTurista,edadTurisa,procedencia,usuario,password) VALUES('"+nombre+"',"+edad+",'"+procedencia+"','"+username+"','"+password+"') ";
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
                                out.println("<center><button type='button' class='btn btn-outline-success'><a href='index.html'>Aceptar</a></button></center>");
                                out.println("</div></center>");
                                out.println("</body>");
                                out.println("</html>");
                        }
                    }
                }
                else{
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" \n" +
                    "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
                    out.println("<title>Servlet lugares</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<center><div class='alert alert-danger' role='alert' style='margin-top: 100px; width: 70%;'>");
                    out.println("<center><h3>Las contraseñas no coinciden</h3></center>");
                    out.println("<br>");
                    out.println("<center><button type='button' class='btn btn-outline-danger'><a href='registro.html'>Aceptar</a></button></center>");
                    out.println("</div></center>");
                    out.println("</body>");
                    out.println("</html>");
                }
                
                
            }
        }catch(SQLException e){
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
