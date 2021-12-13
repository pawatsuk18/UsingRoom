package com.example.usingroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.usingroom.db.ThaivbDB
import com.example.usingroom.model.Product
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var db : ThaivbDB
    private lateinit var p : Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        ThaivbDB.getDatebase(this)?.let {
            db = it
        }
        intent.getParcelableExtra<Product>("data")?.let {
            p = it
        }
        editProductName.setText(p.ProductName)
        editProductPrice.setText(p.ProductPrice.toString())

        cmdEdit.setOnClickListener {
            p.ProductName = editProductName.text.toString()
            p.ProductPrice = editProductPrice.text.toString().toInt()

            GlobalScope.launch {
                db.productDao().update(p)
                runOnUiThread {
                    Toast.makeText(this@EditActivity,"อัพเดตสินค้าเรียบร้อยแล้ว",Toast.LENGTH_LONG).show()
                    this@EditActivity.finish()
                }
            }
        }
    }
}