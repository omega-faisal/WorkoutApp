package eu.tutorials.workout07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorials.workout07.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var bindingHistory:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHistory=ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(bindingHistory?.root)

        setSupportActionBar(bindingHistory?.toolbarHistoryActivity)
        if(supportActionBar!=null)
        {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            bindingHistory?.toolbarHistoryActivity?.setNavigationOnClickListener {
                onBackPressed()
            }
        }

        //setting up the history dao and passing it to the function getALLDates
        val dao=(application as HistoryApplication).db.historyDao()
        getAllDates(dao)

    }
    private fun getAllDates(historyDao: HistoryDao)
    {
        Log.e("Date:","getALLDates")
        lifecycleScope.launch{
            historyDao.fetchAllDates().collect(){AllDatesInFormat->

                if(AllDatesInFormat.isNotEmpty())
                {
                     bindingHistory?.tvHistory?.visibility= View.VISIBLE
                    bindingHistory?.rvHistory?.visibility=View.VISIBLE
                    bindingHistory?.tvNoDataAvailable?.visibility=View.INVISIBLE

                    bindingHistory?.rvHistory?.layoutManager=LinearLayoutManager(this@HistoryActivity)

                    val dates=ArrayList<String>()
                    for(date in AllDatesInFormat)
                    {
                        dates.add(date.date)
                    }
                    val historyAdapter=HistoryAdapter(dates)

                    bindingHistory?.rvHistory?.adapter=historyAdapter

                }
                else{
                    bindingHistory?.tvHistory?.visibility= View.INVISIBLE
                    bindingHistory?.rvHistory?.visibility=View.INVISIBLE
                    bindingHistory?.tvNoDataAvailable?.visibility=View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingHistory=null
    }
}