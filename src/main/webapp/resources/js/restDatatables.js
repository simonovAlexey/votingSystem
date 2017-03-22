var ajaxUrl = "ajax/restaurants/";
var datatableApi;
var editTitleKey = "rest.edit";
var menuTitleKey = "rest.menu";

// $(document).ready(function () {
function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

function updateTable() {
    $.ajax({
        type: 'POST',
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
        success: updateTableByData
    });
}

function drawMenu(id) {
    $('#modalTitle').html(i18n[menuTitleKey]);
    debugger;
    $.ajax({
        url: 'ajax/restaurants/menu=' + id,
        type: 'GET',
        dataType: 'JSON',
        success: function () {
            $.each(data, function (key, value) {
                alert( key + ": " + value );
            });
            $('#showMenu').modal()
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
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.votes > 3 ? 'exceeded' : 'normal');
        }
    }));
});