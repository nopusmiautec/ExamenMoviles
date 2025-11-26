package com.app.examenapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.examenapp.data.local.dao.CharacterDao
import com.app.examenapp.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
)
abstract class ExamenDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
