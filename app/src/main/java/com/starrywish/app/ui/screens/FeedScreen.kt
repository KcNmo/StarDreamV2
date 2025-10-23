package com.starrywish.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeedScreen() {
    var scope by remember { mutableStateOf(FeedScope.Public) }
    val posts = remember { mutableStateOf((1..6).map { PostItem("分享 #$it", if (scope==FeedScope.Public) "公开可见" else "仅星圈可见") }) }
    Column(modifier = Modifier.fillMaxSize()) {
        SmallTopAppBar(title = { Text(if (scope==FeedScope.Public) "星海" else "星圈") }, actions = {
            AssistChip(onClick = { scope = if (scope==FeedScope.Public) FeedScope.Circle else FeedScope.Public }, label = { Text(if (scope==FeedScope.Public) "切到星圈" else "切到星海") })
            Spacer(Modifier.width(8.dp))
            TextButton(onClick = { /* 发布入口占位 */ }) { Text("发布") }
        })
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(posts.value) { item ->
                ElevatedCard {
                    ListItem(headlineContent = { Text(item.title) }, supportingContent = { Text("${item.content} · 可分组展示占位") })
                }
            }
        }
    }
}

private enum class FeedScope { Public, Circle }

private data class PostItem(val title: String, val content: String)
