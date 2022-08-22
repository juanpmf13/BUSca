package me.dio.busca.dados

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Linha (
    val cl: Int?,
    val lc: Boolean?,
    val lt: String?,
    val sl: Int?,
    val tl: Int?,
    val tp: String?,
    val ts: String?
):Parcelable
