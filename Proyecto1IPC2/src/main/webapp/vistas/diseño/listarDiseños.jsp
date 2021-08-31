<%-- 
    Document   : listaDiseños
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

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

        <h1 class="text-center">Materia prima</h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->
        <div class="d-flex justify-content-center">
            <%Pieza piezaTemp = (Pieza) request.getSession().getAttribute("piezaDiseñoActiva");
                Mueble muebleTemp = (Mueble) request.getSession().getAttribute("muebleDiseñoActivo");
                String codigoPieza = "";
                String tipoPieza = "";
                String codigoMueble = "";
                String tipoMueble = "";
                if (piezaTemp != null) {
                    tipoPieza = piezaTemp.getNombre();
                    codigoPieza = piezaTemp.getCodigo() + "";
                }
                if (muebleTemp != null) {
                    tipoMueble = muebleTemp.getNombre();
                    codigoMueble = muebleTemp.getModelo() + "";
                }
            %>
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="DiseñoServlet">
                        <div>
                            <label>Buscar mueble</label>
                            <div >
                                <label>Modelo</label> 
                                <input type ="text" name = "txtModelo" class="form-control" value="<%=codigoMueble%>">      
                                <input type = "submit" name = "accion" value = "BuscarModeloMueble"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombreMueble" class="form-control" value="<%=tipoMueble%>">
                                <input type = "submit" name = "accion" value = "BuscarNombreMueble"><br>

                            </div>
                        </div>
                        <div>
                            <label>Agregar pieza al diseño</label>
                            <div >
                                <label>Codigo</label> 
                                <input type ="text" name = "txtCodigoPieza" class="form-control" value="<%=codigoPieza%>">      
                                <input type = "submit" name = "accion" value = "BuscarCodigoPieza"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombrePieza" class="form-control" value="<%=tipoPieza%>">
                                <input type = "submit" name = "accion" value = "BuscarNombrePieza"><br>

                            </div>
                            <div>
                                <label>Cantidad:</label> 
                                <input type = "number" min="1" name = "txtCantidad" class="form-control"><br>  
                            </div>
                        </div>

                        <input type = "submit" name = "accion" value = "añadir"><br>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6">

                <label>Piezas en el diseño</label>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th >Codigo</th>
                            <th>Nombre</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead> 
                    <%
                        if (request.getSession().getAttribute("modeloDiseño") != null) {

                            String modelo = (String) request.getSession().getAttribute("modeloDiseño");
                            DiseñoDAO diseñoDAO = new DiseñoDAO();
                            ArrayList<DiseñoListo> diseños = diseñoDAO.listarPorMueble(modelo);
                            Iterator<DiseñoListo> iterator = diseños.iterator();
                            DiseñoListo diseño = null;
                            while (iterator.hasNext()) {
                                diseño = iterator.next();

                    %>
                    <tbody>
                        <tr>
                            <td><%=diseño.getCodigo()%></td>
                            <td><%=diseño.getNombre()%></td>
                            <td><%=diseño.getCantidad()%></td>
                        </tr>
                        <%}

                            }%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
