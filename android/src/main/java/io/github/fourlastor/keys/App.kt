package io.github.fourlastor.keys

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.fourlastor.keys.vaultlist.VaultListNavigation
import io.github.fourlastor.keys.vaultlist.vaultListPage
import io.github.fourlastor.keys.keydetails.keyDetailsPage
import io.github.fourlastor.keys.keylist.KeyListNavigation
import io.github.fourlastor.keys.keylist.keyListPage
import io.github.fourlastor.keys.ui.theme.KeysTheme

@Composable
fun App() {
  val navController = rememberNavController()

  val backStackEntry by navController
    .currentBackStackEntryFlow
    .collectAsState(initial = navController.currentBackStackEntry)

  val route by derivedStateOf { backStackEntry?.destination?.route }

  AppWrapper(route = route) {
    NavHost(navController = navController, startDestination = VaultListNavigation.go()) {
      vaultListPage(navController)
      keyListPage(navController)
      keyDetailsPage()
    }
  }
}

@Composable
fun AppWrapper(
  route: String?,
  content: @Composable (PaddingValues) -> Unit,
) = KeysTheme {
  Scaffold(
    bottomBar = {
      BottomAppBar {
      }
    },
    isFloatingActionButtonDocked = true,
    floatingActionButton = {
      FloatingActionButton(
        onClick = { }
      ) {
        val icon = when (route) {
          VaultListNavigation.ROUTE -> Icons.Rounded.Add
          KeyListNavigation.ROUTE -> Icons.Rounded.QrCodeScanner
          else -> Icons.Rounded.Add
        }
        Icon(
          imageVector = icon,
          contentDescription = "Add",
          modifier = Modifier.size(42.dp)
        )
      }
    },
    floatingActionButtonPosition = FabPosition.Center,
    content = content,
  )
}

@Preview
@Composable
private fun AppPreview() {
  AppWrapper(null) {

  }
}
