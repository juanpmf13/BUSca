package me.dio.busca.dados

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Veiculos (
    val hr: String,
    val l: List<L>
):Parcelable{
    @JvmName("getL1")
    fun getL(): List<L> {
        return l
    }
}

@Parcelize
data class VeiculoLinha(
            val hs : String,
            val vs: List<V>
):Parcelable

@Parcelize
data class VeiculoHora(
    val hr : String,
    val p : ParadaHora
):Parcelable

@Parcelize
data class ParadaHora(
    val cp: Int,
    val np: String,
    val py: Double,
    val px: Double,
    val l: List<L>
):Parcelable

@Parcelize
data class HoraPrevista(
    val np: String,
    val c: String,
    val lt0: String,
    val lt1: String,
    val sl: Int,
    val t: String
):Parcelable

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
    val t: String,
    val py: Double,
    val px: Double
):Parcelable
