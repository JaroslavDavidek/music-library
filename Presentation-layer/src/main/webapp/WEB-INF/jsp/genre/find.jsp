<%-- 
    Document   : find
    Created on : 14.12.2015, 12:26:50
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
        <h1>Find genres</h1>
    </div>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/genre/findByID">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="genreId" cssClass="col-sm-3 text-right control-label">Find genre by ID</form:label>
            <div class="col-sm-6">
                <form:input path="genreId" cssClass="form-control"/>
                <form:errors path="genreId" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/genre/findByTitle">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="title" cssClass="col-sm-3 text-right control-label">Find genre by title</form:label>
            <div class="col-sm-6">
                <form:input path="title" cssClass="form-control"/>
                <form:errors path="title" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/genre/findByYearRange">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="genreId" cssClass="col-sm-3 text-right control-label">Find all genres</form:label>
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
               action="${pageContext.request.contextPath}/genre/findOrdered">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="sortASC" cssClass="col-sm-2 text-right control-label">Use ascending order</form:label>
            <div class="col-sm-1">
                <form:checkbox path="sortASC" cssClass="col-sm-1 form-control"/>
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>

</jsp:attribute>
</own:masterpage>
