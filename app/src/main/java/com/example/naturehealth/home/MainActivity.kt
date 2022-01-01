package com.example.naturehealth.home

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.content.Intent
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.speech.SpeechRecognizer
import android.speech.RecognizerIntent
import android.widget.Toast
import com.example.naturehealth.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ResultInterface, MultiplePermissionsListener {
    private val REQ_CODE_SPEECH_INPUT = 100
    private lateinit var mSpeechRecognizer: SpeechRecognizer
    private lateinit var mSpeechRecognizerIntent: Intent
    private val mIslistening = false
    private lateinit var mTTS: TextToSpeech
    lateinit var wifiManager: WifiManager
    private var grantallpermission= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
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

        btn.performClick()
        val listener = SpeechRecognitionListener(this)
        mSpeechRecognizer.setRecognitionListener(listener)
        btn.setOnClickListener {
            if (!mIslistening) {
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            }
        }
    }

    override fun resultData(data: String) {
        txt.text=data
        when (data) {
            "call doctor" -> {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:+919633107311")
                startActivity(callIntent)
            }
            "off Wi-Fi" -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                    startActivityForResult(panelIntent, 0)
                } else {
                    // add appropriate permissions to AndroidManifest file (see https://stackoverflow.com/questions/3930990/android-how-to-enable-disable-wifi-or-internet-connection-programmatically/61289575)
                    (this.applicationContext?.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.apply {
                        isWifiEnabled = true /*or false*/
                    }
                }
                wifiManager.isWifiEnabled = false
            }
            "on wifi" -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                    startActivityForResult(panelIntent, 0)
                } else {
                    // add appropriate permissions to AndroidManifest file (see https://stackoverflow.com/questions/3930990/android-how-to-enable-disable-wifi-or-internet-connection-programmatically/61289575)
                    (this.applicationContext?.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.apply {
                        isWifiEnabled = true /*or false*/
                    }
                }
                wifiManager.isWifiEnabled = true
            }
            "on Wi-Fi" ->{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                    startActivityForResult(panelIntent, 0)
                } else {
                    // add appropriate permissions to AndroidManifest file (see https://stackoverflow.com/questions/3930990/android-how-to-enable-disable-wifi-or-internet-connection-programmatically/61289575)
                    (this.applicationContext?.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.apply {
                        isWifiEnabled = true /*or false*/
                    }
                }
                wifiManager.isWifiEnabled = true
            }
        }

    }

    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
        if (p0 != null) {
            if (p0.areAllPermissionsGranted()) {
                grantallpermission = true


            } else if (p0.isAnyPermissionPermanentlyDenied) {
                grantallpermission = false
                Toast.makeText(this, "Need Permissions", Toast.LENGTH_LONG).show()
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:$packageName")
                )
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        p0: MutableList<PermissionRequest>?,
        p1: PermissionToken?
    ) {
        if (p1 != null) {
            p1.continuePermissionRequest()
        }
    }
    private fun CheckPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.CHANGE_WIFI_STATE
            )
            .withListener(this)
            .check()
    }
}