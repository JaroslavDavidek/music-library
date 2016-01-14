<%-- 
    Document   : listAsAdmin
    Created on : 14.12.2015, 12:32:21
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
            <h1>All genres</h1>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Year of Origin</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${allGenres}" var="genre">
                        <tr>
                            <td><c:out value="${genre.id}"/></td>
                            <td><c:out value="${genre.title}"/></td>
                            <td><c:out value="${genre.yearOfOrigin}"/></td>d>
                            
                            <td><form method="get" action="${pageContext.request.contextPath}/genre/detailAsAdmin/${genre.id}">
                                    <button type="submit" class="btn btn-primary">manage</button></form></td>
                            
                            <td><form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}">
                                    <button type="submit" class="btn btn-primary">delete</button></form></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

    </jsp:attribute>
</own:administrationpage>
