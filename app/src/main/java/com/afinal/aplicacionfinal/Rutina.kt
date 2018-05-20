package com.afinal.aplicacionfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_rutina.*
import java.text.SimpleDateFormat
import java.util.*
import com.afinal.aplicacionfinal.R.id.fecha



class Rutina : AppCompatActivity() {
    var comidas = ArrayList<String>()
    var productos = ArrayList<String>()
    var dias = ArrayList<String>()
    var rutinas = ArrayList<dtrutina>()
    var request_code = 1
    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina)

        val c = ArrayAdapter(this, android.R.layout.simple_spinner_item, comidas)
        c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val p = ArrayAdapter(this, android.R.layout.simple_spinner_item, productos)
        p.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        newDay.setOnClickListener {
            val intent = Intent(this,  NuevoDiaRutina::class.java)
            cont++
            intent.putExtra("day", cont)
            startActivityForResult(intent, request_code)
        }

        spinnerDias?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                comidas.clear()
                rutinas.forEach {
                    if (it.day.toString().equals(spinnerDias.selectedItem.toString())) {
                        if (!it.comidas.isEmpty()) {
                            it.comidas.forEach {
                                if (!it.productos.isEmpty()) {
                                    comidas.add(it.tipo)
                                }
                            }
                        }
                        spinnerComidas!!.adapter = c
                    }
                }
            }
        }
        spinnerComidas?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                productos.clear()
                rutinas.forEach {
                    if (it.day.toString().equals(spinnerDias.selectedItem.toString())) {
                        if (!it.comidas.isEmpty()) {
                            it.comidas.forEach {
                                if (!it.productos.isEmpty() && it.tipo.equals(spinnerComidas.selectedItem.toString())) {
                                    it.productos.forEach(){
                                        productos.add(it.name)
                                    }
                                }
                            }
                        }
                        spinnerProductos!!.adapter = p
                    }
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        if ((requestCode == request_code) && (resultCode == RESULT_OK)){
            cargarDatos(data.extras.get("rutinaSeleccionada") as ArrayList<dtrutina>)
        }
    }

    fun cargarDatos(dtrutina: ArrayList<dtrutina>) {
        rutinas = dtrutina
        rutinas.forEach {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, it.day)
            var dateformated = SimpleDateFormat("dd/MM/yyyy")
            var fecha= dateformated.format(calendar.time)
            dias.add(fecha)
        }

        val d = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDias!!.adapter = d

    }
}
