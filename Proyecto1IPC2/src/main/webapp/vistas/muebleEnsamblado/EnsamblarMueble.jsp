<%-- 
    Document   : EnsamblarMuebles
    Created on : 14/08/2021, 10:05:57 PM
    Author     : ordson
--%>

<%@page import="Modelos.Coincidencia"%>
<%@page import="DAO.MuebleEnsambladoDAO"%>
<%@page import="Modelos.Mueble"%>
<%@page import="Modelos.DiseñoListo"%>
<%@page import="Modelos.Pieza"%>
<%@page import="DAO.PiezaDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DiseñoDAO"%>
<%@page import="Modelos.Diseño"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Materia prima</title>
    </head>
    <body>

        <h1 class="text-center">Ensamblar mueble</h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->
        <div class="d-flex justify-content-center">
            <%  Mueble muebleTemp = (Mueble) request.getSession().getAttribute("modeloEnsambleActivo");
                String codigoMueble = "";
                String tipoMueble = "";

                if (muebleTemp != null) {
                    tipoMueble = muebleTemp.getNombre();
                    codigoMueble = muebleTemp.getModelo() + "";
                } else {
                    System.out.println("modelonull");
                }
            %>
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="MuebleEnsambladoServlet">
                        <div>
                            <label>Buscar mueble</label>
                            <div >
                                <label>Modelo</label> 
                                <input type ="text" name = "txtModelo" class="form-control" value="<%=codigoMueble%>">      
                                <input type = "submit" name = "accion" value = "Buscar modelo" class="btn btn-outline-primary"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombreMueble" class="form-control" value="<%=tipoMueble%>">
                                <input type = "submit" name = "accion" value = "Buscar por nombre" class="btn btn-outline-primary"><br>

                            </div>
                            <div >

                                <label>Fecha:</label> 
                                <input type ="date" name = "fecha" class="form-control">
                            </div>
                        </div>

                        <input type = "submit" name = "accion" value = "añadir" class="btn btn-success"><br>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6">

                <label>Piezas en el diseño</label>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th >Codigo de pieza</th>
                            <th>Nombre</th>
                            <th>Costo</th>
                            <th>Disponibles</th>
                            <th>Necesarias</th>
                            <th>Estado</th>
                        </tr>
                    </thead> 
                    <%
                        if (request.getSession().getAttribute("modeloEnsamble") != null) {

                            String modelo = (String) request.getSession().getAttribute("modeloEnsamble");
                            MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();
                            ArrayList<Coincidencia> diseños = muebleEnsambladoDAO.listarPorMueble(modelo);
                            Iterator<Coincidencia> iterator = diseños.iterator();
                            Coincidencia coincidencia = null;
                            while (iterator.hasNext()) {
                                coincidencia = iterator.next();

                    %>
                    <tbody>
                        <tr>
                            <td><%=coincidencia.getPieza()%></td>
                            <td><%=coincidencia.getTipo()%></td>
                            <td><%=coincidencia.getCosto()%></td>
                            <td><%=coincidencia.getDisponibles()%></td>
                            <td><%=coincidencia.getNecesarias()%></td>
                            <td>
                                <%
                                    if (coincidencia.getDisponibles() <= (coincidencia.getNecesarias() * 2)) {%>
                                <input type="submit" value="Por agotarse" disabled="disabled" class="btn btn-warning"/>
                                <% } else if (coincidencia.getDisponibles() == (coincidencia.getNecesarias())) {%>
                                <input type="submit" value="Ultimas piezas" disabled="disabled" class="btn btn-warning"/>
                                <%} else if (coincidencia.getDisponibles() < (coincidencia.getNecesarias())) {%>
                                <input type="submit" value="Insuficientes" disabled="disabled" class="btn btn-danger"/>
                                <%} else { %>
                                <input type="submit" value="BIEN" disabled="disabled" class="btn btn-success"/>
                                <%}%>
                            </td>
                        </tr>
                        <%}

                            }%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>