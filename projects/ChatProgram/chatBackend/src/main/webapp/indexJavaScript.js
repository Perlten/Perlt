const ERROR = document.getElementById("error");

document.getElementById("join").addEventListener("click", joinChatRoom);
document.getElementById("create").addEventListener("click", createChatRoom);

window.addEventListener("load", function () {
    document.getElementById("name").value = getCookie("name");
});

window.addEventListener("load", createPublicRoomHtml);
window.setInterval(createPublicRoomHtml, 5000);

window.setInterval(function () {
    var name = document.getElementById("name").value;
    if (name === "" || name === " ") {
        document.getElementById("forms").style.visibility = "hidden";
    }else{
        document.getElementById("forms").style.visibility = "visible";
    }
}, 20);
function createChatRoom() {
    var name = document.getElementById("name").value;
    var chatRoomName = document.getElementById("createChatRoomName").value;
    var publicRoom = document.getElementById("publicRoom").checked;

    if (name === "" || chatRoomName === "") {
        ERROR.innerText = "Error You need a name";
        return;
    }

    if (name === " " || chatRoomName === " ") {
        ERROR.innerText = "Error fields cant be empty!";
        return;
    }

    var jsonObject = {
        "name": chatRoomName,
        "owner": name,
        "publicRoom": publicRoom
    };

    var json = JSON.stringify(jsonObject);

    fetch("api/chat/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: json
    }).then(function (response) {
        if (response.status === 200) {
            createNameAndRoomCookie(name, chatRoomName);
            window.location.href = "chat.html";
        } else {
            ERROR.innerText = "Error room already exits";
        }
    });

}


function joinChatRoom() {
    var name = document.getElementById("name").value;
    var chatRoomName = document.getElementById("joinChatRoomName").value;

    if (name === "" || chatRoomName === "") {
        ERROR.innerText = "Error You need a name";
        return;
    }

    if (name === " " || chatRoomName === " ") {
        ERROR.innerText = "Error fields cant be empty!";
        return;
    }

    fetch("api/chat/" + chatRoomName).then(function (response) {
        if (response.status === 200) {
            createNameAndRoomCookie(name, chatRoomName)
            window.location.href = "chat.html";
        } else {
            ERROR.innerText = "Could not find room";
        }
    });
}

function changeToPublicRoom(chatRoomName) {
    var name = document.getElementById("name").value;
    if (name === "") {
        ERROR.innerText = "Error You need a name";
        return;
    }
    createNameAndRoomCookie(name, chatRoomName);
    window.location.href = "chat.html";
}

function createPublicRoomHtml() {
    fetch("api/chat/publicRooms").then(function (response) {
        return response.json();
    }).then(function (json) {
        var html = "";
        json.forEach(function (element) {
            html += "<p> Chat room name : " + element.name;
            html += "<br> Chat room owner : " + element.owner;
            html += "</p>";
            console.log(element.name);
            html += '<input type="submit" onclick="changeToPublicRoom(\'' + element.name + '\')" value="Join"/>';
            console.log("end");
        });

        document.getElementById("createdRooms").innerHTML = html;
    });
}


function createNameAndRoomCookie(name, chatRoomName) {
    createCookie("name", name);
    createCookie("chatRoomName", chatRoomName);
}


function createCookie(name, value) {
    document.cookie = name + "=" + value;
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


