<%-- 
    Document   : index
    Created on : 10 ene 2024, 12:31:43
    Author     : Jordan
--%>

<%@page import="java.util.ArrayList" %>
<%@page import="Dates.Student"%>
<%@page import="Dates.StudentManagement"%>
<%@page import="Dates.AdminManagement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Creación de la String action que se encangará de
indicar la acción que tendrá que realizar el index --%>
<% String action = request.getParameter("action"); %>

<%-- Login --%>
<%  
    if (action != null && action.equals("login")) {
        AdminManagement adminManagement = new AdminManagement();

        String user = request.getParameter("user");
        String password = request.getParameter("password");

        if (!adminManagement.existsAdmin(user, password)) {
            response.sendRedirect("login.jsp?redirectFromIndex=true");   
            return;
        }
            
        session.setAttribute("username", user);
    }
%>

<%-- Controlar sesión al index.jsp --%>
<% 
    session = request.getSession();

    // Obtener el atributo "username" de la sesión
    String username = (session != null) ? (String) session.getAttribute("username") : "";

    // Verificar si el usuario es administrador
    boolean isAdmin = "admin".equals(username);

    System.out.println(username);

    // Si no hay sesión activa o el usuario no es administrador, redirigir a login.jsp
    if (!isAdmin) {
        session.invalidate();
        response.sendRedirect("login.jsp?redirectForWrongAccess=true");
    }
%>

<%-- Objetos generales --%>
<%
    StudentManagement studentManagement = new StudentManagement();

    // Control de mensaje de alerta
    boolean error = false;
    String textSuccess = "";
    String colorTextSuccess = "";

%>
<%-- Insert Student --%>
<%
    if (action != null && action.equals("insert")) {
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            int age = Integer.parseInt(request.getParameter("age"));
            String address = request.getParameter("address");
            int course = Integer.parseInt(request.getParameter("course"));
            String familyDates = request.getParameter("familyDates");
            double note = Double.parseDouble(request.getParameter("note"));

            if (studentManagement.insertStudent(name, surname, age, address, course, familyDates)) {
                textSuccess = "Student INSERT successfully";
                colorTextSuccess = "primary";
            }
            

        } catch (NumberFormatException nfe) {
            error = true;
        }
    }
%>
<%-- Update Student --%>
<%
    if (action != null && action.equals("update")) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            int age = Integer.parseInt(request.getParameter("age"));
            String address = request.getParameter("address");
            int course = Integer.parseInt(request.getParameter("course"));
            String familyDates = request.getParameter("familyDates");
            double note = Double.parseDouble(request.getParameter("note"));

            if (studentManagement.updateStudent(id, name, surname, age, address, course, familyDates, note)) {
                textSuccess = "Student UPDATE successfully";
                colorTextSuccess = "warning";
            }

        } catch (NumberFormatException nfe) {
            error = true;
        }
    }
%>

<%-- Delete Student --%>
<%
    if (action != null && action.equals("delete")) {
        int id = Integer.parseInt(request.getParameter("id"));

        if (studentManagement.deleteStudent(id)) {
            textSuccess = "Student DELETE successfully";
            colorTextSuccess = "danger";
        }

    }
%>


<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>

    <body>
    <% if(error) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong>Error in insert!</strong> There is an error with parameter Age or Course. No insert.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <%
        }

        if(!textSuccess.isEmpty()) {
        %>
        <div class="alert alert-<%=colorTextSuccess%> alert-dismissible fade show" role="alert">
            <strong>Congrats!</strong> <%=textSuccess%>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <%

        }
    %>
        <div id="main-page" class="container-fluid">
            <header class="container">
                <div class="row">
                    <div class="col-8 my-4">
                        <h1>Manage Student - IES ElRincón</h1>
                    </div>
                    <div class="col-1 my-4"></div>
                    <div class="col-3 my-4">
                        <form method="get" action="login.jsp">
                            <div class="d-grid gap-2">
                                <input type="hidden" name="signOut" value="true">
                                <button type="submit" class="btn btn-primary btn-block"><strong>Sign out</strong></button>
                            </div>
                        </form>
                    </div>
                </div>
            </header>

            <main class="container">
                <div class="row">
                    <section id="tableDates" class="col-12 px-0">

                        <%
                            ArrayList<Student> listStudents = studentManagement.getStudents();
                        %>
                        <table class="table table-striped">
                            <thead class="table-dark ">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Apellidos</th>
                                    <th scope="col">Edad</th>
                                    <th scope="col">Dirección</th>
                                    <th scope="col">Curso</th>
                                    <th scope="col">Datos de familia</th>
                                    <th scope="col">Nota global</th>
                                    <th scope="col" colspan="2"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Student student : listStudents) {
                                %>
                                    <tr>
                                        <th scope="row"><% out.print(student.getId()); %></th>
                                        <td><% out.print(student.getName()); %></td>
                                        <td><% out.print(student.getSurname()); %></td>
                                        <td><% out.print(student.getAge()); %></td>
                                        <td><% out.print(student.getAddress()); %></td>
                                        <td><% out.print(student.getCourse()); %></td>
                                        <td><% out.print(student.getFamilyDates()); %></td>
                                        <td>
                                            <%
                                                double note = student.getNote();
                                                if (note >= 0 && note < 5) {
                                                    out.print("Suspendido");
                                                } else if (note >= 5 && note < 8) {
                                                    out.print("Aprobado");
                                                } else if (note >= 8 && note < 10) {
                                                    out.print("Notable");
                                                } else if (note == 10) {
                                                    out.print("Sobresaliente");
                                                } else {
                                                    out.print("Nota no válida");
                                                }
                                            %>
                                        </td>
                                        <td>
                                            <form method="get" action="formulario.jsp">
                                                <button type="submit" class="btn btn-warning">Update</button>
                                                <input type="hidden" name="action" value="update">
                                                <input type="hidden" name="id" value="<%=student.getId()%>">
                                                <input type="hidden" name="name" value="<%=student.getName()%>">
                                                <input type="hidden" name="surname" value="<%=student.getSurname()%>">
                                                <input type="hidden" name="age" value="<%=student.getAge()%>">
                                                <input type="hidden" name="address" value="<%=student.getAddress()%>">
                                                <input type="hidden" name="course" value="<%=student.getCourse()%>">
                                                <input type="hidden" name="familyDates" value="<%=student.getFamilyDates()%>">
                                                <input type="hidden" name="note" value="<%=student.getNote()%>">
                                            </form>
                                        </td>
                                        <td>
                                            <form method="get" action="index.jsp">
                                                <button type="submit" class="btn btn-danger ">Delete</button>
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="<% out.print(student.getId());%>">
                                            </form>
                                        </td>
                                    </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </section>
                </div>

                <div class="row">
                    <section id="insertButton" class="col-12 px-0 mb-4">
                        <form method="get" action="formulario.jsp">
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-success py-2">Insert</button>
                                <input type="hidden" name="action" value="insert">
                            </div>    
                        </form>
                    </section>
                </div>
            </main>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>

</html>