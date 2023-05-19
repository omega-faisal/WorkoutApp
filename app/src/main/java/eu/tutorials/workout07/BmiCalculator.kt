package eu.tutorials.workout07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.viewmodel.CreationExtras
import eu.tutorials.workout07.databinding.ActivityBmiCalculatorBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiCalculator : AppCompatActivity() {
    private  var bindingBmi:ActivityBmiCalculatorBinding?=null
    private var diffViewFlag:String="Metric unit system"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingBmi=ActivityBmiCalculatorBinding.inflate(layoutInflater)
        setContentView(bindingBmi?.root)


        setSupportActionBar(bindingBmi?.toolbarBMI)
        if(supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"

            bindingBmi?.toolbarBMI?.setNavigationOnClickListener {
                onBackPressed()
            }
        }

        bindingBmi?.btnCalculateUnits?.setOnClickListener {
            CalculateUnits()

        }
        bindingBmi?.rbMetricUnits?.setOnClickListener{
            diffViewFlag="Metric unit system"
            bindingBmi?.llUsUnitsTextbox?.visibility=View.INVISIBLE
            // when we click on radio button metric unit then it must make the us units text box invisible
            //and metric unit textbox visible
            bindingBmi?.tilMetricUnitHeight?.visibility=View.VISIBLE
            bindingBmi?.etMetricUnitSystemWeight?.text!!.clear()
            bindingBmi?.etMetricUnitSystemHeight?.text!!.clear()

            bindingBmi?.llDiplayBMIResult?.visibility=View.INVISIBLE
            // making the previous results invisible when we switch the conversion medium from us to metric


        }
        bindingBmi?.rbUsUnits?.setOnClickListener {
            diffViewFlag="Us unit system"
            bindingBmi?.llUsUnitsTextbox?.visibility=View.VISIBLE
            bindingBmi?.tilMetricUnitHeight?.visibility=View.INVISIBLE

            bindingBmi?.llDiplayBMIResult?.visibility=View.INVISIBLE
            // making the previous results invisible when we switch the conversion medium from metric to us

            bindingBmi?.etMetricUnitSystemWeight?.text!!.clear()

            bindingBmi?.etUsUnitSystemHeightFeet?.text!!.clear()
            //height feet and inch values are cleared if available prev.
            bindingBmi?.etUsUnitSystemHeightInch?.text!!.clear()

        }
        // these are the codes responsible for changing the view when we switch from
        // metric to us units or vice versa
    }
    private fun CalculateUnits()
    {
        if(diffViewFlag=="Metric unit system")
        {
            if(validateMetricUnit())
            {
                var height:Float=bindingBmi?.etMetricUnitSystemHeight?.text.toString().toFloat()/100
                // 100 is used to change the value from cm to meter
                var weight:Float=bindingBmi?.etMetricUnitSystemWeight?.text.toString().toFloat()

                var bmi:Float=weight/(height*height)

                displayBMIResults(bmi)
                // calling this function to change the text of bmi in xml and
                // for showing bmi type and description
            }
            else
            {
                Toast.makeText(this, "Please Enter Valid Values", Toast.LENGTH_SHORT).show()
            }
        }
        else{
               if(validateUSUnit())
               {
                   var UsheightFeet:Float=bindingBmi?.etUsUnitSystemHeightFeet?.text.toString().toFloat()

                   var USheightInchs:Float=bindingBmi?.etUsUnitSystemHeightInch?.text.toString().toFloat()

                   var weightPounds:Double=2.205*(bindingBmi?.etMetricUnitSystemWeight?.text.toString().toDouble())
                   // we have taken weight in kg from the weight text box inside Us view
                   //and converted it into pounds

                   var heightvalue:Float=
                       USheightInchs+UsheightFeet*12
                   // taken the total value in inch

                   val bmius=703 * (weightPounds/(heightvalue * heightvalue))
                   displayBMIResults(bmius.toFloat())
               }
        }
    }
    private fun displayBMIResults(Bmi:Float)
    {
        var bmiLabel:String
        var bmiDescription:String


        if(Bmi.compareTo(15f)<=0)
        // this means if it is less than or equal to 15
        {
            bmiLabel="Very Severely underweight"
            bmiDescription="Oops! you really need to take care of yourself!Eat More"
        }
        else if(Bmi.compareTo(15f)>0 && Bmi.compareTo(16f)<=0)
        {
            bmiLabel="Severely underweight"
            bmiDescription="Oops! you really need to take care of yourself!Eat More"
        }
        else if(Bmi.compareTo(16f)>0 && Bmi.compareTo(18.5f)<=0)

        {
            bmiLabel="Underweight"
            bmiDescription="Oops! you really need to take care of yourself!Eat More"
        }
        else if(Bmi.compareTo(18.5f)>0 && Bmi.compareTo(25f)<=0)

        {
            bmiLabel="Normal"
            bmiDescription="Congratulations! You are in good shape!"
        }
        else if(Bmi.compareTo(25f)>0 && Bmi.compareTo(30f)<=0)
        {
            bmiLabel="Overweight"
            bmiDescription="Oops! you really need to take care of yourself!Workout More"
        }
        else if(Bmi.compareTo(30f)>0 && Bmi.compareTo(35f)<=0)
        {
            bmiLabel="Obese Class | (Moderately Obese)"
            bmiDescription="Oops! you really need to take care of yourself!Workout More"
        }else if(Bmi.compareTo(35f)>0 && Bmi.compareTo(40f)<=0)
        {
            bmiLabel="Obese Class | (Severely Obese)"
            bmiDescription="OMG! You are in a very dangerous condition!Act now!"
        }
        else
        {
            bmiLabel="Obese Class | ( Very Severely Obese)"
            bmiDescription="OMG! You are in a very dangerous condition!Act now!"
        }
        bindingBmi?.llDiplayBMIResult?.visibility=View.VISIBLE

        val bmiValue= BigDecimal(Bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        // this is the code to round off the bmi value
        bindingBmi?.tvBMIValue?.text=bmiValue
        bindingBmi?.tvBMIType?.text=bmiLabel
        bindingBmi?.tvBMIDescription?.text=bmiDescription

    }
    private fun validateMetricUnit():Boolean
    {
        var isValid:Boolean=true
        if(bindingBmi?.etMetricUnitSystemHeight?.text.toString().isEmpty())
        {
            isValid=false
        }
        if(bindingBmi?.etMetricUnitSystemWeight?.text.toString().isEmpty())
        {
            isValid=false
        }
        return isValid
    }
    private fun validateUSUnit():Boolean
    {
        var isvalid:Boolean=true
        if(bindingBmi?.etUsUnitSystemHeightInch?.text.toString().isEmpty())
        {
            isvalid=false
        }
        if(bindingBmi?.etUsUnitSystemHeightFeet?.text.toString().isEmpty())
        {
            isvalid=false
        }
        return isvalid
    }
}