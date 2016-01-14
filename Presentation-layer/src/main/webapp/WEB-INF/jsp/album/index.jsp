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

<own:masterpage>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Albums section</h1>
    </div>

    <div class="row">
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/album/list" role="button">View albums</a></p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/album/find" role="button">Find albums</a></p>
    </div>

</jsp:attribute>
</own:masterpage>
