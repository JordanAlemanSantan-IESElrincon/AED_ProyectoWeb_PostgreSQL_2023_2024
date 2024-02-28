<%-- 
    Document   : formulario
    Created on : 15 ene 2024, 9:00:17
    Author     : Jordan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Controlar sesión al index.jsp --%>
<% 
    session = request.getSession();

    // Obtener el atributo "username" de la sesión
    String username = (session != null) ? (String) session.getAttribute("username") : null;

    // Verificar si el usuario es administrador
    boolean isAdmin = "admin".equals(username);

    System.out.println(username);

    // Si no hay sesión activa o el usuario no es administrador, redirigir a login.jsp
    if (!isAdmin) {
        assert session != null;
        session.invalidate();
        response.sendRedirect("login.jsp?redirectForWrongAccess=true");
    }
%>

<%
    String action = request.getParameter("action");

    String id = "";
    String name = ""; 
    String surname = ""; 
    String age = ""; 
    String address = ""; 
    String course = ""; 
    String familyDates = ""; 
    String note = ""; 

    if (action != null && action.equals("update")) {
        id = request.getParameter("id");
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        age = request.getParameter("age");
        address = request.getParameter("address");
        course = request.getParameter("course");
        familyDates = request.getParameter("familyDates");
        note = request.getParameter("note");
        note = note.substring(0, note.indexOf('.'));
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
    <div id="main-page" class="container-fluid">
        <header class="container-fluid ">
            <div class="row">
                <div class="col-12 text-center m-4">
                    <h1>
                        <%
                            if (action != null && action.equals("insert")) {
                                %>Insert student<%
                            }

                            if (action != null && action.equals("update")) {
                                %>Update student<%
                            }
                        %>
                    </h1>
                </div>
            </div>
        </header>

        <main class="container-fluid">
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">
                    <form method="get" action="index.jsp">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" value="<%=name%>">
                        </div>
                        <div class="mb-3">
                            <label for="surname" class="form-label">Surname</label>
                            <input type="text" class="form-control" id="surname" name="surname" value="<%=surname%>">
                        </div>
                        <div class="mb-3">
                            <label for="age" class="form-label">Age</label>
                            <input type="number" class="form-control" id="age" name="age" value="<%=age%>">
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" id="address" name="address" value="<%=address%>">
                        </div>
                        <div class="mb-3">
                            <label for="course" class="form-label">Course</label>
                            <input type="text" class="form-control" id="course" name="course" value="<%=course%>">
                        </div>
                        <div class="mb-3">
                            <label for="familyDates" class="form-label">Family Dates</label>
                            <textarea class="form-control" name="familyDates" id="familyDates" cols="30" rows="5"><%=familyDates%></textarea>
                        </div>
                         <div class="mb-3">
                            <label for="note" class="form-label">Note</label>
                            <select class="form-select" aria-label="note" id="note" name="note">
                                <%
                                    for (int i = 0; i <= 10; i++) {
                                        // Compara el valor de 'note' con el valor actual del bucle y selecciona si son iguales
                                        String selected = note.equals(String.valueOf(i)) ? "selected" : "";
                                %>
                                <option value="<%=i%>" <%=selected%>><%=i%></option>
                                <%
                                    }
                                %>
                            </select>                        
                        </div>

                        <div class="d-grid gap-2 ">
                        <%
                            if (action != null && action.equals("insert")) {
                                %>
                                    <input type="hidden" name="action" value="insert">
                                    <button type="submit" class="btn btn-success py-2">Insert</button>
                                <%
                            }

                            if (action != null && action.equals("update")) {
                                %>
                                    <input type="hidden" name="action" value="update">
                                    <button type="submit" class="btn btn-warning py-2">Update</button>
                                    <input type="hidden" name="id" value="<%=id%>">
                                <%
                            }
                        %>
                        </div>
                    </form>
                </div>
                <div class="col-2"></div>
            </div>
        </main>
    </div>
</body>
</html>