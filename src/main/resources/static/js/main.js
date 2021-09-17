var main = {
    init: function init() {
        var _this = this;

        document.addEventListener('click', function (e) {
            if (e.target && e.target.classList.contains('content')) {
                _this.request.get(e.target.id);
            }
        })

        const date = document.getElementsByClassName("date-input");
        Array.from(date).forEach(function (element) {
            element.addEventListener("change", function () {
                const month = document.getElementById("month").value;
                const day = document.getElementById("day").value;
                _this.request.getByDate(month, day);
            })
        })

    },
    request: {
        get(id) {
            window.location.href = `/${id}`;
        },

        getByDate(month, day) {
            const url = `/date?month=${month}&day=${day}`;
            fetch(url, {
                method: 'GET',
            }).then(response => {
                if (response.ok) {
                    return response.text();
                }
                return response.json().then(json => {
                    throw new Error(json.message)
                })
            }).then(fragments => {
                document.querySelector("table").innerHTML = fragments;
            }).catch(function (error) {
                alert(error.message);
            })
        }
    },

}

main.init();