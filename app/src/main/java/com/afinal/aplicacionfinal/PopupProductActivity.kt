package com.afinal.aplicacionfinal


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import kotlinx.android.synthetic.main.activity_popup_calendar.*
import kotlinx.android.synthetic.main.popup_product.*

import com.afinal.aplicacionfinal.MainProductActivity.*

class PopupProductActivity : AppCompatActivity() {

    var newProduct :Product = Product()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_product)

        //ComboBox todas sus funciones y declaraciones
        var optionsCombo = arrayOf("Bebida","Lacteo","Carne","Cereal","Fruta")
        comboType.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionsCombo)
        comboType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               //To change body of created functions use File | Settings | File Templates.
            }
        }

        btnIcon.setOnClickListener(){
            val intent = Intent(baseContext, PopupProductIconsActivity::class.java)
            startActivity(intent)
        }

        btnAddProduct.setOnClickListener(){

        }

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width*.8).toInt(), (height*.8).toInt())
    }
}
