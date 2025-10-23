package com.starrywish.app.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.starrywish.app.data.InMemoryRepository
import com.starrywish.app.data.Session
import com.starrywish.app.data.SessionStore
import kotlinx.coroutines.launch

@Composable
fun AuthGate(sessionStore: SessionStore, repo: InMemoryRepository, onAuthed: (Session) -> Unit) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "login") {
        composable("login") {
            LoginScreen(onRegister = { nav.navigate("register") }, onLoggedIn = onAuthed, sessionStore = sessionStore, repo = repo)
        }
        composable("register") {
            RegisterScreen(onDone = { nav.popBackStack() }, repo = repo)
        }
    }
}

@Composable
private fun LoginScreen(onRegister: ()->Unit, onLoggedIn: (Session)->Unit, sessionStore: SessionStore, repo: InMemoryRepository) {
    var id by remember { mutableStateOf("") }
    var secret by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("登录", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("UID/手机号") })
        OutlinedTextField(value = secret, onValueChange = { secret = it }, label = { Text("密码/验证码") }, visualTransformation = PasswordVisualTransformation())
        Button(onClick = {
            scope.launch {
                repo.loginByUidOrPhone(id, secret)?.let { sess ->
                    sessionStore.save(sess)
                    onLoggedIn(sess)
                }
            }
        }) { Text("同意并登录") }
        TextButton(onClick = onRegister) { Text("没有账号？去注册") }
    }
}

@Composable
private fun RegisterScreen(onDone:()->Unit, repo: InMemoryRepository) {
    var phone by remember { mutableStateOf("") }
    var nick by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("注册", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("手机号") })
        OutlinedTextField(value = code, onValueChange = { code = it }, label = { Text("验证码") })
        OutlinedTextField(value = nick, onValueChange = { nick = it }, label = { Text("昵称") })
        Button(onClick = {
            scope.launch { repo.register(phone, code, nick); onDone() }
        }) { Text("注册完成") }
    }
}
