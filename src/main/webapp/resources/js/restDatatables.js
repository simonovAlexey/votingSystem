var ajaxUrl = "ajax/restaurants/";
var disableVoting = ( voteRest != "" && timeToVoting ); //see lang.jsp
var editTitleKey = "rest.edit";
var menuTitleKey = "rest.menu";


function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}
function showNote() {
    $('#WarnNote').show(200);
}
function showMenu(id) {
    $("#resultMenuModal").load('menu=' + id);
    $('#resultMenuModal').modal();

}

function vote(id) {
    $.ajax({
        url: ajaxUrl + 'v',
        type: 'POST',
        data: 'id=' + id,
        success: function () {
            $('#votedRest').load("ajax/restaurants/vRest");
            disableVoting = ( new Date().getHours() >= voteT );
            updateTable();
            successNoty('common.saved');
        }
    });
}

function renderVoteBtn(data, type, row) {
    if (type == 'display') {
        if (disableVoting) {
            return '<a class="btn btn-xs btn-primary vote" disabled="true" onclick="showNote()" >' +
                '<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"><spring:message code="rest.vote"/></span></a>';
        }
        else {
            return '<a class="btn btn-xs btn-primary vote" onclick="vote(' + row.id + ')">' +
                '<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"><spring:message code="rest.vote"/></span></a>';
        }
    }
    return data;
}

function renderMenuBtn(data, type, row) {
    if (type == 'display') {
        if (type == 'display') {
            return '<a class="btn btn-sm btn-primary" onclick="showMenu(' + row.id + ');">' +
                '<span class="glyphicon glyphicon-cutlery" aria-hidden="true"> <spring:message code="rest.menu"/></span></a>';
        }
        return data;
    }
}


