var ajaxUrl = "ajax/restaurants/";
var datatableApi;
var disableVoting = ( voteRest != "" && timeToVoting ); //see lang.jsp
var editTitleKey = "rest.edit";
var menuTitleKey = "rest.menu";


function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}
function showNote() {
    $('#WarnNote').show(200);
}

function vote(id) {
    $.ajax({
        url: ajaxUrl + 'v',
        type: 'POST',
        data: 'id=' + id,
        success: function () {
            $('#votedRest').load("ajax/restaurants/vRest");
            disableVoting = ( new Date().getHours() >= 11 );
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

function drawMenu(id) {
    $('#modalTitleMenu').html(i18n[menuTitleKey]);
    // debugger;
    $.ajax({
        url: 'ajax/restaurants/menu=' + id,
        type: 'GET',
        dataType: 'JSON',
        success: function () {
            $.each(data, function (key, value) {
                alert(key + ": " + value);
            });
            // $('#showMenu').modal()
        }
    });
    /*$.get( + id,
     function (data) {
     $.each(data, function (key, value) {
     alert( key + ": " + value );
     // $('#showMenu').find("td id='" + key + "']").val(value);
     });
     $('#showMenu').modal();
     });*/
}

$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "name"

            },
            {
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<a class="btn btn-xs btn-primary" onclick="drawMenu(' + row.id + ');">' +
                            '<span class="glyphicon glyphicon-cutlery" aria-hidden="true"><spring:message code="rest.menu"/></span></a>';
                    }
                    return data;
                },
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
        ],
        "order": [
            [
                2,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.votes == 0 ? 'notVoted' : 'voted');
            // datatableApi.column( 'restVote:name' ).addClass('disabled');
            //addClass(data.votes == 0 ? 'notVoted' : 'voted');
        }
    }));
});