<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <p class="navbar-brand"><spring:message code="app.title"/></p>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="restaurants"><spring:message code="rest.title"/></a></li>
            <li><a href="users"><spring:message code="users.title"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="btn btn-default btn-sm" role="button" href="profile">${userTo.name} <spring:message code="app.profile"/></a></li>
            <%--<li>
                    <form:form class="navbar-form" action="logout" method="post">
                        <sec:authorize access="isAuthenticated()">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-info" href="users"><spring:message code="users.title"/></a>
                            </sec:authorize>
                            <a class="btn btn-info" role="button" href="profile">${userTo.name} <spring:message code="app.profile"/></a>
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            </button>
                        </sec:authorize>
                    </form:form>
                </li>--%>
            <%--<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>  глификон с текстом--%>
            <jsp:include page="lang.jsp"/>
        </ul>
    </div>
</div>