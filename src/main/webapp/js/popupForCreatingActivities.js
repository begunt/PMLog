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

    $("#select_Activity").change(function() {
        var activity = $( "#select_Activity option:selected" ).text();
        if(activity == "Full test pass"){
            $(".fullPass").css("display","block");
            $(".bugVerification").css("display","none");
            $(".storyRegression").css("display","none");
        }
        if(activity == "Bug verification"){
            $(".fullPass").css("display","none");
            $(".bugVerification").css("display","block");
            $(".storyRegression").css("display","none");
        }
        if(activity == "Regression"){
            $(".fullPass").css("display","none");
            $(".bugVerification").css("display","none");
            $(".storyRegression").css("display","block");
        }
        if(activity != "Full test pass" && activity != "Bug verification" && activity != "Regression"){
            $(".fullPass").css("display","none");
            $(".bugVerification").css("display","none");
            $(".storyRegression").css("display","none");
        }
    });
});
