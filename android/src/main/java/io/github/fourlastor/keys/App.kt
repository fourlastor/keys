package io.github.fourlastor.keys

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.fourlastor.keys.databaselist.DatabaseListNavigation
import io.github.fourlastor.keys.databaselist.databaseListPage
import io.github.fourlastor.keys.keydetails.keyDetailsPage
import io.github.fourlastor.keys.keylist.keyListPage
import io.github.fourlastor.keys.ui.theme.KeysTheme

@Composable
fun App() = AppWrapper {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = DatabaseListNavigation.ROUTE) {
    databaseListPage(navController)
    keyListPage(navController)
    keyDetailsPage()
  }
}

@Composable
fun AppWrapper(
  content: @Composable () -> Unit
) = KeysTheme {
  // A surface container using the 'background' color from the theme
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background,
    content = content
  )
}
