<%-- 
    Document   : create
    Created on : 14.12.2015, 12:22:09
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
            <h1>Create genre</h1>
        </div>  


        <form:form method="POST" modelAttribute="genreDTO" acceptCharset=""
                   action="${pageContext.request.contextPath}/genre/create">

            <div class="col-md-8 col-md-offset-2 form-group ${title_error?'has-error':''}">
                <form:label path="title" cssClass="col-sm-3 text-right control-label">Genre title</form:label>
                    <div class="col-sm-5">
                    <form:input path="title" cssClass="form-control"/>
                    <form:errors path="title" cssClass="help-block"/>
                </div>
            </div>         

            <div class="col-md-8 col-md-offset-2 form-group ${yearOfOrigin_error?'has-error':''}">
                <form:label path="yearOfOrigin" cssClass="col-sm-3 text-right control-label">Year of origin</form:label>
                    <div class="col-sm-5">
                    <form:input path="yearOfOrigin" cssClass="form-control"/>
                    <form:errors path="yearOfOrigin" cssClass="help-block"/>
                </div>
            </div>

            <button class="col-md-2 col-md-offset-5 btn btn-primary " type="submit">Create genre</button>
        </form:form>
    </jsp:attribute>
</own:masterpage>