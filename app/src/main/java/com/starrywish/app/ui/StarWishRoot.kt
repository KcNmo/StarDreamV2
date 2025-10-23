package com.starrywish.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.starrywish.app.data.InMemoryRepository
import com.starrywish.app.data.SessionStore
import com.starrywish.app.ui.auth.AuthGate

@Composable
fun StarWishRoot() {
    val context = LocalContext.current
    val sessionStore = rememberSessionStore()
    val repo = rememberRepository()
    val session by sessionStore.sessionFlow.collectAsState(initial = null)

    if (session == null) {
        AuthGate(sessionStore = sessionStore, repo = repo) { /* session saved in store */ }
    } else {
        StarWishApp(repo = repo, currentUid = session!!.uid)
    }
}

@Composable
private fun rememberSessionStore(): SessionStore = SessionStore(LocalContext.current)

@Composable
private fun rememberRepository(): InMemoryRepository = InMemoryRepository()
