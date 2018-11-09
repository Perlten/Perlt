import React from 'react';
import { View, Text, StyleSheet, Button, Alert, TextInput, AsyncStorage} from 'react-native';
import LoginFacade from './../facades/LoginFacade';

export default class LoginScreen extends React.Component {

    constructor(props) {
        super(props);
        this.state = { email: "", password: "" };
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={{fontSize: 42 }}>Login</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={(email) => this.setState({ email })}
                    value={this.state.email}
                    placeholder="Email"
                />
                <TextInput
                    style={styles.input}
                    onChangeText={(password) => this.setState({ password })}
                    value={this.state.password}
                    placeholder="Password"
                />
                <Button style={styles.input} title="Login" onPress={this.verifyLogin} />
            </View>
        );
    }

    verifyLogin = async () => {
        const email = this.state.email;
        const password = this.state.password;
        const user = await LoginFacade.verifyLogin(email, password);
        if (user) {
           await this.saveUser(user);
            this.props.navigation.navigate("Chat");
        } else {
            Alert.alert("Logn failed");
        }
    }

    saveUser = async (user) => {
        await AsyncStorage.setItem("user", JSON.stringify(user));
    }
}


const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: '#ecf0f1',
    },
    input: {
      width: 200,
      height: 44,
      padding: 10,
      borderWidth: 1,
      borderColor: 'black',
      marginBottom: 10,
    },
  });
  