package app.starwish.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("星愿用户") }
    var bio by remember { mutableStateOf("高学历·乐于分享·爱好阅读、旅行") }

    Scaffold(topBar = {
        TopAppBar(title = { Text("我的") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = null) }
        })
    }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("昵称") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = bio, onValueChange = { bio = it }, label = { Text("个性签名/社交名片概览") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Button(onClick = { /* 保存 */ }, modifier = Modifier.fillMaxWidth()) { Text("保存") }
            TextButton(onClick = { /* 打开设置 */ }) { Text("设置 / 账号与隐私") }
        }
    }
}
