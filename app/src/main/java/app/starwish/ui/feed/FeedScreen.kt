package app.starwish.ui.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeedScreen(onBack: () -> Unit) {
    var tab by remember { mutableStateOf(0) }
    val tabs = listOf("星圈(朋友)", "星海(社群)")
    val posts = remember { mutableStateListOf("今天打卡学习2小时", "分享一张旅行风景照") }

    Scaffold(topBar = {
        TopAppBar(title = { Text("动态") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = null) }
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { posts.add(0, "新动态（Demo）") }) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }
    }) { padding ->
        Column(Modifier.padding(padding)) {
            TabRow(selectedTabIndex = tab) {
                tabs.forEachIndexed { i, t ->
                    Tab(selected = tab == i, onClick = { tab = i }, text = { Text(t) })
                }
            }
            LazyColumn(Modifier.fillMaxSize()) {
                items(posts) { p ->
                    ListItem(headlineText = { Text(p) }, supportingText = { Text(if (tab==0) "仅朋友可见" else "公开可见") })
                    Divider()
                }
            }
        }
    }
}
