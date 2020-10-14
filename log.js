const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

const form1 = document.getElementById('form');
const form2 = document.getElementById('form2');

const username1 = document.getElementById('usr');
const username2 = document.getElementById('usr2');

const password = document.getElementById('psw');
const password2 = document.getElementById('psw-repeat');

const passwordLog = document.getElementById('psw2');
form1.addEventListener('submit', e => {


    e.preventDefault();

    checkRegInputs();
    /*if successAll() {
        redirect to a personal page with name as a parameter
    }*/
});

form2.addEventListener('submit', e => {

    e.preventDefault();

    checkLogInputs();
    /*if successAll() {
        redirect to a personal page with name as a parameter
    }*/
});

function checkRegInputs() {
    // trim to remove the whitespaces
    const usernameValue = username1.value.trim();
    const passwordValue = password.value.trim();
    const password2Value = password2.value.trim();


    if (usernameValue === '') {
        setErrorFor(username, 'Username cannot be blank');
    }
    /* else if (registered(usernameValue) == "true") { //also check if database doesnt contain same username
                setErrorFor(username, 'Username is already taken');
            }*/
    else {
        setSuccessFor(username);
    }

    if (passwordValue === '') {
        setErrorFor(password, 'Password cannot be blank');
    } else if (!checkpassword(passwordValue)) {
        setErrorFor(password, 'Password must contain 4 to 10 characters which contain only characters, numeric digits, underscore and first character must be a letter');
    } else {
        setSuccessFor(password);
    }

    if (password2Value === '') {
        setErrorFor(password2, 'Password2 cannot be blank');
    } else if (passwordValue !== password2Value) {
        setErrorFor(password2, 'Passwords does not match');
    } else {
        setSuccessFor(password2);
    }
}

function checkLogInputs() {
    // trim to remove the whitespaces
    const usernameValue = username2.value.trim();
    const passwordValue = passwordLog.value.trim();



    if (usernameValue === '') {
        setErrorFor(username, 'Username cannot be blank');
    } else {
        setSuccessFor(username);
    }

    if (passwordValue === '') {
        setErrorFor(password, 'Password cannot be blank');
    } else if (!checkpassword(passwordValue)) {
        setErrorFor(password, 'Password must contain 4 to 10 characters which contain only characters, numeric digits, underscore and first character must be a letter');
    } else {
        setSuccessFor(password);
    }

}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    formControl.className = 'form-control error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}

function checkpassword(p) {
    var passw = /^[A-Za-z]\w{3,9}$/;
    if (p.match(passw)) {

        return true;
    } else {
        return false;
    }
}

function registered(usr) {

    $.ajax({
        url: '/checkUsername?usr=' + usr,
        dataType: 'text',
        success: function(r) {
            return r;
        }
    });
}