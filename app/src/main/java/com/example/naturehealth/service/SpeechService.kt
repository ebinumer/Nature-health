package com.example.naturehealth.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import com.example.naturehealth.home.ResultInterface
import com.example.naturehealth.home.SpeechRecognitionListener
import com.example.naturehealth.SplashActivity
import java.util.*


class SpeechService  : Service(), ResultInterface {
    // declaring object of MediaPlayer
    private var mTimer: CountDownTimer? = null
    private lateinit var player: MediaPlayer
    private lateinit var mSpeechRecognizer: SpeechRecognizer
    private lateinit var mSpeechRecognizerIntent: Intent

    // execution of service will start
    // on calling this method
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        mSpeechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_CALLING_PACKAGE,
            this.packageName
        )
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
        val listener = SpeechRecognitionListener(this)
        mSpeechRecognizer.setRecognitionListener(listener)
        mTimer = object : CountDownTimer(800, 500) {
            override fun onTick(millisUntilFinished: Long) {
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
            }

            override fun onFinish() {
                this.start()
            }
        }
        (mTimer as CountDownTimer).start()
        return START_NOT_STICKY
    }

    // execution of the service will
    // stop on calling this method
    override fun onDestroy() {
        super.onDestroy()
        Log.e("hit at", "onDestroy")
        mSpeechRecognizer.cancel()
        mSpeechRecognizer.destroy();
        // stopping the process

    }


//    start

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun resultData(data: String) {
        Log.e("speech", "hit here====$data")
        if (data == "ok") {
            Log.e("speech", "hit here====$data")
            (mTimer as CountDownTimer).cancel()
            mSpeechRecognizer.stopListening()
            mSpeechRecognizer.stopListening()
            mSpeechRecognizer.cancel()
            mSpeechRecognizer.destroy()
            this.stopSelf()
            val bringToForegroundIntent = Intent(this, SplashActivity::class.java)
            bringToForegroundIntent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            bringToForegroundIntent.flags =
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(bringToForegroundIntent)

        }

    }



}