<%-- 
    Document   : create
    Created on : Dec 8, 2015, 10:30:39 PM
     Author     : JaroslavDavidek
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<own:administrationpage>
<jsp:attribute name="body">
        <div class="jumbotron">
            <h1>Create song</h1>
        </div>  
        
        
        <form:form method="POST" modelAttribute="songCreateDTO" acceptCharset=""
                   action="${pageContext.request.contextPath}/song/create">

            <div class="col-md-8 col-md-offset-2 form-group ${title_error?'has-error':''}">
                <form:label path="title" cssClass="col-sm-3 text-right control-label">Song title</form:label>
                <div class="col-sm-5">
                    <form:input path="title" cssClass="form-control"/>
                    <form:errors path="title" cssClass="help-block"/>
                </div>
            </div>

            <div class="col-md-8 col-md-offset-2 form-group ${albumId_error?'has-error':''}">
                <form:label path="albumId" cssClass="col-sm-3 text-right control-label">Album</form:label>
                    <div class="col-sm-5">
                    <form:select path="albumId" cssClass="form-control">
                        <c:forEach items="${albums}" var="album">
                            <form:option value="${album.id}">${album.title}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="albumId" cssClass="error"/></p>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${musicianId_error?'has-error':''}">
                <form:label path="musicianId" cssClass="col-sm-3 text-right control-label">Musician</form:label>
                    <div class="col-sm-5">
                    <form:select path="musicianId" cssClass="form-control">
                        <c:forEach items="${musicians}" var="musician">
                            <form:option value="${musician.id}">${musician.artistName}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="musicianId" cssClass="error"/></p>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${genreId_error?'has-error':''}">
                <form:label path="genreId" cssClass="col-sm-3 text-right control-label">Genre</form:label>
                    <div class="col-sm-5">
                    <form:select path="genreId" cssClass="form-control">
                        <c:forEach items="${genres}" var="genre">
                            <form:option value="${genre.id}">${genre.title}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="genreId" cssClass="error"/></p>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${albumPosition_error?'has-error':''}">
                <form:label path="albumPosition" cssClass="col-sm-3 text-right control-label">Album position</form:label>
                <div class="col-sm-5">
                    <form:input path="albumPosition" cssClass="form-control"/>
                    <form:errors path="albumPosition" cssClass="help-block"/>
                </div>
            </div>               
                
            <div class="col-md-8 col-md-offset-2 form-group ${bitrate_error?'has-error':''}">
                <form:label path="bitrate" cssClass="col-sm-3 text-right control-label">Bitrate</form:label>
                <div class="col-sm-5">
                    <form:input path="bitrate" cssClass="form-control"/>
                    <form:errors path="bitrate" cssClass="help-block"/>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${commentary_error?'has-error':''}">
                <form:label path="commentary" cssClass="col-sm-3 text-right control-label">Commentary</form:label>
                <div class="col-sm-5">
                    <form:input path="commentary" cssClass="form-control"/>
                    <form:errors path="commentary" cssClass="help-block"/>
                </div>
            </div>

            

            <button class="col-md-0 col-md-offset-6 btn btn-primary " type="submit">Create</button>
        </form:form>
    </jsp:attribute>
</own:administrationpage>