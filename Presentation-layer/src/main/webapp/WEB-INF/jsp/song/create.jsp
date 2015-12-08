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


<own:masterpage>
<jsp:attribute name="body">
        <div class="jumbotron">
            <h1>Create song</h1>
        </div>  
        
        
        <form:form method="POST" modelAttribute="songCreate" acceptCharset=""
                   action="${pageContext.request.contextPath}/song/create">

            <div class="col-md-8 col-md-offset-2 form-group ${title_error?'has-error':''}">
                <form:label path="title" cssClass="col-sm-3 text-right control-label">Song title</form:label>
                <div class="col-sm-5">
                    <form:input path="title" cssClass="form-control"/>
                    <form:errors path="title" cssClass="help-block"/>
                </div>
            </div>

            <div class="col-md-8 col-md-offset-2 form-group ${album_error?'has-error':''}">
                <form:label path="album" cssClass="col-sm-3 text-right control-label">Album</form:label>
                    <div class="col-sm-5">
                    <form:select path="album" cssClass="form-control">
                        <c:forEach items="${albums}" var="album">
                            <form:option value="${album.id}">${album.title}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="album" cssClass="error"/></p>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${musician_error?'has-error':''}">
                <form:label path="musician" cssClass="col-sm-3 text-right control-label">Musician</form:label>
                    <div class="col-sm-5">
                    <form:select path="musician" cssClass="form-control">
                        <c:forEach items="${musicians}" var="musician">
                            <form:option value="${musician.id}">${musician.artistName}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="musician" cssClass="error"/></p>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${genre_error?'has-error':''}">
                <form:label path="genre" cssClass="col-sm-3 text-right control-label">Genre</form:label>
                    <div class="col-sm-5">
                    <form:select path="genre" cssClass="form-control">
                        <c:forEach items="${genres}" var="genre">
                            <form:option value="${genre.id}">${genre.title}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="genre" cssClass="error"/></p>
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

            

            <button class="col-md-2 col-md-offset-5 btn btn-primary " type="submit">Create song</button>
        </form:form>
    </jsp:attribute>
</own:masterpage>