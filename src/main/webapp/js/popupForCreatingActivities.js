/**
 * Created by Asus on 23/01/2017.
 */
var projectName = 'Select';
var mainObject = document.getElementById("mainObject").value;
var forAddButton = document.getElementById("forAddButton").value;

$(window).load(function () {
    if (mainObject != "null/") {

        var selectProduct= $( "#selectIdProject option:selected" ).text();
        if(selectProduct != ""){
            selectProject();
        }
        $('#myModal').modal('show');
        /*var activity = $("#mainObject").text();
        console.log(activity);
        chooseActivity(activity);*/
    }

    if(forAddButton == "updating"){
        $('#myModal').modal('show');
    }

    //$( "#usersDevices" ).attr('disabled','disabled');

    $("#select_Activity").change(function() {
        var activity = $( "#select_Activity option:selected" ).text();
        //if(activity == "Full test pass" || activity == "Bug verification" || activity == "Regression")
        chooseActivity(activity);
    });

    $("#selectIdProject").change(function () {
        //var e = document.getElementById("selectIdProject");
        console.log("selected project = " + projectName);
        $("#fields_of_project_id_" + projectName).css("display", "none");
        $(".fields_of_project_id_" + projectName).each(function(){
            $(this).removeAttr("required");
            $(this).val("");
        })
        projectName = $("#selectIdProject").val();
        $("#fields_of_project_id_" + projectName).css("display","block");
        $(".fields_of_project_id_" + projectName).each(function(){
            if( $(this).css("display") != "none" || $("#mainObject").value == "Regression" || $("#mainObject").value == "Full test pass" || $("#mainObject").value == "Bug verification")
                $(this).attr("required", "");
        })
    });

    function chooseActivity(activity){
       /* if(activity == "Full test pass" && selectProduct== "Mobile"){
            console.log(activity + "chooseActivity()");
            $(".fullPass").css("display","block");
            $(".fullPass").attr("required", "");
            $(".bugVerification").css("display","none");
            $(".bugVerification").removeAttr("required");
            $(".storyRegression").css("display","none");
            $(".storyRegression").removeAttr("required");
        }
        if(activity == "Bug verification"&& selectProduct== "Mobile"){
            console.log(activity + "chooseActivity()");
            $(".fullPass").css("display","none");
            $(".fullPass").removeAttr("required");
            $(".bugVerification").css("display","block");
            $(".bugVerification").attr("required", "");
            $(".storyRegression").css("display","none");
            $(".storyRegression").removeAttr("required");
        }
        if(activity == "Regression"&& selectProduct== "Mobile"){
            console.log(activity + "chooseActivity()");
            $(".fullPass").css("display","none");
            $(".fullPass").removeAttr("required");
            $(".bugVerification").css("display","none");
            $(".bugVerification").removeAttr("required");
            $(".storyRegression").css("display","block");
            $(".storyRegression").attr("required", "");
        }
        if(activity != "Full test pass" && activity != "Bug verification" && activity != "Regression"){
            console.log(activity + "chooseActivity()");
            $(".fullPass").css("display","none");
            $(".fullPass").removeAttr("required");
            $(".bugVerification").css("display","none");
            $(".bugVerification").removeAttr("required");
            $(".storyRegression").css("display","none");
            $(".storyRegression").removeAttr("required");
        }*/
    }

    function selectProject() {
        var e = document.getElementById("selectIdProject");
        console.log("selected project = " + projectName);
        $("#fields_of_project_id_" + projectName).css("display", "none");
        $(".fields_of_project_id_" + projectName).each(function(){
            $(this).removeAttr("required");
            $(this).val("");
        })
        projectName = e.options[e.selectedIndex].value;
        $("#fields_of_project_id_" + projectName).css("display","block");

     //   chooseActivity(mainObject);

       /* $(".fields_of_project_id_" + projectName).each(function(){
            if( $(this).css() != "display: none;" && $("#mainObject").text() == 'null')
                $(this).attr("required", "");
        })*/
    }

});

function getUserChoice(tempName) {
    var selectedValues = $( "#select_"+tempName+" option:selected" ).text();
    var temp = $("#users_"+tempName).val();
    if(temp != "undefined"){
        $("#users_"+tempName).val(temp+", "+ selectedValues);
        $("#users_"+tempName).removeAttr('disabled');
    }
    if(temp == "") {
        $("#users_"+tempName).val(selectedValues+"");
        $("#users_"+tempName).removeAttr('disabled');
    }

};

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function submitForm() {
    //var $myForm = $('#reportForm');
    jQuery.validator.setDefaults({
        debug: true,
        ignore: '*:not([name])',
        success: "valid"
    });
    var form = $( "#reportForm" );
    form.validate();
    if(form.valid())
        sentToController();
    if (mainObject != "null/")
        sentToController();
        /*var i = 0 ;
    $(".fields_of_project_id_" + projectName).each(function(){
        if (!$(this).checkValidity())
            i++;
    })
    if(i == 0)
        sentToController();*/
}

function sentToController(){
    var divArray = $(".fields_of_project");
    for(var index = 0; index < divArray.length; index++) {
        if (divArray[index].id != ("fields_of_project_id_" + projectName))
            divArray[index].remove();
    }
    var data = JSON.stringify($('#reportForm').serializeObject());
    $.ajax({
        type: 'POST',
        url: '/reportController/reports/add',
        data: data,
        contentType: "application/json"
    }).done(function(data, status) {
        console.log(status);
        window.location.href = data;
    }).fail(function(data, status) {
        console.log(status);
        console.log(JSON.stringify(data));
    });
}

/*
function openPopup(){
    if (document.getElementById("mainObject").value != "null") {
        var e = document.getElementById("selectIdProject");
        var projects = [];
        for (var i = 0; i < e.options.length; i++) {
            projects[i] = e.options[i].value;
            $(".fields_of_project_id_" + projects[i]).each(function () {
                $(this).val("");
            })
        }
    }
    $('#myModal').modal('show');
}*/
