package com.alansito.simuladordepresenciaenausencia.modules.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import com.alansito.simuladordepresenciaenausencia.modules.utils.Common
import com.alansito.simuladordepresenciaenausencia.modules.utils.service.SPAEndpoints
import kotlinx.android.synthetic.main.activity_profile_selected.*
import kotlinx.android.synthetic.main.activity_profile_selection.*
import kotlinx.android.synthetic.main.activity_room.myToolbar
import kotlinx.android.synthetic.main.activity_room_light.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileSelectedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_selected)

        setSupportActionBar(myToolbar as Toolbar)

        supportActionBar?.title = getString(R.string.profile_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtons()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.room_edit_menu -> {
                Toast.makeText(this, "Editar habitacion", Toast.LENGTH_SHORT).show()}
            android.R.id.home -> {
                this.finish()
            }
        }

        return true
    }

    private fun setupButtons() {
        imgProfileBig.setOnClickListener {
            turnProfile()
        }
    }

    private fun turnProfile() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Common.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SPAEndpoints::class.java)
        val turnProfile = service.turnProfile()

        turnProfile.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.code() == 200){
                    if  (response.body() == "1"){
                        txtProfileState.text = getString(R.string.room_off)
                    }else{
                        txtProfileState.text = getString(R.string.room_on)
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Retrofit", "Error -> $t")
            }
        })
    }
}