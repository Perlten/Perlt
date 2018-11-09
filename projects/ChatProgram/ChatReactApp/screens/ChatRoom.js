import React from 'react';
import { View, Text, StyleSheet, Button, Alert, TextInput, AsyncStorage } from 'react-native';
import ChatFacade from './../facades/ChatFacade';

export default class ChatRoom extends React.Component {

    constructor(props) {
        super(props);
        this.state = { msg: [], message: "" }
    }

    async componentDidMount() {
        this.user = await this.getUser();
        console.log(this.user);

        this.getMsg();
       this.intervalId = setInterval(this.getMsg, 3000);
    }

    componentWillUnmount(){
        clearInterval(this.intervalId);
    }

    getUser = async () => {
        const user = await AsyncStorage.getItem("user");
        return JSON.parse(user);
    }


    render() {
        return (
            <View style={styles.container}>
                <View style={styles.container}>
                    <RenderChat msg={this.state.msg} />
                </View>
                <View>
                    <TextInput
                        style={styles.input}
                        onChangeText={(message) => this.setState({ message })}
                        value={this.state.message}
                        placeholder="Message"
                    />
                    <Button title="Send" onPress={this.sendMessage} />
                </View>
            </View>
        );
    }

    sendMessage = async () => {
        const messageValue = this.state.message;
        this.setState({ message: "" });
        const message = {
            message: messageValue,
            chatRoomName: "room",
            sender: this.user
        }
        await ChatFacade.sendMessage(message);
        this.getMsg();
    }



    getMsg = async () => {
        const msg = await ChatFacade.getAllMessages(6, "room");
        this.setState({ msg });
    }
}

function RenderChat({ msg }) {
    if (!msg[0]) {
        console.log("no render");
        return (
            <View>
            </View>
        );
    }
    const chats = msg.map((m) => {
        return (
            <View key={m.id}>
                <Text>{m.sender.firstName}</Text>
                <Text style={{ fontSize: 16 }}> {m.message} </Text>
                <Text> ------------------------------------------------- </Text>
            </View>
        )
    });
    return (
        <View>
            {chats}
        </View>
    );
}



const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    }, input: {
        width: 200,
        height: 44,
        padding: 10,
        borderWidth: 1,
        borderColor: 'black',
        marginBottom: 10,
    },
});