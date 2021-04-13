var post = {
    init: function () {
        var _this = this;
        $("#btn-save").on("click", function () {
            _this.save();
        });
        $("#btn-update").on("click", function () {
            _this.update();
        });
        $("#btn-delete").on("click", function () {
            _this.delete();
        });
        $(".postId").on("click", function () {
            _this.getData(this);
        })
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
        })
        .fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            title: $("#title").val(),
            content: $("#content").html(),
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
        })
        .fail(function (error) {
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
        })
        .fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    getData: function(obj){
        const id = $(obj).html();
        $.ajax({
            type:"GET",
            url:"/api/v1/posts/"+id,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
        }).done(function (data, textStatus, xhr) {
            console.log(data);
            $("#aauthor").html(data.author)
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
