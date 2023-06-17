package com.example.composenotesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.composenotesapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("Select * from notes_tbl")
     fun getNotes(): Flow<List<Note>>

    @Query("select * from notes_tbl where uuid=:uuid")
    suspend fun getNoteById(uuid:String):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Update
   suspend fun updateNote(note:Note)

   @Query("delete from notes_tbl")
   suspend fun deleteAll()

   @Delete
   suspend fun deleteNote(note:Note)


}