package com.example.usingroom.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.usingroom.EditActivity
import com.example.usingroom.MainActivity
import com.example.usingroom.R
import com.example.usingroom.db.ThaivbDB
import com.example.usingroom.model.Product
import kotlinx.android.synthetic.main.rv_product_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class adt(val productLists : List<Product>) : RecyclerView.Adapter<adt.ViewHolder>() {
    private lateinit var db: ThaivbDB

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return productLists.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvProductName.text = productLists[position].ProductName
        holder.itemView.tvProductPrice.text = productLists[position].ProductPrice.toString()

        ThaivbDB.getDatebase(holder.itemView.context)?.let {
            db = it
        }

        holder.itemView.cmdUpdate.setOnClickListener {
            val editIt = Intent(it.context, EditActivity::class.java)
            editIt.putExtra("data", productLists[position])
            it.context.startActivity(editIt)
        }
        holder.itemView.cmdDelete.setOnClickListener {
            GlobalScope.launch {
                db.productDao().delete(productLists[position])
                (holder.itemView.context as MainActivity).runOnUiThread {
                    Toast.makeText(
                        holder.itemView.context,
                        "ลบข้อมูลเรียบร้อยแล้ว",
                        Toast.LENGTH_LONG
                    ).show()
                }
                (holder.itemView.context as MainActivity).read()
            }
        }
    }
}

