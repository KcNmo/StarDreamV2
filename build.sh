#!/bin/bash

# 星愿APP构建脚本
echo "🌟 开始构建星愿APP..."

# 检查是否安装了必要的工具
if ! command -v ./gradlew &> /dev/null; then
    echo "❌ 未找到Gradle Wrapper，请确保在项目根目录运行此脚本"
    exit 1
fi

# 清理项目
echo "🧹 清理项目..."
./gradlew clean

# 构建Debug版本
echo "🔨 构建Debug版本..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "✅ 构建成功！"
    echo "📱 APK文件位置: app/build/outputs/apk/debug/app-debug.apk"
    echo "🚀 可以使用以下命令安装到设备:"
    echo "   adb install app/build/outputs/apk/debug/app-debug.apk"
else
    echo "❌ 构建失败，请检查错误信息"
    exit 1
fi

echo "🌟 星愿APP构建完成！"