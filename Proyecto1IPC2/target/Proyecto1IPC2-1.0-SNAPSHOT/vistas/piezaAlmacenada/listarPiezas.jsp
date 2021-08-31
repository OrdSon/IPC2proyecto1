<%-- 
    Document   : listaPiezaAlmacenadas
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

<%@page import="Modelos.piezaComprada"%>
<%@page import="Modelos.Pieza"%>
<%@page import="DAO.PiezaDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.PiezaAlmacenadaDAO"%>
<%@page import="Modelos.PiezaAlmacenada"%>
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

        <!--<a href="PiezaAlmacenadaServlet?accion=nuevo ">Añadir pieza</a> -->
        <div class="d-flex justify-content-center">
            <%Pieza piezaTemp = (Pieza) request.getSession().getAttribute("piezaActiva");
                String codigo = "";
                String tipo = "";
                if (piezaTemp != null) {
                    tipo = piezaTemp.getNombre();
                    codigo = piezaTemp.getCodigo() + "";
                }
            %>
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="CompraServlet">
                        <div >
                            <label>Codigo</label> 
                            <input type ="text" name = "txtModelo" class="form-control" value="<%=codigo%>">      
                            <input type = "submit" name = "accion" value = "Buscar codigo"><br>

                        </div>
                        <div >

                            <label>Nombre:</label> 
                            <input type ="text" name = "txtNombre" class="form-control" value="<%=tipo%>">
                            <input type = "submit" name = "accion" value = "Buscar nombre"><br>

                        </div>
                        <div>
                            <label>Precio:</label> 
                            <input type ="number" min="0" step="0.01" name = "txtPrecio" class="form-control"><br>  
                        </div>
                        <div>
                            <label>Cantidad:</label> 
                            <input type = "number" min="1" name = "txtCantidad" class="form-control"><br>  
                        </div>
                        <input type = "submit" name = "accion" value = "añadir"><br>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th >Tipo</th>
                            <th>Costo</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead> 
                    <%
                        PiezaAlmacenadaDAO piezaDAO = new PiezaAlmacenadaDAO();
                        ArrayList<piezaComprada> piezas = piezaDAO.listarCompradas();
                        Iterator<piezaComprada> iterator = piezas.iterator();
                        piezaComprada pieza = null;
                        while (iterator.hasNext()) {
                            pieza = iterator.next();

                    %>
                    <tbody>
                        <tr>
                            <td><%=pieza.getTipo()%></td>
                            <td><%=pieza.getCosto() %></td>
                            <td><%=pieza.getCantidad() %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
