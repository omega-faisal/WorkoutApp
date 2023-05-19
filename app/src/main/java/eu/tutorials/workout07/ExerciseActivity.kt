package eu.tutorials.workout07

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorials.workout07.databinding.ActivityExerciseBinding
import eu.tutorials.workout07.databinding.BackButtonDialogueBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding02:ActivityExerciseBinding?=null
    private var resttimer:CountDownTimer?=null
    private var resttimeDuration:Long=10000
    private var restprogress:Long=0

    private var pauseoffsetExercise:Long=0 //// pause offset is the time which has been passed
    // or pauseoffset=(time duration-time left)
    private var exerciseclockFlag=1 //using clock flag so that start button
    //can't' be pressed again and again while clock is running.


    private var exerciseTimer:CountDownTimer?=null // set for exercise timer
    private var ExetimeDuration:Long=30000
    private var Exerciseprogress:Long=0

    private var exerciselist: ArrayList<exerciseModel>? =null
    private var currentExercisePosition=-1

    private var tts:TextToSpeech?=null // setting text to speech

    private var mediaPlayer:MediaPlayer?=null // setting media player

    private var exeStatusAdapter:exerciseStatusAdapter?=null
    // making a adapter of type exeStatAdapter..


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding02=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding02?.root)

        setSupportActionBar(binding02?.toolbarExercise)
        if(supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding02?.toolbarExercise?.setNavigationOnClickListener {
            pauseTimer()
            CustomDialogAlert()
        }
        exerciselist=Constants.defaultExerciseList()// calling this function of constants which will
        // return an exercise list having various exercises


        tts=TextToSpeech(this,this) // initialising tts
        setuprestView()

        setupExerciseStatusRecyclerView()
       // we will always check if the resttimer is already running or not
    }
    private fun setupExerciseStatusRecyclerView()
    {
        binding02?.rvExerciseStatus?.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exeStatusAdapter=exerciseStatusAdapter(exerciselist!!)
        // passing our exercise list as an arraylist of type exercisemodel
        binding02?.rvExerciseStatus?.adapter=exeStatusAdapter
    }

    private fun setuprestView()
    {


        binding02?.flRestClock?.visibility=View.VISIBLE //make rest view visible
        binding02?.tvTitle?.visibility=View.VISIBLE // making it work for rest view
        binding02?.tvNxtExe?.visibility=View.VISIBLE // making it work for rest view
        binding02?.tvNxtexerciseName?.visibility=View.VISIBLE // making it work for rest view
        binding02?.flExerciseClockView?.visibility=View.INVISIBLE //make exe view invisible
        binding02?.tvExercisename?.visibility=View.INVISIBLE// make this text invisible
        binding02?.ivImage?.visibility=View.INVISIBLE // making the image of exercise invisible
        if(resttimer!=null) // will check if the rest timer is running or it is not null
        {
            resttimer!!.cancel()// if it will be already running then control will cancel it if this exercise
            // activity is started.
            restprogress=0 // progress will be set to zero
        }
        binding02?.tvNxtexerciseName?.text=exerciselist!![currentExercisePosition+1].getname()
        speakOut("UPCOMING EXERCISE IS "+exerciselist!![currentExercisePosition+1].getname())
        setrestTimer()


    }
    private fun setrestTimer()
    {
        binding02?.progressBar?.progress=(resttimeDuration-restprogress).toInt()
        resttimer= object :CountDownTimer(resttimeDuration,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                restprogress=(resttimeDuration-millisUntilFinished)/1000
                binding02?.progressBar?.progress=(millisUntilFinished/1000).toInt()
                binding02?.tvTimer?.text=(millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                currentExercisePosition++
                exerciselist!![currentExercisePosition].setIsSelected(true)
                exeStatusAdapter!!.notifyDataSetChanged()
                // always notify the adapter about any change in recycler view
                // so that it can show that change
                setupExerciseView()
            }

        }.start()
    }
    private fun setupExerciseView()
    {
        pauseoffsetExercise=0
        exerciseclockFlag=1

        binding02?.flRestClock?.visibility=View.INVISIBLE //make rest view invisible
        binding02?.tvTitle?.visibility=View.INVISIBLE // making it not work for exercise
        binding02?.tvNxtExe?.visibility=View.INVISIBLE // making it not work for rest view
        binding02?.tvExercisename?.visibility=View.INVISIBLE // making it not work for rest view
        binding02?.flExerciseClockView?.visibility=View.VISIBLE //make exe view visible
        binding02?.tvExercisename?.visibility=View.VISIBLE// make this text visible
        binding02?.ivImage?.visibility=View.VISIBLE // making the image of exercise visible

        if(exerciseTimer!=null) // will check if the exe timer is running or it is not null
        {
            exerciseTimer!!.cancel()// if it will be already running then control will cancel it if this exercise
            // activity is started.
            Exerciseprogress=0 // progress will be set to zero
        }
        binding02?.ivImage?.setImageResource(exerciselist!![currentExercisePosition].getimage())// setting up the image
        binding02?.tvExercisename?.text=exerciselist!![currentExercisePosition].getname()
        speakOut(exerciselist!![currentExercisePosition].getname()) // calling the text to speech methode we created
        // by passing the name of exercise to be spoken..
        if(exerciseclockFlag==1) {
            exerciseTimer(pauseoffsetExercise)
        }
        exerciseclockFlag=0

    }
    private fun exerciseTimer(pauseoffsetExercisel:Long) {
        binding02?.exerciseProgressBar?.progress=(ExetimeDuration-Exerciseprogress).toInt()
        exerciseTimer= object :CountDownTimer(ExetimeDuration-pauseoffsetExercisel,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                pauseoffsetExercise=ExetimeDuration-millisUntilFinished
                // this will store the time which has been passed
                Exerciseprogress=(ExetimeDuration-millisUntilFinished)/1000
                binding02?.exerciseProgressBar?.progress=(millisUntilFinished/1000).toInt()
                binding02?.tvTimerExercise?.text=(millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                exerciselist!![currentExercisePosition].setIsSelected(false)
                exerciselist!![currentExercisePosition].setisCompleted(true)
                exeStatusAdapter!!.notifyDataSetChanged()
                if(currentExercisePosition<exerciselist!!.size-1)
                {
                    setuprestView()
                }
                else{
                    val ResultIntent=Intent(this@ExerciseActivity,ResultActivity::class.java)
                    startActivity(ResultIntent)
                    this@ExerciseActivity.finish()
                    // it is a better way to finish the activity when we are inside nested classes and objects
                }
            }

        }.start()

    }
    private fun speakOut(text:String)
    {
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    private fun CustomDialogAlert()
    {
        val customdialog= Dialog(this)
        var CustomDialogBinding=BackButtonDialogueBinding.inflate(layoutInflater)
        customdialog.setContentView(CustomDialogBinding.root)
        customdialog.setCanceledOnTouchOutside(false)
        // this will not make the user unintentionally just dismiss the dialogue by touching
        // anywhere on the screen
        CustomDialogBinding.tvYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customdialog.dismiss()
        }
        CustomDialogBinding.tvNo.setOnClickListener {
            customdialog.dismiss()
            setupExerciseView()

        }
        customdialog.show()
    }
    private fun pauseTimer() {
        if(exerciseTimer!=null)
        {
            exerciseTimer!!.cancel() // this will cancel the timer function and that function will be
            //printing the current value of time as the last value
            exerciseclockFlag=1
            // this set of code will pause the
            // timer which is running in background when Custom Dialog will appear
            // so that the user will be having the remaining time of exercise as left...
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(resttimer!=null)
        {
            resttimer!!.cancel()
            restprogress=0
        }
        if(exerciseTimer!=null)
        {
            exerciseTimer!!.cancel()
            Exerciseprogress=0
        }
        if(tts!=null)
        {
            tts!!.stop()
            tts!!.shutdown()
        }
        binding02=null
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS)
        {
            val result=tts?.setLanguage(Locale.ENGLISH)
            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("tts","Language specified is not supported")
            }
        }
        else
        {
            Log.e("tts","initialisation failed")
        }
    } // this function is used for text to speech conversion

}