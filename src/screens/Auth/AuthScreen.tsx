import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Dimensions,
  ImageBackground,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import { useNavigation } from '@react-navigation/native';
import { StackNavigationProp } from '@react-navigation/stack';
import { RootStackParamList } from '../../types';

type AuthScreenNavigationProp = StackNavigationProp<RootStackParamList, 'Auth'>;

const { width, height } = Dimensions.get('window');

const AuthScreen: React.FC = () => {
  const navigation = useNavigation<AuthScreenNavigationProp>();

  return (
    <LinearGradient
      colors={['#0f0f23', '#1a1a2e', '#16213e']}
      style={styles.container}
    >
      <View style={styles.content}>
        {/* Logo和标题区域 */}
        <View style={styles.logoSection}>
          <View style={styles.starContainer}>
            <Text style={styles.starIcon}>⭐</Text>
            <Text style={styles.starIcon}>✨</Text>
            <Text style={styles.starIcon}>🌟</Text>
          </View>
          <Text style={styles.appName}>星愿</Text>
          <Text style={styles.tagline}>遇见你的星辰大海</Text>
          <Text style={styles.subtitle}>高学历轻社交，让每颗星都闪闪发光</Text>
        </View>

        {/* 特色介绍 */}
        <View style={styles.featuresSection}>
          <View style={styles.feature}>
            <Text style={styles.featureIcon}>🎓</Text>
            <Text style={styles.featureText}>高学历认证</Text>
          </View>
          <View style={styles.feature}>
            <Text style={styles.featureIcon}>🔮</Text>
            <Text style={styles.featureText}>星盘匹配</Text>
          </View>
          <View style={styles.feature}>
            <Text style={styles.featureIcon}>💫</Text>
            <Text style={styles.featureText}>轻松社交</Text>
          </View>
        </View>

        {/* 按钮区域 */}
        <View style={styles.buttonSection}>
          <TouchableOpacity
            style={styles.loginButton}
            onPress={() => navigation.navigate('Login')}
          >
            <LinearGradient
              colors={['#ffd700', '#ffed4e']}
              style={styles.buttonGradient}
            >
              <Text style={styles.loginButtonText}>登录</Text>
            </LinearGradient>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.registerButton}
            onPress={() => navigation.navigate('Register')}
          >
            <Text style={styles.registerButtonText}>注册新账号</Text>
          </TouchableOpacity>

          <Text style={styles.agreementText}>
            继续使用即表示同意
            <Text style={styles.linkText}>《用户协议》</Text>
            和
            <Text style={styles.linkText}>《隐私政策》</Text>
          </Text>
        </View>
      </View>
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  content: {
    flex: 1,
    paddingHorizontal: 30,
    justifyContent: 'space-between',
  },
  logoSection: {
    alignItems: 'center',
    marginTop: height * 0.15,
  },
  starContainer: {
    flexDirection: 'row',
    marginBottom: 20,
  },
  starIcon: {
    fontSize: 40,
    marginHorizontal: 10,
    opacity: 0.8,
  },
  appName: {
    fontSize: 48,
    fontWeight: 'bold',
    color: '#ffd700',
    marginBottom: 10,
    textShadowColor: 'rgba(255, 215, 0, 0.3)',
    textShadowOffset: { width: 0, height: 2 },
    textShadowRadius: 4,
  },
  tagline: {
    fontSize: 18,
    color: '#ccd6f6',
    marginBottom: 8,
    fontWeight: '500',
  },
  subtitle: {
    fontSize: 14,
    color: '#8892b0',
    textAlign: 'center',
    lineHeight: 20,
  },
  featuresSection: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginVertical: 40,
  },
  feature: {
    alignItems: 'center',
  },
  featureIcon: {
    fontSize: 32,
    marginBottom: 8,
  },
  featureText: {
    fontSize: 12,
    color: '#ccd6f6',
    fontWeight: '500',
  },
  buttonSection: {
    marginBottom: 50,
  },
  loginButton: {
    marginBottom: 15,
    borderRadius: 25,
    overflow: 'hidden',
  },
  buttonGradient: {
    paddingVertical: 15,
    alignItems: 'center',
    borderRadius: 25,
  },
  loginButtonText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#1a1a2e',
  },
  registerButton: {
    paddingVertical: 15,
    alignItems: 'center',
    borderRadius: 25,
    borderWidth: 1,
    borderColor: '#ffd700',
    marginBottom: 20,
  },
  registerButtonText: {
    fontSize: 16,
    color: '#ffd700',
    fontWeight: '600',
  },
  agreementText: {
    fontSize: 12,
    color: '#8892b0',
    textAlign: 'center',
    lineHeight: 18,
  },
  linkText: {
    color: '#ffd700',
    textDecorationLine: 'underline',
  },
});

export default AuthScreen;