// 应用常量
export const APP_CONFIG = {
  NAME: '星愿',
  VERSION: '1.0.0',
  DESCRIPTION: '高学历轻社交APP',
};

// 颜色主题
export const COLORS = {
  PRIMARY: '#1a1a2e',
  PRIMARY_DARK: '#0f0f23',
  SECONDARY: '#16213e',
  ACCENT: '#ffd700',
  WHITE: '#ffffff',
  BLACK: '#000000',
  TEXT_PRIMARY: '#ccd6f6',
  TEXT_SECONDARY: '#8892b0',
  ERROR: '#ff6b6b',
  SUCCESS: '#51cf66',
  WARNING: '#ffd43b',
  INFO: '#339af0',
};

// 字体大小
export const FONT_SIZES = {
  XS: 10,
  SM: 12,
  MD: 14,
  LG: 16,
  XL: 18,
  XXL: 20,
  XXXL: 24,
  TITLE: 28,
  LARGE_TITLE: 32,
};

// 间距
export const SPACING = {
  XS: 4,
  SM: 8,
  MD: 12,
  LG: 16,
  XL: 20,
  XXL: 24,
  XXXL: 32,
};

// 边框圆角
export const BORDER_RADIUS = {
  SM: 4,
  MD: 8,
  LG: 12,
  XL: 16,
  XXL: 20,
  ROUND: 50,
};

// 阴影
export const SHADOWS = {
  SMALL: {
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 2,
    elevation: 2,
  },
  MEDIUM: {
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.15,
    shadowRadius: 4,
    elevation: 4,
  },
  LARGE: {
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 8 },
    shadowOpacity: 0.2,
    shadowRadius: 8,
    elevation: 8,
  },
};

// API 配置
export const API_CONFIG = {
  BASE_URL: 'https://api.starwish.app',
  TIMEOUT: 10000,
  RETRY_ATTEMPTS: 3,
};

// 星座列表
export const CONSTELLATIONS = [
  '白羊座', '金牛座', '双子座', '巨蟹座',
  '狮子座', '处女座', '天秤座', '天蝎座',
  '射手座', '摩羯座', '水瓶座', '双鱼座',
];

// 兴趣标签
export const INTEREST_TAGS = [
  '阅读', '旅行', '摄影', '音乐', '电影', '健身',
  '美食', '游戏', '编程', '设计', '绘画', '舞蹈',
  '瑜伽', '跑步', '登山', '游泳', '篮球', '足球',
  '网球', '羽毛球', '咖啡', '茶艺', '烘焙', '手工',
  '花艺', '宠物', '动漫', '科技', '投资', '创业',
];

// 学历选项
export const EDUCATION_LEVELS = [
  '高中及以下', '大专', '本科', '硕士', '博士',
];

// 家庭状况选项
export const FAMILY_STATUS = [
  '独生子女', '有兄弟姐妹', '家庭和睦', '单亲家庭',
];

// 生活方式选项
export const LIFESTYLE_OPTIONS = [
  '规律作息', '夜猫子', '早起鸟', '健康生活',
  '热爱运动', '宅家族', '社交达人', '独处爱好者',
];

// 理想伴侣选项
export const IDEAL_PARTNER_TRAITS = [
  '温柔善良', '幽默有趣', '有上进心', '成熟稳重',
  '活泼开朗', '知识渊博', '有责任心', '浪漫体贴',
  '独立自主', '有共同话题', '三观一致', '颜值很高',
];

// 匹配算法权重
export const MATCHING_WEIGHTS = {
  EDUCATION: 0.3,      // 学历匹配权重
  INTERESTS: 0.25,     // 兴趣匹配权重
  CONSTELLATION: 0.15, // 星座匹配权重
  AGE: 0.15,          // 年龄匹配权重
  LOCATION: 0.1,      // 地理位置权重
  ACTIVITY: 0.05,     // 活跃度权重
};

// 星盘预测文案
export const STAR_PREDICTIONS = [
  '今日你的桃花运旺盛，适合主动出击寻找真爱',
  '水星逆行期间，建议多关注内心感受',
  '金星进入你的感情宫，有机会遇到心仪的人',
  '今日适合与火象星座的人交流',
  '月亮在你的社交宫，朋友可能为你介绍对象',
  '木星带来好运，今天是表白的好日子',
  '土星提醒你要脚踏实地，感情不可急躁',
  '天王星带来意外惊喜，可能有意想不到的邂逅',
  '海王星增强你的直觉，相信第一感觉',
  '冥王星带来深层变化，感情将有新突破',
];

// 应用设置
export const APP_SETTINGS = {
  MAX_DAILY_MATCHES: 21,        // 每日最大推荐数
  MAX_STAR_PREDICTIONS: 5,      // 每日最大星盘推荐次数
  STAR_STRANGER_REVEAL_DAYS: 3, // 星盘陌生人解锁天数
  MAX_MOMENT_LENGTH: 500,       // 动态最大字数
  MAX_PHOTOS: 9,               // 最大照片数量
  MIN_PASSWORD_LENGTH: 6,       // 最小密码长度
  MAX_NICKNAME_LENGTH: 20,      // 最大昵称长度
};