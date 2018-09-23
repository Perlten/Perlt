window.setInterval(getMessages, 2000);
document.getElementById("send").addEventListener("click", postMessage);
document.addEventListener('DOMContentLoaded', postWelcome ,false);
document.addEventListener('DOMContentLoaded', function (){
    document.getElementById("headLine").innerText = "Chat Room Name : " + getCookie("chatRoomName");
} ,false);


function getMessages() {
    fetch("api/chat/" + getCookie("chatRoomName")).then(function (response) {
        return response.json();
    }).then(function (messageList) {
        var html = convertJsonToHtml(messageList);
        document.getElementById("chatBox").innerHTML = html;
    });
}

function convertJsonToHtml(messageList) {
    var html = "";
    messageList.forEach(function (message) {
        var name = message.name;
        var mes = message.message;

        html += '<div class="container">';
        html += name + ': ' + mes;
        html += '</div>';
    });
    return html;
}

function postMessage() {
    var name = getCookie("name");
    var message = document.getElementById("message").value;

    if (name === "" || message === "") {
        return;
    }
    document.getElementById("message").value = "";

    var object = {
        "name": name,
        "message": message
    };

    var json = JSON.stringify(object);
    console.log(json);

    fetch("api/chat/" + getCookie("chatRoomName"), {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: json
    }).then(getMessages);
}

function postWelcome() {
    var name = "Server";
    var message = getCookie("name") + " has joined the chatroom";

    var object = {
        "name": name,
        "message": message
    };

    var json = JSON.stringify(object);
    console.log(json);

    fetch("api/chat/" + getCookie("chatRoomName"), {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: json
    }).then(getMessages);
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
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


