setInterval(function () {
    getMessages(10);
}, 2000)


function createOwnChatHtml(message) {
    console.log("own")
    var imageId = JSON.parse(sessionStorage.getItem("user")).avatar;
    console.log(imageId);
    console.log('resources/images/avatar' + imageId + '.png');
    var html = ' <div class="col-md-4">' +
        '<div class="container">' +
        '<img src="resources/images/avatar' + imageId + '.png" alt="Avatar">' +
        '<p>Hello. How are you today?</p>' +
        '<span class="time-right">11:00</span>' +
        '</div>' +
        '</div>'

    document.getElementById("chat").innerHTML += html;
}

function createOtherChatHtml(message) {
    var imageId = message.sender.avatar;
    var html = '<div class="col-md-4">' +
        '<div class="container">' +
        '<img src="resources/images/avatar' + imageId + '.png" alt="Avatar" class="right">' +
        '<p>Hello. How are you today?</p>' +
        '<span class="time-left">11:00</span>' +
        '</div>' +
        '</div>'
    document.getElementById("chat").innerHTML += html;
}

function getMessages(amount) {
    var room = sessionStorage.getItem("room");
    var url = rootURL + "chatRoom/message/receive/" + room + "/10";
    REST(url, function (data) {
        var user = JSON.parse(sessionStorage.getItem("user"));
        document.getElementById("chat").innerHTML = "";
        data.forEach(message => {
            console.log(message)
            if (message.sender.id === user.id) {
                createOwnChatHtml(message);
            } else {
                createOtherChatHtml(message);
            }
        });
    });

}

document.getElementById("test").addEventListener("click", createOwnChatHtml);