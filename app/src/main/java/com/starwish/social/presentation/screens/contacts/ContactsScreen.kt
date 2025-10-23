package com.starwish.social.presentation.screens.contacts

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
fun ContactsScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("好友", "星盘陌生人")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceDark)
    ) {
        // 顶部标题栏
        TopAppBar(
            title = {
                Text(
                    text = "联系人",
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            },
            actions = {
                IconButton(onClick = { /* 搜索 */ }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "搜索",
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
            0 -> FriendsList()
            1 -> StarStrangersList()
        }
    }
}

@Composable
private fun FriendsList() {
    val mockFriends = remember {
        listOf(
            MockContact("小星星", "今天心情不错", "2分钟前", true, 5),
            MockContact("月光", "在图书馆学习", "1小时前", false, 3),
            MockContact("银河", "刚看完一部好电影", "3小时前", false, 7),
            MockContact("晨曦", "准备去旅行", "昨天", false, 2)
        )
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(mockFriends) { friend ->
            ContactItem(
                contact = friend,
                isStarContact = false,
                onClick = { /* 进入聊天 */ }
            )
        }
    }
}

@Composable
private fun StarStrangersList() {
    val mockStarStrangers = remember {
        listOf(
            MockContact("星盘陌生人1", "通过星盘匹配", "1小时前", true, 0),
            MockContact("星盘陌生人2", "星座运势很配", "2小时前", false, 0),
            MockContact("星盘陌生人3", "今日星运推荐", "昨天", false, 0)
        )
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(mockStarStrangers) { stranger ->
            ContactItem(
                contact = stranger,
                isStarContact = true,
                onClick = { /* 进入星盘聊天 */ }
            )
        }
    }
}

@Composable
private fun ContactItem(
    contact: MockContact,
    isStarContact: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SpaceMedium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 头像
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = if (isStarContact) StarGold else StarBlueLight,
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    if (isStarContact) Icons.Default.Star else Icons.Default.Person,
                    contentDescription = null,
                    tint = if (isStarContact) SpaceDark else TextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // 用户信息
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = contact.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    
                    if (isStarContact) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = StarGold
                        )
                    }
                }
                
                Text(
                    text = contact.lastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            // 右侧信息
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = contact.lastActiveTime,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextTertiary
                )
                
                if (contact.unreadCount > 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = StarGold,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = contact.unreadCount.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = SpaceDark,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                if (isStarContact && contact.starLevel > 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(contact.starLevel) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier.size(12.dp),
                                tint = StarGold
                            )
                        }
                    }
                }
            }
        }
    }
}

data class MockContact(
    val name: String,
    val lastMessage: String,
    val lastActiveTime: String,
    val isOnline: Boolean,
    val unreadCount: Int,
    val starLevel: Int = 0
)