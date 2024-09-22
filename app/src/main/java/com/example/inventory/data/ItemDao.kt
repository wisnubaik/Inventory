package com.example.inventory

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventory.data.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    // Menyisipkan data item, abaikan jika ada konflik
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    // Memperbarui data item
    @Update
    suspend fun update(item: Item)

    // Menghapus data item
    @Delete
    suspend fun delete(item: Item)

    // Mendapatkan item berdasarkan ID
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    // Mendapatkan semua item yang diurutkan berdasarkan nama (ascending)
    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
