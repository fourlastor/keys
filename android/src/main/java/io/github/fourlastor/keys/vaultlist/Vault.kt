package io.github.fourlastor.keys.vaultlist

import io.github.fourlastor.keys.data.model.LongId

data class Vault(
  val id: LongId<Vault>,
  val name: String,
)
