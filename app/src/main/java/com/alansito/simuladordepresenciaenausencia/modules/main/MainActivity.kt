package com.alansito.simuladordepresenciaenausencia.modules.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import com.alansito.simuladordepresenciaenausencia.modules.wifi.WifiConnectActivity
import com.alansito.simuladordepresenciaenausencia.modules.room.RoomSelectionActivity
import com.rbddevs.splashy.Splashy
import kotlinx.android.synthetic.main.control_selection.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.control_selection)

        setupActionBar()
        setSplashy()

        setupButtons()


    }

    private fun setSplashy() {
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

    private fun setupActionBar(){
        setSupportActionBar(myToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.control_toolbartext)
    }

    private fun setupButtons() {

        imgHabitaciones.setOnClickListener{
            startActivity(Intent(this, RoomSelectionActivity::class.java))
        }
    }

}