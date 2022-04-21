package io.github.fourlastor.keys.data.database

import io.github.fourlastor.keys.data.Var
import io.github.fourlastor.keys.databaselist.Database
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor() {

  private val dbs: Var<List<Database>> = Var.create(emptyList())

  fun observe(): Flow<List<Database>> = dbs.observe()

  suspend fun insert(database: Database) = dbs.update { it.plus(database) }

}
