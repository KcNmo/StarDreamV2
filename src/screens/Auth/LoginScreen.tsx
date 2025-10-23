import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  Alert,
  KeyboardAvoidingView,
  Platform,
  ScrollView,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { useNavigation } from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';

const LoginScreen: React.FC = () => {
  const navigation = useNavigation();
  const [loginType, setLoginType] = useState<'uid' | 'phone'>('uid');
  const [uidOrPhone, setUidOrPhone] = useState('');
  const [password, setPassword] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const [useVerificationCode, setUseVerificationCode] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [agreed, setAgreed] = useState(false);

  const handleLogin = async () => {
    if (!agreed) {
      Alert.alert('提示', '请先同意用户协议和隐私政策');
      return;
    }

    if (!uidOrPhone.trim()) {
      Alert.alert('提示', loginType === 'uid' ? '请输入UID' : '请输入手机号');
      return;
    }

    if (!useVerificationCode && !password.trim()) {
      Alert.alert('提示', '请输入密码');
      return;
    }

    if (useVerificationCode && !verificationCode.trim()) {
      Alert.alert('提示', '请输入验证码');
      return;
    }

    setIsLoading(true);

    try {
      // 模拟登录API调用
      await new Promise(resolve => setTimeout(resolve, 1500));
      
      // 保存登录状态
      await AsyncStorage.setItem('userToken', 'mock_token_' + Date.now());
      await AsyncStorage.setItem('userInfo', JSON.stringify({
        uid: '12345678',
        nickname: '星辰用户',
        avatar: '',
      }));

      Alert.alert('登录成功', '欢迎回到星愿！', [
        { text: '确定', onPress: () => navigation.reset({ index: 0, routes: [{ name: 'Main' as never }] }) }
      ]);
    } catch (error) {
      Alert.alert('登录失败', '请检查账号信息后重试');
    } finally {
      setIsLoading(false);
    }
  };

  const sendVerificationCode = async () => {
    if (loginType === 'phone' && !uidOrPhone.trim()) {
      Alert.alert('提示', '请输入手机号');
      return;
    }
    
    // 模拟发送验证码
    Alert.alert('验证码已发送', '请查收短信验证码');
  };

  const handleThirdPartyLogin = (platform: string) => {
    Alert.alert('功能开发中', `${platform}登录功能正在开发中`);
  };

  return (
    <LinearGradient colors={['#0f0f23', '#1a1a2e']} style={styles.container}>
      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={styles.keyboardView}
      >
        <ScrollView contentContainerStyle={styles.scrollContainer}>
          {/* 头部 */}
          <View style={styles.header}>
            <TouchableOpacity
              style={styles.backButton}
              onPress={() => navigation.goBack()}
            >
              <Icon name="arrow-back" size={24} color="#ccd6f6" />
            </TouchableOpacity>
            <Text style={styles.title}>登录星愿</Text>
          </View>

          {/* 登录方式切换 */}
          <View style={styles.loginTypeContainer}>
            <TouchableOpacity
              style={[styles.typeButton, loginType === 'uid' && styles.activeTypeButton]}
              onPress={() => setLoginType('uid')}
            >
              <Text style={[styles.typeButtonText, loginType === 'uid' && styles.activeTypeButtonText]}>
                UID登录
              </Text>
            </TouchableOpacity>
            <TouchableOpacity
              style={[styles.typeButton, loginType === 'phone' && styles.activeTypeButton]}
              onPress={() => setLoginType('phone')}
            >
              <Text style={[styles.typeButtonText, loginType === 'phone' && styles.activeTypeButtonText]}>
                手机号登录
              </Text>
            </TouchableOpacity>
          </View>

          {/* 输入区域 */}
          <View style={styles.inputContainer}>
            <View style={styles.inputWrapper}>
              <Icon 
                name={loginType === 'uid' ? 'badge' : 'phone'} 
                size={20} 
                color="#8892b0" 
                style={styles.inputIcon} 
              />
              <TextInput
                style={styles.input}
                placeholder={loginType === 'uid' ? '请输入8位UID' : '请输入手机号'}
                placeholderTextColor="#8892b0"
                value={uidOrPhone}
                onChangeText={setUidOrPhone}
                keyboardType={loginType === 'phone' ? 'phone-pad' : 'default'}
                maxLength={loginType === 'uid' ? 8 : 11}
              />
            </View>

            {!useVerificationCode ? (
              <View style={styles.inputWrapper}>
                <Icon name="lock" size={20} color="#8892b0" style={styles.inputIcon} />
                <TextInput
                  style={styles.input}
                  placeholder="请输入密码"
                  placeholderTextColor="#8892b0"
                  value={password}
                  onChangeText={setPassword}
                  secureTextEntry
                />
              </View>
            ) : (
              <View style={styles.inputWrapper}>
                <Icon name="sms" size={20} color="#8892b0" style={styles.inputIcon} />
                <TextInput
                  style={[styles.input, { flex: 1 }]}
                  placeholder="请输入验证码"
                  placeholderTextColor="#8892b0"
                  value={verificationCode}
                  onChangeText={setVerificationCode}
                  keyboardType="number-pad"
                  maxLength={6}
                />
                <TouchableOpacity
                  style={styles.codeButton}
                  onPress={sendVerificationCode}
                >
                  <Text style={styles.codeButtonText}>获取验证码</Text>
                </TouchableOpacity>
              </View>
            )}

            {loginType === 'phone' && (
              <TouchableOpacity
                style={styles.switchButton}
                onPress={() => setUseVerificationCode(!useVerificationCode)}
              >
                <Text style={styles.switchButtonText}>
                  {useVerificationCode ? '使用密码登录' : '使用验证码登录'}
                </Text>
              </TouchableOpacity>
            )}
          </View>

          {/* 协议勾选 */}
          <View style={styles.agreementContainer}>
            <TouchableOpacity
              style={styles.checkbox}
              onPress={() => setAgreed(!agreed)}
            >
              <Icon
                name={agreed ? 'check-box' : 'check-box-outline-blank'}
                size={20}
                color={agreed ? '#ffd700' : '#8892b0'}
              />
            </TouchableOpacity>
            <Text style={styles.agreementText}>
              我已阅读并同意
              <Text style={styles.linkText}>《用户协议》</Text>
              和
              <Text style={styles.linkText}>《隐私政策》</Text>
            </Text>
          </View>

          {/* 登录按钮 */}
          <TouchableOpacity
            style={[styles.loginButton, !agreed && styles.disabledButton]}
            onPress={handleLogin}
            disabled={isLoading || !agreed}
          >
            <LinearGradient
              colors={agreed ? ['#ffd700', '#ffed4e'] : ['#666', '#666']}
              style={styles.buttonGradient}
            >
              <Text style={styles.loginButtonText}>
                {isLoading ? '登录中...' : '登录'}
              </Text>
            </LinearGradient>
          </TouchableOpacity>

          {/* 第三方登录 */}
          <View style={styles.thirdPartyContainer}>
            <Text style={styles.thirdPartyTitle}>其他登录方式</Text>
            <View style={styles.thirdPartyButtons}>
              <TouchableOpacity
                style={styles.thirdPartyButton}
                onPress={() => handleThirdPartyLogin('微信')}
              >
                <Text style={styles.thirdPartyIcon}>💬</Text>
                <Text style={styles.thirdPartyText}>微信</Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={styles.thirdPartyButton}
                onPress={() => handleThirdPartyLogin('QQ')}
              >
                <Text style={styles.thirdPartyIcon}>🐧</Text>
                <Text style={styles.thirdPartyText}>QQ</Text>
              </TouchableOpacity>
            </View>
          </View>

          {/* 注册链接 */}
          <TouchableOpacity
            style={styles.registerLink}
            onPress={() => navigation.navigate('Register' as never)}
          >
            <Text style={styles.registerLinkText}>
              还没有账号？<Text style={styles.linkText}>立即注册</Text>
            </Text>
          </TouchableOpacity>
        </ScrollView>
      </KeyboardAvoidingView>
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  keyboardView: {
    flex: 1,
  },
  scrollContainer: {
    flexGrow: 1,
    paddingHorizontal: 30,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 50,
    marginBottom: 40,
  },
  backButton: {
    marginRight: 15,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  loginTypeContainer: {
    flexDirection: 'row',
    marginBottom: 30,
    backgroundColor: '#16213e',
    borderRadius: 25,
    padding: 4,
  },
  typeButton: {
    flex: 1,
    paddingVertical: 12,
    alignItems: 'center',
    borderRadius: 21,
  },
  activeTypeButton: {
    backgroundColor: '#ffd700',
  },
  typeButtonText: {
    fontSize: 16,
    color: '#8892b0',
    fontWeight: '500',
  },
  activeTypeButtonText: {
    color: '#1a1a2e',
    fontWeight: 'bold',
  },
  inputContainer: {
    marginBottom: 20,
  },
  inputWrapper: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#16213e',
    borderRadius: 12,
    marginBottom: 15,
    paddingHorizontal: 15,
  },
  inputIcon: {
    marginRight: 10,
  },
  input: {
    flex: 1,
    paddingVertical: 15,
    fontSize: 16,
    color: '#ccd6f6',
  },
  codeButton: {
    paddingHorizontal: 15,
    paddingVertical: 8,
    backgroundColor: '#ffd700',
    borderRadius: 6,
  },
  codeButtonText: {
    fontSize: 12,
    color: '#1a1a2e',
    fontWeight: '600',
  },
  switchButton: {
    alignSelf: 'flex-end',
    marginBottom: 10,
  },
  switchButtonText: {
    fontSize: 14,
    color: '#ffd700',
  },
  agreementContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 30,
  },
  checkbox: {
    marginRight: 10,
  },
  agreementText: {
    flex: 1,
    fontSize: 12,
    color: '#8892b0',
    lineHeight: 18,
  },
  linkText: {
    color: '#ffd700',
  },
  loginButton: {
    marginBottom: 30,
    borderRadius: 25,
    overflow: 'hidden',
  },
  disabledButton: {
    opacity: 0.6,
  },
  buttonGradient: {
    paddingVertical: 15,
    alignItems: 'center',
  },
  loginButtonText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#1a1a2e',
  },
  thirdPartyContainer: {
    alignItems: 'center',
    marginBottom: 30,
  },
  thirdPartyTitle: {
    fontSize: 14,
    color: '#8892b0',
    marginBottom: 20,
  },
  thirdPartyButtons: {
    flexDirection: 'row',
    justifyContent: 'center',
  },
  thirdPartyButton: {
    alignItems: 'center',
    marginHorizontal: 20,
    padding: 15,
    backgroundColor: '#16213e',
    borderRadius: 12,
    minWidth: 80,
  },
  thirdPartyIcon: {
    fontSize: 24,
    marginBottom: 5,
  },
  thirdPartyText: {
    fontSize: 12,
    color: '#ccd6f6',
  },
  registerLink: {
    alignItems: 'center',
    marginBottom: 30,
  },
  registerLinkText: {
    fontSize: 14,
    color: '#8892b0',
  },
});

export default LoginScreen;