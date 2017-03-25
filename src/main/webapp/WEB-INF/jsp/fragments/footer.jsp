<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer class="container-fluid text-center">
    <br>
    <div class="container">
        <div class="page-information">
            <p> </p>
        </div>
        <p class="text-muted"><spring:message code="app.footer"/></p>
    </div>
</footer>

<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.status", "users.edit", "meals.edit", "common.search"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>
