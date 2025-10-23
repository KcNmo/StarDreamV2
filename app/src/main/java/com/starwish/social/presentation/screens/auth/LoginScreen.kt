package com.starwish.social.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import com.starwish.social.R
import com.starwish.social.presentation.theme.*

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    var uidOrPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoginMode by remember { mutableStateOf(true) } // true: 密码登录, false: 验证码登录
    var verificationCode by remember { mutableStateOf("") }
    var agreeToTerms by remember { mutableStateOf(false) }
    
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo和标题
            Spacer(modifier = Modifier.height(60.dp))
            
            Text(
                text = "星愿",
                style = MaterialTheme.typography.displayLarge,
                color = StarGold,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "万千星辰，遇见你",
                style = MaterialTheme.typography.titleMedium,
                color = TextSecondary,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(60.dp))
            
            // 登录方式切换
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { isLoginMode = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isLoginMode) StarBlueLight else Color.Transparent
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "密码登录",
                        color = if (isLoginMode) TextPrimary else TextSecondary
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Button(
                    onClick = { isLoginMode = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isLoginMode) StarBlueLight else Color.Transparent
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "验证码登录",
                        color = if (!isLoginMode) TextPrimary else TextSecondary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 输入框
            OutlinedTextField(
                value = uidOrPhone,
                onValueChange = { uidOrPhone = it },
                label = { Text("UID/手机号", color = TextSecondary) },
                modifier = Modifier.fillMaxWidth(),
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
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (isLoginMode) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("密码", color = TextSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
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
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = verificationCode,
                        onValueChange = { verificationCode = it },
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
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 同意条款
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = agreeToTerms,
                    onCheckedChange = { agreeToTerms = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = StarBlueLight,
                        uncheckedColor = SpaceLight
                    )
                )
                
                Text(
                    text = "我已阅读并同意《用户协议》和《隐私政策》",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 登录按钮
            Button(
                onClick = { onNavigateToMain() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (agreeToTerms) StarBlueLight else SpaceLight
                ),
                enabled = agreeToTerms && uidOrPhone.isNotEmpty() && 
                         (if (isLoginMode) password.isNotEmpty() else verificationCode.isNotEmpty())
            ) {
                Text(
                    text = "登录",
                    color = if (agreeToTerms) TextPrimary else TextSecondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 第三方登录
            Text(
                text = "其他登录方式",
                color = TextSecondary,
                fontSize = 12.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // 微信登录
                Button(
                    onClick = { /* 微信登录 */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF07C160)),
                    modifier = Modifier.size(48.dp)
                ) {
                    Text("微", color = TextPrimary, fontWeight = FontWeight.Bold)
                }
                
                // QQ登录
                Button(
                    onClick = { /* QQ登录 */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF12B7F5)),
                    modifier = Modifier.size(48.dp)
                ) {
                    Text("Q", color = TextPrimary, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 注册链接
            Row {
                Text(
                    text = "还没有账号？",
                    color = TextSecondary,
                    fontSize = 14.sp
                )
                
                TextButton(onClick = onNavigateToRegister) {
                    Text(
                        text = "立即注册",
                        color = StarGold,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}