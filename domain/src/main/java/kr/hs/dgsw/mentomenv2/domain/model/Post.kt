package kr.hs.dgsw.mentomenv2.domain.model

import android.os.Parcel
import android.os.Parcelable
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo

data class Post(
    val author: Int,
    val content: String,
    val imgUrls: List<String?> = emptyList(),
    val createDateTime: String,
    val postId: Int,
    val profileUrl: String,
    val stdInfo: StdInfo,
    val tag: String,
    val updateDateTime: String,
    val updateStatus: String,
    val userName: String
) : Parcelable {

    // Parcelable 구현 코드
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(author)
        parcel.writeString(content)
        parcel.writeStringList(imgUrls)
        parcel.writeString(createDateTime)
        parcel.writeInt(postId)
        parcel.writeString(profileUrl)
        parcel.writeParcelable(stdInfo, flags)
        parcel.writeString(tag)
        parcel.writeString(updateDateTime)
        parcel.writeString(updateStatus)
        parcel.writeString(userName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        author = parcel.readInt(),
        content = parcel.readString() ?: "",
        imgUrls = parcel.createStringArrayList() ?: emptyList(),
        createDateTime = parcel.readString() ?: "",
        postId = parcel.readInt(),
        profileUrl = parcel.readString() ?: "",
        stdInfo = parcel.readParcelable(StdInfo::class.java.classLoader)!!,
        tag = parcel.readString() ?: "",
        updateDateTime = parcel.readString() ?: "",
        updateStatus = parcel.readString() ?: "",
        userName = parcel.readString() ?: ""
    )
}
