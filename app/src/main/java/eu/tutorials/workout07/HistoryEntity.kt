package eu.tutorials.workout07

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history-table")
data class HistoryEntity(

    @PrimaryKey
     val date:String
)
