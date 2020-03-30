$(document).ready(function () {


    var token = document.getElementById("token");

    var logIn = document.getElementById("logIn");
    var logOut = document.getElementById("logOut");


    if (token.value !== "null") {
        logIn.style.display = "none";
        logOut.style.display = "block";
    } else {
        logIn.style.display = "block";
        logOut.style.display = "none";
    }



    // e.preventDefault();
    $(".logInBtn").click(function (e) {
        var phoneNumber = document.getElementById("phoneNumber");
        var password = document.getElementById("password");

        var logInForm = {
            "phoneNumber": phoneNumber.value,
            "password": password.value
        };

        if (phoneNumber.value === ""){
            e.preventDefault();
            phoneNumber.setAttribute("placeholder", "cant be enpty");
            phoneNumber.style.background = "red";
        } else {
            $.post({
                url:"",
                data: logInForm,
                success: function (response) {
                    alert(response.header("user"));
                }
            });
            alert("Successes");
        }

    });


    // type: $(this).attr('method'),
    //     action: $(this).attr('action'),

    //////// CREATE USER ////////
    // $(".logI").submit(function (e) {
    //     var loginInput = document.getElementById("login").value;
    //     var password = document.getElementById("password").value;
    //     var firstName = document.getElementById("firstName").value;
    //     var lastName = document.getElementById("lastName").value;
    //     var email = document.getElementById("email").value;
    //     var phoneNumber = document.getElementById("phoneNumber").value;
    //     var personalInfo = document.getElementById("personalInfo").value;
    //     var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    //     if (password.length < 4) {
    //         alert("Enter correct password")
    //     } else if (reg.test(email) === false) {
    //         alert('Enter correct e-mail');
    //     } else {
    //         alert("alert user 4");
    //         var user = {
    //             "login": loginInput,
    //             "password": password,
    //             "firstName": firstName,
    //             "lastName": lastName,
    //             "email": email,
    //             "phoneNumber": phoneNumber,
    //             "personalInfo": personalInfo
    //         };
    //         alert("alert user 5");
    //         e.preventDefault();
    //         alert("alert user 6");
    //         $.ajax({
    //             type: $(this).attr('method'),
    //             url: "/user/create",
    //             // data: $(this).serialize(),
    //             data: JSON.stringify(user),
    //             // async: false,
    //             contentType: "application/json",
    //             success: function (result) {
    //
    //                 alert("User success created");
    //             }
    //         });
    //         alert(user.email + user.firstName);
    //         alert("alert user 7");
    //     }
    // });
    //////// END CREATE USER ////////


    // var namePage = document.getElementById("namePage");
    // var access = document.getElementById("access");
    //
    //
    // var homePage = document.getElementById("homePage");
    // var createTeamLink = document.getElementById("createTeamLink");
    // var createEventLink = document.getElementById("createEventLink");
    // var registrationLink = document.getElementById("registrationLink");
    // var logInLink = document.getElementById("logInLink");
    // var logOutLink = document.getElementById("logOutLink");
    //
    //
    // if (token.value === "0") {
    //     if (createTeamLink != null){
    //         createTeamLink.style.display = "none";
    //     }
    //     if (createEventLink != null){
    //         createEventLink.style.display = "none";
    //     }
    //     if (logOutLink != null){
    //         logOutLink.style.display = "none";
    //     }
    //     if (homePage != null){
    //         homePage.style.display = "none";
    //     }
    // } else {
    //     if (registrationLink != null){
    //         registrationLink.style.display = "none";
    //     }
    //     if (logInLink != null){
    //         logInLink.style.display = "none";
    //     }
    // }
    //
    // if (namePage.value === "user") {
    //     homePage.style.display = "none";
    // }
    //
    // if (access != null){
    //     if (access.value === "0" ){
    //         document.getElementById("changeInformationBottom").style.display = "none";
    //         document.getElementById("setAwardsBottom").style.display = "none";
    //         document.getElementById("setMetadata").style.display = "none";
    //     }
    // }


    //////// WORK WITH FORM CHANGE EVENT ////////
    // OPEN FORM CREATE EVENT
    $(".changeEventInfoLink").click(function () {
        $(".createEventForChangeInfo").fadeIn("fast");
        $(".linkForEnter").fadeOut("fast");
        $("section").css("filter", "blur(5px)");
    });
    //CLOSE FORM CREATE EVENT
    $(".exitFromChangeEvent").click(function () {
        $(".createEventForChangeInfo").fadeOut("fast");
        $(".linkForEnter").fadeIn("fast");
        $("section").css("filter", "none");
    });
    //////// END WORK WITH FORM CHANGE EVENT ////////


});
