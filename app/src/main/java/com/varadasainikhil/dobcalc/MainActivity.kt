package com.varadasainikhil.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById<Button>(R.id.button)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    @SuppressLint("NewApi")
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
            var selectedDateText :TextView? = null
            selectedDateText = findViewById(R.id.SelectedDate)
            val selectedDate = "${selectedDayOfMonth}/${selectedMonth+1}/${selectedYear}"
            selectedDateText?.text=selectedDate
            val sdf =SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time/ 60000
            val difference = currentDateInMinutes - selectedDateInMinutes
            var minutes : TextView? = null
            minutes = findViewById<TextView>(R.id.minutesLeft)
            if(currentDateInMinutes>selectedDateInMinutes){
                minutes?.text = "$difference"
            }
            else{
                var futuretext = findViewById<TextView>(R.id.minHavePassed)
                futuretext.text="Do not rush into the Future "
            }
        } ,
            year,
            month,
            day
        ).show()
    }
}