$(function () {
    $("#startdatepicker").datepicker({
        autoclose: true,
        todayHighlight: true,
        startDate: new Date(),
    }).datepicker('update', new Date())
    let stDate = new Date();
    stDate.setDate(stDate.getDate() + 1);
    $("#stopdatepicker").datepicker({
        //beforeShow: stDate = getStartDate(), //document.getElementById('startDate').value,
        //onSelect: stDate = getStartDate(),
        autoclose: true,
        todayHighlight: true,
        startDate: new Date(stDate),
    }).datepicker('update', new Date());
});
/*
$('#stopdate').on('change', checkDate);

   function getStartDate(){
  //  var stDate = document.getElementById('startDate').value
       let stDate = new Date();
       stDate.setDate(stDate.getDate() + 1);
       console.log(stDate);
       return stDate
    }

(function(){

    let startDateValue = document.getElementById('startDate').value;
    startDateValue.onchange(checkDate());


})();
    function checkDate(){
        console.log("hejhej");
       // document.getElementById('startDate').value
    }*/
/*
    let nowTemp = new Date();
    let now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
    let startDate = $("#startdatepicker").datepicker({
        beforeShowDay: function (date) {
            return date.valueOf() >= now.valueOf();
        },
        autoclose: true

    }).on('changeDate', function (ev) {
        if (ev.date.valueOf() > endDate.datepicker("getDate").valueOf() || !endDate.datepicker("getDate").valueOf()) {

            let newDate = new Date(ev.date);
            newDate.setDate(newDate.getDate() + 1);
            endDate.datepicker("update", newDate);
        }
        $("#stopdatepicker")[0].focus();
    });

    let endDate = $("#stopdatepicker").datepicker({
        beforeShowDay: function (date) {
            if (!startDate.datepicker("getDate").valueOf()) {
                return date.valueOf() >= new Date().valueOf();
            } else {
                return date.valueOf() > startDate.datepicker("getDate").valueOf();
            }
        },
        autoclose: true
    }).on('changeDate', function (ev) {})*/
