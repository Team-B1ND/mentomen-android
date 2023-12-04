package kr.hs.dgsw.mentomenv2.feature.post

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.ImageAdapter
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityPostBinding
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getUserInfo
import kr.hs.dgsw.smartschool.dodamdodam.dauth.model.response.UserResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostBinding, PostViewModel>() {
    override val viewModel: PostViewModel by viewModels()

    private var imageAdapter: ImageAdapter? = null
    private val imageList = MutableLiveData<ArrayList<Uri?>>(arrayListOf())

    lateinit var userInfo: UserResponse

    private var launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                imageList.value?.clear()
                viewModel.imgFile.value?.clear()
                if (result.data?.clipData != null) {
                    val count = result.data?.clipData!!.itemCount
                    if (count > 10) {
                        Toast.makeText(this, "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG)
                            .show()
                        return@registerForActivityResult
                    }
                    for (i in 0 until count) {
                        val imageUri = result.data?.clipData!!.getItemAt(i).uri
                        val file = File(absolutelyPath(imageUri, this))
                        val extension = file.toString().split(".")[1]
                        val requestFile = file.asRequestBody("image/$extension".toMediaTypeOrNull())
                        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

                        imageList.value?.add(imageUri)
                        viewModel.imgFile.value?.add(body)
                    }
                }
            }

            imageAdapter?.notifyDataSetChanged()
        }

    private fun absolutelyPath(path: Uri?, context: Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var cursor: Cursor? = null
        var result: String? = null

        try {
            cursor = context.contentResolver.query(path!!, proj, null, null, null)
            val index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            result = cursor?.getString(index!!)
        } catch (e: Exception) {
            // 예외 처리
        } finally {
            cursor?.close()
        }

        return result ?: ""
    }


    override fun start() {
        collectStates()
        viewModel.getToken()
        getUserInfo(viewModel.token.value!!,
            onFailure = {
                Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                userInfo = it
            }
        )
        imageAdapter = ImageAdapter()
        mBinding.rvImage.adapter = imageAdapter
        bindingViewEvent {
            when (it) {
                PostViewModel.ON_CLICK_IMAGE -> {
                    getImageGallery()
                }

                PostViewModel.ON_CLICK_SUBMIT -> {
                    submitPost()
                }
            }
        }
        mBinding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun collectStates() {
        viewModel.content.observe(this) { content ->
            if (content.isNotEmpty()) {
                viewModel.tagState.observe(this) { tag ->
                    Log.d("collectStates in PostActivity", "tag: $tag + content: $content")
                    if (tag != "ALL") {
                        mBinding.btnConfirm.setBackgroundColor(R.drawable.bg_btn_enable)
                    } else {
                        mBinding.btnConfirm.setBackgroundColor(R.drawable.bg_btn_disable)
                    }
                }
            } else {
                mBinding.btnConfirm.setBackgroundColor(R.drawable.bg_btn_disable)
            }
        }
    }

    fun submitPost() {
        if (viewModel.content.value.isNullOrBlank()) {
            Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (viewModel.tagState.value == "ALL") {
            Toast.makeText(this, "태그를 선택해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.submitPost(userInfo)
    }

    private fun getImageGallery() {
        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        val intent = Intent(Intent.ACTION_PICK)

        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, intent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "사용할 앱을 선택해주세요.")
        launcher.launch(chooserIntent)
    }
}
