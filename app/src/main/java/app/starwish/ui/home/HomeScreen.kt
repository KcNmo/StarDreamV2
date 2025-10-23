package app.starwish.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onOpenContacts: () -> Unit,
    onOpenFeed: () -> Unit,
    onOpenProfile: () -> Unit,
) {
    var dailyLeft by remember { mutableStateOf(21) }
    var starLeft by remember { mutableStateOf(5) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavItem(Icons.Default.Home, "首页") {}
                NavItem(Icons.Default.Group, "联系人", onOpenContacts)
                NavItem(Icons.Default.Star, "动态", onOpenFeed)
                NavItem(Icons.Default.AccountCircle, "我的", onOpenProfile)
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("今日推荐剩余：$dailyLeft 人  |  星盘推荐剩余：$starLeft 次")
            Spacer(Modifier.height(16.dp))
            ElevatedCard(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("为你推荐：高学历小伙伴（Demo）")
                    Spacer(Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(onClick = { if (dailyLeft > 0) dailyLeft-- }) { Text("✅ 喜欢") }
                        OutlinedButton(onClick = { if (dailyLeft > 0) dailyLeft-- }) { Text("❌ 不感兴趣") }
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            ElevatedCard(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("星盘推荐（匿名聊天入口）")
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { if (starLeft > 0) starLeft-- }) { Text("开始一次星盘预测推荐") }
                }
            }
        }
    }
}

@Composable
private fun NavItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    NavigationBarItem(selected = false, onClick = onClick, icon = { Icon(icon, contentDescription = label) }, label = { Text(label) })
}
