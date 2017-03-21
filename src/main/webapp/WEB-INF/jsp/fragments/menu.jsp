<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="showMenu">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <table class="table table-striped display" id="menutable">
                    <thead>
                    <tr>
                        <th><fmt:message key="meal.description"/></th>
                        <th><fmt:message key="meal.price"/></th>
                    </tr>
                    </thead>
                    <c:forEach items="${meals}" var="meal">
                        <jsp:useBean id="meal" scope="page" type="by.simonow.VotingSystem.model.Meal"/>
                        <tr>
                            <td><c:out value="${meal.description}"/></td>
                            <td>${meal.price/100},${meal.price%100}</td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
    </div>
</div>