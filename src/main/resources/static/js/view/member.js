var member = {
    init: function () {
        var _this = this;
        $("#signUp").on("click", function () {
            _this.save();
        });
        $("#loginBtn").on("click", function () {
            _this.login();
        });
    },
    save: function () {
        var data = {
            email: $("#email").val(),
            password: $("#password").val(),
        };
        $.ajax({
            type: "POST",
            url: "/signUp",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
        }).done(function (response,textStatus,xhr) {
            console.log(response);
            console.log(textStatus);
            alert("회원가입이 완료되었습니다.")
        }).fail(function (response) {
            alert(response.responseJSON.message);
        });
    },
    login: function (){
        var data = {
            email: $("#email").val(),
            password: $("#password").val(),
        };
        $.ajax({
            type: "POST",
            url: "/login",
            // dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
        }).done(function (response,textStatus,xhr) {
            console.log(response);
            console.log(textStatus);
            alert("로그인 되었습니다.")

            localStorage.setItem('token',response.Authorization);


        }).fail(function (response) {
            alert(response.responseJSON.message);
        });
    }
};
member.init();
