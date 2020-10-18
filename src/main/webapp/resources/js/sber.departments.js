var departmentsAjaxUrl = "sber/departments/";

$(function () {
    makeEditable(departmentsAjaxUrl, {
        "columns": [
            {
                "data": "name",
                "render": function (data, type, row, id) {
                    return "<a href='employees/'>" + data + "</a>"
                }
            },
            {
                "data": "averageSalary"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    }, function () {
        $.get(departmentsAjaxUrl, updateTableByData);
    });
});