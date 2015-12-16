<%-- 
    Document   : detail
    Created on : Dec 16, 2015, 11:14:10 AM
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
            <h1>Musician detail</h1>
        </div>

        <div class="col-md-8 col-md-offset-2">
            <b><jsp:text>ID: </jsp:text></b>
            <c:out value="${musician.id}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Artist name: </jsp:text></b>
            <c:out value="${musician.artistName}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Real name: </jsp:text></b>
            <c:out value="${musician.realName}"/>
        </div>
        
        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Date of birth: </jsp:text></b>
            <c:out value="${musician.dateOfBirth}"/>
        </div>

    </jsp:attribute>
</own:masterpage>
