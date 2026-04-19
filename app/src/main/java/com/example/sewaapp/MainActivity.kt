package com.example.sewaapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sewaapp.data.DataStore
import com.example.sewaapp.model.Barang

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etHarga = findViewById<EditText>(R.id.etHarga)
        val listView = findViewById<ListView>(R.id.listBarang)

        fun refresh() {
            val data = DataStore.barangList.map {
                "${it.nama} - Rp${it.harga}"
            }

            listView.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                data
            )
        }

        findViewById<Button>(R.id.btnTambah).setOnClickListener {
            val nama = etNama.text.toString()
            val harga = etHarga.text.toString().toIntOrNull() ?: 0

            DataStore.barangList.add(Barang(nama, harga))
            refresh()
        }

        listView.setOnItemLongClickListener { _, _, pos, _ ->
            DataStore.barangList.removeAt(pos)
            refresh()
            true
        }

        findViewById<Button>(R.id.btnSewa).setOnClickListener {
            startActivity(Intent(this, SewaActivity::class.java))
        }

        findViewById<Button>(R.id.btnRiwayat).setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }

        refresh()
    }
}