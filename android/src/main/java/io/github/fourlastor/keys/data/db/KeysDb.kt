package io.github.fourlastor.keys.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.fourlastor.keys.data.vault.VaultDao
import io.github.fourlastor.keys.data.vault.VaultEntity

@Database(
  entities = [VaultEntity::class],
  version = 1
)
abstract class KeysDb : RoomDatabase() {

  abstract fun vaultDao(): VaultDao
}
