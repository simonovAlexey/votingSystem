<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://votingSystem.simonow.by/functions" %>

<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h2 class="modal-title" id="menuModalTitle"><spring:message code="rest.menu"/></h2>

        </div>
        <div class="modal-body">
            <h3><c:out value="${restaurant}"/></h3>
            <hr>
            <table class="table table-striped display" id="menutable">
                <thead>
                <tr>
                    <th><spring:message code="dishes.description"/></th>
                    <th><spring:message code="dishes.price"/></th>
                </tr>
                </thead>
                <c:forEach items="${dishes}" var="dish">
                    <%--<jsp:useBean id="dish" scope="page" type="Dish"/>--%>
                    <tr>
                        <td><c:out value="${dish.description}"/></td>
                        <td>${fn:formatPrice(dish.price)}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
</div>