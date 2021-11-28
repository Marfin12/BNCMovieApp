package com.example.bncmovieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Session(
    val token: String
) : Parcelable