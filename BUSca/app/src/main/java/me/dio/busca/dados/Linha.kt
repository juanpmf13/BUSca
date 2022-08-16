package me.dio.busca.dados

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Linha (
    val cl: Long?,
    val lc: Boolean?,
    val lt: String?,
    val sl: Long?,
    val tl: Long?,
    val tp: String?,
    val ts: String?
):Parcelable
