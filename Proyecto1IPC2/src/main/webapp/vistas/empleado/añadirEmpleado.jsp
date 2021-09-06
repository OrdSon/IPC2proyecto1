<%-- 
    Document   : añadirEmpleado
    Created on : 17/08/2021, 17:41:00 PM
    Author     : ordson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria clientes</title>
    </head>
    <body>
        <div class="d-flex justify-content-center">
            <h1>Añadir Empleado</h1>
        </div>
        <div class="d-flex justify-content-center">
            <div class="container mt-4 col-lg-4 text-center">
                <div class="card">
                    <form>
                        Nombre: <br>
                        <input type ="text" name = "txtNombre" class="form-control"><br>
                        <div>
                            <label for="area">Area</label>

                            <select name="txtarea" id="area" class="form-control">
                                <option value="Fabrica">Fabrica</option>
                                <option value="Ventas">Financiero</option>
                                <option value="Administracion">Administracion</option>
                            </select> 
                        </div>
                        Contraseña: <br>
                        <input type ="password" name = "txtContraseña" class="form-control"><br>
                        DPI: <br>
                        <input type ="text" name = "txtDpi" class="form-control"><br>
                        Telefono: <br>
                        <input type ="text" name = "txtTelefono" class="form-control"><br>
                        Direccion: <br>
                        <input type ="text" name = "txtDireccion" class="form-control"><br>
                        Fecha de nacimiento: <br>
                        <input type ="date" name = "txtBirth" class="form-control"><br>
                        Salario: <br>
                        <input type ="text" name = "txtSalario" class="form-control"><br>
                        Fecha de contratacion: <br>
                        <input type ="date" name = "txtDebut" class="form-control"><br>

                        <input type = "submit" name = "accion" value = "añadir" class="btn btn-success"><br>
                        <br>
                        <a href="EmpleadoServlet?accion=listar" class="btn btn-info">Volver</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
