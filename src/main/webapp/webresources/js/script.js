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
        } else if (password.value === ""){
            password.setAttribute("placeholder", "cant be enpty");
            password.style.background = "red";
        } else {
            $.post({
                url:"",
                data: logInForm
            });
        }
    });



    setInterval(function () {
        date = new Date(),
            h = date.getHours(),
            m = date.getMinutes(),
            s = date.getSeconds(),
            h = (h < 10) ? '0' + h : h,
            m = (m < 10) ? '0' + m : m,
            s = (s < 10) ? '0' + s : s,
            document.getElementById('time').innerHTML = h + ':' + m + ':' + s;
    }, 1000);

});
