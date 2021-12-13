package com.example.usingroom.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Product (
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo(name = "ProductName") var ProductName : String,
    @ColumnInfo(name = "ProductPrice") var ProductPrice : Int
        ) : Parcelable{
}