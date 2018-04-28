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

class PopupProductIconsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_item_icon)



        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width*.8).toInt(), (height*.8).toInt())
    }

}
