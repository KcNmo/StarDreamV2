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

const RegisterScreen: React.FC = () => {
  const navigation = useNavigation();
  const [step, setStep] = useState(1); // 1: 手机验证, 2: 设置密码, 3: 完善资料
  const [phone, setPhone] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [nickname, setNickname] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [agreed, setAgreed] = useState(false);

  const sendVerificationCode = async () => {
    if (!phone.trim() || phone.length !== 11) {
      Alert.alert('提示', '请输入正确的手机号');
      return;
    }
    
    // 模拟发送验证码
    Alert.alert('验证码已发送', '请查收短信验证码（测试码：123456）');
  };

  const verifyPhone = async () => {
    if (!phone.trim()) {
      Alert.alert('提示', '请输入手机号');
      return;
    }

    if (!verificationCode.trim()) {
      Alert.alert('提示', '请输入验证码');
      return;
    }

    if (verificationCode !== '123456') {
      Alert.alert('提示', '验证码错误');
      return;
    }

    setStep(2);
  };

  const setUserPassword = async () => {
    if (!password.trim()) {
      Alert.alert('提示', '请输入密码');
      return;
    }

    if (password.length < 6) {
      Alert.alert('提示', '密码至少6位');
      return;
    }

    if (password !== confirmPassword) {
      Alert.alert('提示', '两次输入的密码不一致');
      return;
    }

    setStep(3);
  };

  const completeRegistration = async () => {
    if (!nickname.trim()) {
      Alert.alert('提示', '请输入昵称');
      return;
    }

    if (!agreed) {
      Alert.alert('提示', '请先同意用户协议和隐私政策');
      return;
    }

    setIsLoading(true);

    try {
      // 模拟注册API调用
      await new Promise(resolve => setTimeout(resolve, 2000));
      
      const uid = Math.floor(10000000 + Math.random() * 90000000).toString();
      
      Alert.alert(
        '注册成功！',
        `欢迎加入星愿！\n您的UID是：${uid}\n请妥善保管，可用于登录`,
        [
          {
            text: '开始使用',
            onPress: () => navigation.reset({
              index: 0,
              routes: [{ name: 'Login' as never }]
            })
          }
        ]
      );
    } catch (error) {
      Alert.alert('注册失败', '请稍后重试');
    } finally {
      setIsLoading(false);
    }
  };

  const renderStepIndicator = () => (
    <View style={styles.stepIndicator}>
      {[1, 2, 3].map((stepNumber) => (
        <View key={stepNumber} style={styles.stepContainer}>
          <View style={[
            styles.stepCircle,
            step >= stepNumber && styles.activeStepCircle
          ]}>
            <Text style={[
              styles.stepText,
              step >= stepNumber && styles.activeStepText
            ]}>
              {stepNumber}
            </Text>
          </View>
          {stepNumber < 3 && (
            <View style={[
              styles.stepLine,
              step > stepNumber && styles.activeStepLine
            ]} />
          )}
        </View>
      ))}
    </View>
  );

  const renderStep1 = () => (
    <View style={styles.stepContent}>
      <Text style={styles.stepTitle}>验证手机号</Text>
      <Text style={styles.stepSubtitle}>我们将向您的手机发送验证码</Text>

      <View style={styles.inputWrapper}>
        <Icon name="phone" size={20} color="#8892b0" style={styles.inputIcon} />
        <TextInput
          style={styles.input}
          placeholder="请输入手机号"
          placeholderTextColor="#8892b0"
          value={phone}
          onChangeText={setPhone}
          keyboardType="phone-pad"
          maxLength={11}
        />
      </View>

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

      <TouchableOpacity
        style={styles.nextButton}
        onPress={verifyPhone}
      >
        <LinearGradient
          colors={['#ffd700', '#ffed4e']}
          style={styles.buttonGradient}
        >
          <Text style={styles.nextButtonText}>下一步</Text>
        </LinearGradient>
      </TouchableOpacity>
    </View>
  );

  const renderStep2 = () => (
    <View style={styles.stepContent}>
      <Text style={styles.stepTitle}>设置密码</Text>
      <Text style={styles.stepSubtitle}>请设置一个安全的登录密码</Text>

      <View style={styles.inputWrapper}>
        <Icon name="lock" size={20} color="#8892b0" style={styles.inputIcon} />
        <TextInput
          style={styles.input}
          placeholder="请输入密码（至少6位）"
          placeholderTextColor="#8892b0"
          value={password}
          onChangeText={setPassword}
          secureTextEntry
        />
      </View>

      <View style={styles.inputWrapper}>
        <Icon name="lock" size={20} color="#8892b0" style={styles.inputIcon} />
        <TextInput
          style={styles.input}
          placeholder="请确认密码"
          placeholderTextColor="#8892b0"
          value={confirmPassword}
          onChangeText={setConfirmPassword}
          secureTextEntry
        />
      </View>

      <TouchableOpacity
        style={styles.nextButton}
        onPress={setUserPassword}
      >
        <LinearGradient
          colors={['#ffd700', '#ffed4e']}
          style={styles.buttonGradient}
        >
          <Text style={styles.nextButtonText}>下一步</Text>
        </LinearGradient>
      </TouchableOpacity>
    </View>
  );

  const renderStep3 = () => (
    <View style={styles.stepContent}>
      <Text style={styles.stepTitle}>完善资料</Text>
      <Text style={styles.stepSubtitle}>设置您的基本信息</Text>

      <View style={styles.inputWrapper}>
        <Icon name="person" size={20} color="#8892b0" style={styles.inputIcon} />
        <TextInput
          style={styles.input}
          placeholder="请输入昵称"
          placeholderTextColor="#8892b0"
          value={nickname}
          onChangeText={setNickname}
          maxLength={20}
        />
      </View>

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

      <TouchableOpacity
        style={[styles.nextButton, !agreed && styles.disabledButton]}
        onPress={completeRegistration}
        disabled={isLoading || !agreed}
      >
        <LinearGradient
          colors={agreed ? ['#ffd700', '#ffed4e'] : ['#666', '#666']}
          style={styles.buttonGradient}
        >
          <Text style={styles.nextButtonText}>
            {isLoading ? '注册中...' : '完成注册'}
          </Text>
        </LinearGradient>
      </TouchableOpacity>
    </View>
  );

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
              onPress={() => {
                if (step > 1) {
                  setStep(step - 1);
                } else {
                  navigation.goBack();
                }
              }}
            >
              <Icon name="arrow-back" size={24} color="#ccd6f6" />
            </TouchableOpacity>
            <Text style={styles.title}>注册星愿</Text>
          </View>

          {/* 步骤指示器 */}
          {renderStepIndicator()}

          {/* 步骤内容 */}
          {step === 1 && renderStep1()}
          {step === 2 && renderStep2()}
          {step === 3 && renderStep3()}

          {/* 登录链接 */}
          <TouchableOpacity
            style={styles.loginLink}
            onPress={() => navigation.navigate('Login' as never)}
          >
            <Text style={styles.loginLinkText}>
              已有账号？<Text style={styles.linkText}>立即登录</Text>
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
    marginBottom: 30,
  },
  backButton: {
    marginRight: 15,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  stepIndicator: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 40,
  },
  stepContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  stepCircle: {
    width: 30,
    height: 30,
    borderRadius: 15,
    backgroundColor: '#16213e',
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 2,
    borderColor: '#8892b0',
  },
  activeStepCircle: {
    backgroundColor: '#ffd700',
    borderColor: '#ffd700',
  },
  stepText: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#8892b0',
  },
  activeStepText: {
    color: '#1a1a2e',
  },
  stepLine: {
    width: 40,
    height: 2,
    backgroundColor: '#8892b0',
    marginHorizontal: 10,
  },
  activeStepLine: {
    backgroundColor: '#ffd700',
  },
  stepContent: {
    flex: 1,
  },
  stepTitle: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#ccd6f6',
    textAlign: 'center',
    marginBottom: 10,
  },
  stepSubtitle: {
    fontSize: 16,
    color: '#8892b0',
    textAlign: 'center',
    marginBottom: 40,
  },
  inputWrapper: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#16213e',
    borderRadius: 12,
    marginBottom: 20,
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
  nextButton: {
    marginTop: 20,
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
  nextButtonText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#1a1a2e',
  },
  loginLink: {
    alignItems: 'center',
    marginBottom: 30,
  },
  loginLinkText: {
    fontSize: 14,
    color: '#8892b0',
  },
});

export default RegisterScreen;