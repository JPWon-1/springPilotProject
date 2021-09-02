var detail = {
    init: function init() {
        var _this = this;
        const submitBtn = document.getElementById("comment_submit");
        submitBtn.onclick = () => _this.request.post();
    },
    request: {
        post() {
            const postUrl = "/comment";
            const payload = {
                historyId: document.getElementById("historyId").value,
                content: document.getElementById("comment_textarea").value,
            }
            return fetch(postUrl, {
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
        delete(target) {
            const deleteUrl = "/comment/api";
            const el = target;
            const historyId = document.getElementById("historyId").value;
            const commentId = el.dataset.commentid

            return fetch(`${deleteUrl}/${historyId}/${commentId}`, {
                method: 'DELETE',
            }).then(function(){
                el.closest('ul').parentNode.remove()
            });
        }
    },

}

detail.init();