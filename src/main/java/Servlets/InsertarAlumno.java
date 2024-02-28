package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import Servlets.com.databases.AlumnoDB;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jordan
 */
public class InsertarAlumno extends HttpServlet {
    static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    static final String JDBC_URL = "jdbc:mysql://localhost/aed";
    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGeneric(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGeneric(req, resp);
    }

    protected void doGeneric(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String nombreAlumno = request.getParameter("nombre");
        String apellidosAlumno = request.getParameter("apellidos");
        String cursoAlumno = request.getParameter("curso-alumno");
        String notaAlumno = request.getParameter("nota-alumno");

        AlumnoDB miConexion = new AlumnoDB(JDBC_DRIVER_CLASS, JDBC_URL, JDBC_USER, JDBC_PASS);

        try {
            miConexion.insertAlumn(nombreAlumno, apellidosAlumno, cursoAlumno,
                    Integer.parseInt(notaAlumno));
            // miConexion.insertAlumn("Jordan", "Aleman", "AED", 10);

            out.println("<!DOCTYPE html>\r\n" + //
                    "<html lang=\"en\">\r\n" + //
                    "<head>\r\n" + //
                    "  <meta charset=\"UTF-8\">\r\n" + //
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                    "  <title>Información del Alumno</title>\r\n" + //
                    "  <!-- Bootstrap CSS -->\r\n" + //
                    "  <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
                    + //
                    "  <style>\r\n" + //
                    "    body {\r\n" + //
                    "      background-color: #f8f9fa; /* Color de fondo de la página */\r\n" + //
                    "    }\r\n" + //
                    "    .container {\r\n" + //
                    "      margin-top: 50px;\r\n" + //
                    "    }\r\n" + //
                    "    .table-container {\r\n" + //
                    "      background-color: #ffffff; /* Color de fondo del contenedor */\r\n" + //
                    "      padding: 30px;\r\n" + //
                    "      border-radius: 10px;\r\n" + //
                    "      box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);\r\n" + //
                    "      margin-bottom: 20px; /* Espacio en la parte inferior */\r\n" + //
                    "      margin-top: 10px;\r\n" + //
                    "      position: relative;\r\n" + //
                    "    }\r\n" + //
                    "    .table-title {\r\n" + //
                    "      text-align: center;\r\n" + //
                    "      margin-bottom: 20px;\r\n" + //
                    "    }\r\n" + //
                    "    .table th,\r\n" + //
                    "    .table td {\r\n" + //
                    "      border: 1px solid #dee2e6; /* Bordes */\r\n" + //
                    "    }\r\n" + //
                    "    .table thead {\r\n" + //
                    "      background-color: #d6e4ff; /* Color de fondo del header */\r\n" + //
                    "      color: #000000; /* Color de texto del header */\r\n" + //
                    "    }\r\n" + //
                    "    .table thead th {\r\n" + //
                    "      border-color: #d6e4ff; /* Bordes del header */\r\n" + //
                    "      font-weight: bold;\r\n" + //
                    "      font-size: 18px; /* Tamaño de fuente más grande */\r\n" + //
                    "      text-align: center; /* Centrado */\r\n" + //
                    "    }\r\n" + //
                    "    .table tbody tr td:first-child {\r\n" + //
                    "      background-color: #f2f2f2; /* Color de fondo de la columna de información */\r\n" + //
                    "      font-weight: bold; /* Texto en negrita */\r\n" + //
                    "    }\r\n" + //
                    "  </style>\r\n" + //
                    "</head>\r\n" + //
                    "<body>\r\n" + //
                    "  <div class=\"container\">\r\n" + //
                    "    <div class=\"table-container\">\r\n" + //
                    "      <h2 class=\"table-title\">Información del Alumno</h2>\r\n" + //
                    "      <div class=\"table-responsive\">\r\n" + //
                    "        <table class=\"table table-bordered\">\r\n" + //
                    "          <thead>\r\n" + //
                    "            <tr>\r\n" + //
                    "              <th>Información</th>\r\n" + //
                    "              <th>Valor</th>\r\n" + //
                    "            </tr>\r\n" + //
                    "          </thead>\r\n" + //
                    "          <tbody>\r\n" + //
                    "            <tr>\r\n" + //
                    "              <td>Nombre</td>\r\n" + //
                    "              <td>" + nombreAlumno + "</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "              <td>Apellidos</td>\r\n" + //
                    "              <td>" + apellidosAlumno + "</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "              <td>Curso</td>\r\n" + //
                    "              <td>" + cursoAlumno + "</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "            <tr>\r\n" + //
                    "              <td>Nota</td>\r\n" + //
                    "              <td>" + notaAlumno + "</td>\r\n" + //
                    "            </tr>\r\n" + //
                    "          </tbody>\r\n" + //
                    "        </table>\r\n" + //
                    "      </div>\r\n" + //
                    "    </div>\r\n" + //
                    "  </div>\r\n" + //
                    "\r\n" + //
                    "  <!-- Bootstrap JS and jQuery (Necesarios para el funcionamiento de Bootstrap) -->\r\n" + //
                    "  <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>\r\n" + //
                    "  <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js\"></script>\r\n"
                    + //
                    "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n"
                    + //
                    "</body>\r\n" + //
                    "</html>\r\n" + //
                    "");

            out.close();
            miConexion.getCon().close();

        } catch (SQLException e) {
            out.print("<p>Mal</p>");
        }

    }
}
