<%-- 
    Document   : listAsAdmin
    Created on : 13.12.2015, 14:20:09
    Author     : Peter Franek
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:administrationpage>
    <jsp:attribute name="body">

        <div class="jumbotron">
            <h1>All albums</h1>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Release Date</th>
                        <th>Musician</th>
                        <th>Commentary</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${allAlbums}" var="album">
                        <tr>
                            <td><c:out value="${album.id}"/></td>
                            <td><c:out value="${album.title}"/></td>
                            <td><c:out value="${album.releaseDate}"/></td>
                            <td><c:out value="${album.musician.artistName}"/></td>
                            <td><c:out value="${album.commentary}"/></td>
                            
                            <td><form method="get" action="${pageContext.request.contextPath}/album/detailAsAdmin/${album.id}">
                                    <button type="submit" class="btn btn-primary">manage</button></form></td>
                            
                            <td><form method="post" action="${pageContext.request.contextPath}/album/delete/${album.id}">
                                    <button type="submit" class="btn btn-primary">delete</button></form></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

    </jsp:attribute>
</own:administrationpage>
