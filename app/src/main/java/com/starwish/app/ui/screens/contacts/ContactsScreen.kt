package com.starwish.app.ui.screens.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactsScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val friends = remember { generateMockFriends() }
    val starStrangers = remember { generateMockStarStrangers() }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D1117))
    ) {
        // Header with Star Luck
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A237E)
            )
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "🌟 我的星运",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "当前星缘：${calculateStarLuck(friends)}",
                        fontSize = 14.sp,
                        color = Color(0xFFFFD700)
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Star Luck Visual
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFFFD700),
                                    Color(0xFFFFA500)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⭐",
                        fontSize = 24.sp
                    )
                }
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
                onClick = { selectedTab = 0 },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == 0) Color(0xFFFFD700) else Color.Transparent
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.5f))
                    )
                )
            ) {
                Text(
                    text = "好友",
                    color = if (selectedTab == 0) Color.Black else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Button(
                onClick = { selectedTab = 1 },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == 1) Color(0xFFFFD700) else Color.Transparent
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.5f))
                    )
                )
            ) {
                Text(
                    text = "星盘陌生人",
                    color = if (selectedTab == 1) Color.Black else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Content
        when (selectedTab) {
            0 -> FriendsList(friends = friends)
            1 -> StarStrangersList(starStrangers = starStrangers)
        }
    }
}

@Composable
private fun FriendsList(friends: List<MockContact>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(friends) { friend ->
            ContactCard(
                contact = friend,
                isStarStranger = false,
                onChatClick = { /* TODO: Navigate to chat */ },
                onUnlockClick = null
            )
        }
    }
}

@Composable
private fun StarStrangersList(starStrangers: List<MockContact>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(starStrangers) { stranger ->
            ContactCard(
                contact = stranger,
                isStarStranger = true,
                onChatClick = { /* TODO: Navigate to chat */ },
                onUnlockClick = { /* TODO: Unlock identity */ }
            )
        }
    }
}

@Composable
private fun ContactCard(
    contact: MockContact,
    isStarStranger: Boolean,
    onChatClick: () -> Unit,
    onUnlockClick: (() -> Unit)?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        if (isStarStranger) Color(0xFF1A237E) else Color(0xFFFFD700)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isStarStranger) "⭐" else contact.nickname.first().toString(),
                    color = if (isStarStranger) Color.White else Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Contact Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (isStarStranger) "星盘陌生人" else contact.nickname,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Text(
                    text = if (isStarStranger) "神秘身份待解锁" else contact.lastMessage,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    maxLines = 1
                )
                
                if (isStarStranger) {
                    Text(
                        text = "剩余时间：${contact.remainingTime}",
                        fontSize = 12.sp,
                        color = Color(0xFFFFD700)
                    )
                } else {
                    Text(
                        text = "星缘：${contact.starConnectionDays}天",
                        fontSize = 12.sp,
                        color = Color(0xFFFFD700)
                    )
                }
            }
            
            // Action Buttons
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = onChatClick,
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700)
                    )
                ) {
                    Text("💬", fontSize = 16.sp)
                }
                
                if (isStarStranger && onUnlockClick != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Button(
                        onClick = onUnlockClick,
                        modifier = Modifier.height(32.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A237E)
                        )
                    ) {
                        Text(
                            text = "解锁身份",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

private fun calculateStarLuck(friends: List<MockContact>): String {
    val totalDays = friends.sumOf { it.starConnectionDays }
    return when {
        totalDays >= 100 -> "璀璨"
        totalDays >= 50 -> "明亮"
        totalDays >= 20 -> "闪烁"
        else -> "微弱"
    }
}

// Mock data
private data class MockContact(
    val nickname: String,
    val lastMessage: String,
    val starConnectionDays: Int,
    val remainingTime: String = ""
)

private fun generateMockFriends(): List<MockContact> {
    return listOf(
        MockContact("星辰", "今天天气真不错！", 15),
        MockContact("月光", "最近在读什么书？", 8),
        MockContact("清风", "周末一起出去玩吧", 22),
        MockContact("阳光", "谢谢你的建议", 5),
        MockContact("雨露", "晚安，好梦", 12)
    )
}

private fun generateMockStarStrangers(): List<MockContact> {
    return listOf(
        MockContact("神秘人A", "你好，很高兴认识你", 0, "2天12小时"),
        MockContact("神秘人B", "你的星座很有趣", 0, "1天8小时"),
        MockContact("神秘人C", "我们有很多共同话题", 0, "3天5小时")
    )
}