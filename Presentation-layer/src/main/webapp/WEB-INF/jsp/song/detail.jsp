<%-- 
    Document   : detail
    Created on : Dec 11, 2015, 4:00:38 PM
    Author     : JaroslavDavidek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>


 <td><c:out value="${song.genre.title}"/></td>
                <td><c:out value="${song.albumPosition}"/></td>
                <td><c:out value="${song.bitrate}"/></td>
                <td><c:out value="${song.commentary}"/></td>