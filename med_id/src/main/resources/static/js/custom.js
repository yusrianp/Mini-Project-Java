// $(document).ready(SignUp(1))

$('#formLogin').parsley();
$('#formEmail').parsley();
$('#formOTP').parsley();
$('#formPassword').parsley();

//has uppercase
window.Parsley.addValidator('uppercase', {
    requirementType: 'number',
    validateString: function(value, requirement) {
        var uppercases = value.match(/[A-Z]/g) || [];
        return uppercases.length >= requirement;
    },
    messages: {
        en: 'Password harus mengandung huruf besar.'
    }
});

//has lowercase
window.Parsley.addValidator('lowercase', {
    requirementType: 'number',
    validateString: function(value, requirement) {
        var lowecases = value.match(/[a-z]/g) || [];
        return lowecases.length >= requirement;
    },
    messages: {
        en: 'Password harus mengandung huruf kecil.'
    }
});

//has number
window.Parsley.addValidator('number', {
    requirementType: 'number',
    validateString: function(value, requirement) {
        var numbers = value.match(/[0-9]/g) || [];
        return numbers.length >= requirement;
    },
    messages: {
        en: 'Password harus mengandung angka.'
    }
});

//has special char
window.Parsley.addValidator('special', {
    requirementType: 'number',
    validateString: function(value, requirement) {
        var specials = value.match(/[^a-zA-Z0-9]/g) || [];
        return specials.length >= requirement;
    },
    messages: {
        en: 'Password harus mengandung symbol.'
    }
});

function LupaPassword()
{
    console.log("Lupa password")
}

function OpenRegister()
{
    $('#loginModal').modal('toggle')

    setTimeout(function () {
        $('#registerModal').modal('toggle')
    }, 1000);
}

$('.checkEmail').on('submit', function (e) {

    const checkEmail = '{ "email" : "'+$('#emailAdress').val()+'" }'

    let EmailTrigger = ''
    EmailTrigger += '<button class="btn btn-primary" type="button" disabled>'
    EmailTrigger += '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"> </span>'
    EmailTrigger += ' Loading...'
    EmailTrigger += '</button>'

    $('.EmailTrigger').html(EmailTrigger)

    $.ajax({
        url: '/api/v1/user/checkemail',
        type: 'POST',
        contentType: "application/json",
        data: checkEmail,
        statusCode: {
            302: function (response) {

                let buttonEmail = ''
                buttonEmail += '<div class="text-center EmailTrigger">'
                buttonEmail += '<button type="submit" class="btn btn-primary">Kirim OTP</button>'
                buttonEmail += '</div>'

                let exist = ''
                exist += '<div class="form-group emailAdress">'
                exist += '<label></label>'
                exist += '<input type="email" class="form-control text-center is-invalid" value="'+ $('#emailAdress').val() + '" id="emailAdress" data-parsley-type="email" required>'
                exist += '<div class="invalid-feedback">Email sudah digunakan</div>'
                exist += '</div>'

                setTimeout(function () {
                    $('.EmailTrigger').html(buttonEmail)
                    $('.emailAdress').html(exist)
                }, 2000)
            }
        },
        success: function(responseEmail) {

            setTimeout(function(){
                $('#registerModal').modal('toggle')

                setTimeout(function () {
                    VerifOTP(responseEmail.userId)
                }, 800)

            }, 2000);
        },
        // error: function (xhr, statu)

    });

    e.preventDefault()
})

function VerifOTP(id)
{
    $('#OTPModal').modal('toggle')

    let userId = '<input type="hidden" id="userId" value="' + id + '" >'
    $('.userId').html(userId)

    Countdown();

    $('.OTPCode').on('submit', function (e) {

        let OTPTrigger = ''
        OTPTrigger += '<button class="btn btn-primary" type="button" disabled>'
        OTPTrigger += '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"> </span>'
        OTPTrigger += ' Loading...'
        OTPTrigger += '</button>'

        $('.OTPTrigger').html(OTPTrigger)

        $.ajax({
            url: '/api/v1/token/tokenverification/' + $('#OTPCode').val(),
            type: 'GET',
            contentType: 'application/json',
            success: function (responseOTP) {

                let OTPStatus = '{ "expired" : true, "delete" : true }'

                $.ajax({
                    url: '/api/v1/token/changetokenstatus/' + $('#userId').val(),
                    type: 'PUT',
                    contentType: 'application/json',
                    data: OTPStatus,
                    success: function (OTPStatus)
                    {
                        setTimeout(function(){
                            $('#OTPModal').modal('toggle')

                            setTimeout(function(){
                                CreatePassword($('#userId').val())
                            }, 800);

                        }, 2000);
                    }
                })
            }
        });

        e.preventDefault()

    })
}

function CreatePassword(id)
{

    $('#passwordModal').modal('toggle')

    let userId = '<input type="hidden" id="userId" value="' + id + '" >'
    $('.userId').html(userId)

    $('.createPassword').on('submit', function (e) {
        let passwordRequest = '{ "password" : "'+ $('#password').val() +'" }'

        let PasswordTrigger = ''
        PasswordTrigger += '<button class="btn btn-primary" type="button" disabled>'
        PasswordTrigger += '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"> </span>'
        PasswordTrigger += ' Loading...'
        PasswordTrigger += '</button>'

        $('.PasswordTrigger').html(PasswordTrigger)

        $.ajax({
            url: '/api/v1/user/createpassword/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: passwordRequest,
            success: function (responsePassword) {

                setTimeout(function(){
                    $('#passwordModal').modal('toggle')

                    setTimeout(function(){
                        SignUp($('#userId').val())
                    }, 800);

                }, 2000);
            }
        });

        e.preventDefault()
    })

}

function SignUp(id)
{
    $('#signUpModal').modal('toggle')

    let userId = '<input type="hidden" id="userId" value="' + id + '" >'
    $('.userId').html(userId)

    // Get role
    $.ajax({
        url: '/api/v1/roles',
        type: 'GET',
        contentType: 'application/json',
        success: function (role) {
            let employeeRole = '<option>-- Pilih --</option>'
            for (let i = 0; i < role.length; i++) {
                employeeRole += "<option value='" + role[i].id + "'>" + role[i].name + "</option>"
            }
            $('.role').html(employeeRole);
        }
    });

    $('.formSignUp').on('submit', function (e) {

        let biodataRequest = '{'
        biodataRequest += ' "fullname" : "' +$('#fullName').val()+ '", '
        biodataRequest += ' "mobilePhone" : "' +$('#mobilePhone').val()+ '" '
        biodataRequest += '}'

        let roleRequest = '{ "roleId" : ' +$('#roleId').val()+ ' }'

        let SignUpTrigger = ''
        SignUpTrigger += '<button class="btn btn-primary" type="button" disabled>'
        SignUpTrigger += '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"> </span>'
        SignUpTrigger += ' Loading...'
        SignUpTrigger += '</button>'

        $('.SignUpTrigger').html(SignUpTrigger)

        $.ajax({
            url: '/api/v1/user/createuserbiodata/' + $('#userId').val(),
            type: 'PUT',
            contentType: 'application/json',
            data: biodataRequest,
            success: function (responseBiodata) {
                $.ajax({
                    url: '/api/v1/user/addroleuser/' + $('#userId').val(),
                    type: 'PUT',
                    contentType: 'application/json',
                    data: roleRequest,
                    success: function (responseRole) {

                        setTimeout(function(){
                            $('#signUpModal').modal('toggle')

                            Swal.fire({
                                title: 'Selamat!',
                                text: 'Akun berhasil dibuat!',
                                icon: 'success',
                                timer: 2500,
                                showConfirmButton: false,
                            })
                        }, 2000);

                    }
                });
            }
        });

        e.preventDefault()

    })

}

let OTPCountdown
let minutes = 3
let seconds = 0
function Countdown()
{
    OTPCountdown = setInterval(function () {
        if (seconds > 0) {
            seconds--
        }
        else if (minutes > 0) {
            seconds = 59
            minutes--
        }
        else {
            $("#myBtn").removeAttr('style', 'display: none').attr('style', 'cursor: pointer')
            $("#myTimer").fadeTo(100, 0)
        }

        // Show the time left
        $('#myTimer').html("Kirim ulang OTP dalam " + minutes + ":" + seconds);

    }, 1000);
}

$('#myBtn').on('click', function (){
    RequestOTP()
    clearInterval(OTPCountdown);

    setTimeout(function () {
        minutes = 3
        seconds = 0

        $("#myBtn").removeAttr('style', 'cursor: pointer').attr('style', 'display: none')
        $("#myTimer").fadeTo(0, 100)

        Countdown()
    }, 2000)
})

function RequestOTP()
{
    $.ajax({
        url: '/api/v1/token/requestnewtoken/' + $('#userId').val(),
        type: 'PUT',
        contentType: 'application/json',
        success: function (responseNewOTP)
        {

        }
    })
}

let url = new URL(window.location.href)
let getParam = url.searchParams.get("error")

if (getParam == 'true')
{
    $('#loginModal').modal('toggle')
    let error = '<p class="text-danger text-center"> email atau password salah </p>'
    $('.error').html(error)

    $('#loginModal').modal('toggle')

}

function Logout()
{
    $.ajax({
        url: '/logout',
        contentType: 'application/json',
        success: function (logout)
        {
           window.location.replace("/")
        }
    })
}
