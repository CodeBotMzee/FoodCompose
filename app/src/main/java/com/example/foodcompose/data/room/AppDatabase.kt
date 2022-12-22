package com.example.foodcompose.data.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodcompose.data.model.User
import com.example.foodcompose.data.room.dao.UserDao
import com.example.foodcompose.util.Converters


@Database(
    entities = [User::class], version = 1, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context, converters: Converters): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(
                    context,
                    converters
                ).also { instance = it }
            }

        private fun buildDatabase(appContext: Context, converters: Converters) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "lensxm")
                .fallbackToDestructiveMigration()
                .addTypeConverter(converters)
                .build()
    }
}