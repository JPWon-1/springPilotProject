var profileImg = document.getElementById("profile_img")
var aboutMe = document.getElementById("about_me")
window.onscroll = function () {
    let value = window.pageYOffset / aboutMe.offsetTop + 1;
    if(value>1.34){return false}
    profileImg.style.transform = `scale(${value})`;
}

