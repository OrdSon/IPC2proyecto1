<%-- 
    Document   : añadirEmpleado
    Created on : 17/08/2021, 17:41:00 PM
    Author     : ordson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria clientes</title>
    </head>
    <body>
        <h1>Añadir Empleado</h1>
        <form>
            Nombre: <br>
            <input type ="text" name = "txtNombre"><br>
            <div>
                <label for="area">Area</label>

                <select name="txtarea" id="area">
                    <option value="Fabrica">Fabrica</option>
                    <option value="Financiero">Financiero</option>
                    <option value="Administracion">Administracion</option>
                </select> 
            </div>
            Contraseña: <br>
            <input type ="password" name = "txtContraseña"><br>
            DPI: <br>
            <input type ="text" name = "txtDpi"><br>
            Telefono: <br>
            <input type ="text" name = "txtTelefono"><br>
            Direccion: <br>
            <input type ="text" name = "txtDireccion"><br>
            Fecha de nacimiento: <br>
            <input type ="date" name = "txtBirth"><br>
            Salario: <br>
            <input type ="text" name = "txtSalario"><br>
            Fecha de contratacion: <br>
            <input type ="date" name = "txtDebut"><br>

            <input type = "submit" name = "accion" value = "añadir"><br>
            <a href="ClienteServlet?accion=listar">Volver</a>
        </form>
    </body>
</html>
