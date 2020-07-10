package ie3a_2180264.pomodoro

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataStore: SharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val editor = dataStore.edit()
        var startflag = 0
        val sound = SoundManager(this)
        var Wsec: Long = dataStore.getLong("Workminite", 25) * 60
        var Rsec: Long = dataStore.getLong("Restminite", 5) * 60
        val countDown = object : CountDownTimer(Wsec * 1000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                textView.setText((millisUntilFinished / 60000).toString() + ":" + "%02d".format((millisUntilFinished % 60000 / 1000)))
            }

            override fun onFinish() {
                Tomato.setImageResource(R.drawable.tomato_green)
                textView.setText((Rsec/60).toString() + ":00")
                editor.putInt("Today",dataStore.getInt("Today", 0)+1)
                editor.putInt("Tomato",dataStore.getInt("Tomato", 0)+1)
                editor.commit()
                textView3.setText((dataStore.getInt("Today", 0)).toString())
                textView5.setText((dataStore.getInt("Tomato", 0)).toString())
                startbutton.setText("休憩スタート")
                sound.play(0,40)
                startflag = 2
            }
        }
        val countDownRest = object : CountDownTimer(Rsec * 1000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                textView.setText((millisUntilFinished / 60000).toString() + ":" + "%02d".format((millisUntilFinished % 60000 / 1000)))
            }

            override fun onFinish() {
                textView.setText((Wsec/60).toString() + ":00")
                Tomato.setImageResource(R.drawable.tomato_red)
                startbutton.setText("スタート")
                startflag = 0

                sound.play(1,40)
            }
        }
        textView.setText((Wsec/60).toString() + ":00")
        textView3.setText(dataStore.getInt("Today", 0).toString())
        textView5.setText(dataStore.getInt("Tomato", 0).toString())

        startbutton.setOnClickListener(){
            if(startflag == 1) {
                countDown.cancel()
                countDownRest.cancel()
                Tomato.setImageResource(R.drawable.tomato_red)
                startbutton.setText("スタート")
                startflag = 0
                textView.setText((Wsec/60).toString() + ":00")
            }else if (startflag == 2){
                countDownRest.start()
                startbutton.setText("休憩終了")
                startflag = 1
        }else{
                countDown.start()
                startbutton.setText("リセット")
                startflag = 1
            }

        }
    }
}