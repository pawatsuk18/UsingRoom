package com.example.usingroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.usingroom.db.ThaivbDB
import com.example.usingroom.model.Product
import kotlinx.android.synthetic.main.activity_addactivity.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Addactivity : AppCompatActivity() {
    private lateinit var db : ThaivbDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addactivity)
        ThaivbDB.getDatebase(this)?.let {
            db = it
        }
        cmdAdd.setOnClickListener {
            if (editProductName.text.isNotEmpty() && editProductPrice.text.isNotEmpty()){
                val p = Product(null,editProductName.text.toString(),editProductPrice.text.toString().toInt())
                GlobalScope.launch {
                    db.productDao().add(p)
                    runOnUiThread {
                        Toast.makeText(this@Addactivity,"บันทึกข้อมูลสินค้าใหม่แล้ว",Toast.LENGTH_LONG).show()
                        this@Addactivity.finish()
                    }
                }
            }else{
                Toast.makeText(this,"กรุณาป้อนข้อมูลสินค้าให้ครบ",Toast.LENGTH_LONG).show()
            }
        }
    }
}