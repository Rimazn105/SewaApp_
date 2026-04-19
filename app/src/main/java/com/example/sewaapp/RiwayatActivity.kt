package com.example.sewaapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.sewaapp.data.DataStore

class RiwayatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        val listView = findViewById<ListView>(R.id.listRiwayat)

        val data = DataStore.sewaList.map {
            "${it.namaPenyewa} sewa ${it.barang} (${it.hari} hari) = Rp${it.total}"
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )

        listView.adapter = adapter
    }
}