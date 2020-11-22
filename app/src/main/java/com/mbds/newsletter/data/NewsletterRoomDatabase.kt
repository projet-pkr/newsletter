package com.mbds.newsletter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mbds.newsletter.data.article.ArticleDao
import com.mbds.newsletter.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 2, exportSchema = false)
abstract class NewsletterRoomDatabase : RoomDatabase() {

    abstract fun articleDao() : ArticleDao

    companion object {
        @Volatile
        private var INSTANCE : NewsletterRoomDatabase? = null
        fun getDatabase(context: Context) : NewsletterRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsletterRoomDatabase::class.java,
                    "newsletter_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}