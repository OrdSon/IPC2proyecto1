<%-- 
    Document   : listarVentasRealizadas
    Created on : 2/09/2021, 07:10:11 PM
    Author     : OrdSon
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="Modelos.MuebleVendido"%>
<%@page import="Modelos.Empleado"%>
<%@page import="Modelos.DetalleVenta"%>
<%@page import="Modelos.Cliente"%>
<%@page import="Modelos.VentaRealizada"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.VentaDAO"%>
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
        <h1 class="text-center"> Reporte de ganancias </h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->

        <div class="d-flex justify-content-center">


            <%
                Empleado empleado = (Empleado) request.getSession().getAttribute("empleadoReporteActivo");
                String nombreEmpleado = "";
                String dpiEmpleado = "";
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                double contador = 0;
                if (empleado != null) {
                    nombreEmpleado = empleado.getNombre();
                    dpiEmpleado = empleado.getDpi() + "";
                }

            %>
            <div class="card col-sm-2">
                <div class="card-body">
                    <form action="ReporteServlet">
                        <div>
                            <label>Buscar empleado</label>
                            <div >
                                <label>DPI:</label> 
                                <input type ="text" name = "txtDPI" class="form-control" value="<%=dpiEmpleado%>">      
                                <input type = "submit" name = "accion" value = "Buscar" class="btn btn-outline-primary"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombreEmpleado" class="form-control" value="<%=nombreEmpleado%>">

                            </div>
                        </div>
                        <div>
                            <label class="h2">Fecha:</label>
                            <div >
                                <label>Desde:</label> 
                                <input type ="date" name = "inicial" class="form-control">
                                <label>Hasta:</label>
                                <input type ="date" name = "final" class="form-control">      
                            </div>

                        </div>
                        <div class="list-group d-flex justify-content-center">

                            <input type = "submit" name = "accion" value = "Ver ganancias por fecha" class="list-group-item list-group-item-primary"><br>

                            <input type = "submit" name = "accion" value = "Ver ganancias por empleado" class="list-group-item list-group-item-secondary"><br>

                            <input type = "submit" name = "accion" value = "Ver mejor empleado" class="list-group-item list-group-item-primary"><br>
                            <a href="ReporteServlet?accion=exportarGanancias" class="list-group-item list-group-item-info">Exportar tabla</a>
                        </div>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-8" style="position: relative; height: 500px;  overflow: auto; display: block;">

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>DPI Empleado</th>
                            <th>Nombre empleado</th>
                            <th>Codigo producto</th>
                            <th>Nombre producto</th>
                            <th>Precio</th>
                            <th>Costo</th>
                            <th>Ganancia</th>
                            <th>Fecha</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  ArrayList<MuebleVendido> muebles = (ArrayList<MuebleVendido>) request.getSession().getAttribute("reporteGanancias");
                            for (int i = 0; i < muebles.size(); i++) {
                                MuebleVendido temporal = muebles.get(i);
                                contador += temporal.getGanancia();
                        %>
                        <tr>
                            <td><%=temporal.getEmpleadoDPI()%></td>
                            <td><%=temporal.getEmpleadoNombre()%></td>
                            <td><%=temporal.getProductoCodigo()%></td>
                            <td><%=temporal.getNombre()%></td>
                            <td><%=temporal.getPrecio()%></td>
                            <td><%=temporal.getCosto()%></td>
                            <td><%=temporal.getGanancia()%></td>
                            <td><%if (temporal.getFecha() != null) {%>
                                <%=temporal.getFecha()%>
                                <% }
                                %> </td>
                        </tr>             
                        <%}
                        %>

                    </tbody>
                </table>


            </div>  

        </div>
        <div class="d-flex justify-content-center">
            <label class="h2 text-center">Ganancia total: Q.<%=numberFormat.format(contador)%></label>
        </div>
    </body>
</html>
