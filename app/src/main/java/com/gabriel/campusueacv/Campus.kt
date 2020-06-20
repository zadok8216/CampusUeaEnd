package com.gabriel.campusueacv

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Campus(
    val campusName: String,
    val cityName: String,
    val address: String,
    val phoneNumber: String,
    val imageLocal: Int,
    val latitude: Double,
    val longitude: Double
): Parcelable