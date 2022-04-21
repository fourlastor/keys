package io.github.fourlastor.keys.keylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.github.fourlastor.keys.AppWrapper
import io.github.fourlastor.keys.data.model.LongId
import io.github.fourlastor.keys.keydetails.KeyDetailsNavigation


fun NavGraphBuilder.keyListPage(navController: NavHostController) =
  composable(KeyListNavigation.ROUTE) {
    KeyListPage(navController, hiltViewModel())
  }

@Composable
fun KeyListPage(navController: NavHostController, viewModel: KeyListViewModel) {
  val keys by viewModel.observeKeys()
    .collectAsState(initial = emptyList())

  KeyList(keys = keys) { navController.navigate(KeyDetailsNavigation.go(it)) }
}

@Composable
private fun KeyList(
  keys: List<OtpKey>,
  onKeySelected: OnKeySelected,
) = Box {
  if (keys.isEmpty()) {
    Text(text = "No keys!", modifier = Modifier.align(Alignment.Center))
  } else {
    LazyColumn { items(keys) { KeyListItem(it, onKeySelected) } }
  }
}

@Composable
private fun KeyListItem(
  key: OtpKey,
  onKeySelected: OnKeySelected
) = Row(
  modifier = Modifier
    .fillMaxWidth()
    .clickable { onKeySelected(key.id) }
) {
  Icon(
    imageVector = Icons.Default.VpnKey,
    contentDescription = null,
    modifier = Modifier.size(48.dp)
  )
  Text(text = key.accountName)
}


@Preview
@Composable
private fun ListPreview() {
  KeyWrapperPreview {
    KeyList(
      keys = listOf(
        demoKey("user@example.com"),
        demoKey("other@example.com"),
        demoKey("and@example.com"),
      ),
      onKeySelected = {}
    )
  }
}

private fun demoKey(name: String) = OtpKey(
  id = LongId(name.hashCode().toLong()),
  accountName = name,
  issuer = "ACME Co",
  secret = "HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ",
  algorithm = OtpKey.Algorithm.SHA1,
  digits = 6,
  period = 30,
)

@Preview
@Composable
private fun EmptyListPreview() = KeyWrapperPreview {
  KeyList(
    keys = emptyList(),
    onKeySelected = {},
  )
}

@Composable
private fun KeyWrapperPreview(
  content: @Composable (PaddingValues) -> Unit
) = AppWrapper(
  KeyListNavigation.ROUTE,
  content,
)

data class OtpKey(
  val id: LongId<OtpKey>,
  val accountName: String,
  val issuer: String,
  val secret: String,
  val algorithm: Algorithm,
  val digits: Int,
  val period: Int,
) {
  enum class Algorithm(val value: String) {
    SHA1("SHA1"),
    SHA256("SHA256"),
    SHA512("SHA512"),
  }
}

private typealias OnKeySelected = (LongId<OtpKey>) -> Unit
