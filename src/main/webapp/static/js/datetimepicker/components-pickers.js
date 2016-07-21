var ComponentsPickers = function () {


    var handleDatetimePicker = function () {

        if (!jQuery().datetimepicker) {
            return;
        }

        $(".form_datetime").datetimepicker({

            autoclose: true,
            isRTL: Metronic.isRTL(),
            format: "yyyy-mm-dd hh:ii:ss",
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left")
        });

        $(".form_advance_datetime").datetimepicker({

            isRTL: Metronic.isRTL(),
            format: "yyyy-mm-dd hh:ii:ss",
            autoclose: true,
            todayBtn: true,
            startDate: "2013-02-14 10:00:00",
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
            minuteStep: 10
        });

        $(".form_meridian_datetime").datetimepicker({
            language: 'cn',
            isRTL: Metronic.isRTL(),
            format: "yyyy-mm-dd hh:ii:ss",
            showMeridian: true,
            autoclose: true,
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
            todayBtn: true
        });

        $('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
    }




   

    return {
        //main function to initiate the module
        init: function () {
            handleDatetimePicker();
        }
    };

}();