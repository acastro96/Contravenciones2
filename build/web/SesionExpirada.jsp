<%@page import="com.civitrans.model.LoginUser"%>
<%@page import="com.civitrans.singleton.AuthSingleton"%>
<%@page import="com.civitrans.utility.Log_Handler"%>
<%@page import="com.civitrans.singleton.SessionSingleton"%>
<%@page import="com.civitrans.jsf.bean.BeanLogin"%>
<!DOCTYPE html>
<!--
Civitrans
La Cívica Impresores S.A.S
Copyright 2016.
-->
<%
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("X-FRAME-OPTIONS", "DENY");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Server", "La Civica Impresores SAS");
    response.setHeader("X-Powered-By", "La Civica Impresores SAS");
    int id_usu = 0;
    try {
        BeanLogin bean = (BeanLogin) request.getSession().getAttribute("loginBean");
        if (bean != null) {
            if (bean.getID_Usuario() != null && !bean.getID_Usuario().isEmpty()) {
                AuthSingleton.getInstancia().reesstablecerFuncionario(Integer.parseInt(bean.getID_Usuario()));
                LoginUser loginObj = SessionSingleton.getInstancia().getUsuarioSesion(Integer.parseInt(bean.getID_Usuario()));
                if (loginObj != null) {
                    SessionSingleton.getInstancia().invalidarSesion(loginObj.getId_usuario());
                }
                id_usu = Integer.parseInt(bean.getID_Usuario());
            }
        }
    } catch (NumberFormatException e) {
        System.err.println("Error al cerrar sesión. Conversión Numérica fallida.");
    } catch (Exception e) {
        Log_Handler.registrarEvento("Error en proceso de Sesión Expirada...", e, Log_Handler.ERROR, getClass(), id_usu);
    } finally {

    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Sesión Expirada o Inválida</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>    
    <body style="background-color: #efefef;font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif">

        <div style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;margin: 0px;">
            <br><br><br>
            <table border="0px" style="margin: 0 auto">
                <tr>
                    <td valign="middle">
                <center>
                    <img align="middle" onclick="redirect()" style="margin: 0 auto;width: 80px;opacity: 0.5" src="/Civitrans/resources/images/loading.gif"/>
                </center>
                </td>
                </tr>
                <tr>
                    <td valign="middle" style="text-align: center;">
                        <h3 style="margin: 4px;"><span style="color: darkblue">Sesión Expirada o Inválida</span></h3>
                        <h5 onclick="redirect()" style="width: 600px;margin: 0px;">Su sesión ha expirado o ha quedado inválida, se le regresará a la página de inicio de sesión en <span id="contador"></span> segundos...</h5>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <br><br><br><br><br>
                        <p style="text-align: center;font-size: 12px">La Civica Impresores S.A.S</p>
                    </td>
                </tr>
            </table>
        </div>
        <script>
            /*setTimeout(redirect, 4*1000);*/
            function redirect() {
                window.location.href = "/Civitrans/index.civ";
                return;
            }
            var x = document.getElementById("contador");
            var segundos = 5;
            var cont = segundos;
            x.innerHTML = segundos;
            setTimeout(contar, 1 * 1000);
            function contar() {
                if (cont === 1) {
                    redirect();
                    return;
                }
                cont--;
                x.innerHTML = cont;
                setTimeout(contar, 1 * 1000);
            }

        </script>
    </body>

</html>
