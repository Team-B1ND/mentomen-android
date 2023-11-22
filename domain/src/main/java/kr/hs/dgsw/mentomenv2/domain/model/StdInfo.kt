package kr.hs.dgsw.mentomenv2.domain.model

import android.os.Parcel
import android.os.Parcelable

data class StdInfo(
    val grade: Int,
    val room: Int,
    val number: Int,
) : Parcelable {

    // Parcelable 구현 코드
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(grade)
        parcel.writeInt(number)
        parcel.writeInt(room)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StdInfo> {
        override fun createFromParcel(parcel: Parcel): StdInfo {
            return StdInfo(parcel)
        }

        override fun newArray(size: Int): Array<StdInfo?> {
            return arrayOfNulls(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        grade = parcel.readInt(),
        number = parcel.readInt(),
        room = parcel.readInt()
    )
}
