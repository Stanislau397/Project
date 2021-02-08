$(document).ready(function () {
    $('#submit').click(function () {
        var name = $('#user_name').val();
        var email = $('#email').val();
        var password = $('#password').val();
        var confirm_password = $('#confirm_password').val();

        if (name.length === 0) {
            $("#name_error").text("Please enter your user name").style("color", "red");
            $("#user_name").focus();
            return false;
        }

    })
})