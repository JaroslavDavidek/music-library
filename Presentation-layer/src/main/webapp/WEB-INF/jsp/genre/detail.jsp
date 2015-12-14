<%-- 
    Document   : detail
    Created on : 14.12.2015, 12:24:37
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
            <h1>Genre detail</h1>
        </div>

        <div class="col-md-8 col-md-offset-2">
            <b><jsp:text>ID: </jsp:text></b>
            <c:out value="${genre.id}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Title: </jsp:text></b>
            <c:out value="${genre.title}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Year of origin: </jsp:text></b>
            <c:out value="${genre.yearOfOrigin}"/>
        </div>

    </jsp:attribute>
</own:masterpage>
