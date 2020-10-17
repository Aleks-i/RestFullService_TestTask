var departmentsAjaxUrl = "sber/departments/";

// $(document).ready(function () {
$(function () {
    makeEditable(departmentsAjaxUrl, {
        "columns": [
            {
                "data": "name",
                "render": function (data, type, row, id) {
                    return "<a href='employees/" + row.id + "'>" + data + "</a>"
//                    makeEditable("employees/" + row.id)
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