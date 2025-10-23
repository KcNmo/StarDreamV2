package com.starrywish.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(onOpenSettings: ()->Unit = {}, onEditProfile: ()->Unit = {}) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("我的名片", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        ElevatedCard(onClick = onEditProfile) {
            ListItem(
                headlineContent = { Text("UID: 12345678 · 星愿用户") },
                supportingContent = { Text("高学历 · 狮子座 · 正在认证中…") },
                trailingContent = { TextButton(onClick = onEditProfile) { Text("编辑") } }
            )
        }
        ElevatedCard(onClick = onOpenSettings) { ListItem(headlineContent = { Text("设置") }, supportingContent = { Text("账号切换、注销、更换手机号") }) }
    }
}
