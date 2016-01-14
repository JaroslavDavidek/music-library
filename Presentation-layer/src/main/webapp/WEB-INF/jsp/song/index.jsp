<%-- 
    Document   : index
    Created on : Dec 11, 2015, 4:04:31 PM
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
        <h1>Songs section</h1>
    </div>

    <div class="row">
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/song/list" role="button">View all songs</a></p>
        <p><a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/song/find" role="button">Find songs</a></p>
    </div>

</jsp:attribute>
</own:masterpage>
