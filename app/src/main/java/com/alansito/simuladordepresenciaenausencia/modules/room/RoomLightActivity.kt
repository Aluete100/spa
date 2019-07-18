package com.alansito.simuladordepresenciaenausencia.modules.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import kotlinx.android.synthetic.main.activity_room_light.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.alansito.simuladordepresenciaenausencia.utils.Common.Companion.BASE_URL
import com.alansito.simuladordepresenciaenausencia.utils.service.SPAEndpoints
import kotlinx.android.synthetic.main.activity_room_light.myToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RoomLightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_light)

        setSupportActionBar(myToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.room_light1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtons()
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
        val turnLight = service.turnLight()

        turnLight.enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.code() == 200){
                    if  (response.body() == "1"){
                        txtLightState.text = getString(R.string.room_off)
                        imgOn.background = getDrawable(R.drawable.circle_background_yellow)
                    }else{
                        txtLightState.text = getString(R.string.room_on)
                        imgOn.background = getDrawable(R.drawable.circle_background)
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Retrofit", "Error -> $t")
            }
        })
    }
}