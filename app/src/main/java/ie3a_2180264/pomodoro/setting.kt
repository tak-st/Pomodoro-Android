package ie3a_2180264.pomodoro

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*

class setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val dataStore: SharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val editor = dataStore.edit()
        editTextNumberDecimal.setText(dataStore.getInt("Today", 0).toString())
        editTextNumberDecimal2.setText(dataStore.getInt("Tomato", 0).toString())
        imageView2.setOnClickListener() {
            //editor.putInt("WorkMinute",(editTextNumberDecimal.getText())
            //editor.putInt("RestMinute",dataStore.getInt("Tomato", 0)+1)
            finish()
        }
    }
}