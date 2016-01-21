<%-- 
    Document   : find
    Created on : Dec 15, 2015, 12:36:25 AM
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
        <h1>Find musicians</h1>
    </div>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/musician/findByID">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="musicianId" cssClass="col-sm-3 text-right control-label">Find musician by ID</form:label>
            <div class="col-sm-6">
                <form:input path="musicianId" cssClass="form-control"/>
                <form:errors path="musicianId" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/musician/findByArtistName">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="artistName" cssClass="col-sm-3 text-right control-label">Find musician by artist name</form:label>
            <div class="col-sm-6">
                <form:input path="artistName" cssClass="form-control"/>
                <form:errors path="artistName" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/musician/findByRealName">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="realName" cssClass="col-sm-3 text-right control-label">Find musician by real name</form:label>
            <div class="col-sm-6">
                <form:input path="realName" cssClass="form-control"/>
                <form:errors path="realName" cssClass="help-block"/>              
            </div>
            <button class="btn btn-primary " type="submit">Find</button>
        </div>
    </form:form>
    
    <form:form method="POST" modelAttribute="searchDTO" acceptCharset="" 
               action="${pageContext.request.contextPath}/musician/findByYearRange">
        <div class="col-md-12 form-group ${title_error?'has-error':''}">
            <form:label path="musicianId" cssClass="col-sm-3 text-right control-label">Find all musicians</form:label>
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
               action="${pageContext.request.contextPath}/musician/findOrdered">
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
