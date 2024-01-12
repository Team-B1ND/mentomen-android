package kr.hs.dgsw.mentomenv2.data.datasource_impl.remote.base

import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.domain.usecase.token.GetTokenUseCase
import kr.hs.dgsw.mentomenv2.domain.util.Result
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

abstract class RetrofitDataSourceImpl<SV> (
    private val getTokenUseCase: GetTokenUseCase
) : BaseDataSourceImpl<SV>() {
    protected fun <T> createApi(service: Class<T>): T {
        return RETROFIT.create(service)
    }

    private val RETROFIT: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://mentomen.team-alt.com/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            )
        )
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()

    private val client: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            // 토큰을 가져오는 메서드가 있다고 가정하고, "YOUR_TOKEN_METHOD"를 실제 메서드로 대체하세요.
            val token = getTokenUseCase.invoke().let {
                it.map {
                    when (it) {
                        is Result.Success -> it.data?.refreshToken ?: ""
                        is Result.Error -> ""
                        is Result.Loading -> ""
                    }
                }
            }

            val okHttpBuilder = OkHttpClient().newBuilder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor)

            return okHttpBuilder.build()
        }
}