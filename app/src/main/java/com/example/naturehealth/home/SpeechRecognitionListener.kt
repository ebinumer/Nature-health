package com.example.naturehealth.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.speech.RecognitionListener
import android.util.Log
import android.speech.SpeechRecognizer


class SpeechRecognitionListener(val mInterface: ResultInterface): RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) {
        Log.e(TAG, "onReadyForSpeech")
        Log.e(TAG, "onReadyForSpeech$params")
    }

    override fun onBeginningOfSpeech() {

    }

    override fun onRmsChanged(rmsdB: Float) {

    }

    override fun onBufferReceived(buffer: ByteArray?) {
        Log.e(TAG, "onResults$buffer")
    }

    override fun onEndOfSpeech() {

    }

    override fun onError(error: Int) {
        Log.e(TAG, "onError$error")
    }

    override fun onResults(results: Bundle?) {
        Log.e(TAG, "onResults"); //$NON-NLS-1$
        val matches = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        Log.e(TAG, "onResults$matches")
        mInterface.resultData(matches?.get(0).toString())


    }

    override fun onPartialResults(partialResults: Bundle?) {
        Log.e(TAG, "onResults$partialResults")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        Log.e(TAG, "onResults$params")
    }
}