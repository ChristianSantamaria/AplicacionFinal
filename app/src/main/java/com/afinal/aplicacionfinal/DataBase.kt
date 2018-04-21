package com.afinal.aplicacionfinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DATABASE_NAME ="DataBase"

//Tabla de productos
val TABLE_NAME="Products"
val COL_ID = "ID"
val COL_NAME_PRODUCT = "Name"
val COL_PRICE = "Price"
val COL_DURATION = "Duration"
val COL_TYPE = "Type"
val COL_IMAGE = "Duration"


class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1){

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_PRODUCT + " VARCHAR(256)," +
                COL_PRICE +" INTEGER," +
                COL_DURATION + " INTEGER," +
                COL_TYPE + " VARCHAR(256)," +
                COL_IMAGE + "INTEGER);";
        System.out.println(createTable)
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {

    }

    //Inserta en la base de datos un Producto si todo fue bien manda un toast confirmando
    fun insertProduct(product : Product){
        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COL_NAME_PRODUCT, product.name)
        cv.put(COL_PRICE, product.price)
        cv.put(COL_DURATION, product.duration)
        cv.put(COL_TYPE, product.type)
        cv.put(COL_IMAGE,product.image)

        var result = db.insert(TABLE_NAME, null, cv)
        if(result == -1.toLong())
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Insertado Correctamente",Toast.LENGTH_SHORT).show()
    }

    //Lee la base producto y la carga en la lista
    fun readData() : MutableList<Product>{
        var list : MutableList<Product> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var prod = Product()
                prod.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                prod.name = result.getString(result.getColumnIndex(COL_NAME_PRODUCT))
                prod.price = result.getString(result.getColumnIndex(COL_PRICE)).toFloat()
                prod.duration = result.getString(result.getColumnIndex(COL_DURATION)).toInt()
                prod.image = result.getString(result.getColumnIndex(COL_IMAGE)).toInt()
                System.out.println(prod)
                list.add(prod)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    //Borrar la Base de datos
    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,null,null)
        System.out.println("Base de datos eliminada");
        db.close()
    }

//    fun updateData() {
//        val db = this.writableDatabase
//        val query = "Select * from " + TABLE_NAME
//        val result = db.rawQuery(query,null)
//        if(result.moveToFirst()){
//            do {
//                var cv = ContentValues()
//                cv.put(COL_PRICE,(result.getInt(result.getColumnIndex(COL_PRICE))+1))
//                db.update(TABLE_NAME,cv,COL_ID + "=? AND " + COL_NAME_PRODUCT + "=?",
//                        arrayOf(result.getString(result.getColumnIndex(COL_ID)),
//                                result.getString(result.getColumnIndex(COL_NAME_PRODUCT))))
//            }while (result.moveToNext())
//        }
//
//        result.close()
//        db.close()
//    }


}