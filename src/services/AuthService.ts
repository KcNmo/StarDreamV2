import AsyncStorage from '@react-native-async-storage/async-storage';
import { User } from '../types';

class AuthService {
  private static instance: AuthService;

  public static getInstance(): AuthService {
    if (!AuthService.instance) {
      AuthService.instance = new AuthService();
    }
    return AuthService.instance;
  }

  // 登录
  async login(uidOrPhone: string, password?: string, verificationCode?: string): Promise<{ success: boolean; user?: User; message?: string }> {
    try {
      // 模拟API调用
      await new Promise(resolve => setTimeout(resolve, 1000));

      // 简单验证逻辑
      if (password && password.length < 6) {
        return { success: false, message: '密码至少6位' };
      }

      if (verificationCode && verificationCode !== '123456') {
        return { success: false, message: '验证码错误' };
      }

      // 模拟用户数据
      const mockUser: User = {
        uid: uidOrPhone.length === 8 ? uidOrPhone : '12345678',
        phone: uidOrPhone.length === 11 ? uidOrPhone : '138****8000',
        nickname: '星辰用户',
        avatar: '',
        signature: '愿所有美好都如期而至 ✨',
        birthday: '1995-06-15',
        constellation: '双子座',
        interests: ['编程', '音乐', '旅行'],
        education: {
          level: '本科',
          school: '某某大学',
          major: '计算机科学',
          verified: true,
        },
        work: {
          company: '某某科技',
          position: '软件工程师',
          verified: true,
        },
        personalInfo: {
          height: 175,
          hometown: '北京',
          family: '独生子',
          lifestyle: '规律生活',
          idealPartner: '温柔善良',
        },
        photos: [],
        starPower: 88,
        isVerified: true,
        createdAt: '2024-01-01',
        lastActive: new Date().toISOString(),
      };

      // 保存登录状态
      await AsyncStorage.setItem('userToken', 'mock_token_' + Date.now());
      await AsyncStorage.setItem('userInfo', JSON.stringify(mockUser));

      return { success: true, user: mockUser };
    } catch (error) {
      return { success: false, message: '登录失败，请稍后重试' };
    }
  }

  // 注册
  async register(phone: string, verificationCode: string, password: string, nickname: string): Promise<{ success: boolean; uid?: string; message?: string }> {
    try {
      // 模拟API调用
      await new Promise(resolve => setTimeout(resolve, 1500));

      // 验证码验证
      if (verificationCode !== '123456') {
        return { success: false, message: '验证码错误' };
      }

      // 生成UID
      const uid = Math.floor(10000000 + Math.random() * 90000000).toString();

      // 创建用户
      const newUser: User = {
        uid,
        phone,
        nickname,
        avatar: '',
        signature: '新用户，请多关照～',
        birthday: '',
        constellation: '',
        interests: [],
        education: {
          level: '',
          school: '',
          major: '',
          verified: false,
        },
        work: {
          company: '',
          position: '',
          verified: false,
        },
        personalInfo: {
          height: 0,
          hometown: '',
          family: '',
          lifestyle: '',
          idealPartner: '',
        },
        photos: [],
        starPower: 50,
        isVerified: false,
        createdAt: new Date().toISOString(),
        lastActive: new Date().toISOString(),
      };

      // 保存用户信息
      await AsyncStorage.setItem('userInfo', JSON.stringify(newUser));

      return { success: true, uid };
    } catch (error) {
      return { success: false, message: '注册失败，请稍后重试' };
    }
  }

  // 发送验证码
  async sendVerificationCode(phone: string): Promise<{ success: boolean; message?: string }> {
    try {
      // 模拟API调用
      await new Promise(resolve => setTimeout(resolve, 500));

      // 简单手机号验证
      if (!/^1[3-9]\d{9}$/.test(phone)) {
        return { success: false, message: '请输入正确的手机号' };
      }

      return { success: true, message: '验证码已发送' };
    } catch (error) {
      return { success: false, message: '发送失败，请稍后重试' };
    }
  }

  // 退出登录
  async logout(): Promise<void> {
    try {
      await AsyncStorage.removeItem('userToken');
      await AsyncStorage.removeItem('userInfo');
    } catch (error) {
      console.error('退出登录失败:', error);
    }
  }

  // 检查登录状态
  async isLoggedIn(): Promise<boolean> {
    try {
      const token = await AsyncStorage.getItem('userToken');
      return !!token;
    } catch (error) {
      return false;
    }
  }

  // 获取当前用户信息
  async getCurrentUser(): Promise<User | null> {
    try {
      const userInfo = await AsyncStorage.getItem('userInfo');
      return userInfo ? JSON.parse(userInfo) : null;
    } catch (error) {
      return null;
    }
  }
}

export default AuthService;