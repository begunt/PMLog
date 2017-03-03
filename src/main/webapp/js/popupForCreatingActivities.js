/**
 * Created by Asus on 23/01/2017.
 */
var projectName = 'Select';
var mainObject = document.getElementById("mainObject").value;
var forAddButton = document.getElementById("forAddButton").value;

$(window).load(function () {

    if(document.getElementById('reportTable') != undefined){
        $('#activityAddBtn').removeClass('hide-add-btn')
    }
    else {
        $('#activityAddBtn').addClass('btn btn-primary for-add add-btn-initial');
        $('#activityAddBtn').removeClass('hide-add-btn')
    }


    var dropEnvs = $("select[id*=select_Environment]");
     dropEnvs = [].slice.call(dropEnvs);
     dropEnvs.forEach(function(element){
        element.setAttribute('multiple', '');
     });
    $("select[id*=select_Environment]").selectpicker('render');

    resetDrops();

    if (mainObject != "null/") {

        var selectProduct= $( "#selectIdProject option:selected" ).val();

        if(selectProduct == 'M0bile'){
            setDevices();
        }

        setEnvs(selectProduct);

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

    function resetDrops(){
        $('.selectpicker').selectpicker('val', '');
        $('.selectpicker').selectpicker('render');
    }

    function setDevices(){
        var devices = $('#users_Devices_M0bile').val();
        var arrDevices = devices.split(',');
        $('#select_Devices_M0bile').selectpicker('val', arrDevices);
        $('#select_Devices_M0bile').selectpicker('render');
    }

    function setEnvs(product){
          var inputsVals = $('#users_Environment_' + product).val();
          var arrVals = inputsVals.split(',');
          $('#select_Environment_' + product).selectpicker('val', arrVals);
          $('#select_Environment_' + product).selectpicker('render');
    }
    //$( "#usersDevices" ).attr('disabled','disabled');
/*
    $("#select_Activity").change(function() {
        var activity = $( "#select_Activity option:selected" ).text();
        //if(activity == "Full test pass" || activity == "Bug verification" || activity == "Regression")
        chooseActivity(activity);
    });*/

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
    /*function chooseActivity(activity){
       if(activity == "Full test pass" && selectProduct== "Mobile"){
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
        }
    }
*/

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

/*function getUserChoice(tempName) {
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

};*/

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
/*
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
    }).done(function() {
        console.log(data);
        parsedData = JSON.parse(data);

        var table = document.getElementById('reportTable');
        if (table == null){
            var tempTable = document.createElement('table');
            tempTable.setAttribute('id', 'reportTable');
            document.body.appendChild(tempTable);
            build(tempTable);
        }
        else {
            build(table);
        }

    }).fail(function(data, status) {
        console.log(status);
        console.log(JSON.stringify(data));
    });
}

function build(table){
    var row = table.insertRow(-1);

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);


    //Task
    var task = parsedData.product + ' - ' + parsedData.project + ' - ' + parsedData.activity;
    var taskPlaceholder = document.createElement('span');
    taskPlaceholder.innerHTML = task;
    cell1.appendChild(taskPlaceholder);

    //Create comment cell
    var env = 'Environment: ' + parsedData.environment;
    var envPlaceholder = document.createElement('span');
    envPlaceholder.style.display = 'block';
    envPlaceholder.innerHTML = env;

    var sprint = 'Sprint: ' + parsedData.sprint;
    var sprintPlaceholder = document.createElement('span');
    sprintPlaceholder.style.display = 'block';
    sprintPlaceholder.innerHTML = sprint;

    var comment = 'Comment: ' + parsedData.comment;
    var commentPlaceholder = document.createElement('span');
    commentPlaceholder.style.display = 'block';
    commentPlaceholder.innerHTML = comment;

    var link = 'Link: ' + parsedData.link;
    var linkPlaceholder = document.createElement('span');
    linkPlaceholder.style.display = 'block';
    linkPlaceholder.innerHTML = link;

    cell2.appendChild(envPlaceholder);
    cell2.appendChild(sprintPlaceholder);
    cell2.appendChild(commentPlaceholder);
    cell2.appendChild(linkPlaceholder);

    //Time
    var time = parsedData.time;
    var timePlaceholder = document.createElement('span');
    timePlaceholder.innerHTML = time;
    cell3.appendChild(timePlaceholder);


    //buttons
    var btn = document.createElement('button');
    btn.classList.add('btn', 'btn-primary');
    btn.setAttribute('id', 'edit');
    btn.style.marginLeft = '0px';
    btn.onclick = function () {
        btn.value = "/reportController/edit/" + parsedData.idReport.toString();
    }
    btn.innerHTML = 'Edit';
    cell4.appendChild(btn);

   // document.getElementById('emptyList').style.display = 'none';
    document.getElementById("reportForm").reset();
    var selectToDrop = document.getElementById('selectIdProject');


    $('#myModal').modal('hide');

}
*/

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


$( "#select_Devices_M0bile" ).change(function() {
    var pickerValue = $( "#select_Devices_M0bile" ).val();
    var inputValue = document.getElementById('users_Devices_M0bile');
    inputValue.value = pickerValue;
    console.log( 'Picker = ' + pickerValue);
    console.log('Input = ' + inputValue);
});

//fix for dropdown value
// recheck
/*$("select:not(#select_Devices_M0bile)").change(function() {
    $("this option:selected").text();
});*/


//Env drop down
/*
$("select[id*=select_Environment]").change(function(){
    self = this;
    console.log('Before convert = ' + self);
    console.log(self.id);
    var selfArray = [].slice.call(self);
    console.log("After convert =  " + selfArray);
    selfArray.forEach(function (element) {
        if(self.id.contains('select_Environment_')){
            var pickerValueServer = $('#select_Environment_Server').val();
            var inputValueServer = document.getElementById('users_Environment_Server');
            inputValueServer.value = pickerValueServer.toString();
            console.log('Picker = ' + pickerValueServer);
            console.log('Input = ' + inputValueServer);
        }
        if(self.id == 'select_Environment_M0bile'){
            var pickerValueMobile = $('#select_Environment_M0bile').val();
            var inputValueMobile = document.getElementById('users_Environment_M0bile');
            inputValueMobile.value = pickerValueMobile.toString();
            console.log('Picker Mobile = ' + pickerValueMobile);
            console.log('Input Mobile = ' + inputValueMobile);
        }
    })
})*/

$("select[id*=select_Environment]").change(function(){
   self = this;
   console.log('Before convert => ' + self);
   var projectId = self.id;
   var parsedProjectId = projectId.split('_')[2];
   console.log(parsedProjectId);
   var pickervalue = $('#' + self.id).val();
   var inputVal = document.getElementById('users_Environment_' + parsedProjectId);
   inputVal.value = pickervalue;

});

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


$('#select_Activity').change(function(){
    switch ($('#select_Activity').val())
   {
       case "Full test pass":
           $('.full-test-pass').show('fast');
           $(".full-test-pass").attr("required", "");
           hideMobileFields($('.bug-verification, .regression'));
           break;

       case "Bug verification":
           $('.bug-verification').show('fast');
           $(".bugVerification").attr("required", "");
           hideMobileFields($('.full-test-pass, .regression'));
           break;

       case "Regression":
           $('.regression').show('fast');
           $(".regression").attr("required", "");
           hideMobileFields($('.full-test-pass, .bug-verification'));
           break;

        default:
            hideMobileFields($('.bug-verification, .regression, .full-test-pass'));

   }

});

//var isFormParent = $( "input[type='checkbox']" )

function hideMobileFields(collectionToHide){
    $(collectionToHide).hide('slow');
    $(collectionToHide).removeAttr("required");
    $(collectionToHide).val('');
    $( "input[type='number']").val(0);

}


