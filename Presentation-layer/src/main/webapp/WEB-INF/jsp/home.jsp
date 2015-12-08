<%-- 
    Document   : home
    Created on : Dec 1, 2015, 10:54:09 PM
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
        <h1>Welcome to the Music Library project !</h1>
        <p class="lead">Manage your content with ease... </p>
    </div>
    
    <div class="row">
        <table class="table">
        <thead>
        <tr>
            <th>Song title</th>
        </tr>
        </thead>
        <tbody>
            <%-- 
        <c:forEach items="${songs}" var="song">
            <tr>
                <td><c:out value="${song.title}"/></td>
            </tr>
        </c:forEach>
            --%>
        </tbody>
    </table>
    </div>

</jsp:attribute>
</own:masterpage>
