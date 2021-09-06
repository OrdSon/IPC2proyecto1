<%-- 
    Document   : listaEnsambles
    Created on : 2/09/2021, 01:06:00 PM
    Author     : OrdSon
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelos.MuebleEnsamblado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.MuebleEnsambladoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de muebles ensamblados</h1>
        <div class="d-flex justify-content-center">
            <div class="col-sm-6">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Modelo</th>
                            <th>Precio</th>
                            <th>Costo</th>
                            <th>Fecha</th>

                        </tr>
                    </thead>
                    <%
                        int orden = 0;
                        if (request.getSession().getAttribute("ordenEnsambles") != null) {
                            orden = (int) request.getSession().getAttribute("ordenEnsambles");
                        }
                        MuebleEnsambladoDAO muebleDAO = new MuebleEnsambladoDAO();
                        ArrayList<MuebleEnsamblado> muebles = muebleDAO.listar(orden);
                        Iterator<MuebleEnsamblado> iterator = muebles.iterator();
                        MuebleEnsamblado mueble = null;
                        while (iterator.hasNext()) {
                            mueble = iterator.next();

                    %>
                    <tbody>
                        <tr>

                            <td><%=mueble.getCodigo()%></td>
                            <td><%=mueble.getMuebleNombre()%></td>
                            <td><%=mueble.getMuebleModelo()%></td>
                            <td><%=mueble.getPrecio()%></td>
                            <td><%=mueble.getCosto()%></td>
                            <%
                                if (mueble.getFecha() != null) {
                            %>
                            <td><%=mueble.getFecha()%></td>
                            <%} else {%>
                            <td> </td>
                            <%}
                            }%>
                        </tr>
                    </tbody>
                    <tfoot><label>Ordenar:</label><a href="MuebleEnsambladoServlet?accion=desc" class="btn btn-info">Descendiente</a>
                    <a href="MuebleEnsambladoServlet?accion=asc" class="btn btn-info">Ascendiente</a>  </tfoot>
                </table>
            </div>
        </div>
    </body>
</html>
