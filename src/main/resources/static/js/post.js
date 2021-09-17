var post = {
    init: function () {

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
        const jwt = localStorage.getItem('token');
        const url = "/post/api/v1/posts";
        const payload = {
            title: document.getElementById("title").value,
            content: document.getElementById("content").value,
        };
        return fetch(url, {
            method: 'POST',
            headers: {
                'content-Type': 'application/json',
                Authorization: jwt,
            },
            body: JSON.stringify(payload)
        }).then(response => {
            if (response.ok) {
                return response.text();
            }
            return response.json().then(json=>{
                throw new Error(json.message)
            })
        }).then(data => {
            console.log(data)
        }).catch(function (error) {
            console.log(error.message);
        })
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
