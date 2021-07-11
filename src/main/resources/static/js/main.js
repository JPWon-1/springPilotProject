var main = {
    init: function init() {
        var _this = this;
        const contents = document.getElementsByClassName('content');
        Array.from(contents).forEach(function (element) {
            element.onclick = () => _this.request.get(element.id)
                .then(response => response.json())
                .then(detail=>{console.log(detail);
                    document.getElementById("detail_date").innerText = detail.date;
                    document.getElementById("detail_content").innerText = detail.content;
                    document.getElementById("detail_source").innerText = detail.source;
                })
                .catch(err=>console.log(err))
            ;
        })
    },
    request: {
        get(id) {
            return fetch(`history/api/v1/${id}`);
        },
        post(url, payload) {
            return fetch(url, {
                method: 'POST',
                headers: { 'content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
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

main.init();