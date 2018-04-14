$(document).ready(function () {

    const CELL_PATTERN = /^(?![= ]).{1,255}/;
    const CELL_ERROR_MESSAGE = "The string must be between 1 and 255 characters. The first character must not be '=' or ' '";
    const SUCCESS_MESSAGE = "Done!";

    $('input[name="search-criteria"]').bind('keyup change', function () {
        let passwordValue = $(this).val();
        if (!CELL_PATTERN.test(passwordValue)) {
            setErrorMessage(CELL_ERROR_MESSAGE);
        } else {
            setSuccessMessage(SUCCESS_MESSAGE);
        }
    });

    $('input[clas="updated"]').bind('keyup change', function () {
        let passwordValue = $(this).val();
        if (!CELL_PATTERN.test(passwordValue)) {
            setErrorMessage(CELL_ERROR_MESSAGE);
        } else {
            setSuccessMessage(SUCCESS_MESSAGE);
        }
    });
});


function setSuccessMessage(element, message) {
    $('.validator-message').removeClass('error').addClass('not_error');
    setActionMessage(element, message);
}

function setErrorMessage(message) {
    $('.validator-message').removeClass('not_error').addClass('error');
    setActionMessage(message);
}

function setActionMessage(message) {
    $('.validator-message').html(message);
}