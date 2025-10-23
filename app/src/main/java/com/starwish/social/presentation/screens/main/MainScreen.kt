package com.starwish.social.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.starwish.social.presentation.screens.home.HomeScreen
import com.starwish.social.presentation.screens.contacts.ContactsScreen
import com.starwish.social.presentation.screens.posts.PostsScreen
import com.starwish.social.presentation.screens.profile.ProfileScreen
import com.starwish.social.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    val tabs = listOf(
        TabItem("首页", Icons.Default.Home, HomeScreen()),
        TabItem("联系人", Icons.Default.People, ContactsScreen()),
        TabItem("动态", Icons.Default.DynamicFeed, PostsScreen()),
        TabItem("我的", Icons.Default.Person, ProfileScreen())
    )
    
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = SpaceMedium,
                contentColor = TextPrimary
            ) {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = StarGold,
                            selectedTextColor = StarGold,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            tabs[selectedTab].content
        }
    }
}

data class TabItem(
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
)