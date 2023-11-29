package kr.hs.dgsw.mentomenv2.feature.post

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mentomenv2.adapter.ImageAdapter
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityPostBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PostActivity : BaseActivity<ActivityPostBinding, PostViewModel>() {
    override val viewModel: PostViewModel by viewModels()

    private var imageAdapter: ImageAdapter? = null
    private val imageList = MutableLiveData<ArrayList<Uri?>>(arrayListOf())

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

            imageAdapter?.submitList(imageList.value)
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
        imageAdapter = ImageAdapter()
        mBinding.rvImage.adapter = imageAdapter
        bindingViewEvent {
            when (it) {
                PostViewModel.ON_CLICK_IMAGE -> {
                    getImageGallery()
                }
                PostViewModel.ON_CLICK_CONFIRM -> {
                    submitPost()
                }
            }
        }
        mBinding.backButton.setOnClickListener {
            finish()
        }
    }

    fun submitPost() {
        Toast.makeText(this, "등록 성공.", Toast.LENGTH_SHORT).show()
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
