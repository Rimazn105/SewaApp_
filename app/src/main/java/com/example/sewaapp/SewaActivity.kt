package com.example.sewaapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sewaapp.data.DataStore
import com.example.sewaapp.model.Sewa

class SewaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa)

        val etNama = findViewById<EditText>(R.id.etNamaPenyewa)
        val spinner = findViewById<Spinner>(R.id.spinnerBarang)
        val etHari = findViewById<EditText>(R.id.etHari)

        val listBarang = DataStore.barangList.map { it.nama }

        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listBarang
        )

        findViewById<Button>(R.id.btnSimpan).setOnClickListener {

            val nama = etNama.text.toString()
            val barang = spinner.selectedItem.toString()
            val hari = etHari.text.toString().toIntOrNull() ?: 0

            val harga = DataStore.barangList.find {
                it.nama == barang
            }?.harga ?: 0

            val total = harga * hari

            DataStore.sewaList.add(
                Sewa(nama, barang, hari, total)
            )

            Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}