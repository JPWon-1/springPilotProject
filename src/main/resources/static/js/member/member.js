var member = {
    init: function () {
        var _this = this;
        document.getElementById("signUp").onclick = function(){
            _this.save();
        };
        document.getElementById("loginBtn").onclick = function(){
            _this.login();
        };
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
        const payload = {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
        };
        const url = "/login";
        return fetch(url, {
            method: 'POST',
            headers: { 'content-Type': 'application/json' },
            body: JSON.stringify(payload)
        }).then(response => {
            return response.json();
        }).then(data => {
            console.log(data)
        })
    }
};
member.init();
