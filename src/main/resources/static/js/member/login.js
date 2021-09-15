var login = {
    init: function () {
        var _this = this;
        document.getElementById("loginBtn").onclick = function(){
            _this.login();
        };
    },
    login: function (){
        const payload = {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
        };
        const url = "/login";
        return fetch(url, {
            method: 'POST',
            headers: { 'content-Type': 'application/json' },
            body: JSON.stringify(payload)
        }).then(response => {
            if(response.ok){
                return response.json();
            }
            return response.json().then(json=>{
                throw new Error(json.message)
            })
        }).then(data => {
            localStorage.setItem("token",data.Authorization)
            console.log(data)
        }).catch(function(error){
            alert(error.message)
        })
    }
};
login.init();
