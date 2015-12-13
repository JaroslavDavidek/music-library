<%-- 
    Document   : list
    Created on : Dec 8, 2015, 1:53:38 PM
    Author     : JaroslavDavidek
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:masterpage>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>All songs</h1>
    </div>
    
    <div class="row">
        <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Album</th>
            <th>Musician</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allSongs}" var="song">
            <tr>
                <td><c:out value="${song.id}"/></td>
                <td><c:out value="${song.title}"/></td>
                <td><c:out value="${song.album.title}"/></td>
                <td><c:out value="${song.musician.artistName}"/></td>
                <td><form method="get" action="${pageContext.request.contextPath}/song/detail/${song.id}">
                <button type="submit" class="btn btn-primary">View</button></form></td>
            </tr>
        </c:forEach>
           
        </tbody>
    </table>
    </div>

</jsp:attribute>
</own:masterpage>