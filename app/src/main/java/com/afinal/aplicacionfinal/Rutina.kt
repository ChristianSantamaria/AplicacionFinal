package com.afinal.aplicacionfinal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rutina.*

class Rutina : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina)

        newDay.setOnClickListener {
            val intent = Intent(this,  NuevoDiaRutina::class.java)
            startActivity(intent)
        }


    }
}
