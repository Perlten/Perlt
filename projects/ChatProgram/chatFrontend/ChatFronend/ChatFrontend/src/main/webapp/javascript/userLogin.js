document.getElementById("loginbtn").addEventListener("click", login);

function login(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    if(!email || !password){
        document.getElementById("error").innerText = "Fill out all fields";
        return;
    } 

    var loginUser = {
        "email" : email,
        "password" : password
    }

    var opts = makeOpts("POST", loginUser);

    REST(rootURL + "user/login", function(response){
        console.log(response);
        sessionStorage.setItem("user", JSON.stringify(response));
        //TODO relocate to right location
        location.href = "index.html"
    }, opts);
}