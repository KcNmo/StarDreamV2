package com.starwish.app.ui.screens.moments

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MomentsScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val privateMoments = remember { generateMockPrivateMoments() }
    val publicMoments = remember { generateMockPublicMoments() }
    
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
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "📱 动态广场",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "分享你的生活，发现更多精彩",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Button(
                    onClick = { /* TODO: Share moment */ },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700)
                    )
                ) {
                    Text("+", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                    text = "私域星圈",
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
                    text = "公域星海",
                    color = if (selectedTab == 1) Color.Black else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Content
        when (selectedTab) {
            0 -> MomentsList(moments = privateMoments, isPrivate = true)
            1 -> MomentsList(moments = publicMoments, isPrivate = false)
        }
    }
}

@Composable
private fun MomentsList(moments: List<MockMoment>, isPrivate: Boolean) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(moments) { moment ->
            MomentCard(
                moment = moment,
                isPrivate = isPrivate,
                onLike = { /* TODO: Handle like */ },
                onComment = { /* TODO: Handle comment */ },
                onShare = { /* TODO: Handle share */ },
                onProfileClick = { /* TODO: Navigate to profile */ }
            )
        }
    }
}

@Composable
private fun MomentCard(
    moment: MockMoment,
    isPrivate: Boolean,
    onLike: () -> Unit,
    onComment: () -> Unit,
    onShare: () -> Unit,
    onProfileClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // User Info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            if (moment.isAnonymous) Color(0xFF1A237E) else Color(0xFFFFD700)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (moment.isAnonymous) "?" else moment.nickname.first().toString(),
                        color = if (moment.isAnonymous) Color.White else Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = if (moment.isAnonymous) "匿名用户" else moment.nickname,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = moment.timeAgo,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                if (isPrivate) {
                    Text(
                        text = "仅好友可见",
                        fontSize = 12.sp,
                        color = Color(0xFFFFD700)
                    )
                }
            }
            
            // Content
            Text(
                text = moment.content,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            // Images (placeholder)
            if (moment.hasImages) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF1A237E))
                        ) {
                            // Placeholder for image
                        }
                    }
                }
            }
            
            // Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onLike,
                    modifier = Modifier.size(32.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (moment.isLiked) Color(0xFFFF6B6B) else Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.3f))
                        )
                    )
                ) {
                    Text(
                        text = if (moment.isLiked) "❤️" else "🤍",
                        fontSize = 12.sp
                    )
                }
                
                Text(
                    text = "${moment.likes}",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                
                Button(
                    onClick = onComment,
                    modifier = Modifier.size(32.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.3f))
                        )
                    )
                ) {
                    Text("💬", fontSize = 12.sp)
                }
                
                Text(
                    text = "${moment.comments}",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                
                Button(
                    onClick = onShare,
                    modifier = Modifier.size(32.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.3f))
                        )
                    )
                ) {
                    Text("📤", fontSize = 12.sp)
                }
                
                Text(
                    text = "${moment.shares}",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                if (!isPrivate && !moment.isAnonymous) {
                    Button(
                        onClick = onProfileClick,
                        modifier = Modifier.height(32.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFD700)
                        )
                    ) {
                        Text(
                            text = "私聊",
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// Mock data
private data class MockMoment(
    val nickname: String,
    val content: String,
    val timeAgo: String,
    val likes: Int,
    val comments: Int,
    val shares: Int,
    val isLiked: Boolean = false,
    val hasImages: Boolean = false,
    val isAnonymous: Boolean = false
)

private fun generateMockPrivateMoments(): List<MockMoment> {
    return listOf(
        MockMoment(
            "星辰",
            "今天去了海边，心情特别好！🌊",
            "2小时前",
            12, 3, 1,
            hasImages = true
        ),
        MockMoment(
            "月光",
            "刚读完一本很棒的书，推荐给大家《小王子》",
            "5小时前",
            8, 2, 0
        ),
        MockMoment(
            "清风",
            "周末和朋友一起爬山，虽然累但是很开心！",
            "1天前",
            15, 5, 2,
            hasImages = true
        )
    )
}

private fun generateMockPublicMoments(): List<MockMoment> {
    return listOf(
        MockMoment(
            "阳光",
            "分享一个学习编程的心得：坚持每天写代码，哪怕只有半小时",
            "1小时前",
            25, 8, 3
        ),
        MockMoment(
            "匿名用户",
            "最近工作压力很大，有没有同样在找工作的朋友？",
            "3小时前",
            18, 12, 1,
            isAnonymous = true
        ),
        MockMoment(
            "雨露",
            "今天做了一道新菜，味道还不错！",
            "6小时前",
            6, 2, 0,
            hasImages = true
        ),
        MockMoment(
            "匿名用户",
            "求助：如何提高自己的社交能力？",
            "1天前",
            32, 15, 5,
            isAnonymous = true
        )
    )
}