# 星愿APP - 快速开始指南

## 🚀 快速启动

### 1. 环境准备

确保你的开发环境已安装以下工具：

- **Node.js** (版本 >= 16)
- **npm** 或 **yarn**
- **Android Studio** (用于Android开发)
- **Java JDK 11**

### 2. 克隆项目

```bash
git clone https://github.com/your-repo/star-wish-app.git
cd star-wish-app
```

### 3. 安装依赖

```bash
npm install
```

### 4. 运行应用

#### 在Android模拟器中运行：
```bash
npm run android
```

#### 在Android设备中运行：
1. 启用开发者选项和USB调试
2. 连接设备到电脑
3. 运行 `npm run android`

### 5. 构建APK

使用提供的构建脚本：

```bash
./build.sh
```

或手动构建：

```bash
cd android
./gradlew assembleDebug
```

## 📱 应用功能测试

### 登录测试账号
- **UID**: 12345678
- **密码**: 123456
- **验证码**: 123456 (万能验证码)

### 注册测试
- 使用任意11位手机号
- 验证码：123456
- 设置密码和昵称即可完成注册

## 🎯 主要功能演示

### 1. 首页推荐
- 查看每日推荐用户
- 点击❤️或❌进行匹配
- 尝试星盘推荐功能

### 2. 联系人
- 查看匹配成功的好友
- 体验星盘陌生人功能
- 查看星缘值系统

### 3. 动态分享
- 在私域星圈发布动态
- 在公域星海浏览内容
- 尝试匿名问答功能

### 4. 个人主页
- 完善个人资料
- 查看星运值
- 体验各种设置功能

## 🛠️ 开发调试

### 启动Metro服务器
```bash
npm start
```

### 查看日志
```bash
npx react-native log-android
```

### 清理缓存
```bash
npm start -- --reset-cache
```

## 📦 项目结构说明

```
src/
├── screens/           # 页面组件
│   ├── Auth/         # 登录注册页面
│   ├── Home/         # 首页推荐
│   ├── Chat/         # 联系人页面
│   ├── Moments/      # 动态分享
│   └── Profile/      # 个人主页
├── components/       # 公共组件
├── navigation/       # 导航配置
├── services/         # 业务服务
├── utils/           # 工具函数
├── contexts/        # 状态管理
└── types/           # 类型定义
```

## 🎨 UI主题

应用采用深色星空主题：

- **主色调**: #1a1a2e (深空蓝)
- **背景色**: #0f0f23 (深邃黑)  
- **强调色**: #ffd700 (星光金)
- **文字色**: #ccd6f6 (星云白)

## 🔧 常见问题

### Q: Metro服务器启动失败
A: 尝试清理缓存 `npm start -- --reset-cache`

### Q: Android构建失败
A: 检查Android SDK和Java JDK版本，确保环境变量正确设置

### Q: 应用闪退
A: 查看日志输出，检查是否有未处理的异常

### Q: 热重载不工作
A: 摇晃设备或按Ctrl+M打开开发菜单，启用热重载

## 📞 技术支持

如遇到问题，请：

1. 查看控制台错误信息
2. 检查环境配置
3. 参考React Native官方文档
4. 提交Issue到项目仓库

---

**祝你开发愉快！** ✨