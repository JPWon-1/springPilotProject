var member = {
    init: function () {
        var _this = this;
        $("#signUp").on("click", function () {
            _this.save();
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
            // dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
        }).done(function (data,textStatus,xhr) {
            console.log(data);
            console.log(textStatus);
            alert("회원가입이 완료되었습니다.")
        })
        .fail(function (xhr,textStatus,errorThrown) {
            console.log(xhr);
            console.log(textStatus);
            alert("회원가입에 실패했습니다.");
        });
    },
};
member.init();
