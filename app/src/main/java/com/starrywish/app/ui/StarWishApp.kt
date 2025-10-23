package com.starrywish.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.starrywish.app.ui.screens.*
import com.starrywish.app.data.InMemoryRepository

sealed class TabDestination(val route: String, val label: String, val icon: ImageVector) {
    data object Home: TabDestination("home", "推荐", Icons.Default.Home)
    data object Contacts: TabDestination("contacts", "联系人", Icons.Default.Chat)
    data object Feed: TabDestination("feed", "动态", Icons.Default.Public)
    data object Profile: TabDestination("profile", "我的", Icons.Default.Person)
}

@Composable
fun StarWishApp(repo: InMemoryRepository, currentUid: String?) {
    val navController = rememberNavController()
    val tabs = listOf(TabDestination.Home, TabDestination.Contacts, TabDestination.Feed, TabDestination.Profile)

    Scaffold(
        bottomBar = {
            BottomBar(navController, tabs)
        }
    ) { inner ->
        NavHost(navController = navController, startDestination = TabDestination.Home.route, modifier = androidx.compose.ui.Modifier.padding(inner)) {
            composable(TabDestination.Home.route) { HomeScreen() }
            composable(TabDestination.Contacts.route) { ContactsScreen(onOpenChat = { id -> navController.navigate("chat/$id") }) }
            composable(TabDestination.Feed.route) { FeedScreen() }
            composable(TabDestination.Profile.route) { ProfileScreen(onOpenSettings = { navController.navigate("settings") }, onEditProfile = { navController.navigate("editProfile") }) }
            composable("chat/{id}") { backStack ->
                val id = backStack.arguments?.getString("id") ?: ""
                ChatScreen(conversationId = id)
            }
            composable("settings") { SettingsScreen(onLogout = { /* handled in ProfileScreen via callback later */ }) }
            composable("editProfile") { EditProfileScreen(currentUid = currentUid) }
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController, tabs: List<TabDestination>) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination?.route

    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = currentDestination == tab.route,
                onClick = {
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(tab.icon, contentDescription = tab.label) },
                label = { Text(tab.label) }
            )
        }
    }
}
