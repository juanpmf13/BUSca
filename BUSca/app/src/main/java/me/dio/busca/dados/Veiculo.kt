package me.dio.busca.dados

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Veiculo (
    val hr: String,
    val l: List<L>
):Parcelable{
    @JvmName("getL1")
    fun getL(): List<L> {
        return l
    }
}

@Parcelize
data class L (
    val c: String,
    val cl: Long,
    val sl: Long,
    val lt0: String,
    val lt1: String,
    val qv: Long,
    val vs: List<V>
):Parcelable{
    @JvmName("getVs1")
    fun getVs():List<V>{
        return vs
    }
}

@Parcelize
data class V (
    val p: Long,
    val a: Boolean,
    val ta: String,
    val py: Double,
    val px: Double
):Parcelable
