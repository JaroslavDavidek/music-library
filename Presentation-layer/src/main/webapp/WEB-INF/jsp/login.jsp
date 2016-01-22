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
            <div class="col-md-6 col-sm-12 ${empty param.error ? 'loginFormCenter' : ''}">

                <form:form method="POST"
                           action="j_spring_security_check"
                           acceptCharset=""
                           modelAttribute="user"
                           cssClass="form-horizontal form-signin"
                           style="margin-top: 2cm;"
                           >
                    <h2 class="form-signin-heading">Sign in</h2>

                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            Invalid username or password.
                        </div>
                    </c:if>

                    <div style="margin: 20px 0px 20px 0px;">
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <input type="email" name="user" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
                    </div>
                    <div style="margin: 20px 0px 20px 0px;">
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" name="pass" id="inputPassword" class="form-control" placeholder="Password" required="">
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                </form:form>     
            </div>
        </div>

    </jsp:attribute>
</own:indexpage>
