package com.starwish.social.presentation.screens.posts

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.starwish.social.presentation.theme.*

@Composable
fun PostsScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("私域星圈", "公域星海")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceDark)
    ) {
        // 顶部标题栏
        TopAppBar(
            title = {
                Text(
                    text = "动态",
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            },
            actions = {
                IconButton(onClick = { /* 发布动态 */ }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "发布",
                        tint = StarGold
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
            0 -> PrivatePostsList()
            1 -> PublicPostsList()
        }
    }
}

@Composable
private fun PrivatePostsList() {
    val mockPrivatePosts = remember {
        listOf(
            MockPost("小星星", "今天学习了一整天，感觉收获满满！", "2小时前", 5, 3, true),
            MockPost("月光", "分享一张今天拍的照片", "4小时前", 12, 8, false),
            MockPost("银河", "刚看完一部很棒的科幻电影，推荐给大家", "6小时前", 8, 5, false)
        )
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(mockPrivatePosts) { post ->
            PostItem(post = post)
        }
    }
}

@Composable
private fun PublicPostsList() {
    val mockPublicPosts = remember {
        listOf(
            MockPost("匿名用户", "求助：如何准备研究生面试？", "1小时前", 15, 12, true, isAnonymous = true),
            MockPost("星辰", "分享我的学习经验", "3小时前", 25, 18, false),
            MockPost("匿名用户", "今天心情不好，有人聊聊吗？", "5小时前", 8, 6, false, isAnonymous = true)
        )
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(mockPublicPosts) { post ->
            PostItem(post = post)
        }
    }
}

@Composable
private fun PostItem(post: MockPost) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SpaceMedium)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // 用户信息
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = if (post.isAnonymous) StarGold else StarBlueLight,
                            shape = MaterialTheme.shapes.medium
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (post.isAnonymous) Icons.Default.Person else Icons.Default.Person,
                        contentDescription = null,
                        tint = if (post.isAnonymous) SpaceDark else TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = post.author,
                        style = MaterialTheme.typography.titleSmall,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = post.time,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextTertiary
                    )
                }
                
                if (post.isAnonymous) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Default.VisibilityOff,
                        contentDescription = "匿名",
                        modifier = Modifier.size(16.dp),
                        tint = TextTertiary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 内容
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary,
                lineHeight = 20.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 操作按钮
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // 点赞
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /* 点赞 */ },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                if (post.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "点赞",
                                tint = if (post.isLiked) StarGold else TextSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = post.likes.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                    
                    // 评论
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /* 评论 */ },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                Icons.Default.Comment,
                                contentDescription = "评论",
                                tint = TextSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = post.comments.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                    
                    // 分享
                    IconButton(
                        onClick = { /* 分享 */ },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "分享",
                            tint = TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                // 更多操作
                IconButton(
                    onClick = { /* 更多 */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "更多",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

data class MockPost(
    val author: String,
    val content: String,
    val time: String,
    val likes: Int,
    val comments: Int,
    val isLiked: Boolean,
    val isAnonymous: Boolean = false
)