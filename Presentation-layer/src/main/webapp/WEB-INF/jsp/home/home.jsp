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
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/song/list"
              role="button">View songs</a></p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/song/new"
              role="button">Create song</a></p>
    </div>
    
    

</jsp:attribute>
</own:masterpage>