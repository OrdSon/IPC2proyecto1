<%-- 
    Document   : editarEmpleado
    Created on : 15/08/2021, 07:00:45 PM
    Author     : ordson
--%>


<%@page import="Modelos.Empleado"%>
<%@page import="DAO.EmpleadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar empleado</h1>
        <%

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            int codigo = Integer.parseInt((String) request.getSession().getAttribute("codigoEmpleado"));
            Empleado empleado = empleadoDAO.listarCodigo(codigo);
        %>
        <div>
            <form>
                Nombre: <br>
                <input type ="text" name = "txtNombre" value="<%=empleado.getNombre() %>"><br>
                <div>
                    <label for="area">Area</label>

                    <select name="txtarea" id="area">
                        <option value="Fabrica">Fabrica</option>
                        <option value="Financiero">Financiero</option>
                        <option value="Administracion">Administracion</option>
                    </select> 
                </div>
                Contraseña: <br>
                <input type ="password" name = "txtContraseña" value="<%=empleado.getContraseña()%>"><br>
                DPI: <br>
                <input type ="text" name = "txtDpi" value="<%=empleado.getDpi()%>"><br>
                Telefono: <br>
                <input type ="text" name = "txtTelefono" value="<%=empleado.getTelefono()%>"><br>
                Direccion: <br>
                <input type ="text" name = "txtDireccion" value="<%=empleado.getDireccion()%>"><br>
                Salario: <br>
                <input type ="text" name = "txtSalario"value="<%=empleado.getSalario()%>"><br>
                Fecha de nacimiento: <br>
                <input type ="date" name = "txtBirth" value="<%=empleado.getFecha_nacimiento()%>"><br>
                Fecha de contratacion: <br>
                
                <input type ="date" name = "txtDebut" value="<%=empleado.getFecha_contratacion()%>"><br>

                <input type = "submit" name = "accion" value = "actualizar"><br>
                <a href="EmpleadoServlet?accion=listar">Volver</a>
            </form>
        </div>
    </body>
</html>
