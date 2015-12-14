<%-- 
    Document   : find
    Created on : 14.12.2015, 10:18:41
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
        <h1>Find albums</h1>
    </div>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/album/findByID">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="albumId" cssClass="col-sm-3 text-right control-label">Find album by ID</form:label>
            <div class="col-sm-6">
                <form:input path="albumId" cssClass="form-control"/>
                <form:errors path="albumId" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/album/findByTitle">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="title" cssClass="col-sm-3 text-right control-label">Find album by title</form:label>
            <div class="col-sm-6">
                <form:input path="title" cssClass="form-control"/>
                <form:errors path="title" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/album/findByMusician">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="musicianId" cssClass="col-sm-3 text-right control-label">Find all albums by artist</form:label>
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
               action="${pageContext.request.contextPath}/album/findByYearRange">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="albumId" cssClass="col-sm-3 text-right control-label">Find all albums</form:label>
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

</jsp:attribute>
</own:masterpage>
