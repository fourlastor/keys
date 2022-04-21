package io.github.fourlastor.keys.databaselist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fourlastor.keys.data.database.DatabaseRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DatabaseListViewModel
@Inject
constructor(
  private val repository: DatabaseRepository,
) : ViewModel() {
  val databases: Flow<List<Database>>
    get() = repository.observe()

  suspend fun addDb(database: Database) = repository.insert(database)
}
