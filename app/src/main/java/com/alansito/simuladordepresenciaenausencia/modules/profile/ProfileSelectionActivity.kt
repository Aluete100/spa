package com.alansito.simuladordepresenciaenausencia.modules.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import com.alansito.simuladordepresenciaenausencia.modules.room.RoomLightActivity
import com.alansito.simuladordepresenciaenausencia.modules.room.RoomRadioActivity
import kotlinx.android.synthetic.main.activity_profile_selection.*
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.activity_room.myToolbar

class ProfileSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_selection)

        setSupportActionBar(myToolbar as Toolbar)

        supportActionBar?.title = getString(R.string.profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtons()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            android.R.id.home -> {this.finish()}
        }

        return true
    }

    private fun setupButtons(){
        imgProfile1.setOnClickListener{
            startActivity(Intent(this, ProfileSelectedActivity::class.java))
        }
    }
}

