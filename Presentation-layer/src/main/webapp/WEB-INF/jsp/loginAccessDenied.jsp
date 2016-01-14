<%-- 
    Document   : login
    Created on : Dec 17, 2015, 11:36:18 PM
    Author     : Jergus Fasanek
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:indexpage title="Login">
    <jsp:attribute name="body">

        <div class="row">
            <div>
                <h1 style="color:red;">Access denied</h1>               
                <label  class="col-md-10">The account you are logged with does not have administrator rights, which are required to enter this section. If you have administrator access, please perform logout and then login with corresponding credentials.</label>
                <p><a class="col-md-4 btn btn-lg btn-primary" href="${pageContext.request.contextPath}/logout"
                  role="button">logout</a></p>
            </div>
        </div>

    </jsp:attribute>
</own:indexpage>
