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
    
        <div class="col-md-8 col-md-offset-2">
             <jsp:text>ID: </jsp:text>
             <c:out value="${song.id}"/>
        </div>
       
        <div class="col-md-8 col-md-offset-2" >
             <jsp:text>Title: </jsp:text>
             <c:out value="${song.title}"/>
        </div>
        
        <div class="col-md-8 col-md-offset-2" >
             <jsp:text>Album: </jsp:text>
             <c:out value="${song.album.title}"/>
        </div>
        
        <div class="col-md-8 col-md-offset-2" >
             <jsp:text>Artist: </jsp:text>
             <c:out value="${song.musician.artistName}"/>
        </div>
        
        <div class="col-md-8 col-md-offset-2" >
             <jsp:text>Genre: </jsp:text>
             <c:out value="${song.genre.title}"/>
        </div>
        
        <div class="col-md-8 col-md-offset-2" >
             <jsp:text>Album position: </jsp:text>
             <c:out value="${song.albumPosition}"/>
        </div>
        
        <div class="col-md-8 col-md-offset-2" >
             <jsp:text>Bitrate: </jsp:text>
             <c:out value="${song.bitrate}"/>
        </div>
        
        <div class="col-md-8 col-md-offset-2" >
             <jsp:text>Commentary: </jsp:text>
             <c:out value="${song.commentary}"/>
        </div>

</jsp:attribute>
</own:masterpage>