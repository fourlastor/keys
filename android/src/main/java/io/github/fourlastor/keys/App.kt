package io.github.fourlastor.keys

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import io.github.fourlastor.keys.keydetails.keyDetailsPage
import io.github.fourlastor.keys.keylist.KeyListNavigation
import io.github.fourlastor.keys.keylist.keyListPage
import io.github.fourlastor.keys.ui.theme.KeysTheme
import io.github.fourlastor.keys.vaultlist.VaultListNavigation
import io.github.fourlastor.keys.vaultlist.vaultListPage

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun App() {
  val bottomSheetNavigator = rememberBottomSheetNavigator()
  val navController = rememberNavController(bottomSheetNavigator)

  val backStackEntry by navController
    .currentBackStackEntryFlow
    .collectAsState(initial = navController.currentBackStackEntry)

  val route by derivedStateOf { backStackEntry?.destination?.route }

  AppWrapper(route = route, onFabClicked = {
    when (route) {
      VaultListNavigation.ROUTE -> "add_vault"
      KeyListNavigation.ROUTE -> "add_key"
      else -> null
    }?.also { navController.navigate(it) }
  }) {
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
      NavHost(navController = navController, startDestination = VaultListNavigation.go()) {
        vaultListPage(navController)
        keyListPage(navController)
        keyDetailsPage()
        bottomSheet("add_vault") {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp)
          ) {
            Text(text = "Add vault!")
          }
        }
        bottomSheet("add_key") {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp)
          ) {
            Text(text = "Add key!")
          }
        }
      }
    }
  }
}

@Composable
fun DemoWrapper(
  route: String?,
  content: @Composable (PaddingValues) -> Unit,
) = AppWrapper(route, {}, content)

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AppWrapper(
  route: String?,
  onFabClicked: () -> Unit = {},
  content: @Composable (PaddingValues) -> Unit,
) = KeysTheme {
  Scaffold(
    bottomBar = {
      BottomAppBar {
      }
    },
    isFloatingActionButtonDocked = true,
    floatingActionButton = {
      when (route) {
        VaultListNavigation.ROUTE -> Icons.Rounded.Add
        KeyListNavigation.ROUTE -> Icons.Rounded.QrCodeScanner
        else -> null
      }?.let {
        FloatingActionButton(onClick = onFabClicked) {
          Icon(
            imageVector = it,
            contentDescription = "Add",
            modifier = Modifier.size(42.dp)
          )
        }
      }
    },
    floatingActionButtonPosition = FabPosition.Center,
    content = content
  )
}

@Preview
@Composable
private fun AppPreview() {
  DemoWrapper(null) {

  }
}
