package com.afinal.aplicacionfinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME ="DataBase"

//Tabla de comida
val TABLE_FOODS="Foods"
val COL_DATE = "Date"
val COL_ID_BREAKFAST = "Id_Breakfast"
val COL_ID_LUNCH = "Id_Lunch"
val COL_ID_FOOD = "Id_Food"
val COL_ID_SNACK = "Id_Snack"
val COL_ID_DINNER = "Id_Dinner"

//Tabla de hora de desayuno
val TABLE_BREAKFAST="Breakfast"

//Tabla de hora de almuerzo
val TABLE_LUNCH="Lunch"

//Tabla de hora de comer
val TABLE_FOOD="Food"

//Tabla de hora de merienda
val TABLE_SNACK="Snack"

//Tabla de hora de cena
val TABLE_DINNER="Dinner"

//Tabla de productos
val TABLE_PRODUCT="Products"
val COL_ID_PRODUCT = "Id_Product"
val COL_NAME_PRODUCT = "Name"
val COL_PRICE = "Price"
val COL_DURATION = "Duration"
val COL_TYPE = "Type"
val COL_IMAGE = "Image"


class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1){


    override fun onCreate(db: SQLiteDatabase?) {
        //Creacion tabla base de comida
        val createTableFoods = "CREATE TABLE IF NOT EXISTS " + TABLE_FOODS +" (" +
                COL_DATE +" DATE PRIMARY KEY," +
                COL_ID_BREAKFAST + " INTEGER," +
                COL_ID_LUNCH +" INTEGER," +
                COL_ID_FOOD + " INTEGER," +
                COL_ID_SNACK + " INTEGER," +
                COL_ID_DINNER + " INTEGER);"
        db?.execSQL(createTableFoods)

        //Creacion tabla desayuno
        val createTableBreakfast = "CREATE TABLE IF NOT EXISTS " + TABLE_BREAKFAST +" (" +
                COL_ID_BREAKFAST +" INTEGER PRIMARY KEY," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(createTableBreakfast)

        //Creacion tabla almuerzo
        val createTableLunch = "CREATE TABLE IF NOT EXISTS " + TABLE_LUNCH +" (" +
                COL_ID_LUNCH +" INTEGER PRIMARY KEY," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(createTableLunch)

        //Creacion tabla comida
        val createTableFood = "CREATE TABLE IF NOT EXISTS " + TABLE_FOOD +" (" +
                COL_ID_FOOD +" INTEGER PRIMARY KEY," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(createTableFood)

        //Creacion tabla merienda
        val createTableSnack = "CREATE TABLE IF NOT EXISTS " + TABLE_SNACK +" (" +
                COL_ID_SNACK +" INTEGER PRIMARY KEY," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(createTableSnack)

        //Creacion tabla cena
        val createTableDinner = "CREATE TABLE IF NOT EXISTS " + TABLE_DINNER +" (" +
                COL_ID_DINNER +" INTEGER PRIMARY KEY," +
                COL_ID_PRODUCT + " INTEGER);"
        db?.execSQL(createTableDinner)

        //Creacion tabla productos
        val createTableProduct = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT +" (" +
                COL_ID_PRODUCT +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_PRODUCT + " VARCHAR(256)," +
                COL_PRICE +" INTEGER," +
                COL_DURATION + " INTEGER," +
                COL_TYPE + " VARCHAR(256)," +
                COL_IMAGE + " INTEGER);"
        db?.execSQL(createTableProduct)
    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {

    }

    //Insertar cualquier hora de comida (desayuno, almuerzo, comida, merienda, cena)
    fun insertAnything(product : Product, NameTable : String){
        val db = this.writableDatabase
        var values = ContentValues()

        values.put(COL_ID_PRODUCT, product.id)

        var result = db.insert(NameTable, null, values)
        if(result == -1.toLong())
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Insertado " + NameTable + " Correctamente",Toast.LENGTH_SHORT).show()
    }

    //PRODUCTOS
    fun createTableProduct(db: SQLiteDatabase?){
        val createTable = "CREATE TABLE " + TABLE_PRODUCT +" (" +
                COL_ID_PRODUCT +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME_PRODUCT + " VARCHAR(256)," +
                COL_PRICE +" INTEGER," +
                COL_DURATION + " INTEGER," +
                COL_TYPE + " VARCHAR(256)," +
                COL_IMAGE + " INTEGER);"
        System.out.println("Tabla productos creada")
        db?.execSQL(createTable)
    }

    //Inserta en la base de datos un Producto si todo fue bien manda un toast confirmando
    fun insertProduct(product : Product){
        val db = this.writableDatabase
        var values = ContentValues()

        values.put(COL_NAME_PRODUCT, product.name)
        values.put(COL_PRICE, product.price)
        values.put(COL_DURATION, product.duration)
        values.put(COL_TYPE, product.type)
        values.put(COL_IMAGE, product.image)

        var result = db.insert(TABLE_PRODUCT, null, values)
        if(result == -1.toLong())
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Insertado Correctamente",Toast.LENGTH_SHORT).show()
    }

    //Lee la base producto y la carga en la lista
    fun readProduct() : MutableList<Product>{
        var list : MutableList<Product> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_PRODUCT
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var prod = Product()
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
    fun deleteProduct(id: Int){
        val db = this.writableDatabase
        db.delete(TABLE_PRODUCT,COL_ID_PRODUCT + "="+ id,null)
        System.out.println("Base de datos eliminada")

        var arrayProducts : MutableList<Product> = ArrayList()
        arrayProducts = readProduct()

        deleteAllProduct()

        for (i in 0..(arrayProducts.size - 1)) {
            var ProductNew = Product(arrayProducts.get(i).name , arrayProducts.get(i).price, arrayProducts.get(i).duration, arrayProducts.get(i).type, arrayProducts.get(i).image);
            val db = this.writableDatabase
            var values = ContentValues()

            values.put(COL_NAME_PRODUCT, ProductNew.name)
            values.put(COL_PRICE, ProductNew.price)
            values.put(COL_DURATION, ProductNew.duration)
            values.put(COL_TYPE, ProductNew.type)
            values.put(COL_IMAGE, ProductNew.image)

            var result = db.insert(TABLE_PRODUCT, null, values)
        }

        db.close()
    }

    //Borrar la Base de datos
    fun deleteAllProduct(){
        val db = this.writableDatabase
        //db.delete(TABLE_NAME,null,null)

        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT)
        System.out.println("Base de datos eliminada");

        createTableProduct(db)
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