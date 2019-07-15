package com.alansito.simuladordepresenciaenausencia.modules.room

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import kotlinx.android.synthetic.main.room_selection.*
import kotlinx.android.synthetic.main.room_selection.myToolbar

class RoomSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room_selection)

        setSupportActionBar(myToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.control_toolbartext2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtons()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.house_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.house_edit_menu -> {Toast.makeText(this, "Editar casa", Toast.LENGTH_SHORT).show()}
            android.R.id.home -> {this.finish()}
        }

        return true
    }

    private fun setupButtons(){
        imgHabitacion1.setOnClickListener{
            startActivity(Intent(this, RoomActivity::class.java))
        }
    }
}