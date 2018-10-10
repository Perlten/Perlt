document.getElementById("createForm").addEventListener("submit", function(){
    checkSession();
    var roomName = document.getElementById("createName").value;
    var publicRoom = document.getElementById("publicRoom").value;
    var owner = JSON.parse(sessionStorage.getItem("user"));

    if(!roomName){
        //Handle error
        return;
    }
    
    var chatRoomDTO = {
        "owner" : owner, 
        "roomName" : roomName
    } 
    
    var url = rootURL + "chatRoom";
    var opts = makeOpts("POST", chatRoomDTO);
    
    REST(url, function(data){
        console.log(data);
        sessionStorage.setItem("room", data.chatRoomName);
        location.href = "chat.html";
    }, opts);
})

document.getElementById("joinForm").addEventListener("submit", function(){
    checkSession();
    var roomName = document.getElementById("joinName").value;
    if(!roomName){
        //Handle error
        return;
    }
    
    sessionStorage.setItem("room", roomName);
    location.href = "chat.html";
})