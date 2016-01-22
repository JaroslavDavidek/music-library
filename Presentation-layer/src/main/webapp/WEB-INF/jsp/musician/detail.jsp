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

        <div class="row">
            <table class="col-md-3">
                <tbody>
                    <tr>
                        <td><b><jsp:text>ID: </jsp:text></b></td>
                        <td><c:out value="${musician.id}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Artist name: </jsp:text></b></td>
                        <td><c:out value="${musician.artistName}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Real name: </jsp:text></b></td>
                        <td><c:out value="${musician.realName}"/></td>
                    </tr>

                    <tr>
                        <td><b><jsp:text>Date of birth: </jsp:text></b></td>
                        <td><c:out value="${musician.dateOfBirth}"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </jsp:attribute>
</own:masterpage>
