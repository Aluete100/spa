package com.alansito.simuladordepresenciaenausencia.modules.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.activity_room.myToolbar
import kotlinx.android.synthetic.main.room_selection.*

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        setSupportActionBar(myToolbar as Toolbar)

        supportActionBar?.title = getString(R.string.control_toolbartext2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtons()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.room_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.room_edit_menu -> {
                Toast.makeText(this, "Editar habitacion", Toast.LENGTH_SHORT).show()}
            android.R.id.home -> {this.finish()}
        }

        return true
    }

    private fun setupButtons(){
        imgLamp1.setOnClickListener{
            startActivity(Intent(this, RoomLightActivity::class.java))
        }
    }

}