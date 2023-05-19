package eu.tutorials.workout07

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import eu.tutorials.workout07.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        // this tells the control to take activity main xml file inflate it with layout inflater and then store
        //it at binding variable
        setContentView(binding?.root) // set the content view to the root element(which is constraint
        //layout of activity main file) of this binding

        //val fl_start:FrameLayout=findViewById(R.id.fl_Start)

        //ANOTHER APPROACH FOR SETTING ON CLICK LISTENER
        binding?.flStart?.setOnClickListener {
           val intent= Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding?.flBMI?.setOnClickListener{
            val intent02=Intent(this,BmiCalculator::class.java)
            startActivity(intent02)
        }
        binding?.flHistory?.setOnClickListener{
            val intent03=Intent(this,HistoryActivity::class.java)
            startActivity(intent03)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
        // we have to always set binding again to null to avoid the memory leak.
    }
}