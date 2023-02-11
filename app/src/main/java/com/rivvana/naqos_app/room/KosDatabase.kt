package com.rivvana.naqos_app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rivvana.naqos_app.auth.model.Data


@Database(entities = [Data::class] /* List model Ex:NoteModel */, version = 1)
abstract class KosDatabase : RoomDatabase() {
  companion object {
      private var INSTANCE: KosDatabase? = null

      fun getDatabase(context: Context): KosDatabase {
          if (INSTANCE == null) {
              INSTANCE = Room.databaseBuilder(
                  context,
                  KosDatabase::class.java,
                  "kosDB"
              ).build()
          }
          return INSTANCE!!
          }
      }
  }