package app.starwish.ui.contacts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContactsScreen(onBack: () -> Unit) {
    val contacts = remember {
        mutableStateListOf(
            "星盘陌生人·白羊", "好友·小明", "好友·小芳"
        )
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text("联系人") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = null) }
        })
    }) { padding ->
        LazyColumn(Modifier.padding(padding).padding(12.dp)) {
            items(contacts) { c ->
                ListItem(headlineText = { Text(c) }, supportingText = { Text("点击进入聊天（Demo）") })
                Divider()
            }
        }
    }
}
