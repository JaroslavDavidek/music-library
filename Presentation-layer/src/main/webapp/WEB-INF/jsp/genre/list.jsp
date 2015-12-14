<%-- 
    Document   : list
    Created on : 14.12.2015, 12:30:51
    Author     : Peter Franek
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
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${allGenres}" var="genre">
                        <tr>
                            <td><c:out value="${genre.id}"/></td>
                            <td><c:out value="${genre.title}"/></td>
                            <td><c:out value="${genre.yearOfOrigin}"/></td>
                            <td><form method="get" action="${pageContext.request.contextPath}/genre/detail/${genre.id}">
                                    <button type="submit" class="btn btn-primary">View</button></form></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

    </jsp:attribute>
</own:masterpage>
