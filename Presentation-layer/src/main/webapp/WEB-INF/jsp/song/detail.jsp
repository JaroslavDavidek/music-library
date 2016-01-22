<%-- 
    Document   : detail
    Created on : Dec 11, 2015, 4:00:38 PM
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
        <h1>Song detail</h1>
    </div>
    
    <div class="row">
        <table class="col-md-3">
            <tbody>
                <tr>
                     <td><b><jsp:text>ID: </jsp:text></td>
                     <td><c:out value="${song.id}"/></td>
                </tr>

                <tr>
                     <td><b><jsp:text>Title: </jsp:text></td>
                     <td><c:out value="${song.title}"/></td>
                </tr>

                <tr>
                     <td><b><jsp:text>Album: </jsp:text></td>
                     <td><c:out value="${song.album.title}"/></td>
                </tr>

                <tr>
                     <td><b><jsp:text>Artist: </jsp:text></td>
                     <td><c:out value="${song.musician.artistName}"/></td>
                </tr>

                <tr>
                     <td><b><jsp:text>Genre: </jsp:text></td>
                     <td><c:out value="${song.genre.title}"/></td>
                </tr>

                <tr>
                     <td><b><jsp:text>Album position: </jsp:text></td>
                     <td><c:out value="${song.albumPosition}"/></td>
                </tr>

                <tr>
                     <td><b><jsp:text>Bitrate: </jsp:text></td>
                     <td><c:out value="${song.bitrate}"/></td>
                </tr>

                <tr>
                     <td><b><jsp:text>Commentary: </jsp:text></td>
                     <td><c:out value="${song.commentary}"/></td>
                </tr>
            </tbody>
        </table>
    </div>
</jsp:attribute>
</own:masterpage>