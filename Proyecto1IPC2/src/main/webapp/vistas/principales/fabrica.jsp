<%-- 
    Document   : fabrica
    Created on : 25/08/2021, 06:30:42 PM
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
                            <a class="nav-link active text-white"  href="#">Inicio</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link text-white" aria-current="page" href="#">Ensambles</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="DiseñoServlet?accion=listar">Diseños</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="PiezaAlmacenadaServlet?accion=listar" target="frameInterno">Materia prima</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="MuebleServlet?accion=listar" target="frameInterno">Muebles</a>
                        </li>
                        <li class="btn btn-info">
                            <a class="nav-link active text-white" href="PiezaServlet?accion=listar" target="frameInterno">Piezas</a>
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
            <iframe name="frameInterno" style="height: 100%; width: 100%;">

            </iframe>
        </div>
    </body>
</html>
