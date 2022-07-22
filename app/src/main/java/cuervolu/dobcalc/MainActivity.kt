package cuervolu.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =  DatePickerDialog(this,{ _, year, month, dayOfMonth ->
            //Toast.makeText(this,"Year was $year, month was ${month+1},day was $day",Toast.LENGTH_LONG).show()
            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.US)

            val theDate = sdf.parse(selectedDate)
            theDate?.let{
                val selectedDateInMinutes = theDate.time / 60000

                val currentDateInMinutes  = System.currentTimeMillis() / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvAgeInMinutes?.text = differenceInMinutes.toString()
            }


        },year,month,day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}