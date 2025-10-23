package com.starwish.app.ui.screens.profile

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
fun ProfileScreen() {
    val userProfile = remember { generateMockUserProfile() }
    val userPosts = remember { generateMockUserPosts() }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D1117))
    ) {
        // Profile Header
        item {
            ProfileHeader(
                profile = userProfile,
                onEditClick = { /* TODO: Edit profile */ },
                onSettingsClick = { /* TODO: Open settings */ }
            )
        }
        
        // Social Card
        item {
            SocialCard(profile = userProfile)
        }
        
        // Stats
        item {
            ProfileStats(
                posts = userPosts.size,
                friends = 128,
                starLuck = "明亮"
            )
        }
        
        // My Posts
        item {
            Text(
                text = "我的动态",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        items(userPosts) { post ->
            ProfilePostCard(
                post = post,
                onEditClick = { /* TODO: Edit post */ },
                onDeleteClick = { /* TODO: Delete post */ }
            )
        }
    }
}

@Composable
private fun ProfileHeader(
    profile: MockUserProfile,
    onEditClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
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
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar and Verification
            Box(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFD700)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profile.nickname.first().toString(),
                        color = Color.Black,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Verification Badge
                if (profile.isVerified) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50))
                            .align(Alignment.BottomEnd)
                    ) {
                        Text(
                            text = "✓",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            
            // User Info
            Text(
                text = profile.nickname,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Text(
                text = "UID: ${profile.uid}",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 4.dp)
            )
            
            Text(
                text = profile.personalSignature,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            // Action Buttons
            Row(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onEditClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700)
                    )
                ) {
                    Text(
                        text = "编辑资料",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Button(
                    onClick = onSettingsClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.5f))
                        )
                    )
                ) {
                    Text(
                        text = "设置",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun SocialCard(profile: MockUserProfile) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22)
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "社交名片",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Basic Info
            ProfileInfoRow("年龄", "${profile.age}岁")
            ProfileInfoRow("星座", profile.constellation)
            ProfileInfoRow("身高", "${profile.height}cm")
            ProfileInfoRow("学历", profile.education)
            ProfileInfoRow("职业", profile.occupation)
            
            // Interests
            Text(
                text = "兴趣爱好",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.wrapContentHeight()
            ) {
                profile.interests.forEach { interest ->
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

@Composable
private fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label:",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.width(80.dp)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ProfileStats(
    posts: Int,
    friends: Int,
    starLuck: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22)
        )
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem("动态", posts.toString())
            StatItem("好友", friends.toString())
            StatItem("星运", starLuck)
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFD700)
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun ProfilePostCard(
    post: MockUserPost,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = post.timeAgo,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.6f)
                )
                
                Row {
                    Button(
                        onClick = onEditClick,
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
                        Text("✏️", fontSize = 12.sp)
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Button(
                        onClick = onDeleteClick,
                        modifier = Modifier.size(32.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color.Red.copy(alpha = 0.5f))
                            )
                        )
                    ) {
                        Text("🗑️", fontSize = 12.sp)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = post.content,
                fontSize = 14.sp,
                color = Color.White
            )
            
            if (post.hasImages) {
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(2) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF1A237E))
                        ) {
                            // Placeholder for image
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "❤️ ${post.likes}",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = "💬 ${post.comments}",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = "📤 ${post.shares}",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// Mock data
private data class MockUserProfile(
    val uid: String,
    val nickname: String,
    val personalSignature: String,
    val age: Int,
    val constellation: String,
    val height: Int,
    val education: String,
    val occupation: String,
    val interests: List<String>,
    val isVerified: Boolean = false
)

private data class MockUserPost(
    val content: String,
    val timeAgo: String,
    val likes: Int,
    val comments: Int,
    val shares: Int,
    val hasImages: Boolean = false
)

private fun generateMockUserProfile(): MockUserProfile {
    return MockUserProfile(
        uid = "12345678",
        nickname = "星辰",
        personalSignature = "热爱生活，追求梦想",
        age = 22,
        constellation = "白羊座",
        height = 175,
        education = "本科",
        occupation = "软件工程师",
        interests = listOf("读书", "旅行", "摄影", "编程"),
        isVerified = true
    )
}

private fun generateMockUserPosts(): List<MockUserPost> {
    return listOf(
        MockUserPost(
            "今天去了海边，心情特别好！🌊",
            "2小时前",
            12, 3, 1,
            hasImages = true
        ),
        MockUserPost(
            "刚读完一本很棒的书，推荐给大家《小王子》",
            "5小时前",
            8, 2, 0
        ),
        MockUserPost(
            "周末和朋友一起爬山，虽然累但是很开心！",
            "1天前",
            15, 5, 2,
            hasImages = true
        ),
        MockUserPost(
            "分享一个学习编程的心得：坚持每天写代码",
            "2天前",
            25, 8, 3
        )
    )
}