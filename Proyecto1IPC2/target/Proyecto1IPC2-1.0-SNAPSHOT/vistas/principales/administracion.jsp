<%-- 
    Document   : administracion
    Created on : 25/08/2021, 07:28:34 PM
    Author     : OrdSon
--%>

<%@page import="Modelos.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="margin: 0; padding: 0; height: 85vh; width: 100vw;">
        <%Empleado empleadoActivo = (Empleado) request.getSession().getAttribute("empleadoActivo");%>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <div class="container-fluid">
                <!-- <a class="navbar-brand" href="#">Navbar</a> -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="btn btn-info">
                            <a class="nav-link active text-white"  href="vistas/welcome/welcomeAdministracion.jsp" target="frameInterno">Inicio</a>
                        </li>
                        
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="ReporteServlet?accion=listar" target="frameInterno">Reporte de ventas</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="ReporteServlet?accion=listarGanancias" target="frameInterno">Reporte de ganancias</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="ClienteServlet?accion=listar" target="frameInterno">Agregar cliente</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="ClienteServlet?accion=listar" target="frameInterno">Agregar cliente</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="ClienteServlet?accion=listar" target="frameInterno">Agregar cliente</a>
                        </li>
                    </ul>
                    <div class="nav-item dropdown text-center">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Sesión
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><button type="button" class="btn btn-lg btn-light" disabled><%=empleadoActivo.getNombre()%></button></li>
                            <li><hr class="dropdown-divider"></li>
                            <!--//TOCA AQUI, HAY QUE HACER REFERENCIA AL SERVLET PARA QUITAR EL USUARIO ACTIVO Y QUE SEA NULL!!!
                            -->   <li><a class="dropdown-item text-center" href="loginServlet?accion=salir">Salir</a></li>
                        </ul>
                    </div> 
                    <div style="width: 75px;">

                    </div>

                    <!--
                        CREA BARRA DE BUSQUEDA
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form> -->
                </div>
            </div>
        </nav>
        <div class="m-4" style="height: 100%; width: 97%;">
            <iframe src="vistas/welcome/welcomeAdministracion.jsp" name="frameInterno" style="height: 100%; width: 100%;">
                
            </iframe>
        </div>
    </body>
</html>

