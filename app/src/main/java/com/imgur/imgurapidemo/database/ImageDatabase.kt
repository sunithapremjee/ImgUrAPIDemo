package com.imgur.imgurapidemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imgur.imgurapidemo.domain.ImageDetails

@Database(entities = [DatabaseImageDetails::class], version = 3, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {

    /**
     * Connects the database to the DAO.
     */
    abstract val imageDatabaseDao: ImageDatabaseDao

    /**
     * Define a companion object*/
    companion object {
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         */
        @Volatile
        private var INSTANCE: ImageDatabase? = null

        /**
         * Helper function to get the database.

         * @param context The application context Singleton, used to get access to the filesystem.
         */
        fun getInstance(context: Context): ImageDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ImageDatabase::class.java,
                        "Image_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
