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

<own:indexpage>
    <jsp:attribute name="body">

        <div class="jumbotron">
            <h1>Welcome to the Music Library project !</h1>
            <p class="lead">Manage your content with ease... </p>
            <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/song/index"
                  role="button">Songs</a></p>
            <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/musician/index"
                  role="button">Musicians</a></p>
            <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/album/index"
                  role="button">Albums</a></p>
            <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/genre/index"
                  role="button">Genres</a></p>
        </div>



    </jsp:attribute>
</own:indexpage>
