var main = {
    init: function init() {
        var _this = this;
        const contents = document.getElementsByClassName('content');
        Array.from(contents).forEach(function (element) {
            element.onclick = () => _this.request.get(element.id);
        })
    },
    request: {
        get(id) {
            window.location.href = `/${id}`;
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