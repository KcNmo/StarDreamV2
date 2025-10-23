package com.starwish.app.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.starwish.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(
    onProfileComplete: () -> Unit
) {
    var currentStep by remember { mutableStateOf(0) }
    var nickname by remember { mutableStateOf("") }
    var personalSignature by remember { mutableStateOf("") }
    var personalIntro by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var interests by remember { mutableStateOf(listOf<String>()) }
    var familySituation by remember { mutableStateOf("") }
    var dailyLife by remember { mutableStateOf("") }
    var educationWork by remember { mutableStateOf("") }
    var idealPartner by remember { mutableStateOf("") }

    val interestOptions = listOf(
        "读书", "音乐", "电影", "运动", "旅行", "美食", "摄影", "绘画",
        "游戏", "编程", "写作", "舞蹈", "唱歌", "乐器", "手工", "收藏"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D1117),
                        Color(0xFF1A237E)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progress Indicator
            LinearProgressIndicator(
                progress = (currentStep + 1) / 4f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                color = Color(0xFFFFD700),
                trackColor = Color.White.copy(alpha = 0.3f)
            )

            Text(
                text = "完善个人资料",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = "让我们更好地了解你",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            when (currentStep) {
                0 -> BasicInfoStep(
                    nickname = nickname,
                    onNicknameChange = { nickname = it },
                    personalSignature = personalSignature,
                    onPersonalSignatureChange = { personalSignature = it },
                    personalIntro = personalIntro,
                    onPersonalIntroChange = { personalIntro = it }
                )
                1 -> PhysicalInfoStep(
                    birthday = birthday,
                    onBirthdayChange = { birthday = it },
                    height = height,
                    onHeightChange = { height = it },
                    gender = gender,
                    onGenderChange = { gender = it }
                )
                2 -> InterestStep(
                    interests = interests,
                    onInterestsChange = { interests = it },
                    interestOptions = interestOptions
                )
                3 -> LifestyleStep(
                    familySituation = familySituation,
                    onFamilySituationChange = { familySituation = it },
                    dailyLife = dailyLife,
                    onDailyLifeChange = { dailyLife = it },
                    educationWork = educationWork,
                    onEducationWorkChange = { educationWork = it },
                    idealPartner = idealPartner,
                    onIdealPartnerChange = { idealPartner = it }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Navigation Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (currentStep > 0) {
                    Button(
                        onClick = { currentStep-- },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color.White.copy(alpha = 0.5f))
                            )
                        )
                    ) {
                        Text("上一步", color = Color.White)
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }

                Button(
                    onClick = {
                        if (currentStep < 3) {
                            currentStep++
                        } else {
                            onProfileComplete()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700)
                    )
                ) {
                    Text(
                        text = if (currentStep < 3) "下一步" else "完成",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun BasicInfoStep(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    personalSignature: String,
    onPersonalSignatureChange: (String) -> Unit,
    personalIntro: String,
    onPersonalIntroChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "基本信息",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "头像",
                    modifier = Modifier.size(60.dp)
                )
            }

            Text(
                text = "点击上传头像",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )

            OutlinedTextField(
                value = nickname,
                onValueChange = onNicknameChange,
                label = { Text("昵称", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )

            OutlinedTextField(
                value = personalSignature,
                onValueChange = onPersonalSignatureChange,
                label = { Text("个性签名", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )

            OutlinedTextField(
                value = personalIntro,
                onValueChange = onPersonalIntroChange,
                label = { Text("个人简介", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 4,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )
        }
    }
}

@Composable
private fun PhysicalInfoStep(
    birthday: String,
    onBirthdayChange: (String) -> Unit,
    height: String,
    onHeightChange: (String) -> Unit,
    gender: String,
    onGenderChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "身体信息",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Gender Selection
            Text(
                text = "性别",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { onGenderChange("男") },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (gender == "男") Color(0xFFFFD700) else Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.5f))
                        )
                    )
                ) {
                    Text(
                        text = "男",
                        color = if (gender == "男") Color.Black else Color.White
                    )
                }

                Button(
                    onClick = { onGenderChange("女") },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (gender == "女") Color(0xFFFFD700) else Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.5f))
                        )
                    )
                ) {
                    Text(
                        text = "女",
                        color = if (gender == "女") Color.Black else Color.White
                    )
                }
            }

            OutlinedTextField(
                value = birthday,
                onValueChange = onBirthdayChange,
                label = { Text("生日 (YYYY-MM-DD)", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )

            OutlinedTextField(
                value = height,
                onValueChange = onHeightChange,
                label = { Text("身高 (cm)", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )
        }
    }
}

@Composable
private fun InterestStep(
    interests: List<String>,
    onInterestsChange: (List<String>) -> Unit,
    interestOptions: List<String>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "兴趣爱好",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "选择你的兴趣爱好（可多选）",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(interestOptions.size) { index ->
                    val interest = interestOptions[index]
                    val isSelected = interests.contains(interest)
                    
                    Button(
                        onClick = {
                            if (isSelected) {
                                onInterestsChange(interests - interest)
                            } else {
                                onInterestsChange(interests + interest)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0xFFFFD700) else Color.Transparent
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color.White.copy(alpha = 0.5f))
                            )
                        )
                    ) {
                        Text(
                            text = interest,
                            color = if (isSelected) Color.Black else Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LifestyleStep(
    familySituation: String,
    onFamilySituationChange: (String) -> Unit,
    dailyLife: String,
    onDailyLifeChange: (String) -> Unit,
    educationWork: String,
    onEducationWorkChange: (String) -> Unit,
    idealPartner: String,
    onIdealPartnerChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "生活方式",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = familySituation,
                onValueChange = onFamilySituationChange,
                label = { Text("家庭情况", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(80.dp),
                maxLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )

            OutlinedTextField(
                value = dailyLife,
                onValueChange = onDailyLifeChange,
                label = { Text("日常生活", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(80.dp),
                maxLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )

            OutlinedTextField(
                value = educationWork,
                onValueChange = onEducationWorkChange,
                label = { Text("学历工作", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(80.dp),
                maxLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )

            OutlinedTextField(
                value = idealPartner,
                onValueChange = onIdealPartnerChange,
                label = { Text("理想的Ta", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                maxLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    cursorColor = Color.White
                )
            )
        }
    }
}