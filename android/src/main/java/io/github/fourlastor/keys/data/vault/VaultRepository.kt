package io.github.fourlastor.keys.data.vault

import io.github.fourlastor.keys.data.Var
import io.github.fourlastor.keys.vaultlist.Vault
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VaultRepository @Inject constructor() {

  private val vaults: Var<List<Vault>> = Var.create(emptyList())

  fun observe(): Flow<List<Vault>> = vaults.observe()

  suspend fun insert(vault: Vault) = vaults.update { it.plus(vault) }

}
