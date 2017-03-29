<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <p class="navbar-brand"><spring:message code="app.title"/></p>
        </div>
        <ul class="nav navbar-nav">
            <sec:authorize access="isAuthenticated()">
                <li><a href="restaurants"><spring:message code="rest.title"/></a></li>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="users"><spring:message code="users.title"/></a></li>
                </sec:authorize>

                <li><a href="profile"><spring:message code="app.profile"/> <b>${userTo.name}</b></a></li>
            </sec:authorize>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${userTo.todayVote!=null}">
                    <li><p class="navbar-text"><spring:message code="users.todayVote"/> <b
                            id="votedRest">${userTo.todayVote.restaurant.name}</b></p></li>
                </c:when>
                <c:otherwise>
                    <li><p class="navbar-text text-primary"><spring:message code="users.notVote"/> <b id="votedRest"></b>
                    </p></li>
                </c:otherwise>
            </c:choose>
            <li>
                <form:form class="navbar-form" action="logout" method="post">
                    <sec:authorize access="isAuthenticated()">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                        </button>
                    </sec:authorize>
                </form:form>
            </li>
            <%--<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>  глификон с текстом--%>
            <jsp:include page="lang.jsp"/>
        </ul>
    </div>
</div>