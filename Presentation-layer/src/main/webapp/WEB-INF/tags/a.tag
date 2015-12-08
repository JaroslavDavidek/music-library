<%-- 
    Document   : a
    Created on : Dec 8, 2015, 9:44:12 PM
    Author     : jarek
--%>

<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>

<%@ attribute name="href" required="true" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<core:url value='${href}' var="url" scope="page"/>
<a href="<core:out value='${url}'/>" class="${attr['class']}" >
    <jsp:doBody/>
</a>