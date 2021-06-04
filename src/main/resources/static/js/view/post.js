var post = {
    init: function () {
        var jwt = localStorage.getItem('token');
        console.log(jwt)
        /* set ajax default value */
        $.ajaxSetup({
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("Authorization", jwt);
            },
        });
        var _this = this;
        $("#btn-write").on("click",function () {
            _this.toWrite();
        });
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
        });

    },
    toWrite: function () {
        console.log(localStorage.getItem("token"))
        $.ajax({
            headers: {
                "content-type": "application/json",
                Authorization: localStorage.getItem("token")
            },
            type: "GET",
            url: "/posts/write",
        }).done(function () {
            window.location.href = "/posts/write";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
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
post.init();
