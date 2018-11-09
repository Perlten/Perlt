import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

makeOptions = (method, body) => {
    var opts = {
        method: method,
        headers: {
            "Content-type": "application/json",
            'Accept': 'application/json',
        }
    }
    if (body) {
        opts.body = JSON.stringify(body);
    }
    return opts;
}

const url = "https://perlt.net/chat/api/"

class ChatFacade {

    getAllMessages = async (amount, roomName) => {
        const res = await fetch(`${url}chatRoom/message/receive/${roomName}/${amount}`);
        const msgList = await res.json();

        return msgList;
    }

    createAppRoom = async (roomName, owner) => {
        const newChatRoom = {
            owner: {
                id: 1,
                firstName: "Nikolai",
                lastName: "Perlt",
                email: "nikolaiperlt@gmail.com",
                avatar: 3
            },
            roomName: roomName
        }
        const opts = makeOptions("POST", newChatRoom);
        const res = await fetch(`${url}/chatRoom`, opts);
        const chatRoom = await res.json();

        console.log(chatRoom);

    }

    sendMessage = async (message) => {
        const opts = makeOptions("POST", message);
        const res = await fetch(`${url}chatRoom/message/send`, opts);
        const messageRes = await res.json();

        return messageRes;
    }

}

export default new ChatFacade();