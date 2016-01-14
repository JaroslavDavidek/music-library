<%-- 
    Document   : detailAsAdmin
    Created on : Dec 16, 2015, 11:14:37 AM
    Author     : Jergus Fasanek
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
            <h1>Update musician</h1>
        </div>  

        <form:form method="POST" modelAttribute="musicianDetail" acceptCharset=""
                   action="${pageContext.request.contextPath}/musician/detailAsAdmin/${id}">

            <div class="col-md-8 col-md-offset-2 form-group ${title_error?'has-error':''}">
                <form:label path="artistName" cssClass="col-sm-3 text-right control-label">Artist name</form:label>
                    <div class="col-sm-5">
                    <form:input path="artistName" cssClass="form-control"/>
                    <form:errors path="artistName" cssClass="help-block"/>
                </div>
            </div>

            <div class="col-md-8 col-md-offset-2 form-group ${yearOfOrigin_error?'has-error':''}">
                <form:label path="realName" cssClass="col-sm-3 text-right control-label">Real name</form:label>
                    <div class="col-sm-5">
                    <form:input path="realName" cssClass="form-control"/>
                    <form:errors path="realName" cssClass="help-block"/>
                </div>
            </div>
                
            <div class="col-md-8 col-md-offset-2 form-group ${yearOfOrigin_error?'has-error':''}">
                <form:label path="dateOfBirth" cssClass="col-sm-3 text-right control-label">Date of birth</form:label>
                    <div class="col-sm-5">
                    <form:input path="dateOfBirth" cssClass="form-control"/>
                    <form:errors path="dateOfBirth" cssClass="help-block"/>
                </div>
            </div>

            <button class="col-md-2 col-md-offset-5 btn btn-primary " type="submit">Update musician</button>
        </form:form>
    </jsp:attribute>
</own:administrationpage>
