package app.starwish.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.starwish.ui.auth.AuthScreen
import app.starwish.ui.home.HomeScreen
import app.starwish.ui.profile.ProfileScreen
import app.starwish.ui.contacts.ContactsScreen
import app.starwish.ui.feed.FeedScreen

object Routes {
    const val AUTH = "auth"
    const val HOME = "home"
    const val CONTACTS = "contacts"
    const val FEED = "feed"
    const val PROFILE = "profile"
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = Routes.AUTH) {
            composable(Routes.AUTH) { AuthScreen(onAuthed = { navController.navigate(Routes.HOME) { popUpTo(Routes.AUTH) { inclusive = true } } }) }
            composable(Routes.HOME) { HomeScreen(onOpenContacts = { navController.navigate(Routes.CONTACTS) }, onOpenFeed = { navController.navigate(Routes.FEED) }, onOpenProfile = { navController.navigate(Routes.PROFILE) }) }
            composable(Routes.CONTACTS) { ContactsScreen(onBack = { navController.popBackStack() }) }
            composable(Routes.FEED) { FeedScreen(onBack = { navController.popBackStack() }) }
            composable(Routes.PROFILE) { ProfileScreen(onBack = { navController.popBackStack() }) }
        }
    }
}
