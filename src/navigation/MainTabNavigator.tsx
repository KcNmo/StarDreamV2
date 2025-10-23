import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { View, Text, StyleSheet } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

import HomeScreen from '../screens/Home/HomeScreen';
import ContactsScreen from '../screens/Chat/ContactsScreen';
import MomentsScreen from '../screens/Moments/MomentsScreen';
import ProfileScreen from '../screens/Profile/ProfileScreen';
import { MainTabParamList } from '../types';

const Tab = createBottomTabNavigator<MainTabParamList>();

const MainTabNavigator: React.FC = () => {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarStyle: {
          backgroundColor: '#1a1a2e',
          borderTopColor: '#16213e',
          height: 60,
          paddingBottom: 8,
          paddingTop: 8,
        },
        tabBarActiveTintColor: '#ffd700',
        tabBarInactiveTintColor: '#8892b0',
        tabBarIcon: ({ focused, color, size }) => {
          let iconName: string;

          switch (route.name) {
            case 'Home':
              iconName = 'home';
              break;
            case 'Contacts':
              iconName = 'chat';
              break;
            case 'Moments':
              iconName = 'dynamic-feed';
              break;
            case 'Profile':
              iconName = 'person';
              break;
            default:
              iconName = 'help';
          }

          return (
            <View style={[styles.iconContainer, focused && styles.activeIcon]}>
              <Icon name={iconName} size={size} color={color} />
            </View>
          );
        },
        tabBarLabel: ({ focused, color }) => {
          let label: string;
          switch (route.name) {
            case 'Home':
              label = '首页推荐';
              break;
            case 'Contacts':
              label = '联系人';
              break;
            case 'Moments':
              label = '动态';
              break;
            case 'Profile':
              label = '个人主页';
              break;
            default:
              label = '';
          }

          return (
            <Text style={[styles.tabLabel, { color }]}>
              {label}
            </Text>
          );
        },
      })}
    >
      <Tab.Screen name="Home" component={HomeScreen} />
      <Tab.Screen name="Contacts" component={ContactsScreen} />
      <Tab.Screen name="Moments" component={MomentsScreen} />
      <Tab.Screen name="Profile" component={ProfileScreen} />
    </Tab.Navigator>
  );
};

const styles = StyleSheet.create({
  iconContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    width: 40,
    height: 40,
    borderRadius: 20,
  },
  activeIcon: {
    backgroundColor: 'rgba(255, 215, 0, 0.1)',
  },
  tabLabel: {
    fontSize: 11,
    fontWeight: '500',
    marginTop: 2,
  },
});

export default MainTabNavigator;