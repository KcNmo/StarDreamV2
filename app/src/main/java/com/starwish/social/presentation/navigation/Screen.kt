package com.starwish.social.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    
    // 主界面底部导航
    object Home : Screen("home")
    object Contacts : Screen("contacts")
    object Posts : Screen("posts")
    object Profile : Screen("profile")
    
    // 子页面
    object Chat : Screen("chat/{userId}") {
        fun createRoute(userId: String) = "chat/$userId"
    }
    
    object UserProfile : Screen("user_profile/{userId}") {
        fun createRoute(userId: String) = "user_profile/$userId"
    }
    
    object EditProfile : Screen("edit_profile")
    object Settings : Screen("settings")
    object StarChart : Screen("star_chart")
    object Verification : Screen("verification")
}