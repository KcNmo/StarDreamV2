#!/bin/bash

# 星愿APP构建脚本
echo "🌟 开始构建星愿APP..."

# 检查Node.js环境
if ! command -v node &> /dev/null; then
    echo "❌ 未找到Node.js，请先安装Node.js"
    exit 1
fi

# 检查npm
if ! command -v npm &> /dev/null; then
    echo "❌ 未找到npm，请先安装npm"
    exit 1
fi

# 安装依赖
echo "📦 安装项目依赖..."
npm install

if [ $? -ne 0 ]; then
    echo "❌ 依赖安装失败"
    exit 1
fi

# 检查Android环境
if [ ! -d "$ANDROID_HOME" ]; then
    echo "⚠️  未设置ANDROID_HOME环境变量，请确保已安装Android SDK"
fi

# 清理缓存
echo "🧹 清理缓存..."
npm start -- --reset-cache &
METRO_PID=$!
sleep 3
kill $METRO_PID 2>/dev/null

# 构建Android APK
echo "🔨 开始构建Android APK..."

# 进入android目录
cd android

# 清理之前的构建
echo "🧹 清理之前的构建文件..."
./gradlew clean

# 构建Debug APK
echo "🔧 构建Debug版本..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "✅ Debug APK构建成功！"
    echo "📍 APK位置: android/app/build/outputs/apk/debug/app-debug.apk"
else
    echo "❌ Debug APK构建失败"
    exit 1
fi

# 询问是否构建Release版本
read -p "是否构建Release版本？(y/N): " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "🔧 构建Release版本..."
    ./gradlew assembleRelease
    
    if [ $? -eq 0 ]; then
        echo "✅ Release APK构建成功！"
        echo "📍 APK位置: android/app/build/outputs/apk/release/app-release.apk"
    else
        echo "❌ Release APK构建失败"
        echo "💡 提示: Release版本需要签名配置，请检查gradle.properties文件"
    fi
fi

# 返回根目录
cd ..

echo ""
echo "🎉 构建完成！"
echo "📱 你可以将APK文件安装到Android设备上进行测试"
echo ""
echo "📋 构建信息:"
echo "   - 应用名称: 星愿"
echo "   - 包名: com.starwishapp"
echo "   - 版本: 1.0.0"
echo ""
echo "🚀 如需在模拟器中运行，请使用: npm run android"