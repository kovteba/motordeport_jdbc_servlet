$(document).ready(function () {

    var token = document.getElementById("token");

    var logIn = document.getElementById("logIn");
    var logOut = document.getElementById("logOut");

    if (token.value != null){
        logIn.style.display = "none";
        logOut.style.display = "block";
    } else {
        logIn.style.display = "block";
        logOut.style.display = "none";
    }

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
