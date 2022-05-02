package io.github.fourlastor.keys.data.vault

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultDao {
  @Query("SELECT * FROM VaultEntity")
  fun observe(): Flow<List<VaultEntity>>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insert(vault: VaultEntity)
}

@Entity
data class VaultEntity(
  val name: String,
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
}
