const DEFAULTAMOUNT = 5;

setInterval(function () {
    checkSession();
    getMessages(DEFAULTAMOUNT);
}, 2000);

document.addEventListener('DOMContentLoaded', function () {
    checkSession();
    getMessages(DEFAULTAMOUNT);
}, false);


function createOwnChatHtml(message) {
    var imageId = JSON.parse(sessionStorage.getItem("user")).avatar;
    var fullName = message.sender.firstName + " " + message.sender.lastName;
    var messageText = message.message;
    var html = ' <div class="col-md-12">' +
        '<div class="container">' +
        '<img src="resources/images/avatar' + imageId + '.png" alt="Avatar">' +
        '<p>' + "Name: " + fullName + "<br>" + messageText + '</p>' +
        '</div>' +
        '</div>'

    document.getElementById("chat").innerHTML += html;
}

function createOtherChatHtml(message) {
    var imageId = message.sender.avatar;
    var fullName = message.sender.firstName + " " + message.sender.lastName;
    var messageText = message.message;
    var html = '<div class="col-md-12">' +
        '<div class="container">' +
        '<img src="resources/images/avatar' + imageId + '.png" alt="Avatar" class="right">' +
        '<p>' + "Name: " + fullName + "<br>" + messageText + '</p>' +
        '</div>' +
        '</div>'
    document.getElementById("chat").innerHTML += html;
}

function getMessages(amount) {
    var room = sessionStorage.getItem("room");
    var url = rootURL + "chatRoom/message/receive/" + room + "/" + amount;
    REST(url, function (data) {
        data.reverse();
        var user = JSON.parse(sessionStorage.getItem("user"));
        document.getElementById("chat").innerHTML = "";
        data.forEach(message => {
            if (message.sender.id === user.id) {
                createOwnChatHtml(message);
            } else {
                createOtherChatHtml(message);
            }
        });
    });
}

document.getElementById("sendForm").addEventListener("submit", sendMessage);

function sendMessage() {
    var text = document.getElementById("text").value;
    document.getElementById("text").value = "";
    var url = rootURL + "chatRoom/message/send";
    var sender = JSON.parse(sessionStorage.getItem("user"));
    var chatRoomName = sessionStorage.getItem("room");
    var message = {
        "message": text,
        "sender": sender,
        "chatRoomName": chatRoomName
    }

    var opts = makeOpts("POST", message);
    REST(url, function () {
        getMessages(DEFAULTAMOUNT);
    }, opts);
}

