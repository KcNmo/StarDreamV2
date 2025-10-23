# 星愿APP项目结构

## 📁 项目目录结构

```
StarWish/
├── app/
│   ├── build.gradle                 # 应用级构建配置
│   ├── proguard-rules.pro          # ProGuard混淆规则
│   └── src/main/
│       ├── AndroidManifest.xml     # 应用清单文件
│       ├── java/com/starwish/social/
│       │   ├── StarWishApplication.kt    # 应用程序类
│       │   ├── data/                     # 数据层
│       │   │   ├── local/               # 本地数据
│       │   │   │   ├── entity/          # 数据库实体
│       │   │   │   │   ├── UserEntity.kt
│       │   │   │   │   └── MessageEntity.kt
│       │   │   │   ├── dao/             # 数据访问对象
│       │   │   │   │   ├── UserDao.kt
│       │   │   │   │   └── MessageDao.kt
│       │   │   │   └── StarWishDatabase.kt
│       │   │   ├── model/               # 数据模型
│       │   │   │   ├── User.kt
│       │   │   │   ├── Message.kt
│       │   │   │   ├── Post.kt
│       │   │   │   └── Match.kt
│       │   │   └── repository/          # 数据仓库实现
│       │   ├── domain/                  # 业务逻辑层
│       │   │   ├── usecase/            # 用例
│       │   │   └── repository/         # 仓库接口
│       │   └── presentation/           # 表现层
│       │       ├── MainActivity.kt     # 主Activity
│       │       ├── auth/               # 认证相关
│       │       │   ├── LoginActivity.kt
│       │       │   ├── RegisterActivity.kt
│       │       │   └── screens/
│       │       │       ├── LoginScreen.kt
│       │       │       └── RegisterScreen.kt
│       │       ├── screens/            # 界面
│       │       │   ├── main/
│       │       │   │   └── MainScreen.kt
│       │       │   ├── home/
│       │       │   │   └── HomeScreen.kt
│       │       │   ├── contacts/
│       │       │   │   └── ContactsScreen.kt
│       │       │   ├── posts/
│       │       │   │   └── PostsScreen.kt
│       │       │   └── profile/
│       │       │       └── ProfileScreen.kt
│       │       ├── navigation/         # 导航
│       │       │   ├── StarWishNavigation.kt
│       │       │   └── Screen.kt
│       │       └── theme/              # 主题
│       │           ├── Color.kt
│       │           ├── Theme.kt
│       │           └── Type.kt
│       └── res/                        # 资源文件
│           ├── values/
│           │   ├── strings.xml         # 字符串资源
│           │   ├── colors.xml          # 颜色资源
│           │   └── themes.xml          # 主题资源
│           ├── mipmap-hdpi/            # 应用图标
│           │   ├── ic_launcher.xml
│           │   └── ic_launcher_round.xml
│           └── xml/                    # XML配置文件
│               ├── backup_rules.xml
│               └── data_extraction_rules.xml
├── build.gradle                       # 项目级构建配置
├── settings.gradle                    # 项目设置
├── gradle.properties                  # Gradle属性
├── gradle/wrapper/                    # Gradle Wrapper
├── README.md                          # 项目说明
├── QUICK_START.md                     # 快速启动指南
├── PROJECT_STRUCTURE.md               # 项目结构说明
└── build.sh                           # 构建脚本
```

## 🏗️ 架构说明

### 1. Presentation Layer (表现层)
负责用户界面的展示和用户交互处理。

**主要组件：**
- **Activities**: 管理应用生命周期和界面容器
- **Screens**: Compose界面组件
- **Navigation**: 应用内导航管理
- **Theme**: 应用主题和样式

**特点：**
- 使用Jetpack Compose构建现代化UI
- 响应式设计，支持不同屏幕尺寸
- 星辰主题设计，营造星空氛围

### 2. Domain Layer (业务逻辑层)
包含业务逻辑和用例，独立于UI和数据源。

**主要组件：**
- **Use Cases**: 具体的业务用例
- **Repository Interfaces**: 数据仓库接口定义

**特点：**
- 纯Kotlin代码，不依赖Android框架
- 可测试性强
- 业务逻辑集中管理

### 3. Data Layer (数据层)
负责数据的管理和持久化。

**主要组件：**
- **Local Database**: Room数据库
- **Remote API**: 网络数据源
- **Repository Implementation**: 数据仓库实现

**特点：**
- 支持本地和远程数据源
- 数据缓存和同步
- 离线功能支持

## 🎨 设计模式

### MVVM (Model-View-ViewModel)
- **Model**: 数据模型和业务逻辑
- **View**: Compose界面组件
- **ViewModel**: 界面状态管理

### Repository Pattern
- 统一数据访问接口
- 隐藏数据源实现细节
- 支持数据源切换

### Dependency Injection (Hilt)
- 自动依赖管理
- 提高代码可测试性
- 降低组件耦合度

## 📱 功能模块

### 1. 用户认证模块
- 登录/注册界面
- 多种登录方式支持
- 用户信息管理

### 2. 推荐系统模块
- 精准推荐算法
- 星盘匹配功能
- 个性化推荐

### 3. 社交功能模块
- 联系人管理
- 实时聊天
- 动态分享

### 4. 个人中心模块
- 个人资料管理
- 设置和配置
- 认证系统

## 🔧 技术栈

### 前端技术
- **Kotlin**: 主要开发语言
- **Jetpack Compose**: 现代UI框架
- **Material Design 3**: 设计规范

### 后端技术
- **Room**: 本地数据库
- **Retrofit**: 网络请求
- **Hilt**: 依赖注入

### 开发工具
- **Android Studio**: 开发环境
- **Gradle**: 构建工具
- **Git**: 版本控制

## 📊 数据流

```
User Input → ViewModel → UseCase → Repository → DataSource
     ↑                                                    ↓
     ← UI Update ← State Management ← Data Processing ←
```

## 🚀 扩展性

项目采用模块化设计，便于：
- 添加新功能模块
- 替换数据源
- 修改UI组件
- 集成第三方服务

---

这个项目结构确保了代码的可维护性、可测试性和可扩展性，为后续功能开发奠定了良好的基础。