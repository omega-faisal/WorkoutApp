package eu.tutorials.workout07

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase:RoomDatabase() {

    abstract fun historyDao():HistoryDao

    companion object{

        @Volatile
        private var INSTANCE:HistoryDatabase?=null

        fun getInstance(context:Context):HistoryDatabase
        {
            synchronized(this)
            {
                var instance= INSTANCE
                if(instance==null)
                {
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "History database").
                    fallbackToDestructiveMigration().
                            build()
                    // this line of code here only creates an instance of history database if it did't exist
                    // previously i.e if it was null

                    INSTANCE=instance
                }
                return instance
            }
        }
    }

}