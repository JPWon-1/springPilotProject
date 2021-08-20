var post = {
    init: function () {
        var jwt = localStorage.getItem('token');
        console.log(jwt)
        var _this = this;
        document.getElementById("btn_save").onclick = function () {
            _this.save();
        }
        document.getElementById("btn_update").onclick = function () {
            _this.update();
        }
        document.getElementById("btn_delete").onclick = function () {
            _this.delete();
        }

    },
    save: function () {
        var data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        $.ajax({
            type: "POST",
            url: "/api/v1/posts",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
        }).done(function () {
            alert("글이 등록되었습니다.");
            window.location.href = "/posts";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        var id = $("#id").val();
        console.log(data, id);
        $.ajax({
            type: "PUT",
            url: "/api/v1/posts/" + id,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
        }).done(function () {
            alert("글이 수정되었습니다.");
            window.location.href = "/posts/" + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var id = $("#id").html();
        $.ajax({
            type: "DELETE",
            url: "/api/v1/posts/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
        }).done(function () {
            alert("글이 삭제되었습니다.");
            window.location.href = "/posts";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};
post.init();
