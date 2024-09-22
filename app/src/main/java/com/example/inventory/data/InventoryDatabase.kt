package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventory.ItemDao

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    // Abstract function to get the DAO
    abstract fun itemDao(): ItemDao

    companion object {
        // Volatile annotation ensures visibility of changes to different threads
        @Volatile
        private var Instance: InventoryDatabase? = null

        // Function to get the database instance
        fun getDatabase(context: Context): InventoryDatabase {
            // Return the existing instance if available, otherwise create a new one
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration() // Handle migrations by destroying and rebuilding the database
                    .build()
                    .also { Instance = it } // Set the instance after building
            }
        }
    }
}
