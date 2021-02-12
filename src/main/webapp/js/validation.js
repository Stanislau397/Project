$(document).ready(function () {

    var button = $('#submit').hide();

    validate_all();

    $('#user_name').keyup(function () {
        validate_userName();
        validate_all();
    });

    $('#password').keyup(function () {
        validate_password();
        validate_all();
    });

    $('#email').keyup(function () {
        validate_email();
        validate_all();
    });

    $('#confirm_password').keyup(function () {
        validate_confirm_password();
        validate_all();
    });

    function validate_all() {
        if (!validate_email() && !validate_password() && !validate_userName()
            && !validate_confirm_password()) {
            button.hide();
        } else {
            button.show();
        }
    }

    function validate_password() {
        var password = $('#password').val();
        const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
        if (password.length === 0 || password === '' || password === null) {
            $('#password_error').html("Must contain one capital letter and one digit");
            $('#password_error').css('color', 'black');
            button.hide();
            return false;
        } else if (password.length < 8 || password.length > 25) {
            $('#password_error').html("Password must be from 8 to 25 symbols");
            $('#password_error').css('color', 'red');
            button.hide();
            return false;
        } else if (!regex.test(password)) {
            $('#password_error').html("Invalid password date");
            $('#password_error').css('color', 'red');
            button.hide();
            return false;
        } else {
            $('#password_error').html("Password is correct");
            $('#password_error').css('color', 'green');
            return true;
        }
    }

    function validate_userName() {
        var user_name = $('#user_name').val();
        if (user_name.length === 0 || user_name === '' || user_name === null) {
            $('#name_error').html("User name should be from 7 to 25 characters");
            $('#name_error').css('color', 'black');
            button.hide()
            return false;
        } else if (user_name.length < 7 || user_name.length > 25) {
            $('#name_error').html("User name must be form 7 to 25 characters");
            $('#name_error').css('color', 'red');
            button.hide()
            return false;
        } else {
            $('#name_error').html("User name is correct");
            $('#name_error').css('color', 'green');
            return true;
        }
    }

    function validate_confirm_password() {
        var password = $('#password').val();
        var confirm_password = $('#confirm_password').val();
        if (password !== confirm_password) {
            $('#confirm_error').html("Confirm password doesnt match password");
            $('#confirm_error').css('color', 'red');
            button.hide()
            return false;
        } else if (confirm_password.length === 0) {
            $('#confirm_error').html("Please enter confirm password");
            $('#confirm_error').css('color', 'red');
            button.hide();
            return false;
        } else {
            $('#confirm_error').html("Passwords match");
            $('#confirm_error').css('color', 'green');
            return true;
        }
    }

    function validate_email() {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var email = $('#email').val();
        if (email.length === 0 || email === '' || email === null) {
            $('#email_error').html("Email example Laswe@gmail.com");
            $('#email_error').css('color', 'black');
            button.hide();
            return false;
        } else if (!re.test(email)) {
            $('#email_error').html("Invalid email data");
            $('#email_error').css('color', 'red');
        } else {
            $('#email_error').html("Email is correct");
            $('#email_error').css('color', 'green');
            return true;
        }
    }
});