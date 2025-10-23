// 用户基础信息类型
export interface User {
  uid: string;
  phone: string;
  nickname: string;
  avatar: string;
  signature: string;
  birthday: string;
  constellation: string;
  interests: string[];
  education: {
    level: string;
    school: string;
    major: string;
    verified: boolean;
  };
  work: {
    company: string;
    position: string;
    verified: boolean;
  };
  personalInfo: {
    height: number;
    hometown: string;
    family: string;
    lifestyle: string;
    idealPartner: string;
  };
  photos: string[];
  starPower: number; // 星运值
  isVerified: boolean;
  createdAt: string;
  lastActive: string;
}

// 匹配用户类型
export interface MatchUser extends Omit<User, 'phone'> {
  matchScore: number;
  distance?: number;
  commonInterests: string[];
}

// 星盘陌生人类型
export interface StarStranger {
  id: string;
  starAvatar: string;
  constellation: string;
  prediction: string;
  chatDuration: number;
  canReveal: boolean;
  realUser?: User;
}

// 联系人类型
export interface Contact {
  id: string;
  user: User;
  type: 'friend' | 'star_stranger';
  starConnection: number; // 星缘值
  lastChatTime: string;
  unreadCount: number;
}

// 消息类型
export interface Message {
  id: string;
  senderId: string;
  receiverId: string;
  content: string;
  type: 'text' | 'image' | 'audio' | 'system';
  timestamp: string;
  isRead: boolean;
}

// 动态类型
export interface Moment {
  id: string;
  userId: string;
  user: User;
  content: string;
  images: string[];
  type: 'normal' | 'anonymous_qa' | 'help_request';
  visibility: 'private_circle' | 'public_sea';
  tags: string[];
  likes: string[];
  comments: Comment[];
  createdAt: string;
}

// 评论类型
export interface Comment {
  id: string;
  userId: string;
  user: User;
  content: string;
  createdAt: string;
}

// 认证类型
export interface Verification {
  type: 'avatar' | 'education' | 'work' | 'identity';
  status: 'pending' | 'approved' | 'rejected';
  documents: string[];
  submittedAt: string;
  reviewedAt?: string;
}

// 导航参数类型
export type RootStackParamList = {
  Auth: undefined;
  Main: undefined;
  Login: undefined;
  Register: undefined;
  Profile: { userId?: string };
  Chat: { contactId: string };
  EditProfile: undefined;
  Settings: undefined;
};

export type MainTabParamList = {
  Home: undefined;
  Contacts: undefined;
  Moments: undefined;
  Profile: undefined;
};