package io.github.fourlastor.keys.keyadd

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.keyAddPage() {
  bottomSheet(KeyAddNavigation.ROUTE) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
    ) {
      Text(text = "Add key!")
    }
  }
}
