<%-- 
    Document   : index
    Created on : Dec 14, 2015, 8:30:43 PM
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
        <h1>Musician section</h1>
    </div>
    
    <div class="btn-group btn-group-justified" role="group">
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/musician/list" role="button">View all musicians</a>
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/musician/find" role="button">Find musicians</a>
    </div>

</jsp:attribute>
</own:masterpage>
