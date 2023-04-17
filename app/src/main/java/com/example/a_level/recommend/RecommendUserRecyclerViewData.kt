package com.example.a_level.recommend

import android.os.Parcel
import android.os.Parcelable

data class RecommendUserRecyclerViewData(
    val image: String,
    val name: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecommendUserRecyclerViewData> {
        override fun createFromParcel(parcel: Parcel): RecommendUserRecyclerViewData {
            return RecommendUserRecyclerViewData(parcel)
        }

        override fun newArray(size: Int): Array<RecommendUserRecyclerViewData?> {
            return arrayOfNulls(size)
        }
    }
}