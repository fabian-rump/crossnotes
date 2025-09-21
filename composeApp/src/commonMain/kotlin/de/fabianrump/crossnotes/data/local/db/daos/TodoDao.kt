package de.fabianrump.crossnotes.data.local.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.fabianrump.crossnotes.data.local.db.entities.ToDoEntity
import kotlinx.coroutines.flow.Flow
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Dao
internal interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY createdAt DESC")
    fun getAllToDos(): Flow<List<ToDoEntity>>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getToDoById(id: Long): ToDoEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertToDo(todo: ToDoEntity): Long

    @Update
    suspend fun updateToDo(todo: ToDoEntity)

    @Delete
    suspend fun deleteToDo(todo: ToDoEntity)

    @Query("DELETE FROM todos WHERE id = :id")
    suspend fun deleteToDoById(id: Long)

    @Query("SELECT * FROM todos WHERE isCompleted = :isCompleted ORDER BY dueDate ASC")
    fun getToDosByCompletionStatus(isCompleted: Boolean): Flow<List<ToDoEntity>>
}