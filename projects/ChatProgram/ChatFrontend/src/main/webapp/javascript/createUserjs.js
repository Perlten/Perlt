


document.getElementById("createbtn").addEventListener("click", createUser);
document.getElementById("images").addEventListener("click", selectImage)

var imageId = 0;

function createUser() {
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    console.log(firstname);
    console.log(lastname);
    console.log(email);
    console.log(password);

    console.log(imageId);

    if (!firstname || !lastname || !email || !password || !imageId) {
        console.log("FALSE");
        document.getElementById("error").innerText = "Not all fields are filled"
        return;
    }

    var newUser = {
        "firstName": firstname,
        "lastName": lastname,
        "email": email,
        "avatar": imageId,
        "password": password
    }

    var opts = makeOpts("POST", newUser);
    console.log(rootURL);
    REST(rootURL + "user", function (response) {
        sessionStorage.setItem("user", JSON.stringify(response));
        console.log(sessionStorage.getItem("user"));
        location.href = "chatRooms.html";
    }, opts);


}

function selectImage(event) {
    if (imageId != 0) {
        document.getElementById(imageId).classList.remove('highlight');
    }
    imageId = event.target.id;
    document.getElementById(imageId).classList.add('highlight');
}


