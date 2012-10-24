$(function() { 
 
    $('#reg-form\\:btn-submit').click(function() {  
 
        $(".error").hide();
        var hasError = false;
        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        
        var fullnameVal = $("#reg-form\\:username").val();
        if(fullnameVal == '') {
            $("#reg-form\\:username").after('<span class="error">Please enter a username.</span>');
            hasError = true;
        }
        
        var fullnameVal = $("#reg-form\\:fullname").val();
        if(fullnameVal == '') {
            $("#reg-form\\:fullname").after('<span class="error">Please enter your real name.</span>');
            hasError = true;
        }
        
        var emailaddressVal = $("#reg-form\\:mail").val();
        if(emailaddressVal == '') {
            $("#reg-form\\:mail").after('<span class="error">Please enter your email address.</span>');
            hasError = true;
        }
 
        else if(!emailReg.test(emailaddressVal)) {
            $("#reg-form\\:mail").after('<span class="error">Enter a valid email address.</span>');
            hasError = true;
        }
 
        var passwordVal = $("#reg-form\\:passwd").val();
        var checkVal = $("#reg-form\\:pwrepeat").val();
        if (passwordVal == '') {
            $("#reg-form\\:passwd").after('<span class="error">Please enter a password.</span>');
            hasError = true;
        } else if (checkVal == '') {
            $("#reg-form\\:pwrepeat").after('<span class="error">Please re-enter your password.</span>');
            hasError = true;
        } else if (passwordVal != checkVal ) {
            $("#reg-form\\:pwrepeat").after('<span class="error">Passwords do not match.</span>');
            hasError = true;
        }
        
        if (hasError == true) { 
            return false; 
        }
 
    });
});