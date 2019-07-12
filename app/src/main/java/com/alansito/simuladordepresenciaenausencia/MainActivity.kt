package com.alansito.simuladordepresenciaenausencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rbddevs.splashy.Splashy
import kotlinx.android.synthetic.main.activity_wifi_connect.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.control_selection)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.control_toolbartext)
        setSplashy()

    }

    fun setSplashy() {
        Splashy(this)
            .setLogo(R.drawable.splash)
            .setTitleColor(R.color.black)
            .setTitle("SPA")
            .setProgressColor(R.color.white)
            .setBackgroundResource(R.color.white)
            .setFullScreen(true)
            .setTime(5000)
            .show()

        Splashy.onComplete(object : Splashy.OnComplete{
            override fun onComplete() {
                startActivity(Intent(applicationContext, WifiConnectActivity::class.java))
            }
        })
    }
}