var detail = {
    init: function init() {
        var _this = this;
        const postUrl = "/comment";
        const deleteUrl = "/comment/api";
        const submitBtn = document.getElementById("comment_submit");
        const deleteBtn = document.getElementsByClassName("comment_delete");
        for(let btn of deleteBtn){
            btn.addEventListener('click',function(e){
                _this.request.delete(e,deleteUrl);
            })
        }
        submitBtn.onclick = () => _this.request.post(postUrl);
    },
    request: {
        post(url) {
            const payload = {
                historyId: document.getElementById("historyId").value,
                content: document.getElementById("comment_textarea").value,
            }
            return fetch(url, {
                method: 'POST',
                headers: { 'content-Type': 'application/json' },
                body: JSON.stringify(payload)
            }).then(response => {
                return response.text();
            }).then(fragments => {
                document.getElementById("comment_list").innerHTML = fragments
            })
        },
        patch(url, payload) {
            return fetch(url, {
                method: 'PATCH',
                headers: { 'content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
        },
        delete(e,url) {
            const el = e.target;
            const historyId = document.getElementById("historyId").value;
            const commentId = el.dataset.commentid

            return fetch(`${url}/${historyId}/${commentId}`, {
                method: 'DELETE',
            }).then(function(){
                el.closest('ul').parentNode.remove()
            });
        }
    },

}

detail.init();