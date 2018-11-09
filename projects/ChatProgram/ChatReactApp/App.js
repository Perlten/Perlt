import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { createStackNavigator } from 'react-navigation';
import LoginScreen from './screens/LoginScreen';
import ChatRoom from './screens/ChatRoom';

export default class App extends React.Component {
  render() {
    return (
      <AppStackNavigator/>
    );
  }
}

const AppStackNavigator = createStackNavigator({
  Login: LoginScreen,
  Chat : ChatRoom,
  
})


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});