package kr.hs.dgsw.mentomenv2.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Post(
    val author: Int = 0,
    val content: String = "",
    val imgUrls: List<String>? = emptyList(),
    val createDateTime: String = "2024-03-11T01:28:36.93154",
    val postId: Int = 0,
    val profileUrl: String? = null,
    val stdInfo: StdInfo = StdInfo(),
    val tag: String = "",
    val updateDateTime: String = "2024-03-11T11:14:27.13239",
    val updateStatus: String = "",
    val userName: String = "",
    var isExpended: Boolean = false,
) : Parcelable {
    // Parcelable 구현 코드
    override fun writeToParcel(
        parcel: Parcel,
        flags: Int,
    ) {
        parcel.writeInt(author)
        parcel.writeString(content)
        parcel.writeList(imgUrls)
        parcel.writeString(createDateTime)
        parcel.writeInt(postId)
        parcel.writeString(profileUrl)
        parcel.writeParcelable(stdInfo, flags)
        parcel.writeString(tag)
        parcel.writeString(updateDateTime)
        parcel.writeString(updateStatus)
        parcel.writeString(userName)
        parcel.writeByte(if (isExpended) 1 else 0)
    }

    private constructor(parcel: Parcel) : this(
        author = parcel.readInt(),
        content = parcel.readString() ?: "",
        imgUrls =
            mutableListOf<String>().apply {
                parcel.readList(this, String::class.java.classLoader)
            },
        createDateTime = parcel.readString() ?: "",
        postId = parcel.readInt(),
        profileUrl = parcel.readString() ?: "",
        stdInfo = parcel.readParcelable(StdInfo::class.java.classLoader)!!,
        tag = parcel.readString() ?: "",
        updateDateTime = parcel.readString() ?: "",
        updateStatus = parcel.readString() ?: "",
        userName = parcel.readString() ?: "",
        isExpended = parcel.readByte() != 0.toByte(),
    )

    override fun hashCode(): Int {
        var result = author
        result = 31 * result + content.hashCode()
        result = 31 * result + (imgUrls?.hashCode() ?: 0)
        result = 31 * result + createDateTime.hashCode()
        result = 31 * result + postId
        result = 31 * result + (profileUrl?.hashCode() ?: 0)
        result = 31 * result + stdInfo.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + updateDateTime.hashCode()
        result = 31 * result + updateStatus.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + isExpended.hashCode()
        return result
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
}
