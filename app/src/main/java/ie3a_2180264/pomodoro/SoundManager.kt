package ie3a_2180264.pomodoro

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool

class SoundManager(context: Context) {

    private val mSoundPool : SoundPool

    companion object{
        val SoundList = intArrayOf(R.raw.worktime,R.raw.resttime)
    }
    private val mSoundTable = IntArray(SoundList.size)

    init{
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        mSoundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(2)
            .build()
        for (i in SoundList.indices) {
            mSoundTable[i] = mSoundPool.load(context, SoundList[i], 1)
        }
    }

    fun play(no: Int, vol: Int) {
        if (no < 0 || no >= mSoundTable.size) {
            return
        }
        val fvol = (vol / 100).toFloat()

        mSoundPool.play(mSoundTable[no], fvol, fvol, 0, 0, 1.0f)
    }


    fun release() {

        for (i in mSoundTable.indices) {
            mSoundPool.unload(mSoundTable[i])
        }
        mSoundPool.release()
    }
}