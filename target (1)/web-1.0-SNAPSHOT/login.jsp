<%-- 
    Document   : index
    Created on : 22 ene 2024, 08:24:43
    Author     : Jordan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    boolean isRedirectFromIndex = "true".equals(request.getParameter("redirectFromIndex"));
    boolean isRedirectForWrongAccess = "true".equals(request.getParameter("redirectForWrongAccess"));
    boolean signOutClicked = "true".equals(request.getParameter("signOut"));

    if (signOutClicked) 
        session.invalidate();
%>
       
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login.css">
</head>

<body>
    <%-- Alertas en el Login --%>

    <%-- Aleta si ha sido redirigido desde el Index al no haber introducido bien las credenciales --%>
    <% if (isRedirectFromIndex) { %>

    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Error!</strong> Credenciales inválidas.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <%  } %>

    <%-- Aleta si ha sido redirigido al tratar de acceder a una dirección web sin haber hecho login previamente --%>
    <% if (isRedirectForWrongAccess) { %>

    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <strong>Error!</strong> No ha iniciado ninguna sesión.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <%  } %>

    <%-- Alerta si se cerró la sesión correctamente --%>
    <%    if (signOutClicked) {
    %>
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        <strong>Successfull!</strong> Ha cerrado sesión.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%  } %> 
    

    <div id="main-page" class="container main-page mt-5">
        <!-- Section: Design Block -->
        <section class=" text-center text-lg-start mt-5">
            <div class="card mb-3 main-login">
                <div class="row g-0 d-flex align-items-center">
                    <div class="col-lg-4 d-none d-lg-flex">
                        <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg"
                            alt="Trendy Pants and Shoes" class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5" />
                    </div>
                    <div class="col-lg-8">
                        <div class="card-body py-5 px-md-5">
                            <h1 class="text-center mb-5">Login</h1>

                            <form method="get" action="index.jsp">
                                <!-- User input -->
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="user">User name</label>
                                    <input type="text" id="user" name="user" class="form-control" required />
                                </div>

                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="password">Password</label>
                                    <input type="password" id="password" name="password" class="form-control" required />
                                </div>

                                <!-- Submit button -->
                                <div class="d-grid gap-2 button-sign-in">
                                    <input type="hidden" name="action" value="login">
                                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Section: Design Block -->
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>