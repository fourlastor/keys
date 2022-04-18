package io.github.fourlastor.keys.databaselist

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fourlastor.keys.id.LongId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class DatabaseListViewModel
@AssistedInject
constructor() : ViewModel() {
  fun observeDatabases(): Flow<List<Database>> = flowOf(listOf(
    Database(LongId(1), "First db"),
    Database(LongId(2), "Second db"),
    Database(LongId(3), "Third db"),
  ))

  @AssistedFactory
  interface Factory {
    fun create(navHostController: NavHostController): DatabaseListViewModel
  }

}
