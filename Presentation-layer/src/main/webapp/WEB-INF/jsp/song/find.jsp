<%-- 
    Document   : find
    Created on : Dec 13, 2015, 12:54:42 PM
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
        <h1>Find songs</h1>
    </div>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/song/findByID">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="songId" cssClass="col-sm-3 text-right control-label">Find song by ID</form:label>
            <div class="col-sm-6">
                <form:input path="songId" cssClass="form-control"/>
                <form:errors path="songId" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/song/findByTitle">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="title" cssClass="col-sm-3 text-right control-label">Find song by title</form:label>
            <div class="col-sm-6">
                <form:input path="title" cssClass="form-control"/>
                <form:errors path="title" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/song/findByMusician">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="musicianId" cssClass="col-sm-3 text-right control-label">Find all songs by artist</form:label>
            <div class="col-sm-6">
                <form:select path="musicianId" cssClass="form-control">
                    <c:forEach items="${musicians}" var="musician">
                        <form:option value="${musician.id}">${musician.artistName}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="musicianId" cssClass="error"/></p>
                </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/song/findByGenre">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="genreId" cssClass="col-sm-3 text-right control-label">Find all songs by genre</form:label>
            <div class="col-sm-6">
                <form:select path="genreId" cssClass="form-control">
                    <c:forEach items="${genres}" var="genre">
                        <form:option value="${genre.id}">${genre.title}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="genreId" cssClass="error"/></p>
                </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/song/findByAlbum">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="albumId" cssClass="col-sm-3 text-right control-label">Find all songs by album</form:label>
            <div class="col-sm-6">
                <form:select path="albumId" cssClass="form-control">
                        <c:forEach items="${albums}" var="album">
                            <form:option value="${album.id}">${album.title}</form:option>
                        </c:forEach>
                    </form:select>
                <p class="help-block"><form:errors path="albumId" cssClass="error"/></p>
                </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/song/findByMusicianAndReleaseYearRange">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="albumId" cssClass="col-sm-3 text-right control-label">Find all songs by album</form:label>
            <div class="col-sm-2">
               <form:select path="musicianId" cssClass="form-control">
                    <c:forEach items="${musicians}" var="musician">
                        <form:option value="${musician.id}">${musician.artistName}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="musicianId" cssClass="error"/></p>
            </div> 
            <form:label path="fromYear" cssClass="col-sm-1 text-right control-label">From year</form:label>
            <div class="col-sm-1">
               
                <form:input path="fromYear" cssClass="form-control"/>
                <form:errors path="fromYear" cssClass="help-block"/>
            </div>
            <form:label path="toYear" cssClass="col-sm-1 text-right control-label">To year</form:label>
            <div class="col-sm-1">              
                <form:input path="toYear" cssClass="form-control"/>
                <form:errors path="toYear" cssClass="help-block"/>
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
     <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/song/findByAlbumOrdered">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="albumId" cssClass="col-sm-3 text-right control-label">Find all ordered songs by album</form:label>
            <div class="col-sm-3">
                <form:select path="albumId" cssClass="form-control">
                        <c:forEach items="${albums}" var="album">
                            <form:option value="${album.id}">${album.title}</form:option>
                        </c:forEach>
                    </form:select>
                <p class="help-block"><form:errors path="albumId" cssClass="error"/></p>
            </div>
            
            <form:label path="sortASC" cssClass="col-sm-2 text-right control-label">Use ascending order</form:label>
            <div class="col-sm-1">
                <form:checkbox path="sortASC" cssClass="col-sm-1 form-control"/>
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>

</jsp:attribute>
</own:masterpage>