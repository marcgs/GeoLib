$(function () {

    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            alert(data);
        }
    });

});