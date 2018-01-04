<%-- 
    Document   : newjsp
    Created on : 18/07/2016, 03:17:18 PM
    Author     : Miguel Borja
--%>

<%@page import="com.civitrans.singleton.SessionSingleton"%>
<%@page import="com.civitrans.model.LoginUser"%>
<%@page import="com.civitrans.utility.Log_Handler"%>
<%@page import="com.civitrans.singleton.AuthSingleton"%>
<%@page import="com.civitrans.jsf.bean.BeanLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("X-FRAME-OPTIONS", "DENY");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Server", "La Civica Impresores SAS");
    response.setHeader("X-Powered-By", "La Civica Impresores SAS");
    String ok = request.getParameter("OK");
    String _uri = session.getAttribute("uri_destino") + "";
    String _ip_host = session.getAttribute("ip_host") + "";
    request.getRequestURI();
    try {
        LoginUser obj = (LoginUser) session.getAttribute("recurrente");
        if (ok != null && ok.length() > 0) {
            SessionSingleton.getInstancia().invalidarSesion(obj.getId_usuario());
            session.setAttribute("recurrente", null);
            session.setAttribute("uri_destino", null);
            session.setAttribute("ip_host", null);
            response.sendRedirect(_uri);
        }
    } catch (Exception e) {

    }
%>
<html>
    <head>
        <title>Sesión Expirada o Inválida</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
    </head>    
    <body style="background-color: #efefef;">

        <div style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;margin: 0px;">
            <br><br><br>
            <table border="0px" style="margin: 0 auto">
                <tr>
                    <td valign="middle">
                <center>
                    <img align="middle" onclick="redirect()" style="margin: 0 auto;width: 80px;" src="/Civitrans/resources/images/concurrente.png"/>
                </center>
                </td>
                </tr>
                <tr>
                    <td valign="middle" style="text-align: center;">
                        <h3 style="margin: 4px;"><span style="color: #d00000">Ya existe una sesión Iniciada con este usuario</span></h3>
                        <br>
                        <h5 style="width: 800px;margin: 0px;">Ya se ha detectado otra sesión con este mismo usuario (IP: <%=_ip_host%>). Esto puede ocurrir porque la sesión anterior no fue terminada correctamente. Si desconoce el origen de esta sesión, favor comuniquese con el administrador.</h5>
                    </td>
                </tr>
                <tr>
                    <td valign="middle" style="text-align: center;">
                        <br>
                        <a href="<%=request.getContextPath()%>/SesionConcurrente.jsp?OK=1" class="btn btn-sm btn-primary" >Cerrar la otra Sesión</a>
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
    </body>

</html>
