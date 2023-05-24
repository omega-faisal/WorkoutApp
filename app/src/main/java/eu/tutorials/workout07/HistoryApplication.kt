package eu.tutorials.workout07

import android.app.Application

class HistoryApplication:Application() {
    val db by lazy{
        HistoryDatabase.getInstance(this)
    }

}