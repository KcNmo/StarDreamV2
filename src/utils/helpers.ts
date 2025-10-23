import { CONSTELLATIONS, MATCHING_WEIGHTS } from './constants';
import { User, MatchUser } from '../types';

// 时间格式化工具
export const formatTime = (timestamp: string): string => {
  const date = new Date(timestamp);
  const now = new Date();
  const diffMs = now.getTime() - date.getTime();
  const diffMinutes = Math.floor(diffMs / (1000 * 60));
  const diffHours = Math.floor(diffMinutes / 60);
  const diffDays = Math.floor(diffHours / 24);

  if (diffMinutes < 1) return '刚刚';
  if (diffMinutes < 60) return `${diffMinutes}分钟前`;
  if (diffHours < 24) return `${diffHours}小时前`;
  if (diffDays < 7) return `${diffDays}天前`;
  if (diffDays < 30) return `${Math.floor(diffDays / 7)}周前`;
  if (diffDays < 365) return `${Math.floor(diffDays / 30)}个月前`;
  return `${Math.floor(diffDays / 365)}年前`;
};

// 根据生日计算星座
export const getConstellation = (birthday: string): string => {
  if (!birthday) return '';
  
  const date = new Date(birthday);
  const month = date.getMonth() + 1;
  const day = date.getDate();

  const constellationDates = [
    { name: '摩羯座', start: [12, 22], end: [1, 19] },
    { name: '水瓶座', start: [1, 20], end: [2, 18] },
    { name: '双鱼座', start: [2, 19], end: [3, 20] },
    { name: '白羊座', start: [3, 21], end: [4, 19] },
    { name: '金牛座', start: [4, 20], end: [5, 20] },
    { name: '双子座', start: [5, 21], end: [6, 21] },
    { name: '巨蟹座', start: [6, 22], end: [7, 22] },
    { name: '狮子座', start: [7, 23], end: [8, 22] },
    { name: '处女座', start: [8, 23], end: [9, 22] },
    { name: '天秤座', start: [9, 23], end: [10, 23] },
    { name: '天蝎座', start: [10, 24], end: [11, 22] },
    { name: '射手座', start: [11, 23], end: [12, 21] },
  ];

  for (const constellation of constellationDates) {
    const [startMonth, startDay] = constellation.start;
    const [endMonth, endDay] = constellation.end;

    if (startMonth === endMonth) {
      if (month === startMonth && day >= startDay && day <= endDay) {
        return constellation.name;
      }
    } else {
      if ((month === startMonth && day >= startDay) || 
          (month === endMonth && day <= endDay)) {
        return constellation.name;
      }
    }
  }

  return '';
};

// 计算年龄
export const calculateAge = (birthday: string): number => {
  if (!birthday) return 0;
  
  const birthDate = new Date(birthday);
  const today = new Date();
  let age = today.getFullYear() - birthDate.getFullYear();
  const monthDiff = today.getMonth() - birthDate.getMonth();
  
  if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
    age--;
  }
  
  return age;
};

// 计算两个用户的匹配度
export const calculateMatchScore = (user1: User, user2: User): number => {
  let score = 0;

  // 学历匹配
  if (user1.education.level === user2.education.level) {
    score += MATCHING_WEIGHTS.EDUCATION * 100;
  } else {
    const educationLevels = ['高中及以下', '大专', '本科', '硕士', '博士'];
    const level1Index = educationLevels.indexOf(user1.education.level);
    const level2Index = educationLevels.indexOf(user2.education.level);
    const diff = Math.abs(level1Index - level2Index);
    score += MATCHING_WEIGHTS.EDUCATION * Math.max(0, 100 - diff * 20);
  }

  // 兴趣匹配
  const commonInterests = user1.interests.filter(interest => 
    user2.interests.includes(interest)
  );
  const interestScore = (commonInterests.length / Math.max(user1.interests.length, user2.interests.length)) * 100;
  score += MATCHING_WEIGHTS.INTERESTS * interestScore;

  // 星座匹配
  if (user1.constellation === user2.constellation) {
    score += MATCHING_WEIGHTS.CONSTELLATION * 100;
  } else {
    // 简单的星座相性算法
    const compatibleSigns = getCompatibleSigns(user1.constellation);
    if (compatibleSigns.includes(user2.constellation)) {
      score += MATCHING_WEIGHTS.CONSTELLATION * 80;
    } else {
      score += MATCHING_WEIGHTS.CONSTELLATION * 40;
    }
  }

  // 年龄匹配
  const age1 = calculateAge(user1.birthday);
  const age2 = calculateAge(user2.birthday);
  const ageDiff = Math.abs(age1 - age2);
  const ageScore = Math.max(0, 100 - ageDiff * 5);
  score += MATCHING_WEIGHTS.AGE * ageScore;

  // 地理位置匹配（简化处理）
  if (user1.personalInfo.hometown === user2.personalInfo.hometown) {
    score += MATCHING_WEIGHTS.LOCATION * 100;
  } else {
    score += MATCHING_WEIGHTS.LOCATION * 60;
  }

  // 活跃度匹配
  const lastActive1 = new Date(user1.lastActive);
  const lastActive2 = new Date(user2.lastActive);
  const now = new Date();
  const activeScore1 = Math.max(0, 100 - (now.getTime() - lastActive1.getTime()) / (1000 * 60 * 60 * 24));
  const activeScore2 = Math.max(0, 100 - (now.getTime() - lastActive2.getTime()) / (1000 * 60 * 60 * 24));
  score += MATCHING_WEIGHTS.ACTIVITY * (activeScore1 + activeScore2) / 2;

  return Math.round(Math.min(100, Math.max(0, score)));
};

// 获取星座相性
const getCompatibleSigns = (constellation: string): string[] => {
  const compatibility: { [key: string]: string[] } = {
    '白羊座': ['狮子座', '射手座', '双子座', '水瓶座'],
    '金牛座': ['处女座', '摩羯座', '巨蟹座', '双鱼座'],
    '双子座': ['天秤座', '水瓶座', '白羊座', '狮子座'],
    '巨蟹座': ['天蝎座', '双鱼座', '金牛座', '处女座'],
    '狮子座': ['白羊座', '射手座', '双子座', '天秤座'],
    '处女座': ['金牛座', '摩羯座', '巨蟹座', '天蝎座'],
    '天秤座': ['双子座', '水瓶座', '狮子座', '射手座'],
    '天蝎座': ['巨蟹座', '双鱼座', '处女座', '摩羯座'],
    '射手座': ['白羊座', '狮子座', '天秤座', '水瓶座'],
    '摩羯座': ['金牛座', '处女座', '天蝎座', '双鱼座'],
    '水瓶座': ['双子座', '天秤座', '白羊座', '射手座'],
    '双鱼座': ['巨蟹座', '天蝎座', '金牛座', '摩羯座'],
  };

  return compatibility[constellation] || [];
};

// 生成推荐用户
export const generateRecommendedUsers = (currentUser: User, count: number = 21): MatchUser[] => {
  const mockUsers: User[] = [
    {
      uid: '87654321',
      phone: '13800138001',
      nickname: '星辰小仙女',
      avatar: '',
      signature: '愿所有美好都如期而至 ✨',
      birthday: '1998-05-20',
      constellation: '金牛座',
      interests: ['阅读', '旅行', '摄影', '音乐'],
      education: { level: '硕士', school: '清华大学', major: '计算机科学', verified: true },
      work: { company: '字节跳动', position: '产品经理', verified: true },
      personalInfo: { height: 165, hometown: '北京', family: '独生女', lifestyle: '规律作息，热爱运动', idealPartner: '幽默有趣，有上进心' },
      photos: [],
      starPower: 85,
      isVerified: true,
      createdAt: '2024-01-15',
      lastActive: '2024-01-20',
    },
    {
      uid: '76543210',
      phone: '13900139001',
      nickname: '追光少年',
      avatar: '',
      signature: '生活不止眼前的苟且，还有诗和远方',
      birthday: '1996-08-15',
      constellation: '狮子座',
      interests: ['健身', '电影', '编程', '美食'],
      education: { level: '本科', school: '北京大学', major: '软件工程', verified: true },
      work: { company: '腾讯', position: '软件工程师', verified: true },
      personalInfo: { height: 178, hometown: '上海', family: '家庭和睦', lifestyle: '热爱运动，喜欢探索', idealPartner: '善良温柔，有共同话题' },
      photos: [],
      starPower: 78,
      isVerified: true,
      createdAt: '2024-01-10',
      lastActive: '2024-01-20',
    },
    // 可以添加更多模拟用户...
  ];

  return mockUsers.slice(0, count).map(user => {
    const matchScore = calculateMatchScore(currentUser, user);
    const commonInterests = currentUser.interests.filter(interest => 
      user.interests.includes(interest)
    );
    
    return {
      ...user,
      matchScore,
      distance: Math.random() * 10 + 0.5, // 随机距离 0.5-10.5km
      commonInterests,
    };
  }).sort((a, b) => b.matchScore - a.matchScore);
};

// 验证手机号
export const validatePhone = (phone: string): boolean => {
  return /^1[3-9]\d{9}$/.test(phone);
};

// 验证UID
export const validateUID = (uid: string): boolean => {
  return /^\d{8}$/.test(uid);
};

// 验证密码强度
export const validatePassword = (password: string): { isValid: boolean; message: string } => {
  if (password.length < 6) {
    return { isValid: false, message: '密码至少6位' };
  }
  if (password.length > 20) {
    return { isValid: false, message: '密码不能超过20位' };
  }
  if (!/^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]*$/.test(password)) {
    return { isValid: false, message: '密码包含非法字符' };
  }
  return { isValid: true, message: '' };
};

// 脱敏手机号
export const maskPhone = (phone: string): string => {
  if (!phone || phone.length !== 11) return phone;
  return phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
};

// 生成随机UID
export const generateUID = (): string => {
  return Math.floor(10000000 + Math.random() * 90000000).toString();
};

// 计算星运值
export const calculateStarPower = (user: User): number => {
  let power = 50; // 基础值

  // 认证加分
  if (user.isVerified) power += 10;
  if (user.education.verified) power += 5;
  if (user.work.verified) power += 5;

  // 完善度加分
  if (user.avatar) power += 5;
  if (user.signature) power += 3;
  if (user.interests.length > 0) power += 5;
  if (user.personalInfo.hometown) power += 2;
  if (user.personalInfo.lifestyle) power += 2;
  if (user.personalInfo.idealPartner) power += 2;
  if (user.photos.length > 0) power += 5;

  // 活跃度加分
  const lastActive = new Date(user.lastActive);
  const now = new Date();
  const daysSinceActive = (now.getTime() - lastActive.getTime()) / (1000 * 60 * 60 * 24);
  if (daysSinceActive < 1) power += 6;
  else if (daysSinceActive < 3) power += 4;
  else if (daysSinceActive < 7) power += 2;

  return Math.min(100, Math.max(0, power));
};