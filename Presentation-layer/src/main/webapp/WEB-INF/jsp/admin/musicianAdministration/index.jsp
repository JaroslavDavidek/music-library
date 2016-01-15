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

<own:administrationpage>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Manage musicians</h1>
    </div>
    
    <div class="row">
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/musician/new" role="button">Create musician</a></p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/musician/listAsAdmin" role="button">Manage musicians</a></p>
    </div>

</jsp:attribute>
</own:administrationpage>