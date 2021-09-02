var join = {
    init: function () {
        var _this = this;
        document.getElementById("signUp").onclick = function(){
            _this.save();
        };
    },
    save: function () {
        const payload = {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
        };
        const url = "/signUp";
        return fetch(url, {
            method: 'POST',
            headers: { 'content-Type': 'application/json' },
            body: JSON.stringify(payload)
        }).then(response => {
            return response.json();
        }).then(data => {
            console.log(data)
        })
    },
};
join.init();
