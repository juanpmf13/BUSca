package me.dio.busca.dados

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parada(
    val cp: Int,
    val np: String,
    val ed: String,
    val py: Double,
    val px: Double
): Parcelable