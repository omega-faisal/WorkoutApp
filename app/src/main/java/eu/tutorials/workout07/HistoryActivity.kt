package eu.tutorials.workout07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.workout07.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private var bindingHistory:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHistory=ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(bindingHistory?.root)

        setSupportActionBar(bindingHistory?.toolbarHistory)
        if(supportActionBar!=null)
        {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            bindingHistory?.toolbarHistory?.setNavigationOnClickListener {
                onBackPressed()
            }
        }

    }
}