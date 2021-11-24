$(function () {

    $('#description-error').hide();
    $('#title-error').hide();
    $('#run-time-error').hide();
    $('#release-date-error').hide();
    $('#image-error').hide();
    $('#btn').prop('disabled', true);

    $('#title').focusout( function () {
        validateTitle();
    });

    $('#run_time').focusout( function () {
        validateRunTime();
    });

    $('#release_date').focusout( function () {
        validateReleaseDate();
    });

    $('#description').focusout( function () {
        validateDescription();
    });

    $('#inpFile').change( function () {
        validateImage();
    })

    $('#btn').click( function () {
        validateTitle()
        validateImage()
        validateDescription()
        validateRunTime()
        validateReleaseDate()
    })

    function validateImage() {
        var image = $('#inpFile').val();
        var image_regex = /\.(gif|jpg|png|bmp|jpeg)$/i;
        if (image.match(image_regex)) {
            $('#btn').prop('disabled', false);
            $('#image-error').hide();
        } else if (image === '') {
            $('#btn').prop('disabled', false);
            $('#image-error').hide();
        } else {
            $('#btn').prop('disabled', true);
            $('#image-error').show();
        }
    }

    function validateTitle() {
        var title = $('#title').val();
        var title_regex = '^.{1,80}$';
        if (title.match(title_regex)) {
            $('#title-error').hide();
            $('#title').css('border', '2px solid #66cc33');
            $('#btn').prop('disabled', false);
            return false;
        } else if (title === '' || isNaN(title)) {
            $('#title-error').show();
            $('#title').css('border', '2px solid red');
            $('#btn').prop('disabled', true);
            return true;
        } else {
            $('#title-error').show();
            $('#title').css('border', '2px solid red');
            $('#btn').prop('disabled', true);
            return true;
        }
    }

    function validateRunTime() {
        var run_time = $('#run_time').val();
        var run_time_regex = /^\d+$/;
        if (run_time.match(run_time_regex)) {
            $('#run-time-error').hide();
            $('#run_time').css('border', '2px solid #66cc33');
            $('#btn').prop('disabled', false);
        } else if (run_time === '') {
            $('#run-time-error').hide();
            $('#run_time').css('border', '1px solid silver');
            $('#btn').prop('disabled', false);
        } else {
            $('#run-time-error').show();
            $('#run_time').css('border', '2px solid red');
            $('#btn').prop('disabled', true);
        }
    }

    function validateDescription() {
        var descripiton = $('#description').val();
        var description_regex = '^.{1,1500}$';
        if (descripiton.match(description_regex)) {
            $('#description-error').hide();
            $('#description').css('border', '2px solid #66cc33');
            $('#btn').prop('disabled', false);
        } else if (descripiton === '') {
            $('#description-error').hide();
            $('#description').css('border', '1px solid silver');
            $('#btn').prop('disabled', false);
        } else {
            $('#description-error').show();
            $('#description').css('border', '2px solid red');
            $('#btn').prop('disabled', true);
        }
    }

    function validateReleaseDate() {
        var release_date = $('#release_date').val();
        var release_date_regex = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;
        if (release_date.match(release_date_regex)) {
            $('#release-date-error').hide();
            $('#release_date').css('border', '2px solid #66cc33');
            $('#btn').prop('disabled', false);
        } else if (release_date === '') {
            $('#release-date-error').hide();
            $('#release_date').css('border', '1px solid silver');
            $('#btn').prop('disabled', true);
        } else {
            $('#release-date-error').show();
            $('#release_date').css('border', '2px solid red');
            $('#btn').prop('disabled', true);
        }
    }
});