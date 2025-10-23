package com.starwish.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen() {
    var currentRecommendationType by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { 2 })
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D1117))
    ) {
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A237E)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🌟 今日星运",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "根据你的星座为你推荐合适的人",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // Tab Selection
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { currentRecommendationType = 0 },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentRecommendationType == 0) Color(0xFFFFD700) else Color.Transparent
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.5f))
                    )
                )
            ) {
                Text(
                    text = "精准推荐",
                    color = if (currentRecommendationType == 0) Color.Black else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Button(
                onClick = { currentRecommendationType = 1 },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentRecommendationType == 1) Color(0xFFFFD700) else Color.Transparent
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.5f))
                    )
                )
            ) {
                Text(
                    text = "星盘推荐",
                    color = if (currentRecommendationType == 1) Color.Black else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Content
        when (currentRecommendationType) {
            0 -> DailyRecommendations()
            1 -> StarRecommendations()
        }
    }
}

@Composable
private fun DailyRecommendations() {
    var currentIndex by remember { mutableStateOf(0) }
    val recommendations = remember { generateMockRecommendations() }
    
    if (recommendations.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Recommendation Card
            RecommendationCard(
                user = recommendations[currentIndex],
                onLike = {
                    // TODO: Handle like
                    if (currentIndex < recommendations.size - 1) {
                        currentIndex++
                    }
                },
                onDislike = {
                    // TODO: Handle dislike
                    if (currentIndex < recommendations.size - 1) {
                        currentIndex++
                    }
                }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Action Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Button(
                    onClick = { 
                        if (currentIndex < recommendations.size - 1) {
                            currentIndex++
                        }
                    },
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("❌", fontSize = 24.sp)
                }
                
                Button(
                    onClick = { 
                        if (currentIndex < recommendations.size - 1) {
                            currentIndex++
                        }
                    },
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text("✅", fontSize = 24.sp)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "剩余推荐：${recommendations.size - currentIndex - 1}",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "今日推荐已用完\n明天再来看看吧！",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun StarRecommendations() {
    var remainingAttempts by remember { mutableStateOf(5) }
    var currentStarReading by remember { mutableStateOf<String?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A237E)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🔮 星盘解读",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                if (currentStarReading != null) {
                    Text(
                        text = currentStarReading!!,
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "点击下方按钮开始星盘解读",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "剩余次数：$remainingAttempts",
                    color = Color(0xFFFFD700),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = {
                if (remainingAttempts > 0) {
                    currentStarReading = generateStarReading()
                    remainingAttempts--
                }
            },
            enabled = remainingAttempts > 0,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFD700)
            )
        ) {
            Text(
                text = if (remainingAttempts > 0) "开始星盘解读" else "今日次数已用完",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        
        if (remainingAttempts < 5) {
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "根据星盘为你推荐合适的人",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun RecommendationCard(
    user: MockUser,
    onLike: () -> Unit,
    onDislike: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(500.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22)
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image (placeholder)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1A237E),
                                Color(0xFF0D1117)
                            )
                        )
                    )
            )
            
            // User Info
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
            ) {
                Text(
                    text = user.nickname,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Text(
                    text = "${user.age}岁 • ${user.constellation}",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                
                Text(
                    text = user.personalSignature,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 8.dp)
                )
                
                // Interests
                Row(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    user.interests.take(3).forEach { interest ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFD700)
                            )
                        ) {
                            Text(
                                text = interest,
                                color = Color.Black,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// Mock data
private data class MockUser(
    val nickname: String,
    val age: Int,
    val constellation: String,
    val personalSignature: String,
    val interests: List<String>
)

private fun generateMockRecommendations(): List<MockUser> {
    return listOf(
        MockUser("星辰", 22, "白羊座", "热爱生活，追求梦想", listOf("读书", "旅行", "摄影")),
        MockUser("月光", 25, "天秤座", "温柔如水，善解人意", listOf("音乐", "绘画", "咖啡")),
        MockUser("清风", 23, "双子座", "活泼开朗，喜欢交朋友", listOf("运动", "游戏", "美食")),
        MockUser("阳光", 24, "狮子座", "自信阳光，充满正能量", listOf("健身", "电影", "写作")),
        MockUser("雨露", 21, "巨蟹座", "温柔体贴，善解人意", listOf("手工", "园艺", "阅读"))
    )
}

private fun generateStarReading(): String {
    val readings = listOf(
        "今日你的爱情运势极佳，可能会遇到让你心动的人。建议多参加社交活动，展现你的魅力。",
        "今天适合主动出击，你的真诚和善良会吸引到志同道合的朋友。不要害怕表达自己的想法。",
        "星象显示你今天会有意外的惊喜，可能是来自远方的消息或者新的机会。保持开放的心态。",
        "你的直觉今天特别敏锐，相信自己的判断。在感情方面，可能会有重要的进展。",
        "今天适合反思和规划，思考一下你真正想要的是什么。清晰的自我认知会帮助你找到合适的人。"
    )
    return readings.random()
}