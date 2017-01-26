/**
 * Created by Asus on 23/01/2017.
 */

$(window).load(function () {

    if (document.getElementById("mainObject").value != "null")
        $('#myModal').modal('show');

    $( "#usersDevices" ).attr('disabled','disabled');

    var name = $("#typeCheckbox").val();
    $( "#addButton_" + name).click(function() {
        var selectedValues = $( "#select_"+name+" option:selected" ).text();
        var temp = $("#users_"+name).val();
        if(temp != "undefined"){
            $("#users_"+name).val(temp+", "+ selectedValues);
            $("#users_"+name).removeAttr('disabled');
        }
        if(temp == "") {
            $("#users_"+name).val(selectedValues+"");
            $("#users_"+name).removeAttr('disabled');
        }
    });

    $("#select_Activity").change(function() {
        var activity = $( "#select_Activity option:selected" ).text();
        if(activity == "Full test pass"){
            $(".fullPass").css("display","block");
            $(".fullPass").attr("required", "");
            $(".bugVerification").css("display","none");
            $(".bugVerification").removeAttr("required");
            $(".storyRegression").css("display","none");
            $(".storyRegression").removeAttr("required");
        }
        if(activity == "Bug verification"){
            $(".fullPass").css("display","none");
            $(".fullPass").removeAttr("required");
            $(".bugVerification").css("display","block");
            $(".bugVerification").attr("required", "");
            $(".storyRegression").css("display","none");
            $(".storyRegression").removeAttr("required");
        }
        if(activity == "Regression"){
            $(".fullPass").css("display","none");
            $(".fullPass").removeAttr("required");
            $(".bugVerification").css("display","none");
            $(".bugVerification").removeAttr("required");
            $(".storyRegression").css("display","block");
            $(".storyRegression").attr("required", "");
        }
        if(activity != "Full test pass" && activity != "Bug verification" && activity != "Regression"){
            $(".fullPass").css("display","none");
            $(".fullPass").removeAttr("required");
            $(".bugVerification").css("display","none");
            $(".bugVerification").removeAttr("required");
            $(".storyRegression").css("display","none");
            $(".storyRegression").removeAttr("required");
        }
    });

    var projectName = 'Mobile';
    $("#selectIdProject").change(function () {
        //var e = document.getElementById("selectIdProject");
        console.log("selected project = " + projectName);
        $("#fields_of_project_id_" + projectName).css("display", "none");
        $(".fields_of_project_id_" + projectName).each(function(){
            $(this).removeAttr("required");
            $(this).val();
        })
        projectName = $("#selectIdProject").val();
        $("#fields_of_project_id_" + projectName).css("display","block");
        $(".fields_of_project_id_" + projectName).each(function(){
            if( $(this).css() != "display: none;")
                $(this).attr("required", "");
        })
    });

    var selectProduct= $( "#selectIdProject option:selected" ).text();
    if(selectProduct != ""){
        selectProject();
    }

    function selectProject() {
        var e = document.getElementById("selectIdProject");
        console.log("selected project = " + projectName);
        $("#fields_of_project_id_" + projectName).css("display", "none");
        $(".fields_of_project_id_" + projectName).each(function(){
            $(this).removeAttr("required");
            //inputsForDisable[j].val("");
        })
        projectName = e.options[e.selectedIndex].value;
        $("#fields_of_project_id_" + projectName).css("display","block");
        $(".fields_of_project_id_" + projectName).each(function(){
            if( $(this).css() != "display: none;")
                $(this).attr("required", "");
        })
    }
});
