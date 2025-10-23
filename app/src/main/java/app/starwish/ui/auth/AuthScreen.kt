package app.starwish.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AuthScreen(onAuthed: () -> Unit) {
    var isLogin by remember { mutableStateOf(true) }
    var phone by remember { mutableStateOf("") }
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (isLogin) "登录" else "注册", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        if (isLogin) {
            OutlinedTextField(value = uid, onValueChange = { uid = it }, label = { Text("UID / 手机号") }, singleLine = true)
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("密码 / 验证码") }, singleLine = true, visualTransformation = PasswordVisualTransformation())
            Spacer(Modifier.height(12.dp))
            var agreed by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = agreed, onCheckedChange = { agreed = it })
                Text("我已阅读并同意《用户协议》和《隐私政策》")
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = onAuthed, enabled = true, modifier = Modifier.fillMaxWidth()) { Text("登录") }
            TextButton(onClick = { isLogin = false }) { Text("去注册") }
        } else {
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("手机号") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("设置密码") }, singleLine = true, visualTransformation = PasswordVisualTransformation())
            Spacer(Modifier.height(12.dp))
            Button(onClick = { isLogin = true }, modifier = Modifier.fillMaxWidth()) { Text("注册并登录") }
            TextButton(onClick = { isLogin = true }) { Text("已有账号？去登录") }
        }
    }
}
