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
            if(response.ok){
                return response.json();
            }
            return response.json().then(json=>{
                throw new Error(json.message)
            })
        }).then(data => {
            alert(`${data.response}님 회원가입이 완료되었습니다.`)
            window.location.href="/login"
        }).catch(function(error){
            alert(error.message)
        })
    },
};
join.init();
