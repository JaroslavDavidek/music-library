<%-- 
    Document   : create
    Created on : 13.12.2015, 14:35:14
    Author     : Peter Franek
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
            <h1>Create album</h1>
        </div>  


        <form:form method="POST" modelAttribute="albumDTO" acceptCharset=""
                   action="${pageContext.request.contextPath}/album/create">

            <div class="col-md-8 col-md-offset-2 form-group ${title_error?'has-error':''}">
                <form:label path="title" cssClass="col-sm-3 text-right control-label">Album title</form:label>
                    <div class="col-sm-5">
                    <form:input path="title" cssClass="form-control"/>
                    <form:errors path="title" cssClass="help-block"/>
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

            <div class="col-md-8 col-md-offset-2 form-group ${commentary_error?'has-error':''}">
                <form:label path="commentary" cssClass="col-sm-3 text-right control-label">Commentary</form:label>
                    <div class="col-sm-5">
                    <form:input path="commentary" cssClass="form-control"/>
                    <form:errors path="commentary" cssClass="help-block"/>
                </div>
            </div>
                    
            <div class="col-md-8 col-md-offset-2 form-group ${release_error?'has-error':''}">
                <form:label path="releaseDate" cssClass="col-sm-3 text-right control-label">Release date</form:label>
                    <div class="col-sm-5">
                    <form:input path="releaseDate" id="releaseDatePicker" cssClass="form-control"/>
                    <form:errors path="releaseDate" cssClass="help-block">You have submitted date with invalid format, please use the date picker to select it properly.</form:errors>
                </div>
            </div>

            <button class="col-md-0 col-md-offset-6 btn btn-primary " type="submit">Create</button>
        </form:form>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $(function () {
                $("#releaseDatePicker").datepicker({
                dateFormat: "yy-mm-dd",
                firstDay: 1,
                hideIfNoPrevNext: true,
                showAnim: "fold",
                changeYear: true,
                yearRange: "-300:+0",
                prevText: " Previous ",
                nextText: " Next ",
                maxDate: "-1"});
            });
        </script>
    </jsp:attribute>
</own:administrationpage>
