<%-- 
    Document   : list
    Created on : Dec 16, 2015, 11:15:11 AM
    Author     : Jergus Fasanek
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
            <h1>All musicians</h1>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Artist name</th>
                        <th>Real name</th>
                        <th>Date of birth</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${allMusicians}" var="musician">
                        <tr>
                            <td><c:out value="${musician.id}"/></td>
                            <td><c:out value="${musician.artistName}"/></td>
                            <td><c:out value="${musician.realName}"/></td>
                            <td><c:out value="${musician.dateOfBirth}"/></td>
                            <td><form method="get" action="${pageContext.request.contextPath}/musician/detail/${musician.id}">
                                    <button type="submit" class="btn btn-primary">View</button></form></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

    </jsp:attribute>
</own:masterpage>
