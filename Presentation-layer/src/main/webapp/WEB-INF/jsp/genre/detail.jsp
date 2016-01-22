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
        <div class="row">
            <table class="col-md-3">
                <tbody>
                    <tr>
                        <td><b><jsp:text>ID: </jsp:text></b></td>
                        <td><c:out value="${genre.id}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Title: </jsp:text></b></td>
                        <td><c:out value="${genre.title}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Year of origin: </jsp:text></b></td>
                        <td><c:out value="${genre.yearOfOrigin}"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </jsp:attribute>
</own:masterpage>
