package io.github.fourlastor.keys.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf

data class PageAccess(val updateFab: (@Composable () -> Unit) -> Unit)

val LocalPage = compositionLocalOf { PageAccess {} }

@Composable
fun Page(
  fab: @Composable () -> Unit = {},
  content: @Composable () -> Unit,
) {
  val current = LocalPage.current
  LaunchedEffect(Unit) { current.updateFab(fab) }
  content()
}
