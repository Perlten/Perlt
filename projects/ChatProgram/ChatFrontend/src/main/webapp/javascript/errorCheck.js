function checkSession() {
    if (!sessionStorage.getItem("user")) {
        location.href = "index.html";
    }
}