# 星愿 - 高学历轻社交APP

## 项目简介

星愿是一款专为18-29岁高学历年轻人打造的轻社交APP，主打星座匹配和高质量社交体验。通过智能算法和星盘推荐，帮助用户找到志同道合的朋友和理想伴侣。

## 核心功能

### 🔐 认证系统
- 支持手机号/微信/QQ多种登录方式
- 8位UID系统，便于记忆和分享
- 学历/工作/身份三重认证保障真实性

### 🏠 首页推荐
- **智能匹配**：基于学历、兴趣、星座等多维度算法推荐
- **星盘推荐**：结合用户生辰八字和当日星象进行神秘匹配
- 每日21个精准推荐，每日5次星盘推荐机会

### 💬 联系人系统
- **好友管理**：通过双向匹配建立好友关系
- **星盘陌生人**：神秘身份聊天，3天后可解锁真实身份
- **星缘系统**：连续聊天积累星缘值，提升社群影响力

### 🌟 动态分享
- **私域星圈**：仅好友可见的私密分享空间
- **公域星海**：开放的社区动态广场
- 支持普通动态、匿名问答、求助等多种类型

### 👤 个人主页
- 完整的社交名片展示
- 兴趣标签和个性化资料
- 星运值系统展示用户活跃度和受欢迎程度

## 技术栈

- **前端框架**：React Native 0.72.6
- **导航**：React Navigation 6.x
- **状态管理**：React Context + useReducer
- **UI组件**：自定义组件 + React Native Vector Icons
- **数据存储**：AsyncStorage
- **渐变效果**：React Native Linear Gradient
- **动画**：React Native Reanimated

## 项目结构

```
src/
├── components/          # 公共组件
├── screens/            # 页面组件
│   ├── Auth/          # 认证相关页面
│   ├── Home/          # 首页推荐
│   ├── Chat/          # 聊天和联系人
│   ├── Moments/       # 动态分享
│   └── Profile/       # 个人主页
├── navigation/         # 导航配置
├── services/          # 业务服务
├── utils/             # 工具函数
├── contexts/          # React Context
├── types/             # TypeScript 类型定义
└── assets/            # 静态资源
```

## 安装和运行

### 环境要求
- Node.js >= 16
- React Native CLI
- Android Studio (Android开发)
- Java JDK 11

### 安装依赖
```bash
npm install
```

### 运行项目

#### Android
```bash
# 启动Metro服务器
npm start

# 运行Android应用
npm run android
```

#### iOS (如需支持)
```bash
# 安装iOS依赖
cd ios && pod install && cd ..

# 运行iOS应用
npm run ios
```

## 构建发布

### Android APK构建
```bash
# 构建Debug版本
npm run build:android-debug

# 构建Release版本
npm run build:android
```

生成的APK文件位于：`android/app/build/outputs/apk/`

## 主要特色

### 🎯 精准匹配算法
- 学历匹配权重：30%
- 兴趣匹配权重：25%
- 星座相性权重：15%
- 年龄匹配权重：15%
- 地理位置权重：10%
- 活跃度权重：5%

### 🔮 星盘推荐系统
- 基于用户生辰八字分析
- 结合当日星象运势
- 神秘身份保护隐私
- 渐进式身份解锁机制

### ⭐ 星运值系统
- 基础值：50分
- 认证加分：身份认证+10，学历认证+5，工作认证+5
- 完善度加分：头像、签名、兴趣等各项资料
- 活跃度加分：根据最后活跃时间动态调整

### 🛡️ 隐私保护
- 多层级隐私设置
- 匿名功能保护用户身份
- 举报和拉黑机制
- 严格的内容审核

## 设计理念

### 🌙 暗色主题
采用深空蓝配色方案，营造神秘星空氛围：
- 主色调：#1a1a2e (深空蓝)
- 背景色：#0f0f23 (深邃黑)
- 强调色：#ffd700 (星光金)
- 文字色：#ccd6f6 (星云白)

### ✨ 星空元素
- 星座图标和星星装饰
- 渐变效果模拟星空
- 流畅的动画过渡
- 温馨的交互反馈

## 开发规范

### 代码规范
- 使用TypeScript进行类型检查
- 遵循ESLint代码规范
- 组件采用函数式编程
- 使用Hooks管理状态

### 命名规范
- 组件：PascalCase (如：HomeScreen)
- 文件：camelCase (如：authService.ts)
- 常量：UPPER_SNAKE_CASE (如：API_CONFIG)
- 变量：camelCase (如：userName)

## 版本历史

### v1.0.0 (2024-01-20)
- ✅ 完整的用户认证系统
- ✅ 智能匹配推荐算法
- ✅ 星盘推荐功能
- ✅ 联系人和聊天系统
- ✅ 动态分享功能
- ✅ 个人主页和设置
- ✅ Android构建配置

## 后续规划

### v1.1.0
- [ ] 实时聊天功能
- [ ] 语音消息支持
- [ ] 图片上传和相册
- [ ] 推送通知

### v1.2.0
- [ ] 视频通话功能
- [ ] 群组聊天
- [ ] 活动组织功能
- [ ] 地理位置服务

### v2.0.0
- [ ] AI智能客服
- [ ] 更精准的推荐算法
- [ ] 社区功能扩展
- [ ] 付费会员体系

## 联系我们

- 项目地址：https://github.com/starwish/app
- 官方网站：https://starwish.app
- 客服邮箱：support@starwish.app

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

---

**星愿 - 让每颗星都闪闪发光** ✨