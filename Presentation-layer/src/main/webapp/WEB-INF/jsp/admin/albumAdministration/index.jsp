<%-- 
    Document   : index
    Created on : 13.12.2015, 14:18:45
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
        <h1>Manage albums</h1>
    </div>
    
    <div class="btn-group btn-group-justified" role="group">
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/album/new" role="button">Create album</a>
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/album/listAsAdmin" role="button">Manage albums</a>
    </div>

</jsp:attribute>
</own:administrationpage>
