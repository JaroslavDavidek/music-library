<%-- 
    Document   : create
    Created on : Dec 15, 2015, 11:13:19 AM
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
            <h1>Create musician</h1>
        </div>  


        <form:form method="POST" modelAttribute="musicianDTO" acceptCharset=""
                   action="${pageContext.request.contextPath}/musician/create">

            <div class="col-md-8 col-md-offset-2 form-group ${artistName_error?'has-error':''}">
                <form:label path="artistName" cssClass="col-sm-3 text-right control-label">Artist name</form:label>
                    <div class="col-sm-5">
                    <form:input path="artistName" cssClass="form-control"/>
                    <form:errors path="artistName" cssClass="help-block"/>
                </div>
            </div>         

            <div class="col-md-8 col-md-offset-2 form-group ${realName_error?'has-error':''}">
                <form:label path="realName" cssClass="col-sm-3 text-right control-label">Real name</form:label>
                    <div class="col-sm-5">
                    <form:input path="realName" cssClass="form-control"/>
                    <form:errors path="realName" cssClass="help-block"/>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${dateOfBirth_error?'has-error':''}">
                <form:label path="dateOfBirth" cssClass="col-sm-3 text-right control-label">Date of birth</form:label>
                    <div class="col-sm-5">
                    <form:input path="dateOfBirth" cssClass="form-control"/>
                    <form:errors path="dateOfBirth" cssClass="help-block"/>
                </div>
            </div>

            <button class="col-md-2 col-md-offset-5 btn btn-primary " type="submit">Create musician</button>
        </form:form>
    </jsp:attribute>
</own:masterpage>
