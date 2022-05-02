package io.github.fourlastor.keys.vaultlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fourlastor.keys.data.vault.VaultRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class VaultListViewModel
@Inject
constructor(
  private val repository: VaultRepository,
) : ViewModel() {
  val vaults: Flow<List<Vault>>
    get() = repository.observe()

  suspend fun addVault(vault: Vault) = repository.insert(vault)
}
