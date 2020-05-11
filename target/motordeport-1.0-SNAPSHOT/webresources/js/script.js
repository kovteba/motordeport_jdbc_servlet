$(document).ready(function () {


  var userToken = document.getElementById("userToken");

  var logIn = document.getElementById("logIn");

  var logOut = document.getElementById("logOut");

  if (userToken.value !== "0") {
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

    if (phoneNumber.value === "") {
      e.preventDefault();
      phoneNumber.setAttribute("placeholder", "cant be enpty");
      phoneNumber.style.background = "red";
    } else if (password.value === "") {
      password.setAttribute("placeholder", "cant be enpty");
      password.style.background = "red";
    } else {
      $.post({
        url: "",
        data: logInForm
      });
    }
  });

  $(".registration").click(function (e) {

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    var emailAdmin = document.getElementById("emailAdmin");
    var firstNameAdmin = document.getElementById("firstNameAdmin");
    var lastNameAdmin = document.getElementById("lastNameAdmin");
    var phoneNumberAdmin = document.getElementById("phoneNumberAdmin");
    var passwordAdmin = document.getElementById("passwordAdmin");
    var secretCode = document.getElementById("secretCode");

    if (reg.test(emailAdmin.value) === false) {
      emailAdmin.setAttribute("placeholder", "enter correct email");
      emailAdmin.style.background = "red";
      e.preventDefault();
    } else {
      if (firstNameAdmin.value.length < 4) {
        firstNameAdmin.setAttribute("placeholder", "Name very shot");
        firstNameAdmin.style.background = "red";
        e.preventDefault();
      } else {
        if (lastNameAdmin.value.length < 4) {
          lastNameAdmin.setAttribute("placeholder", "Surname very shot");
          lastNameAdmin.style.background = "red";
          e.preventDefault();
        } else {
          if (phoneNumberAdmin.value.length !== 10) {
            phoneNumberAdmin.setAttribute("placeholder", "incorrect phone number");
            phoneNumberAdmin.style.background = "red";
            e.preventDefault();
          } else {
            if (passwordAdmin.value.length < 6) {
              passwordAdmin.setAttribute("placeholder", "Password very short");
              passwordAdmin.style.background = "red";
              e.preventDefault();
            } else {
              if (secretCode.value.length === 0) {
                secretCode.setAttribute("placeholder", "Secret code cant be null");
                secretCode.style.background = "red";
                e.preventDefault();
              } else {
                var registration = {
                  "emailAdmin": emailAdmin.value,
                  "firstNameAdmin": firstNameAdmin.value,
                  "lastNameAdmin": lastNameAdmin.value,
                  "phoneNumberAdmin": phoneNumberAdmin.value,
                  "passwordAdmin": passwordAdmin.value,
                  "secretCode": secretCode.value
                };
                $.post({
                  url: "registration",
                  data: registration
                });
              }
            }
          }
        }
      }
    }
  });

  $(".createDispatcher").click(function (e) {

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    var firstNameDispatcher = document.getElementById("firstNameDispatcher");
    var lastNameDispatcher = document.getElementById("lastNameDispatcher");
    var phoneNumberDispatcher = document.getElementById("phoneNumberDispatcher");
    var passwordDispatcher = document.getElementById("passwordDispatcher");
    var emailDispatcher = document.getElementById("emailDispatcher");

    if (reg.test(emailDispatcher.value) === false) {
      emailDispatcher.setAttribute("placeholder", "enter correct email");
      emailDispatcher.style.background = "red";
      e.preventDefault();
    } else {
      if (firstNameDispatcher.value.length < 4) {
        firstNameDispatcher.setAttribute("placeholder", "Name very shot");
        firstNameDispatcher.style.background = "red";
        e.preventDefault();
      } else {
        if (lastNameDispatcher.value.length < 4) {
          lastNameDispatcher.setAttribute("placeholder", "Surname very shot");
          lastNameDispatcher.style.background = "red";
          e.preventDefault();
        } else {
          if (phoneNumberDispatcher.value.length !== 10) {
            phoneNumberDispatcher.setAttribute("placeholder", "incorrect phone number");
            phoneNumberDispatcher.style.background = "red";
            e.preventDefault();
          } else {
            if (passwordDispatcher.value.length < 6) {
              passwordDispatcher.setAttribute("placeholder", "Password very short");
              passwordDispatcher.style.background = "red";
              e.preventDefault();
            }
          }
        }
      }
    }
  });

  $(".createDriver").click(function (e) {

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    var firstNameDriver = document.getElementById("firstNameDriver");
    var lastNameDriver = document.getElementById("lastNameDriver");
    var phoneNumberDriver = document.getElementById("phoneNumberDriver");
    var passwordDriver = document.getElementById("passwordDriver");
    var emailDriver = document.getElementById("emailDriver");

    if (reg.test(emailDriver.value) === false) {
      emailDriver.setAttribute("placeholder", "enter correct email");
      emailDriver.style.background = "red";
      e.preventDefault();
    } else {
      if (firstNameDriver.value.length < 4) {
        firstNameDriver.setAttribute("placeholder", "Name very shot");
        firstNameDriver.style.background = "red";
        e.preventDefault();
      } else {
        if (lastNameDriver.value.length < 4) {
          lastNameDriver.setAttribute("placeholder", "Surname very shot");
          lastNameDriver.style.background = "red";
          e.preventDefault();
        } else {
          if (phoneNumberDriver.value.length !== 10) {
            phoneNumberDriver.setAttribute("placeholder", "incorrect phone number");
            phoneNumberDriver.style.background = "red";
            e.preventDefault();
          } else {
            if (passwordDriver.value.length < 6) {
              passwordDriver.setAttribute("placeholder", "Password very short");
              passwordDriver.style.background = "red";
              e.preventDefault();
            }
          }
        }
      }
    }
  });
  // (\D{2}\d{4}\D{2})
  $(".addNewCarBtn").click(function (e) {
    var reg = /^(\D{2}\d{4}\D{2})$/;
    var carNumber = document.getElementById("carNumber");
    if (reg.test(carNumber.value) === false) {
      carNumber.setAttribute("placeholder", "Incorrect car number");
      carNumber.style.background = "red";
      e.preventDefault();
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

})
;
