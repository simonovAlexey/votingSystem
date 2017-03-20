var ajaxUrl = "ajax/restaurants/";
var datatableApi;
var editTitleKey = "rest.edit";

// $(document).ready(function () {
function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
        success: updateTableByData
    });
}

$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "name"

            },
            {
                "data": "menu"
                /*"render": function (data, type, row) {
                    if (type == 'display') {
                        return '<a class="btn btn-xs btn-primary" href="ajax/restaurants/menu=' + row.id + '">' +
                            '<span class="glyphicon glyphicon-cutlery" aria-hidden="true"><spring:message code="rest.menu"/></span></a>';
                    }
                    return data;
                },
                "defaultContent": "",
                "orderable": false*/
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
            $(row).addClass(data.votes>3 ? 'exceeded' : 'normal');
        }
    }));
});