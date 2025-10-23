package com.starwish.social.presentation.screens.profile

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
fun ProfileScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceDark)
    ) {
        item {
            // 个人信息卡片
            ProfileHeader()
        }
        
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        item {
            // 功能菜单
            ProfileMenu()
        }
        
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        item {
            // 我的动态
            MyPostsSection()
        }
    }
}

@Composable
private fun ProfileHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceMedium)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 头像
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(StarGold, StarBlueLight)
                        ),
                        shape = MaterialTheme.shapes.large
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = TextPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 基本信息
            Text(
                text = "小星星",
                style = MaterialTheme.typography.headlineSmall,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "UID: 12345678",
                style = MaterialTheme.typography.bodyMedium,
                color = StarGold
            )
            
            Text(
                text = "万千星辰，遇见你",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 认证状态
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Chip(
                    onClick = { /* 头像认证 */ },
                    colors = ChipDefaults.chipColors(
                        containerColor = if (true) Success else SpaceLight
                    )
                ) {
                    Icon(
                        Icons.Default.Verified,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (true) TextPrimary else TextSecondary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "头像认证",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (true) TextPrimary else TextSecondary
                    )
                }
                
                Chip(
                    onClick = { /* 学历认证 */ },
                    colors = ChipDefaults.chipColors(
                        containerColor = if (false) Success else SpaceLight
                    )
                ) {
                    Icon(
                        Icons.Default.School,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (false) TextPrimary else TextSecondary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "学历认证",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (false) TextPrimary else TextSecondary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 星运值
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StarStatItem("星缘", "15", "与好友的连续聊天天数")
                StarStatItem("星运", "8.5", "在社群中的受欢迎程度")
                StarStatItem("星辰", "3", "已匹配的星盘陌生人")
            }
        }
    }
}

@Composable
private fun StarStatItem(title: String, value: String, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = StarGold,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimary
        )
        
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = TextTertiary
        )
    }
}

@Composable
private fun ProfileMenu() {
    val menuItems = listOf(
        MenuItem("编辑资料", Icons.Default.Edit, "完善个人信息"),
        MenuItem("相册", Icons.Default.PhotoLibrary, "管理个人照片"),
        MenuItem("星盘设置", Icons.Default.Star, "配置星座和生辰"),
        MenuItem("认证中心", Icons.Default.Verified, "进行身份认证"),
        MenuItem("隐私设置", Icons.Default.PrivacyTip, "管理隐私权限"),
        MenuItem("通知设置", Icons.Default.Notifications, "管理消息通知"),
        MenuItem("帮助与反馈", Icons.Default.Help, "获取帮助支持"),
        MenuItem("关于我们", Icons.Default.Info, "了解星愿APP")
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceMedium)
    ) {
        Column {
            menuItems.forEachIndexed { index, item ->
                MenuItemRow(
                    item = item,
                    showDivider = index < menuItems.size - 1
                )
            }
        }
    }
}

@Composable
private fun MenuItemRow(
    item: MenuItem,
    showDivider: Boolean
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                item.icon,
                contentDescription = null,
                tint = StarBlueLight,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
            
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextTertiary,
                modifier = Modifier.size(20.dp)
            )
        }
        
        if (showDivider) {
            Divider(
                color = SpaceLight,
                modifier = Modifier.padding(start = 56.dp)
            )
        }
    }
}

@Composable
private fun MyPostsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceMedium)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "我的动态",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 动态统计
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PostStatItem("发布", "12", "条动态")
                PostStatItem("获赞", "156", "个赞")
                PostStatItem("评论", "89", "条评论")
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 最近动态
            Text(
                text = "最近发布",
                style = MaterialTheme.typography.titleMedium,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            val recentPosts = listOf(
                "今天学习了一整天，感觉收获满满！",
                "分享一张今天拍的照片",
                "刚看完一部很棒的科幻电影"
            )
            
            recentPosts.forEach { post ->
                Text(
                    text = "• $post",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun PostStatItem(title: String, value: String, unit: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = StarGold,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "$title$unit",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
    }
}

data class MenuItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val description: String
)