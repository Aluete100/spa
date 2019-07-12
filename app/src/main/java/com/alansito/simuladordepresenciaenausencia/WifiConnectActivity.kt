package com.alansito.simuladordepresenciaenausencia

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_wifi_connect.*
import kotlinx.android.synthetic.main.content_wifi_connect.*
import android.net.wifi.WifiConfiguration
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService



class WifiConnectActivity : AppCompatActivity() {

    lateinit var wifiManager: WifiManager
    lateinit var wifiResults: MutableList<ScanResult>
    lateinit var arrayAdapter: ArrayAdapter<String>
    private var wifiSize = 0
    private var arrayList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_wifi_connect)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (!wifiManager.isWifiEnabled()){
            Toast.makeText(this, "El Wifi se encuentra apagado, vamos a encenderlo", Toast.LENGTH_LONG).show()
            wifiManager.setWifiEnabled(true)
        }

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        wifiList.adapter = arrayAdapter

        scanWifi()

    }

    private fun scanWifi() {
        arrayList.clear()
        registerReceiver(wifiReciver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        Toast.makeText(this, "Buscando redes WiFi", Toast.LENGTH_LONG).show()

    }

    val wifiReciver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            wifiResults = wifiManager.scanResults
            unregisterReceiver(this)

            wifiResults.forEach {
                arrayList.add(it.SSID)
                arrayAdapter.notifyDataSetChanged()
            }
            progressBar.visibility = View.GONE
            wifiList.visibility = View.VISIBLE
        }
    }
}
