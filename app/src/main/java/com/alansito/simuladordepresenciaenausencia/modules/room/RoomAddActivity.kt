package com.alansito.simuladordepresenciaenausencia.modules.room

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alansito.simuladordepresenciaenausencia.R
import kotlinx.android.synthetic.main.activity_room_add.*
import kotlinx.android.synthetic.main.activity_room_light.myToolbar
import java.util.*
import android.os.Handler


class RoomAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_add)

        setSupportActionBar(myToolbar as Toolbar)
        supportActionBar?.title = getString(R.string.control_room_add)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtons()
        setupSpinners()
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
        txtNameRoom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                spinnerType.visibility = View.VISIBLE
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun setupSpinners(){
        val datasetType = LinkedList(Arrays.asList("Tipo", "Habitacion", "Baño", "Comedor"))
        val datasetTypeDevice= LinkedList(Arrays.asList("Dispositivo", "Luz", "TV", "Radio"))

        spinnerType.attachDataSource<String>(datasetType)
        spinnerTypeDevice.attachDataSource<String>(datasetTypeDevice)

        spinnerType.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            spinnerTypeDevice.visibility = View.VISIBLE
        }

        spinnerTypeDevice.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position)

            when (item) {
                "Luz" -> {
                    val handler = Handler()

                    layLight1.visibility = View.VISIBLE
                    handler.postDelayed({
                        layLight2.visibility = View.VISIBLE
                        handler.postDelayed({
                            layLight3.visibility = View.VISIBLE
                            btnSave.visibility = View.VISIBLE
                        }, 1000)
                    }, 1000)


                }

                "TV", "Radio", "Dispositivo" -> {
                    layLight1.visibility = View.GONE
                    layLight2.visibility = View.GONE
                    layLight3.visibility = View.GONE
                    btnSave.visibility = View.GONE
                }

            }
        }
    }

}