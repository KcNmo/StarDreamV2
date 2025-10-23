package com.starrywish.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    val (count, setCount) = remember { mutableStateOf(21) }
    val (astroLeft, setAstroLeft) = remember { mutableStateOf(5) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "今日可匹配：$count 人", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(onClick = { if (count>0) setCount(count-1) }) { Text("✅ 喜欢") }
            Button(onClick = { if (count>0) setCount(count-1) }) { Text("❌ 不感兴趣") }
        }
        Spacer(Modifier.height(16.dp))
        Button(enabled = astroLeft>0, onClick = { setAstroLeft(astroLeft-1) }) { Text("开始一次星盘推荐（剩余 $astroLeft 次）") }
    }
}
