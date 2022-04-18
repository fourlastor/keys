package io.github.fourlastor.keys.databaselist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Storage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.github.fourlastor.keys.AppWrapper
import io.github.fourlastor.keys.id.LongId
import io.github.fourlastor.keys.keylist.KeyListNavigation

fun NavGraphBuilder.databaseListPage(
  navHostController: NavHostController
) {

  composable(DatabaseListNavigation.ROUTE) {
    DatabaseListPage(
      viewModel = hiltViewModel(),
    ) { navHostController.navigate(KeyListNavigation.go()) }
  }
}

@Composable
private fun DatabaseListPage(
  viewModel: DatabaseListViewModel,
  onDbSelected: OnDbSelected,
) {
  val databases by viewModel.observeDatabases()
    .collectAsState(initial = emptyList())
  DatabaseList(databases = databases, onDbSelected = onDbSelected)
}

@Composable
private fun DatabaseList(
  databases: List<Database>,
  onDbSelected: OnDbSelected
) = Box(
  modifier = Modifier.fillMaxSize()
) {
  if (databases.isEmpty()) {
    Text(text = "No databases!", modifier = Modifier.align(Alignment.Center))
  } else {
    LazyColumn {
      items(databases) {
        DatabaseListItem(it, onDbSelected)
      }
    }
  }
}

@Composable
private fun DatabaseListItem(
  database: Database,
  onDbSelected: OnDbSelected
) = Row(
  modifier = Modifier
    .padding(4.dp)
    .fillMaxWidth()
    .clickable { onDbSelected(database.id) }
) {
  Icon(imageVector = Icons.Rounded.Storage, contentDescription = null)
  Text(text = database.name)
}

private typealias OnDbSelected = (LongId<Database>) -> Unit

@Preview
@Composable
private fun ListPreview() = AppWrapper {
  DatabaseList(
    databases = listOf(
      Database(LongId(1), "First db"),
      Database(LongId(2), "Second db"),
      Database(LongId(3), "Third db"),
    ),
    onDbSelected = {}
  )
}

@Preview
@Composable
private fun EmptyListPreview() = AppWrapper {
  DatabaseList(
    databases = emptyList(),
    onDbSelected = {}
  )
}
