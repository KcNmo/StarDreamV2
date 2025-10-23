package com.starrywish.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(conversationId: String) {
    val messages = remember { mutableStateListOf("你好呀！","今天也要元气满满✨") }
    Column(Modifier.fillMaxSize().padding(12.dp)) {
        Text("会话 #$conversationId", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))
        messages.forEach { Text(it) }
        Spacer(Modifier.weight(1f))
        OutlinedTextField(value = "", onValueChange = {}, placeholder = { Text("输入消息…（占位）") }, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun SettingsScreen(onLogout: ()->Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("设置", style = MaterialTheme.typography.titleLarge)
        ElevatedCard { ListItem(headlineContent = { Text("更换手机号") }) }
        ElevatedCard { ListItem(headlineContent = { Text("找到我的方式") }) }
        ElevatedCard { ListItem(headlineContent = { Text("注销账号") }) }
        Button(onClick = onLogout) { Text("退出登录") }
    }
}

@Composable
fun EditProfileScreen(currentUid: String?) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("编辑资料", style = MaterialTheme.typography.titleLarge)
        Text("UID: ${currentUid ?: ""}")
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("昵称") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("个性签名") }, modifier = Modifier.fillMaxWidth())
        ElevatedCard { ListItem(headlineContent = { Text("头像认证（占位）") }) }
        ElevatedCard { ListItem(headlineContent = { Text("学历/工作认证（占位）") }) }
        ElevatedCard { ListItem(headlineContent = { Text("身份认证（占位）") }) }
    }
}
