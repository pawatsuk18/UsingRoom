package com.example.usingroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usingroom.adapter.adt
import com.example.usingroom.db.ThaivbDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db : ThaivbDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ThaivbDB.getDatebase(this)?.let {
            db = it
        }
        fab.setOnClickListener {
            val it = Intent(this,Addactivity::class.java)
            startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()
        read()
    }
    fun read(){
        GlobalScope.launch {
            val productLists = db.productDao().read()
            runOnUiThread {
                val adapter = adt(productLists)
                recyclerview.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                recyclerview.adapter = adapter
            }
        }
    }
}