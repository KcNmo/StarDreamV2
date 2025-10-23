package com.starwish.social.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.starwish.social.presentation.theme.*

@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("推荐", "星盘")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceDark)
    ) {
        // 顶部标题栏
        TopAppBar(
            title = {
                Text(
                    text = "星愿",
                    color = StarGold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            },
            actions = {
                IconButton(onClick = { /* 设置 */ }) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "设置",
                        tint = TextSecondary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = SpaceDark
            )
        )
        
        // 标签页切换
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SpaceMedium,
            contentColor = TextPrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index) StarGold else TextSecondary,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }
        
        // 内容区域
        when (selectedTab) {
            0 -> RecommendationContent()
            1 -> StarChartContent()
        }
    }
}

@Composable
private fun RecommendationContent() {
    var currentUserIndex by remember { mutableStateOf(0) }
    val mockUsers = remember {
        listOf(
            MockUser("小星星", "22岁", "双子座", "计算机科学", "喜欢编程和音乐"),
            MockUser("月光", "25岁", "天秤座", "设计师", "热爱艺术和旅行"),
            MockUser("银河", "23岁", "水瓶座", "研究生", "对宇宙充满好奇")
        )
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (currentUserIndex < mockUsers.size) {
            val user = mockUsers[currentUserIndex]
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .aspectRatio(0.7f),
                colors = CardDefaults.cardColors(containerColor = SpaceMedium)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // 用户信息
                    Column {
                        Text(
                            text = user.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Text(
                            text = "${user.age} • ${user.constellation}",
                            style = MaterialTheme.typography.titleMedium,
                            color = StarGold
                        )
                        
                        Text(
                            text = user.education,
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextSecondary
                        )
                        
                        Text(
                            text = user.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextTertiary,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                    
                    // 操作按钮
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        FloatingActionButton(
                            onClick = { 
                                // 不喜欢
                                currentUserIndex++
                            },
                            containerColor = Error,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "不喜欢",
                                tint = TextPrimary
                            )
                        }
                        
                        FloatingActionButton(
                            onClick = { 
                                // 喜欢
                                currentUserIndex++
                            },
                            containerColor = StarGold,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "喜欢",
                                tint = SpaceDark
                            )
                        }
                    }
                }
            }
        } else {
            // 没有更多推荐
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = StarGold
                )
                
                Text(
                    text = "今日推荐已结束",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )
                
                Text(
                    text = "明天再来看看新的星辰吧",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun StarChartContent() {
    var dailyCount by remember { mutableStateOf(0) }
    val maxDailyCount = 5
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 星盘图标
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(StarGold, StarBlueLight)
                    ),
                    shape = MaterialTheme.shapes.large
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = TextPrimary
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "今日星盘",
            style = MaterialTheme.typography.headlineMedium,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "根据你的星座和生辰，为你解读今日运势",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 今日运势
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SpaceMedium)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "今日运势",
                    style = MaterialTheme.typography.titleLarge,
                    color = StarGold,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "今天是个适合社交的好日子，你可能会遇到志同道合的朋友。保持开放的心态，新的机会正在向你走来。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPrimary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 星盘推荐按钮
        Button(
            onClick = { 
                if (dailyCount < maxDailyCount) {
                    dailyCount++
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (dailyCount < maxDailyCount) StarGold else SpaceLight
            ),
            enabled = dailyCount < maxDailyCount
        ) {
            Text(
                text = if (dailyCount < maxDailyCount) "开始星盘推荐" else "今日次数已用完",
                color = if (dailyCount < maxDailyCount) SpaceDark else TextSecondary,
                fontWeight = FontWeight.Bold
            )
        }
        
        Text(
            text = "今日剩余次数: ${maxDailyCount - dailyCount}",
            style = MaterialTheme.typography.bodySmall,
            color = TextTertiary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

data class MockUser(
    val name: String,
    val age: String,
    val constellation: String,
    val education: String,
    val description: String
)