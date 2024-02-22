package com.example.teht3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AsetuksetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asetukset)

        lataaAsetukset()

        findViewById<Button>(R.id.button).setOnClickListener {
            val lahtoTaksa = findViewById<EditText>(R.id.lahtoInputAsetukset).text.toString()
            val kilometriTaksa =
                findViewById<EditText>(R.id.kilometresInputAsetukset).text.toString()
            tallennaAsetukset(lahtoTaksa, kilometriTaksa)

            finish()
        }
    }

    fun tallennaAsetukset(lahtoTaksa: String, kilometriTaksa: String) {
        val sharedPreferences = getSharedPreferences("Asetukset", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("LahtoTaksa", lahtoTaksa)
        editor.putString("KilometriTaksa", kilometriTaksa)
        editor.apply()
    }

    fun lataaAsetukset() {
        val sharedPreferences = getSharedPreferences("Asetukset", Context.MODE_PRIVATE)
        val lahtoTaksa = sharedPreferences.getString("LahtoTaksa", "")
        val kilometriTaksa = sharedPreferences.getString("KilometriTaksa", "")
        findViewById<EditText>(R.id.lahtoInputAsetukset).setText(lahtoTaksa)
        findViewById<EditText>(R.id.kilometresInputAsetukset).setText(kilometriTaksa)
    }


}