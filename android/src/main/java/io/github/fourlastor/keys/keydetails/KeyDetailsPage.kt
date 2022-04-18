package io.github.fourlastor.keys.keydetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.keyDetailsPage() {
  composable(KeyDetailsNavigation.ROUTE) {
    KeyDetailsPage()
  }
}

@Composable
private fun KeyDetailsPage() {
  Text(text = "key details")
}
