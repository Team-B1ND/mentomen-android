package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.response.ImgUrlResponse
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileService {
    @Multipart
    @POST("file/upload")
    fun postFile(
        @Part imageFile: List<MultipartBody.Part>,
    ): Call<BaseResponse<List<ImgUrlResponse>>>
}
