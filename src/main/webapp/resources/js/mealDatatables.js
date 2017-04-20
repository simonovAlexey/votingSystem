var ajaxUrl = "ajax/dishes/" + mealRestId +'/';
var datatableApi;
var editTitleKey = "dishes.edit";


function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function menuSelect(chkbox, id) {
    var inMenu = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'inMenu=' + inMenu,
        success: function () {
            chkbox.closest('tr').toggleClass('selected');
            successNoty(inMenu ? 'common.enabled' : 'common.disabled');
        }
    });
}

$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "price",
                "render": function (price, type, row) {
                    if (type == 'display') {
                        return formatPrice(price);
                    }
                    return price;
                }
            },
            {
                "data": "inMenu",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="menuSelect($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
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
            if (data.inMenu) {
                $(row).addClass("selected");
            }
        }
    }));
});