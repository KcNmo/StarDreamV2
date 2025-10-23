package com.starrywish.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContactsScreen(onOpenChat: (String)->Unit = {}) {
    var items by remember { mutableStateOf(
        listOf(
            Contact("木星陌生人", true, 0),
            Contact("水星陌生人", true, 0),
            Contact("好友-阿星", false, 12)
        )
    ) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items) { c ->
            ElevatedCard(onClick = { onOpenChat(c.name) }) {
                ListItem(
                    headlineContent = { Text(c.name) },
                    supportingContent = { Text(if (c.isAstro) "匿名星盘聊天（3天无互动将消失）" else "已连续聊天 ${c.streakDays} 天") }
                )
            }
        }
    }
}

data class Contact(val name: String, val isAstro: Boolean, val streakDays: Int)
