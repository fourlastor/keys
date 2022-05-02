package io.github.fourlastor.keys.vaultlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Storage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import io.github.fourlastor.keys.DemoWrapper
import io.github.fourlastor.keys.data.model.LongId
import io.github.fourlastor.keys.keylist.KeyListNavigation

fun NavGraphBuilder.vaultListPage(
  navHostController: NavHostController
) {

  composable(VaultListNavigation.ROUTE) {
    VaultListPage(
      viewModel = hiltViewModel(),
    ) { navHostController.navigate(KeyListNavigation.go()) }
  }
}

typealias OnVaultSelected = (LongId<Vault>) -> Unit

@Composable
private fun VaultListPage(
  viewModel: VaultListViewModel,
  onVaultSelected: OnVaultSelected,
) {
  val vaults by viewModel.vaults
    .collectAsState(initial = emptyList())
  VaultList(vaults = vaults, onVaultSelected = onVaultSelected)

  LaunchedEffect(Unit) {
    listOf(
      Vault(LongId(1), "First vault"),
      Vault(LongId(2), "Second vault"),
      Vault(LongId(3), "Third vault")
    ).forEach { viewModel.addVault(it) }
  }
}

@Composable
private fun VaultList(
  vaults: List<Vault>,
  onVaultSelected: OnVaultSelected
) = Box(
  modifier = Modifier.fillMaxSize()
) {
  if (vaults.isEmpty()) {
    Text(text = "No vault!", modifier = Modifier.align(Alignment.Center))
  } else {
    LazyColumn {
      items(vaults) {
        VaultListItem(it, onVaultSelected)
      }
    }
  }
}

@Composable
private fun VaultListItem(
  vault: Vault,
  onVaultSelected: OnVaultSelected
) = Row(
  modifier = Modifier
    .padding(4.dp)
    .fillMaxWidth()
    .clickable { onVaultSelected(vault.id) }
) {
  Icon(imageVector = Icons.Rounded.Storage, contentDescription = null)
  Text(text = vault.name)
}

@Preview
@Composable
private fun ListPreview() = VaultWrapperPreview {
  VaultList(
    vaults = listOf(
      Vault(LongId(1), "First vault"),
      Vault(LongId(2), "Second vault"),
      Vault(LongId(3), "Third vault"),
    ),
    onVaultSelected = {}
  )
}

@Preview
@Composable
private fun EmptyListPreview() = VaultWrapperPreview {
  VaultList(
    vaults = emptyList(),
    onVaultSelected = {}
  )
}

@Composable
private fun VaultWrapperPreview(
  content: @Composable (PaddingValues) -> Unit
) = DemoWrapper(
  VaultListNavigation.ROUTE,
  content,
)
