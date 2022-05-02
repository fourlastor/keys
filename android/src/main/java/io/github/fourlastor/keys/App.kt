package io.github.fourlastor.keys

import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import io.github.fourlastor.keys.keyadd.keyAddPage
import io.github.fourlastor.keys.keydetails.keyDetailsPage
import io.github.fourlastor.keys.keylist.keyListPage
import io.github.fourlastor.keys.page.LocalPage
import io.github.fourlastor.keys.page.PageAccess
import io.github.fourlastor.keys.ui.theme.KeysTheme
import io.github.fourlastor.keys.vaultadd.vaultAddPage
import io.github.fourlastor.keys.vaultlist.VaultListNavigation
import io.github.fourlastor.keys.vaultlist.vaultListPage

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun App() {
  val bottomSheetNavigator = rememberBottomSheetNavigator()
  val navController = rememberNavController(bottomSheetNavigator)

  AppWrapper {
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
  content: @Composable () -> Unit,
) = AppWrapper(content)

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AppWrapper(
  content: @Composable () -> Unit,
) = KeysTheme {

  val (fab, setFab) = remember { mutableStateOf<@Composable () -> Unit>({}) }
  Scaffold(
    bottomBar = {
      BottomAppBar {
      }
    },
    isFloatingActionButtonDocked = true,
    floatingActionButton = fab,
    floatingActionButtonPosition = FabPosition.Center,
  ) {
    CompositionLocalProvider(LocalPage provides PageAccess(setFab), content = content)
  }
}

@Preview
@Composable
private fun AppPreview() {
  DemoWrapper {

  }
}
