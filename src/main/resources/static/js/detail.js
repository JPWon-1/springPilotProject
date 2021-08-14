var detail = {
    init: function init() {
        var _this = this;
        const apiUrl = "/comment/api";
        const submitBtn = document.getElementById("comment_submit");
        submitBtn.onclick = () => _this.request.post(apiUrl);

    },
    request: {
        post(url) {
            var payload = {
                historyId:document.getElementById("historyId").value,
                // memberId:document.getElementById("memberId").value,
                memberId:0,
                content:document.getElementById("comment_textarea").value,
            }

            return fetch(url, {
                method: 'POST',
                headers: { 'content-Type': 'application/json' },
                body: JSON.stringify(payload)
            }).then(response =>
                console.log(response))
            ;
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