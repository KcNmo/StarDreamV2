package com.starwish.app.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.starwish.app.ui.screens.home.HomeScreen
import com.starwish.app.ui.screens.contacts.ContactsScreen
import com.starwish.app.ui.screens.moments.MomentsScreen
import com.starwish.app.ui.screens.profile.ProfileScreen

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    val tabs = listOf(
        "首页推荐" to "home",
        "联系人" to "contacts", 
        "动态" to "moments",
        "个人主页" to "profile"
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF161B22),
                contentColor = Color.White
            ) {
                tabs.forEachIndexed { index, (title, _) ->
                    NavigationBarItem(
                        icon = {
                            // TODO: Add proper icons
                            Text(
                                text = when (index) {
                                    0 -> "🏠"
                                    1 -> "👥"
                                    2 -> "📱"
                                    3 -> "👤"
                                    else -> ""
                                },
                                fontSize = 20.sp
                            )
                        },
                        label = { Text(title, fontSize = 12.sp) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFFFD700),
                            selectedTextColor = Color(0xFFFFD700),
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            unselectedTextColor = Color.White.copy(alpha = 0.6f)
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
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> ContactsScreen()
                2 -> MomentsScreen()
                3 -> ProfileScreen()
            }
        }
    }
}