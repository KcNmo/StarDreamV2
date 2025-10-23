package com.starwish.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.starwish.app.ui.screens.auth.LoginScreen
import com.starwish.app.ui.screens.auth.RegisterScreen
import com.starwish.app.ui.screens.main.MainScreen
import com.starwish.app.ui.screens.profile.ProfileSetupScreen

@Composable
fun StarWishNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate("register")
                },
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.navigate("profile_setup") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }
        
        composable("profile_setup") {
            ProfileSetupScreen(
                onProfileComplete = {
                    navController.navigate("main") {
                        popUpTo("profile_setup") { inclusive = true }
                    }
                }
            )
        }
        
        composable("main") {
            MainScreen()
        }
    }
}