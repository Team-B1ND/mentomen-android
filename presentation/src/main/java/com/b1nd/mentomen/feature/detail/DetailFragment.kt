package com.b1nd.mentomen.feature.detail

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.b1nd.mentomen.R
import com.b1nd.mentomen.adapter.DetailAdapter
import com.b1nd.mentomen.adapter.callback.CommentAdapterCallback
import com.b1nd.mentomen.base.BaseFragment
import com.b1nd.mentomen.databinding.DialogCommentBinding
import com.b1nd.mentomen.databinding.FragmentDetailBinding
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.feature.detail.comment.CommentViewModel
import com.b1nd.mentomen.feature.main.MainActivity
import com.b1nd.mentomen.feature.post.PostActivity
import com.b1nd.mentomen.util.translateDate
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailViewModel>(),
    CommentAdapterCallback {
    private val commentViewModel: CommentViewModel by viewModels()
    override val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    private var isEdit: MutableLiveData<Boolean> = MutableLiveData(false)
    private var editCommentId: MutableLiveData<Int> = MutableLiveData(0)

    private val commentAdapter = com.b1nd.mentomen.adapter.CommentAdapter(this)
    private val imageAdapter =
        DetailAdapter { item ->
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToDetailImageFragment(
                    item,
                ),
            )
        }

    override fun setupViews() {
        checkPostId()
        observeEvent()
        observeLiveData()
        collectState()
        setListener()
        setBottomSheet()
        (activity as MainActivity).hasBottomBar(false)
        mBinding.viewpager.adapter = imageAdapter
        mBinding.rvComment.adapter = commentAdapter
        mBinding.rvComment.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setBottomSheet() {
        val bottomSheetBinding = DialogCommentBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.window?.attributes?.windowAnimations = R.style.AnimationPopupStyle
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        mBinding.btnMore.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetBinding.tvCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.tvDelete.setOnClickListener {
            viewModel.deletePost()
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.tvEdit.setOnClickListener {
            val intent = Intent(requireContext(), PostActivity::class.java)
            intent.putExtra("isEdit", true)
            intent.putExtra("postId", viewModel.postId.value)
            startActivity(intent)
            bottomSheetDialog.dismiss()
        }
    }

    private fun setListener() {
        mBinding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        mBinding.cvComment.setOnClickListener {
            hideKeyboard()
        }

        mBinding.main.setOnClickListener {
            hideKeyboard()
        }

        mBinding.viewpagerFrame.setOnClickListener {
            hideKeyboard()
        }

        mBinding.root.setOnClickListener {
            hideKeyboard()
        }

        mBinding.srlPost.setOnRefreshListener {
            viewModel.getPostInfo()
            commentViewModel.getComment()
            mBinding.srlPost.isRefreshing = false
        }

        mBinding.ivSend.setOnClickListener {
            if (!viewModel.isLogin.value) {
                viewModel.getUserInfo()
            } else if (isEdit.value == false) {
                commentViewModel.postComment(mBinding.etComment.text.toString())
            } else {
                commentViewModel.updateComment(
                    commentId = editCommentId.value ?: 0,
                    content = mBinding.etComment.text.toString(),
                )
            }
        }

        mBinding.clCommentInput.setOnClickListener {
            if (!viewModel.isLogin.value) {
                viewModel.getUserInfo()
            }
        }

        mBinding.cvLoginCkeckBox.setOnClickListener {
            if (!viewModel.isLogin.value) {
                viewModel.getUserInfo()
            }
        }
    }

    private fun checkPostId() {
        if (args.postId == 0) {
            settingDefaultValue()
        } else {
            viewModel.postId.value = args.postId
            commentViewModel.postId.value = args.postId
            viewModel.getPostInfo()
            commentViewModel.getComment()
        }
    }

    override fun onResume() {
        viewModel.getPostInfo()
        commentViewModel.getComment()
        super.onResume()
    }

    private fun settingDefaultValue() {
        mBinding.detailViewModel = viewModel
        mBinding.commentViewModel = commentViewModel
        mBinding.rvComment.layoutManager = LinearLayoutManager(requireContext())
        viewModel.author.value = args.item.author
        viewModel.imgUrls.value = args.item.imgUrls
        viewModel.userName.value = args.item.userName
        viewModel.content.value = args.item.content
        viewModel.createDateTime.value = args.item.createDateTime
        viewModel.stdInfo.value = args.item.stdInfo
        viewModel.postId.value = args.item.postId
        viewModel.profileImg.value = args.item.profileUrl
        commentViewModel.postId.value = args.item.postId
        commentViewModel.getComment()
    }

    private fun hideKeyboard() {
        mBinding.etComment.clearFocus()
        val imm =
            requireView().context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun showKeyboard() {
        mBinding.etComment.requestFocus()
        if (!mBinding.etComment.text.isNullOrEmpty()) {
            mBinding.etComment.setSelection(mBinding.etComment.text.length)
        }
        val window: Window = requireActivity().window
        WindowCompat.getInsetsController(window, mBinding.etComment)
            .show(WindowInsetsCompat.Type.ime())
    }

    private fun observeLiveData() {
        isEdit.observe(viewLifecycleOwner) { isEdit ->
            if (!isEdit) {
                hideKeyboard()
            }
        }

        viewModel.myProfileImg.observe(this) { profileImage ->
            if (!profileImage.isNullOrBlank()) {
                Glide.with(requireContext())
                    .load(profileImage)
                    .into(mBinding.ivCommentProfile)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_default_user)
                    .into(mBinding.ivCommentProfile)
            }
        }

        viewModel.profileImg.observe(this) {
            if (!it.isNullOrBlank()) {
                Glide.with(requireContext())
                    .load(it)
                    .into(mBinding.ivProfile)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_default_user)
                    .into(mBinding.ivProfile)
            }
        }

        viewModel.imgUrls.observe(this) { urls ->
            if (!urls.isNullOrEmpty()) {
                mBinding.viewpagerFrame.visibility = View.VISIBLE
                imageAdapter.submitList(urls)
                mBinding.wormDotsIndicator.attachTo(mBinding.viewpager)
                mBinding.wormDotsIndicator.visibility = View.VISIBLE
            } else {
                mBinding.viewpagerFrame.visibility = View.GONE
                mBinding.wormDotsIndicator.visibility = View.GONE
            }
        }

        commentViewModel.toastMessage.observe(this) {
            if (it.isNotBlank()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                hideKeyboard()
                isEdit.value = false
                mBinding.etComment.text.clear()
            }
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            commentViewModel.commentState.collect { state ->
                if ((state.commentList ?: emptyList()).isNotEmpty()) {
                    mBinding.rvComment.visibility = View.VISIBLE
                    mBinding.cvComment.visibility = View.VISIBLE
                    commentAdapter.submitList(state.commentList)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.createDateTime.collect {
                mBinding.datetime.text = translateDate(it)
            }
        }
        lifecycleScope.launch {
            viewModel.content.collect { content ->
                mBinding.content.text = content
            }
        }
        lifecycleScope.launch {
            viewModel.stdInfo.collect { info ->
                mBinding.grade.text = String.format("${info.grade}학년 ")
                mBinding.room.text = String.format("${info.room}반 ")
                mBinding.number.text = String.format("${info.number} 번")
            }
        }
        lifecycleScope.launch {
            viewModel.userName.collect { name ->
                mBinding.name.text = name
            }
        }
        lifecycleScope.launch {
            viewModel.myUserId.collect {
                if (viewModel.author.value == it) {
                    mBinding.btnMore.visibility = View.VISIBLE
                } else {
                    mBinding.btnMore.visibility = View.GONE
                }
                commentAdapter.userId = it
            }
        }
        lifecycleScope.launch {
            viewModel.author.collect {
                if (viewModel.myUserId.value == it) {
                    mBinding.btnMore.visibility = View.VISIBLE
                } else {
                    mBinding.btnMore.visibility = View.GONE
                }
            }
        }
        lifecycleScope.launch {
            viewModel.isLogin.collect { isLogin ->
                if (!isLogin) {
                    mBinding.cvLoginCkeckBox.visibility = View.GONE
                }
            }
        }
        lifecycleScope.launch {
            commentViewModel.isLoading.collect { isLoading ->
                Log.d("DetailViewModel", "it: $isLoading")
                if (isLoading) {
                    mBinding.sflComment.startShimmer()
                    mBinding.rvComment.visibility = View.GONE
                    mBinding.sflComment.visibility = View.VISIBLE
                } else {
                    mBinding.sflComment.stopShimmer()
                    mBinding.rvComment.visibility = View.VISIBLE
                    mBinding.sflComment.visibility = View.GONE
                    if ((
                            commentViewModel.commentState.value.commentList
                                ?: emptyList()
                        ).isEmpty()
                    ) {
                        mBinding.cvComment.visibility = View.GONE
//                        mBinding.llCommentEmpty.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun observeEvent() {
        bindingViewEvent {
            when (it) {
                DetailViewModel.DELETE_POST -> {
                    Toast.makeText(context, "게시글 삭제에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun deleteComment(commentId: Int) {
        commentViewModel.deleteComment(commentId)
    }

    override fun updateIsEdit(
        isEdit: Boolean,
        commentId: Int,
        value: String,
    ) {
        mBinding.etComment.setText(value)
        if (isEdit) showKeyboard()
        this.isEdit.value = isEdit
        this.editCommentId.value = commentId
    }
}