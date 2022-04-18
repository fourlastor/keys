package io.github.fourlastor.keys.databaselist

import io.github.fourlastor.keys.id.LongId

data class Database(
  val id: LongId<Database>,
  val name: String,
)
