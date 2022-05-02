package io.github.fourlastor.keys.keydetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.fourlastor.keys.page.Page


fun NavGraphBuilder.keyDetailsPage() {
  composable(KeyDetailsNavigation.ROUTE) {
    KeyDetailsPage()
  }
}

@Composable
private fun KeyDetailsPage() = Page {
  Text(text = "key details")
}
