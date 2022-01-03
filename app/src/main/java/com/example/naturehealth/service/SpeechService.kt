package com.example.naturehealth.service

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


class SpeechService  : Service(), ResultInterface {

    // declaring object of MediaPlayer
    private lateinit var player:MediaPlayer
    private lateinit var mSpeechRecognizer: SpeechRecognizer
    private lateinit var mSpeechRecognizerIntent: Intent

    // execution of service will start
    // on calling this method
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

Toast.makeText(this,"ser",Toast.LENGTH_SHORT).show()
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
                val timer = object: CountDownTimer(800, 500) {
            override fun onTick(millisUntilFinished: Long) {
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
            }
            override fun onFinish() {
                this.start()
            }
        }
        timer.start()
        return START_NOT_STICKY
    }

    // execution of the service will
    // stop on calling this method
    override fun onDestroy() {
        super.onDestroy()
        mSpeechRecognizer.cancel()
        mSpeechRecognizer.destroy();
        // stopping the process

    }

//    start

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun resultData(data: String) {
Log.e("speech","hit here$data")
        if(data=="start"){
        val myIntent = Intent()
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        applicationContext.startActivity(myIntent)
    }
    }
}