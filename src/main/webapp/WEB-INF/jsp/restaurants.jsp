<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://votingSystem.simonow.by/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>


<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/restDatatables.js" defer></script>

<script type="text/javascript">
    var datatableApi;
    $(function () {
        datatableApi = $('#datatable').DataTable(extendsOpts({
            "columns": [
                {
                    "data": "name"

                },
                {
                    "render": renderMenuBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "data": "votes"
                },
                {
                    "render": renderVoteBtn,
                    "defaultContent": "",
                    "orderable": false
                },
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                {
                    "render": function (data, type, row) {
                            if (type == 'display') {
                                return '<a class="btn btn-sm btn-primary" href="meals/' + row.id +'/' + row.name + '">' +
                                    '<span aria-hidden="true"> <spring:message code="rest.dishes"/></span></a>';
                            }
                    },
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false
                }
            </sec:authorize>

            ],
            "order": [
                [
                    2,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).addClass(data.votes == 0 ? 'notVoted' : 'selected');
            }
        }));
    });
</script>

<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<br>
<div class="container">
    <div class="page-header">
        <h3><spring:message code="rest.title"/></h3>
    </div>

    <div class="view-box">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a class="btn btn-info" onclick="add('<spring:message code="rest.add"/>')">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </a>
        </sec:authorize>

        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="rest.name"/></th>
                <th><spring:message code="rest.menu"/></th>
                <th><spring:message code="rest.votes"/></th>
                <th><spring:message code="rest.vote"/></th>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th></th>
                <th></th>
                <th></th>
            </sec:authorize>

            </tr>
            </thead>
        </table>
    </div>
    <br>
    <div id="WarnNote" class="alert alert-warning  alert-dismissable fade in" hidden="hidden">
        <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <spring:message code="rest.noty"/>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

<div id="resultMenuModal" style="display: none" class="modal"></div>


<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message
                                code="rest.name"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="name" name="name"
                                   placeholder="<spring:message code="rest.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>