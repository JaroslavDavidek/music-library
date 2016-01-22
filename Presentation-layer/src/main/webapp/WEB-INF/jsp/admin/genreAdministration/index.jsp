<%-- 
    Document   : index
    Created on : 14.12.2015, 12:21:21
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
        <h1>Manage genres</h1>
    </div>
    
    <div class="btn-group btn-group-justified" role="group">
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/genre/new" role="button">Create genre</a>
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/genre/listAsAdmin" role="button">Manage genres</a>
    </div>

</jsp:attribute>
</own:administrationpage>