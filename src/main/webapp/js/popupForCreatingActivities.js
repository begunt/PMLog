/**
 * Created by Asus on 23/01/2017.
 */
$(window).load(function () {
    $( "#usersDevices" ).attr('disabled','disabled');
    $( "#addButton" ).click(function() {
        var selectedDevice = $( "#select_Devices option:selected" ).text();
        var tempDevices = $( "#usersDevices" ).val();
        if(tempDevices != "undefined"){
            $( "#usersDevices" ).val(tempDevices+", "+ selectedDevice);
            $( "#usersDevices" ).removeAttr('disabled');
        }
        if(tempDevices == "") {
            $( "#usersDevices" ).val(selectedDevice+"");
            $( "#usersDevices" ).removeAttr('disabled');
        }

    });
});
