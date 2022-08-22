package me.dio.busca.dados

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
data class Veiculo(
    @PrimaryKey
    val id:Int,
    val px: Double,
    val py: Double,
    val vindo:String,
    val indo: String
)