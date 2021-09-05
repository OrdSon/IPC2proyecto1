<%-- 
    Document   : listaDiseños
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

<%@page import="Modelos.Mueble"%>
<%@page import="DAO.MuebleEnsambladoDAO"%>
<%@page import="Modelos.MuebleEnsamblado"%>
<%@page import="Modelos.Cliente"%>
<%@page import="Modelos.DiseñoListo"%>
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
        <%!MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();%>
        <h1 class="text-center"> Nueva venta</h1>
        <div style="height: 50px"></div>

        <!--<a href="DiseñoServlet?accion=nuevo ">Añadir diseño</a> -->
        <div class="d-flex justify-content-center">
            <%
                Cliente clienteTemp = (Cliente) request.getSession().getAttribute("clienteVentaActiva");
                Mueble muebleTemp = (Mueble) request.getSession().getAttribute("muebleVentaActivo");

                String codigoProducto = "";
                String nombreProducto = "";
                String nitCliente = "";

                String nombreCliente = "";

                if (clienteTemp != null) {
                    nombreCliente = clienteTemp.getNombre();
                    nitCliente = clienteTemp.getNit() + "";
                    request.getSession().setAttribute("codigoClienteActivo", clienteTemp.getCodigo());
                    System.out.println("CODIGO DE CLIENTE!!" + clienteTemp.getCodigo());

                }
                if (muebleTemp != null) {
                    nombreProducto = muebleTemp.getNombre();
                    codigoProducto = muebleTemp.getModelo() + "";
                }
            %>
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="VentaServlet">
                        <div>
                            <label>Buscar cliente</label>
                            <div >
                                <label>NIT:</label> 
                                <input type ="text" name = "txtNIT" class="form-control" value="<%=nitCliente%>">      
                                <input type = "submit" name = "accion" value = "Buscar" class="btn btn-outline-primary"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombreCliente" class="form-control" value="<%=nombreCliente%>">
                            </div>
                        </div>
                        <div>
                            <label>Buscar producto</label>
                            <div >
                                <label>Modelo:</label> 
                                <input type ="text" name = "txtCodigoProducto" class="form-control" value="<%=codigoProducto%>">      
                                <input type = "submit" name = "accion" value = "Buscar producto" class="btn btn-outline-primary"><br>

                            </div>
                            <div >

                                <label>Nombre:</label> 
                                <input type ="text" name = "txtNombreProducto" class="form-control" value="<%=nombreProducto%>">
                                <input type = "submit" name = "accion" value = "Buscar por nombre" class="btn btn-outline-primary"><br>

                            </div>
                            <div>
                                <label>Cantidad:</label> 
                                <input type = "number" min="1" name = "txtCantidad" class="form-control"><br>  
                            </div>
                        </div>

                        <input type = "submit" name = "accion" value = "Agregar producto" class="btn btn-success"><br>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6">

                <%
                    if (request.getSession().getAttribute("listaProductos") == null) {
                %>
                <label class="h3">Productos disponibles</label>
                <%} else { %>
                <label class="h3">Productos en esta venta</label>
                <%}%>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th >Codigo de producto</th>
                            <th>Nombre</th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                        </tr>
                    </thead> 
                    <%
                         if (request.getSession().getAttribute("listaProductos") != null) {

                            ArrayList<MuebleEnsamblado> muebles = (ArrayList<MuebleEnsamblado>) request.getSession().getAttribute("listaProductos");
                            for (int i = 0; i < muebles.size(); i++) {
                                MuebleEnsamblado muebleEnsamblado = muebles.get(i);

                    %>
                    <tbody>
                        <tr>
                            <td><%=muebleEnsamblado.getCodigo()%></td>
                            <td><%=muebleEnsamblado.getMuebleModelo()%></td>
                            <td><%=muebleEnsamblado.getCantidad()%></td>
                            <td><%=muebleEnsamblado.getPrecio()%></td>
                        </tr>

                    </tbody>-

                    <%}%>
                    <a href="VentaServlet?accion=Ingresar venta" class="btn btn-primary">Ingresar venta</a>
                    <%} else {

                        ArrayList<MuebleEnsamblado> temp = muebleEnsambladoDAO.listarDisponibles();
                        for (int i = 0; i < temp.size(); i++) {
                            MuebleEnsamblado muebleEnsamblado = temp.get(i);

                    %>
                    <tbody>
                        <tr>
                            <td><%=muebleEnsamblado.getCodigo()%></td>
                            <td><%=muebleEnsamblado.getMuebleModelo()%></td>
                            <td><%=muebleEnsamblado.getCantidad()%></td>
                            <td><%=muebleEnsamblado.getPrecio()%></td>
                        </tr>

                    </tbody>
                        <%}%>
                    
                    <%}%>
                </table>
                <div>

                </div>
            </div>
        </div>
    </body>
</html>
