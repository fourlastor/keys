package io.github.fourlastor.keys.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.fourlastor.keys.data.vault.VaultDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

  @Provides
  @Singleton
  fun provideKeysDb(@ApplicationContext context: Context): KeysDb =
    Room.inMemoryDatabaseBuilder(context, KeysDb::class.java).build()

  @Provides
  fun provideVaultDao(db: KeysDb): VaultDao = db.vaultDao()
}
