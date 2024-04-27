$(function () {
    $("#startdatepicker").datepicker({
        autoclose: true,
        todayHighlight: true,
        minDate: '0'
    }).datepicker('update', new Date());
    $("#stopdatepicker").datepicker({
        autoclose: true,
        todayHighlight: true,
    }).datepicker('update', new Date());
});

