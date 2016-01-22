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
        
        <div class="row">
            <table class="col-md-3">
                <tbody>
                    <tr>
                        <td><b><jsp:text>ID: </jsp:text></b></td>
                        <td><c:out value="${album.id}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Title: </jsp:text></b></td>
                        <td><c:out value="${album.title}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Artist: </jsp:text></b></td>
                        <td><c:out value="${album.musician.artistName}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Commentary: </jsp:text></b></td>
                        <td><c:out value="${album.commentary}"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
        
        <div class="row">
            <table class="table">
                <thead>
                    <tr><th><b>Songs:</b></th></tr>
                </thead>
                <tbody>
                    <c:forEach items="${album.songs}" var="song">
                        <tr><td>${song.title}</td></tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


    </jsp:attribute>
</own:masterpage>
