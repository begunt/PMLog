/*
$(window).load(function () {
    $('#myForm').validator()
});*/

/*$(document).ready(function() {
    $('#regexpEmailForm')
        .formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                email: {
                    validators: {
                        emailAddress: {
                            message: '������� ���������� ����� ����������� �����'
                        },
                        regexp: {
                            regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                            message: '������� ���������� ����� ����������� �����'
                        }
                    }
                }
            }
        })
        .on('err.validator.fv', function(e, data) {
            // data.bv        --> The FormValidation.Base instance
            // data.field     --> The field name
            // data.element   --> The field element
            // data.validator --> The current validator name

            if (data.field === 'email') {
                // The email field is not valid
                data.element
                    .data('fv.messages')
                    // Hide all the messages
                    .find('.help-block[data-fv-for="' + data.field + '"]').hide()
                    // Show only message associated with current validator
                    .filter('[data-fv-validator="' + data.validator + '"]').show();
            }
        });
});*/

function validate(input){
    input.className += 'validator';
    input.addEventListener('focus', removePopup);
    input.addEventListener('focusout', returnPopup);
}

function removePopup(e) {
    document.getElementById('alertDiv').classList.add('when-focus')
}

function returnPopup(){
    document.getElementById('alertDiv').classList.remove('when-focus');
}

document.querySelector('button').addEventListener('keypress', function (e){
   var key = e.which || e.keyCode;
   if (key === 13){
    document.querySelector('form').submit();
   }
});