package eu.tutorials.workout07

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import eu.tutorials.workout07.databinding.ActivityResultBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {
    private var bindingg:ActivityResultBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingg=ActivityResultBinding.inflate(layoutInflater)
        setContentView(bindingg?.root)

        setSupportActionBar(bindingg?.toolbarFinishActivity)
        if(supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            bindingg?.toolbarFinishActivity?.setNavigationOnClickListener {
                onBackPressed()
            }
        }
        bindingg?.btnFinish?.setOnClickListener {
            finish()
        }


        //creating the dao and then passing it to the function
        val dao=(application as HistoryApplication).db.historyDao()
        addDateToDatabase(dao)
    }
    private fun addDateToDatabase(historyDao: HistoryDao){

        val c=Calendar.getInstance()
        val DateTime=c.time
        Log.e("Date","$DateTime")

        val sdf=SimpleDateFormat("dd  MMM yyyy HH:mm:ss", Locale.getDefault())
        // this is basically a date format
         val date=sdf.format(DateTime)
         Log.e("Formatted Date", date)
             // this line of code will format our DateTime into the standard format....sdf

        lifecycleScope.launch {
            historyDao.InsertExercise(HistoryEntity(date))
            Log.e("date:",
                "added")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingg=null
    }
}