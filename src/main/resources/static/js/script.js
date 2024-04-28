$(function () {
    $("#startdatepicker").datepicker({
        autoclose: true,
        todayHighlight: true,
        startDate: new Date(),
    }).datepicker('update', new Date());
    let stDate;
    $("#stopdatepicker").datepicker({
        beforeShow: stDate = getStartDate(), //document.getElementById('startDate').value,
        onselect: stDate = getStartDate(),
        autoclose: true,
        todayHighlight: true,
        startDate: new Date(),//.setDate(stDate.getDate()+1),//new Date(stDate),//Date.parse(stDate)),
    }).datepicker('update', new Date());
});

   function getStartDate(){
    var stDate = document.getElementById('startDate').value
    console.log(stDate);
    return stDate
    }