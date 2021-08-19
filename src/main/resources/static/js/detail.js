var detail = {
    init: function init() {
        var _this = this;
        const postUrl = "/comment";
        const submitBtn = document.getElementById("comment_submit");
        submitBtn.onclick = () => _this.request.post(postUrl);

    },
    request: {
        post(url) {
            var payload = {
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
                document.getElementById("comment_list").innerHTML= fragments
            })
        },
        patch(url, payload) {
            return fetch(url, {
                method: 'PATCH',
                headers: { 'content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
        },
        delete(url) {
            return fetch(url, { method: 'DELETE' });
        }
    },

}

detail.init();