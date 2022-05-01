package io.github.fourlastor.keys.data.vault

import io.github.fourlastor.keys.data.model.LongId
import io.github.fourlastor.keys.vaultlist.Vault
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VaultRepository @Inject constructor(
  private val vaults: VaultDao,
) {

  fun observe(): Flow<List<Vault>> =
    vaults.observe().map { vaults -> vaults.map { Vault(LongId(it.id), it.name) } }

  suspend fun insert(vault: Vault) = vaults.insert(VaultEntity(vault.name))
}
