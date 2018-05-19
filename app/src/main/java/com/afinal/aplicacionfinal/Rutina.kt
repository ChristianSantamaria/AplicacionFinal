package com.afinal.aplicacionfinal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_rutina.*
import java.util.ArrayList

class Rutina : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var comidas = arrayOf("Desayuno","Almuerzo","Comida","Merienda","Cena")
    var request_code = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, comidas)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerComidas!!.setAdapter(aa)

        newDay.setOnClickListener {
            val intent = Intent(this,  NuevoDiaRutina::class.java)
            startActivityForResult(intent, request_code)
        }

        @Override
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
            if ((requestCode == request_code) && (resultCode == RESULT_OK)){
                cargarDatos(data.extras.get("rutinaSeleccionada") as ArrayList<Product>)
            }
        }
    }

    fun cargarDatos(list: ArrayList<Product>) {
         list.forEach {

         }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }
}
