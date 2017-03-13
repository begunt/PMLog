/**
 * Created by Asus on 23/01/2017.
 */
var projectName = 'Select';
var mainObject = document.getElementById("mainObject").value;
var forAddButton = document.getElementById("forAddButton").value;

$(window).load(function () {

    if(document.getElementById('reportTable') != undefined){
        $('#activityAddBtn, #activityUpBtn').show('slow');
        $('.total-time').show('slow');
        getTime();
    }
    else {
        $('#activityAddBtn').addClass('btn btn-primary for-add add-btn-initial');
        $('#activityAddBtn').removeClass('hide-add-btn');
    }
    //$("select[id*=select_Environment]").selectpicker('render');

    if (mainObject != "null/") {

        var selectProduct= $( "#selectIdProject option:selected" ).val();

        if(selectProduct == 'M0bile'){
            setDevices();
        }

        setEnvs(selectProduct);

        if(selectProduct != ""){
            //selectProject();
            selectProjectEdit();
        }
        $('#myModal').modal('show');
        /*var activity = $("#mainObject").text();
        console.log(activity);
        chooseActivity(activity);*/
    }

    if(forAddButton == "updating"){
        resetDrops();
        $('#myModal').modal('show');
    }

    function setDevices(){
        var devices = $('#users_Devices_M0bile').val();
        var arrDevices = devices.split(',');
        /*var optionsList = new Array();
        $('#select_Devices_M0bile option').each(function(){
            optionsList.push($(this).val());
        });
        if(optionsList.indexOf(arrDevices[0]) == -1){
         $('#select_Devices_M0bile option:first').text(arrDevices[0]);
        }*/
        $('#select_Devices_M0bile').selectpicker('val', arrDevices);
        $('#select_Devices_M0bile').selectpicker('render');
    }

    function setEnvs(product){
          var inputsVals = $('#users_Environment_' + product).val();
          if (inputsVals) {
              var arrVals = inputsVals.split(',');
              $('#select_Environment_' + product).selectpicker('val', arrVals);
              $('#select_Environment_' + product).selectpicker('render');
          }
    }


      $('#selectIdProject').on('change', function (e) {
          $("[id*=fields_of_project_id_]").not('#fields_of_project_id_' + $(this).val()).hide('slow');
          $("[class*=fields_of_project_id_]").not('#fields_of_project_id_' + $(this).val()).val('').removeAttr("required");
          $('#fields_of_project_id_' + $(this).val()).show('slow'); // показывает поля выбранного проекта
          $('.fields_of_project_id_' + $(this).val()).each(function (index, element) {
              if ($(element).hasClass('isRequired_true')) {
                  $(element).not('.full-test-pass, .bug-verification, .regression').attr("required", "");
              }
          })
          $( "#selectIdProject option:selected" ).each(function(){
            projectName = $(this).val();
          });
          resetErrorClass();
          clearModalContent(event.target.value);

      });

    function selectProjectEdit(){
        projectName = $('#selectIdProject').val();
        $('#fields_of_project_id_' + $('#selectIdProject').val()).show('slow'); // показывает поля выбранного проекта
        $('.fields_of_project_id_' + $('#selectIdProject').val()).show('slow');
        $('.fields_of_project_id_' + $('#selectIdProject').val()).each(function (index, element) {
            if ($(element).hasClass('isRequired_true')) {
                $(element).attr("required", "");
            }
        })
        var activityValue =  $('.pepsi').find("select.fields_of_project_id_" + projectName).val();
        if(activityValue == 'Communication' || activityValue == 'Environment setup') {
            hideDeviseBuildEnvsSetup($('.hide-pc-es'));
        }
    }


    /**/

});

function clearModalContent(project){
    $("[id*=fields_of_project_id_]").not('#fields_of_project_id_' + project).val('');
    $("[class*=fields_of_project_id_]").not('#fields_of_project_id_' + project).val('').selectpicker('val', '').selectpicker('render');
    if ($('.hide-pc-es').css('display') == 'none'){
        unHideDeviseBuildEnvsSetup($('.hide-pc-es'));
    }
    // *TODO: hide custom device field
    /*if($('#customDevice:visible').length != 0){
        $('#customDevice').hide();
    }*/
}

/**/

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
        debug: true
        //onkeyup: false
        //ignore: '*:not([name])'
        //success: "valid"
    });
    var form = $( "#reportForm" );
   /* form.validate({
        invalidHendler: function(event, validator){
            {
                var dropdowns = $('.selectpicker');
                $(dropdowns).on('change', function(event){
                    console.log(event);
                })
            }
        }
    });*/
    if(form.valid())
        sentToController();
    if (mainObject != "null/")
        sentToController();

}
/*

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
  /*if($(this).find("option:selected").attr('title') == 'Other Device'){
        $('#customDevice').show('slow');
        $( "#select_Devices_M0bile" ).selectpicker('toggle');
    }
    if($(this).find("option:selected").attr('title') != 'Other Device'){
        $('#customDevice').hide('slow');
    }*/
    console.log( 'Picker = ' + pickerValue);
    console.log('Input = ' + inputValue);
});

/*$('#bnt-custom-device').on('click', function(){
    var customDeviceInput = document.getElementById('input-custom-device');
    var customvDeviceInputValue = customDeviceInput.value;
    var inputToServer = document.getElementById('users_Devices_M0bile').value;
    var newValue = inputToServer.replace(/Other Device/ig, customvDeviceInputValue.trim());
    $('#custom-device-Name').text(customvDeviceInputValue);
    document.getElementById('users_Devices_M0bile').value = newValue;
    $('#customDevice').hide('slow');
});*/

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

       case "Story regression":
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

function resetDrops(){
    $('.selectpicker').selectpicker('val', '');
    $('.selectpicker').selectpicker('render');
    $('.selectpicker').val('');
}

function noProjectSelected(){
    $("[id*=fields_of_project_id_]").hide();
}

function resetErrorClass(){
    $("[id*=fields_of_project_id_]").removeClass('error');
    $('label.error').remove();
    $(".error").removeClass('error');
}

$('#activityAddBtn, #activityUpBtn').on('click', function(){
    resetDrops();
    document.getElementById('reportForm').reset();
    noProjectSelected();
    resetErrorClass();
})

function getTime() {
    var allTime = 0;
    var timeNodeList = $('.getTime');
    var i;
    if(timeNodeList){
        for(i=0; i<timeNodeList.length; i++){
            allTime += parseInt(timeNodeList[i].innerHTML);
        }
        document.getElementById('totalTime').innerHTML = allTime;
    } else {
        document.getElementById('totalTime').innerHTML = 0;
    }
}

$('#modalClose').on('click', function(e){
    bootbox.confirm({
        title: 'Close confirmation',
        message: "Are you sure?",
        size: 'small',
        className: 'bg-color',
        buttons:{
            confirm:{
                label: 'Yes',
                className: 'btn-success'
            },
        cancel:{
            label: 'No',
            className: 'btn-danger'
            }
        },
        callback: function(result){
            if(result){
                $('#myModal').modal('hide');
            }
        }
    })
});

$('.pepsi').on('change', function(event){

   /* var selectedProject = document.getElementById('selectIdProject').value;
    //$('#select_Activity').selectpicker('val', selectedProject);
    var eventTarget = event.target.value;*/
    var eleventsToChange = $('.hide-pc-es');
    switch (event.target.value){
        case "Communication":
            hideDeviseBuildEnvsSetup(eleventsToChange);
            break;
        case "Environment setup":
            hideDeviseBuildEnvsSetup(eleventsToChange);
            break;
        default:
            unHideDeviseBuildEnvsSetup(eleventsToChange);
            break;
    };
});

function hideDeviseBuildEnvsSetup(fields){
    fields.hide('slow');
    fields.find( $("[class*=fields_of_project_id_]")).val('').selectpicker('val', '').selectpicker('render');
}

function unHideDeviseBuildEnvsSetup(fields) {
    console.log(fields);
    fields.show('slow');
    fields.each(function (index, element) {
        if ($(element).hasClass('isRequired_true')) {
            $(element).attr("required", "");
        }
    })
}


$( function() {

    var availableTags =[];

    $("#devices-to-grab option").each(function()
    {
        console.log($(this).text());
        availableTags.push($(this).text());
    });


    function split( val ) {
        return val.split( /,\s*/ );
    }
    function extractLast( term ) {
        return split( term ).pop();
    }

    $( "#users_Devices_M0bile" )
    // don't navigate away from the field on tab when selecting an item
        .on( "keydown", function( event ) {
            if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).autocomplete( "instance" ).menu.active ) {
                event.preventDefault();
            }
        })
        .autocomplete({
            minLength: 0,
            source: function( request, response ) {
                // delegate back to autocomplete, but extract the last term
                response( $.ui.autocomplete.filter(
                    availableTags, extractLast( request.term ) ) );
            },
            focus: function() {
                // prevent value inserted on focus
                return false;
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.value );
                // add placeholder to get the comma-and-space at the end
                terms.push( "" );
                this.value = terms.join( ", " );
                return false;
            }
        });
} );


var dropEnvs = $("select[id*=select_Environment]");
dropEnvs = [].slice.call(dropEnvs);
dropEnvs.forEach(function(element){
    element.setAttribute('multiple', '');
});

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