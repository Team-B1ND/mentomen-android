package kr.hs.dgsw.mentomenv2.feature.post

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.ImageAdapter
import kr.hs.dgsw.mentomenv2.adapter.callback.DataDeleteListener
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityPostBinding
import kr.hs.dgsw.mentomenv2.domain.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostBinding, PostViewModel>(), DataDeleteListener {
    override val viewModel: PostViewModel by viewModels()
    private val isEdit = MutableStateFlow<Boolean>(false)
    private val postId = MutableLiveData<Int>()
    private var imageAdapter: ImageAdapter? = null
    private val imageList = MutableLiveData<ArrayList<String?>>(arrayListOf())

    private var photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // 선택한 이미지 처리
                if (result.data?.clipData != null) {
                    val count = result.data?.clipData!!.itemCount
                    val imageCount =
                        count + viewModel.imgFile.value!!.size + viewModel.imgUrl.value!!.size
                    if (imageCount > 10) {
                        // 이미지는 10장까지 선택 가능
                        Toast.makeText(this, "사진은 최대 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show()
                        return@registerForActivityResult
                    }

                    for (i in 0 until count) {
                        val imageUri = result.data?.clipData!!.getItemAt(i).uri

                        // URI를 파일로 변환
                        val file = File(absolutelyPath(imageUri, this))

                        // 파일을 RequestBody로 변환
                        val extension = file.toString().split(".")[1]
                        val requestFile = file.asRequestBody("image/$extension".toMediaTypeOrNull())

                        // MultipartBody.Part 생성
                        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

                        // 이미지 및 파일 목록에 추가
                        imageList.value?.add(imageUri.toString())
                        viewModel.imgFile.value?.add(body)
                    }
                }
                imageAdapter?.submitList(imageList.value)
                imageAdapter?.notifyDataSetChanged()
            }
        }

    override fun start() {
        viewModel.getMyInfo()
        isEdit.value = intent?.getBooleanExtra("isEdit", false) ?: false
        postId.value = intent?.getIntExtra("postId", 0) ?: 0
        setUpViews()
        collectStates()
        observerViewModel()
        imageAdapter = ImageAdapter(this)
        mBinding.rvImage.adapter = imageAdapter
        mBinding.rvImage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mBinding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setUpViews() {
        if (isEdit.value) {
            mBinding.btnConfirm.text = "수정 완료"
            mBinding.tvTitle.text = "수정"
            viewModel.getPostInfo(postId.value ?: 0)
        }
    }

    fun absolutelyPath(
        path: Uri?,
        context: Context,
    ): String {
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

    private fun observerViewModel() {
        bindingViewEvent {
            Log.d("PostActivity", "it: $it")
            when (it) {
                PostViewModel.ON_CLICK_IMAGE -> {
                    getImageGallery()
                }

                PostViewModel.ON_CLICK_SUBMIT -> {
                    submitPost()
                }

                PostViewModel.LOAD_IMAGE -> {
                    if (isEdit.value) {
                        viewModel.editPost(
                            postId.value ?: 0,
                        )
                    } else {
                        viewModel.submitPost()
                    }
                }
            }
        }
        viewModel.imgUrl.observe(this) { imgUrls ->
            imageAdapter?.submitList(
                imgUrls.map { imgUrl ->
                    imageList.value?.add(imgUrl?.imgUrl)
                    imgUrl?.imgUrl
                },
            )
        }
    }

    private fun collectStates() {
        viewModel.content.observe(this) { content ->
            if (content.isNotEmpty()) {
                lifecycleScope.launch {
                    viewModel.tagState.collect { tag ->
                        if (tag != "ALL") {
                            mBinding.btnConfirm.setBackgroundResource(R.drawable.bg_btn_enable)
                        } else {
                            mBinding.btnConfirm.setBackgroundResource(R.drawable.bg_btn_disable)
                        }
                    }
                }
            } else {
                mBinding.btnConfirm.setBackgroundResource(R.drawable.bg_btn_disable)
            }
        }
        lifecycleScope.launch {
            viewModel.submitMessage.collect { message ->
                if (message.isNotBlank()) {
                    Toast.makeText(
                        this@PostActivity,
                        message,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
                when (message) {
                    "게시글 등록에 성공했습니다." -> {
                        setResult(RESULT_OK)
                        finish()
                    }

                    "게시글 수정에 성공했습니다." -> {
                        finish()
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.postMessage.collect {
                mBinding.etContents.setText(it)
            }
        }
    }

    private fun submitPost() {
        if (viewModel.content.value.isNullOrBlank()) {
            Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (viewModel.tagState.value == "ALL") {
            Toast.makeText(this, "태그를 선택해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.postTag.value = viewModel.tagState.value
        viewModel.postContent.value = mBinding.etContents.text.toString()
        viewModel.tagState.value = "ALL"
        mBinding.etContents.text.clear()
        viewModel.loadImage()
    }

    private fun getImageGallery() {
        lifecycleScope.launch {
            val chooserIntent = Intent(Intent.ACTION_CHOOSER)
            val intent = Intent(Intent.ACTION_PICK)

            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            chooserIntent.putExtra(Intent.EXTRA_INTENT, intent)
            chooserIntent.putExtra(Intent.EXTRA_TITLE, "사용할 앱을 선택해주세요.")
            photoLauncher.launch(chooserIntent)
        }
    }

    override fun onDataDeleted(url: String) {
        viewModel.imgUrl.value = viewModel.imgUrl.value?.filter { it?.imgUrl != url }
    }
}
