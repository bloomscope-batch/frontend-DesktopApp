function validate_form(user_type){

    const email = document.getElementById("email-input").value;
    const phone = document.getElementById("phone-input").value;
    const pwd = document.getElementById("pwd-input").value;
    const conf_pwd = document.getElementById("conf-pwd-input").value;
    let form = "valid"

    if (email === ""){
        form = "invalid";
        // text above input to fill out the field
    } if (phone === "" || phone.length !== 10){
        form = "invalid";
        // phone input invalid
    } if (pwd === "" || pwd !== conf_pwd){
        form = "invalid";
        // pwd input invalid
    }
    
}