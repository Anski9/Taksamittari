package com.example.teht3

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.example.teht3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            laskettuTaksa()
        }
    }

    private fun laskettuTaksa() {
        val lahtoTaksa = tallennettuLahtoTaksa()
        val kilometriTaksa = tallennettuKilometriTaksa()
        val ajettuMatka = findViewById<EditText>(R.id.kilometresInput).text.toString().toDoubleOrNull() ?: 0.0

        val tulos = kilometriTaksa * ajettuMatka + lahtoTaksa
        val taksaOutput = findViewById<TextView>(R.id.taksaOutput)
        taksaOutput.text = "$tulos €"
    }

    private fun tallennettuLahtoTaksa(): Double {
        val sharedPreferences = getSharedPreferences("Asetukset", MODE_PRIVATE)
        return sharedPreferences.getString("LahtoTaksa", "0.0")!!.toDouble()
    }

    private fun tallennettuKilometriTaksa(): Double {
        val sharedPreferences = getSharedPreferences("Asetukset", MODE_PRIVATE)
        return sharedPreferences.getString("KilometriTaksa", "0.0")!!.toDouble()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, AsetuksetActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}