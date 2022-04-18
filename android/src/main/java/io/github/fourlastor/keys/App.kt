package io.github.fourlastor.keys

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import io.github.fourlastor.keys.databaselist.DatabaseListPage
import io.github.fourlastor.keys.databaselist.databaseListPage
import io.github.fourlastor.keys.keydetails.KeyDetailsPage
import io.github.fourlastor.keys.keylist.keyListPage
import io.github.fourlastor.keys.ui.theme.KeysTheme

@Composable
fun App() = AppWrapper {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "database") {
    navigation("list", "database") {
      databaseListPage(navController)
      navigation("list", "keys") {
        keyListPage(navController)
        composable("detail/{id}") {
          KeyDetailsPage()
        }
      }
    }
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
