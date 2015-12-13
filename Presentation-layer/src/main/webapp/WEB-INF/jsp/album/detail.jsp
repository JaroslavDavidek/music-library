<%-- 
    Document   : detail
    Created on : 13.12.2015, 14:49:25
    Author     : Peter Franek
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


<td><c:out value="${album.title}"/></td>
<td><c:out value="${album.releaseDate}"/></td>
<td><c:out value="${album.musician.artistName}"/></td>
<td><c:out value="${album.commentary}"/></td>
