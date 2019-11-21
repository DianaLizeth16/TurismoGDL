package modelo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import controlador.Conexion;

@WebServlet(name = "inicio", urlPatterns = {"/inicio"})
public class inicio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Conexion conexion = new Conexion("turismo");
            Connection conn = conexion.getConnection();
            ResultSet rs = null;
            ArrayList <String> listaId = new ArrayList <>();
            ArrayList <String> listaNombre = new ArrayList <>();
            ArrayList <String> listaCaracteristica = new ArrayList <>();
            ArrayList <String> listaCategoria = new ArrayList <>();
            ArrayList <String> listaImagenes = new ArrayList <>();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Inicio</title>");      
            out.println("<link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet'>");
            out.println("<link rel='stylesheet' href='css/estilo.css'>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>");
            out.println("<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.2/css/all.css' integrity='sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr' crossorigin='anonymous'>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class id='menu'>");
            out.println("<header>");
            out.println("<nav>");
            out.println("<ul>");
            out.println("<li><a href='#'><span class=''><i class='material-icons fa-2x'>list</i></span>Menu</a>");
            out.println("<ul>");
            out.println("<li><a href='BuscarMapa.html'>Buscar Lugares</a></li>");
            out.println("<li><a href='lugares.html'>AgregarLugar</a></li>");
            out.println("</ul>");
            out.println("</li>");
            out.println("<li><a href='inicio.html'><span class='primero'><i class='material-icons fa-2x'>account_balance</i></span>Guadalajara Lugares</a></li>");
            out.println("<li><a href='#'><span class=''><i class='material-icons fa-2x'>perm_identity</i></span>Usuario</a>");
            out.println("<ul>");
            out.println("<li><a href='index.html'>Salir</a></li>");
            out.println("</ul>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</nav>");
            out.println("</header>");
            out.println("</div>");
            out.println("<body background ='https://www.greatdays.co.uk/wp-content/uploads/Stockholm-Sweden-Old-Town-at-night-%C2%A9-Jeppe-Wikstrom%EF%80%A2mediabank.visitstockholm.com_-e1519646991253.jpg'>");
            out.println("<div class='slider'>");
            out.println("<ul>");
            out.println("<li><img src='https://www.visitmexico.com/viajemospormexico/assets/uploads/destinos/jalisco_destinos-principales_guadalajara_int.jpg' id='primera' width='100%' height='365' align='center'></li>");
            out.println("<li><img src='https://i.ytimg.com/vi/6CzjJ0AhoMg/maxresdefault.jpg' id='primera' width='100%' height='365' align='center'></li>");
            out.println("<li><img src='https://uberblogapi.10upcdn.com/wp-content/uploads/2019/06/6-lugares-para-visitar-en-Guadalajara-que-no-perdonar%C3%A1s-perderte.png' id='primera' width='100%' height='365' align='center'></li>");
            out.println("<li><img src='imagenes/minerba.jpg' id='primera' width='100%' height='365' align='center'></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("<div class='texto'>");
            out.println("<br><br>");
            out.println("<H1>A continuación El Top 3 De los Mejores Lugares, Tal Vez Te gustaría Visitarlos</H1><br>");
            out.println("</div>");
            out.println("<div class=\"popular\">");
            out.println("<div class=\"card-deck\">");
            try{
                String query1 = "SELECT * FROM resenia order by calificacion desc";

                rs = conexion.Result_Set(query1, conn);
                int lista1 = 0;
                int lista2 = 0;
                while(rs.next()){
                     listaId.add(rs.getString(5));
                     System.out.println("rerquest: "+listaId.get(lista1));
                     lista1++;
                }
                for(int i =0;i<3; i++){
                    String query2 = "SELECT * FROM locacion WHERE idLocacion = "+listaId.get(i);

                    rs = conexion.Result_Set(query2, conn);

                    while(rs.next()){
                        listaNombre.add(rs.getString(2));
                        listaCaracteristica.add(rs.getString(3));
                        listaCategoria.add(rs.getString(4));
                        listaImagenes.add(rs.getString(5));    

                        System.out.println("rerquest: "+listaNombre.get(lista2)+","+listaCaracteristica.get(lista2)+","+listaCategoria.get(lista2)+","+listaImagenes.get(lista2)+".\n");
                        lista2++;
                    }
                }
            }catch(SQLException e){
                System.out.println("Excepcion: " +e);
            }
            for(int i =0; i<3; i++){out.println("");
                out.println("<div class=\"card\">");
                out.println("<img src='"+listaImagenes.get(i)+"' width=\"50px\" height=\"300px\" class=\"card-img-top\" alt=\"...\">");
                out.println("<div class=\"card-body\">");
                out.println("<h5 class=\"card-title\">"+listaNombre.get(i)+"</h5>");
                out.println("<p class=\"card-text\">"+listaCaracteristica.get(i)+"</p>");                
                out.println("</div>");                
                out.println("<div class=\"card-footer\">");                
                out.println("<small class=\"text-muted\">"+listaCategoria.get(i)+"</small>");                
                out.println("</div>");                
                out.println("</div>");                
            }
            out.println("</div>");                
            out.println("</div>");                
            out.println("<div class=\"texto\">");                
            out.println("<br><br>");                
            out.println("<H2>¿Qué esperas?<br> ¡Ven y visitanos ya!<br>Podemos garantizarle que su estadía en esta ciudad será una experiencia inolvidable<br><br><br> </H2>");                
            out.println("</div>");
            out.println("<div class=\"footer\">");                
            out.println("<h2>Bienvenidos  a Guadalajara La Capitál de la innovación</h2>");                
            out.println("<p>Guadalajara es la capital y ciudad más grande del estado de Jalisco.<br>");                
            out.println("Se encuentra en la región central de Jalisco,<br>");                
            out.println("en el área del Pacífico occidental de México,<br>");                
            out.println("dentro de la zona geográfica conocida como Valle de Atemajac.</p>");                
            out.println("</div>");               
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
