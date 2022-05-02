package io.github.fourlastor.keys

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
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
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import io.github.fourlastor.keys.keyadd.KeyAddNavigation
import io.github.fourlastor.keys.keyadd.keyAddPage
import io.github.fourlastor.keys.keydetails.keyDetailsPage
import io.github.fourlastor.keys.keylist.KeyListNavigation
import io.github.fourlastor.keys.keylist.keyListPage
import io.github.fourlastor.keys.ui.theme.KeysTheme
import io.github.fourlastor.keys.vaultadd.VaultAddNavigation
import io.github.fourlastor.keys.vaultadd.vaultAddPage
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
      VaultListNavigation.ROUTE -> VaultAddNavigation.go()
      KeyListNavigation.ROUTE -> KeyAddNavigation.go()
      else -> null
    }?.also { navController.navigate(it) }
  }) {
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
      NavHost(navController = navController, startDestination = VaultListNavigation.go()) {
        vaultListPage(navController)
        keyListPage(navController)
        keyDetailsPage()
        vaultAddPage()
        keyAddPage()
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
