<%-- 
    Document   : reporteDevolucion
    Created on : 5/09/2021, 01:32:56 PM
    Author     : OrdSon
--%>


<%@page import="Modelos.DetalleDevolucion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelos.Cliente"%>
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
        <h1 class="text-center"> Lista de devoluciones</h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->


        <div class="d-flex justify-content-center">


            <%
                Cliente clienteTemp = (Cliente) request.getSession().getAttribute("clienteDevolucionActivo");
                String nitCliente = "";
                String nombreCliente = "";

                if (clienteTemp != null) {
                    nombreCliente = clienteTemp.getNombre();
                    nitCliente = clienteTemp.getNit() + "";
                    request.getSession().setAttribute("codigoClienteDevolucionActivo", clienteTemp.getCodigo());
                }else{
                    System.out.println("AQUI NO HAY CLIENTE");
                }

            %>
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="ReporteServlet">
                        <div>
                            <label>Buscar cliente</label>
                            <div >
                                <label>NIT:</label> 
                                <input type ="text" name = "txtNit" class="form-control" value="<%=nitCliente%>">      
                                <input type = "submit" name = "accion" value = "Buscar" class="btn btn-outline-primary"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombreCliente" class="form-control" value="<%=nombreCliente%>">
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
                            <input type = "submit" name = "accion" value = "Ver devoluciones por fecha" class="list-group-item list-group-item-primary"><br>
                            <input type = "submit" name = "accion" value = "Ver devoluciones por cliente" class="list-group-item list-group-item-secondary"><br>
                            <input type = "submit" name = "accion" value = "Ver todas las devoluciones" class="list-group-item list-group-item-primary"><br>
                            <a href="ReporteServlet?accion=exportarDevolucion" class="list-group-item list-group-item-primary">Exportar tabla</a><br>
                        </div>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6" style="position: relative; height: 500px;  overflow: auto; display: block;">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nit cliente</th>
                            <th>Nombre</th>
                            <th>Codigo de producto</th>
                            <th>Modelo</th>
                            <th>Dinero devuelto</th>
                            <th>fecha</th>
                        </tr>
                    </thead>
                    <%
                        if (request.getSession().getAttribute("listaDevoluciones") != null) {
                            ArrayList<DetalleDevolucion> devoluciones = (ArrayList<DetalleDevolucion>) request.getSession().getAttribute("listaDevoluciones");
                            for (int i = 0; i < devoluciones.size(); i++) {
                                DetalleDevolucion temporal = devoluciones.get(i);%>
                    <tbody>
                        <tr>
                            <td><%=temporal.getNit()%></td>
                            <td><%=temporal.getNombre()%></td>
                            <td><%=temporal.getProducto()%></td>
                            <td><%=temporal.getModelo()%></td>
                            <td><%=temporal.getTotal()%></td>
                            <td><%=temporal.getFecha()%></td>
                        </tr>
                    </tbody>      


                    <%}
                        }
                    %>

                </table>
            </div>  
        </div>
    </div>

</body>
</html>
