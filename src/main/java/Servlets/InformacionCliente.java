package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
public class InformacionCliente extends HttpServlet {

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
        
        String servletMethod = request.getMethod();
        String servletRemoteAddress = request.getRemoteAddr();
        String servletLocalAddress = request.getLocalAddr();
        int servletRemotePort = request.getRemotePort();

        out.println("<!DOCTYPE html>\r\n" + //
                "<html lang=\"en\">\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>Info Servlet</title>\r\n" + //
                "    <style>\r\n" + //
                "        table {\r\n" + //
                "            border-collapse: collapse;\r\n" + //
                "            width: 50%;\r\n" + //
                "            margin: 20px auto;\r\n" + //
                "        }\r\n" + //
                "        th, td {\r\n" + //
                "            border: 1px solid #999;\r\n" + //
                "            padding: 8px;\r\n" + //
                "            text-align: left;\r\n" + //
                "        }\r\n" + //
                "        th {\r\n" + //
                "            background-color: #f2f2f2;\r\n" + //
                "        }\r\n" + //
                "    </style>\r\n" + //
                "</head>\r\n" + //
                "<body>\r\n" + //
                "    <h1 style=\"text-align: center;\">Información de nuestro servlet</h1>\r\n" + //
                "    <table>\r\n" + //
                "        <tr>\r\n" + //
                "            <th>Información</th>\r\n" + //
                "            <th>Valor</th>\r\n" + //
                "        </tr>\r\n" + //
                "        <tr>\r\n" + //
                "            <td><b>Método con el que fue invocado el servlet</b></td>\r\n" + //
                "            <td>" + servletMethod + "</td>\r\n" + //
                "        </tr>\r\n" + //
                "        <tr>\r\n" + //
                "            <td><b>Dirección remota desde la que fue llamado</b></td>\r\n" + //
                "            <td>" + servletRemoteAddress + "</td>\r\n" + //
                "        </tr>\r\n" + //
                "        <tr>\r\n" + //
                "            <td><b>Dirección local en la que está desplegado el servlet</b></td>\r\n" + //
                "            <td>" + servletLocalAddress + "</td>\r\n" + //
                "        </tr>\r\n" + //
                "        <tr>\r\n" + //
                "            <td><b>Puerto remoto desde donde se hizo la solicitud</b></td>\r\n" + //
                "            <td>" + servletRemotePort + "</td>\r\n" + //
                "        </tr>\r\n" + //
                "    </table>\r\n" + //
                "</body>\r\n" + //
                "</html>");

        out.close();
    }
}
