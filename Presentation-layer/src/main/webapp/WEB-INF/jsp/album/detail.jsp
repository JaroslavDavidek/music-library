<%-- 
    Document   : detail
    Created on : 13.12.2015, 14:49:25
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
            <h1>Album detail</h1>
        </div>

        <div class="col-md-8 col-md-offset-2">
            <b><jsp:text>ID: </jsp:text></b>
            <c:out value="${album.id}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Title: </jsp:text></b>
            <c:out value="${album.title}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Artist: </jsp:text></b>
            <c:out value="${album.musician.artistName}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Commentary: </jsp:text></b>
            <c:out value="${album.commentary}"/>
        </div>

        <div class="col-md-8 col-md-offset-2" >
            <b><jsp:text>Songs: </jsp:text></b>
            </br>
            <c:forEach items="${album.songs}" var="song">
                <c:out value="${song.title}"></c:out></br>
            </c:forEach>
        </div>

    </jsp:attribute>
</own:masterpage>
