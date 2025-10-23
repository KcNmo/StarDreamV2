package com.starwish.social.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.starwish.social.presentation.theme.*

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var agreeToTerms by remember { mutableStateOf(false) }
    var currentStep by remember { mutableStateOf(1) } // 1: 手机验证, 2: 设置密码, 3: 完善资料
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SpaceDark,
                        SpaceMedium
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 标题
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                text = "加入星愿",
                style = MaterialTheme.typography.displayMedium,
                color = StarGold,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "开启你的星辰之旅",
                style = MaterialTheme.typography.titleMedium,
                color = TextSecondary,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // 步骤指示器
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = if (index < currentStep) StarGold else SpaceLight,
                                shape = MaterialTheme.shapes.small
                            )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            when (currentStep) {
                1 -> {
                    // 步骤1: 手机验证
                    Step1PhoneVerification(
                        phoneNumber = phoneNumber,
                        onPhoneNumberChange = { phoneNumber = it },
                        verificationCode = verificationCode,
                        onVerificationCodeChange = { verificationCode = it },
                        onNext = { currentStep = 2 }
                    )
                }
                2 -> {
                    // 步骤2: 设置密码
                    Step2PasswordSetup(
                        password = password,
                        onPasswordChange = { password = it },
                        confirmPassword = confirmPassword,
                        onConfirmPasswordChange = { confirmPassword = it },
                        passwordVisible = passwordVisible,
                        onPasswordVisibleChange = { passwordVisible = it },
                        confirmPasswordVisible = confirmPasswordVisible,
                        onConfirmPasswordVisibleChange = { confirmPasswordVisible = it },
                        onNext = { currentStep = 3 },
                        onBack = { currentStep = 1 }
                    )
                }
                3 -> {
                    // 步骤3: 完善资料
                    Step3ProfileSetup(
                        onComplete = onNavigateToMain,
                        onBack = { currentStep = 2 }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 返回登录
            Row {
                Text(
                    text = "已有账号？",
                    color = TextSecondary,
                    fontSize = 14.sp
                )
                
                TextButton(onClick = onNavigateToLogin) {
                    Text(
                        text = "立即登录",
                        color = StarGold,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun Step1PhoneVerification(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    verificationCode: String,
    onVerificationCodeChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "验证手机号",
            style = MaterialTheme.typography.headlineSmall,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "请输入您的手机号码",
            color = TextSecondary,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )
        
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = { Text("手机号", color = TextSecondary) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = StarBlueLight,
                unfocusedBorderColor = SpaceLight,
                focusedLabelColor = StarBlueLight,
                unfocusedLabelColor = TextSecondary,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = verificationCode,
                onValueChange = onVerificationCodeChange,
                label = { Text("验证码", color = TextSecondary) },
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = StarBlueLight,
                    unfocusedBorderColor = SpaceLight,
                    focusedLabelColor = StarBlueLight,
                    unfocusedLabelColor = TextSecondary,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            
            Button(
                onClick = { /* 发送验证码 */ },
                colors = ButtonDefaults.buttonColors(containerColor = StarGold),
                modifier = Modifier.height(56.dp)
            ) {
                Text("获取验证码", color = SpaceDark, fontSize = 12.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = StarBlueLight),
            enabled = phoneNumber.isNotEmpty() && verificationCode.isNotEmpty()
        ) {
            Text(
                text = "下一步",
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun Step2PasswordSetup(
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibleChange: (Boolean) -> Unit,
    confirmPasswordVisible: Boolean,
    onConfirmPasswordVisibleChange: (Boolean) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "设置密码",
            style = MaterialTheme.typography.headlineSmall,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "请设置您的登录密码",
            color = TextSecondary,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )
        
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("密码", color = TextSecondary) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = { onPasswordVisibleChange(!passwordVisible) }) {
                    Text(
                        text = if (passwordVisible) "隐藏" else "显示",
                        color = StarBlueLight,
                        fontSize = 12.sp
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = StarBlueLight,
                unfocusedBorderColor = SpaceLight,
                focusedLabelColor = StarBlueLight,
                unfocusedLabelColor = TextSecondary,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = { Text("确认密码", color = TextSecondary) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = { onConfirmPasswordVisibleChange(!confirmPasswordVisible) }) {
                    Text(
                        text = if (confirmPasswordVisible) "隐藏" else "显示",
                        color = StarBlueLight,
                        fontSize = 12.sp
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = StarBlueLight,
                unfocusedBorderColor = SpaceLight,
                focusedLabelColor = StarBlueLight,
                unfocusedLabelColor = TextSecondary,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = TextSecondary
                )
            ) {
                Text("上一步", fontSize = 16.sp)
            }
            
            Button(
                onClick = onNext,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = StarBlueLight),
                enabled = password.isNotEmpty() && confirmPassword.isNotEmpty() && password == confirmPassword
            ) {
                Text(
                    text = "下一步",
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun Step3ProfileSetup(
    onComplete: () -> Unit,
    onBack: () -> Unit
) {
    var nickname by remember { mutableStateOf("") }
    var personalSignature by remember { mutableStateOf("") }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "完善资料",
            style = MaterialTheme.typography.headlineSmall,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "让我们更好地了解你",
            color = TextSecondary,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )
        
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("昵称", color = TextSecondary) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = StarBlueLight,
                unfocusedBorderColor = SpaceLight,
                focusedLabelColor = StarBlueLight,
                unfocusedLabelColor = TextSecondary,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = personalSignature,
            onValueChange = { personalSignature = it },
            label = { Text("个性签名", color = TextSecondary) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = StarBlueLight,
                unfocusedBorderColor = SpaceLight,
                focusedLabelColor = StarBlueLight,
                unfocusedLabelColor = TextSecondary,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = TextSecondary
                )
            ) {
                Text("上一步", fontSize = 16.sp)
            }
            
            Button(
                onClick = onComplete,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = StarGold),
                enabled = nickname.isNotEmpty()
            ) {
                Text(
                    text = "完成注册",
                    color = SpaceDark,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}