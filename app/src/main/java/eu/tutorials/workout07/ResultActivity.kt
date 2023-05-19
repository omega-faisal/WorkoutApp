package eu.tutorials.workout07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.workout07.databinding.ActivityResultBinding

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
    }
}