package com.alansito.simuladordepresenciaenausencia.modules.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import kotlinx.android.synthetic.main.activity_room_light.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.alansito.simuladordepresenciaenausencia.modules.utils.SPAEndpoints
import retrofit2.Call
import retrofit2.Callback


class RoomLightActivity : AppCompatActivity() {

    val BASE_URL = "http://192.168.1.184"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_light)

        setSupportActionBar(myToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.room_light1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtons()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.house_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                this.finish()
            }
        }
        return true
    }

    private fun setupButtons() {
        imgOn.setOnClickListener {
            turnLight()
        }
    }

    private fun turnLight() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SPAEndpoints::class.java)
        val call = service.trunLight()

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                if (response.code() == 200){
                    Log.d("Retrofit", "Prendo")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("Retrofit", "Error" + t)
            }
        })
    }
}