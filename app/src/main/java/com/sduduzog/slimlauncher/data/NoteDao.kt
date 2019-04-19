package com.sduduzog.slimlauncher.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sduduzog.slimlauncher.data.model.Note

@Dao
interface NoteDao {

    @get:Query("SELECT * FROM notes ORDER BY id DESC")
    val notes: LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: Note)

    @Update
    fun update(vararg note: Note)

    @Delete
    fun remove(vararg note: Note)
}