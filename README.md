# 星愿 StarWish

一个面向 18-29 岁年轻人的高学历轻社交 Android 应用。此仓库包含可编译运行的 Kotlin/Compose 工程骨架与核心功能占位，用于快速上架前的落地与迭代。

## 功能概览（本版本）
- 登录/注册：UID/手机号 + 密码/验证码（演示版内存校验，支持 DataStore 会话持久化）
- 底部导航四大分区：推荐、联系人、动态、我的
- 推荐页：每日 21 次 like/dislike 计数，星盘推荐次数 5 次按钮（演示）
- 联系人：展示好友与星盘陌生人示例，提示 3 天未互动消失，支持“星缘连续天数”占位
- 动态：星海公开列表与“发布”入口占位
- 个人主页：社交名片概览与“编辑/设置”入口占位

## 本地构建
此环境未预装 Gradle Wrapper。如需在本地构建，请在有 Android SDK 与 Gradle Wrapper 的环境中执行：

```bash
./gradlew assembleDemoDebug
```

最低支持 Android 7.0 (API 24)。

## 后续对接
- 将 `InMemoryRepository` 替换为后端 API（或 Firebase/LeanCloud）
- 完善星盘推荐算法与匿名头像生成、聊天与消息持久化（Room/Realm）
- 实现认证流程与相册上传（`READ_MEDIA_IMAGES` 权限）
