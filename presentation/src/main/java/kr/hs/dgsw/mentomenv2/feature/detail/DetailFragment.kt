package kr.hs.dgsw.mentomenv2.feature.detail

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.CommentAdapter
import kr.hs.dgsw.mentomenv2.adapter.DetailImageAdapter
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentAdapterCallback
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentDetailBinding
import kr.hs.dgsw.mentomenv2.feature.detail.comment.CommentViewModel
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailViewModel>(),
    CommentAdapterCallback {
    private val commentViewModel: CommentViewModel by viewModels()
    override val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    private val commentAdapter = CommentAdapter(this)

    override fun setupViews() {
        collectState()
        observeViewModel()
        settingDefaultValue()
        (activity as MainActivity).hasBottomBar(false)
        // viewModel.getUserInfo()

        mBinding.backButton.setOnClickListener {
            (activity as MainActivity).hasBottomBar(true)
            findNavController().navigateUp()
        }

        mBinding.cvComment.setOnClickListener {
            hideKeyboard()
        }

        mBinding.main.setOnClickListener {
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
            commentViewModel.postComment()
        }
    }

    private fun settingDefaultValue() {
        mBinding.detailViewModel = viewModel
        mBinding.commentViewModel = commentViewModel
        mBinding.rvComment.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvComment.adapter = commentAdapter
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

    private fun observeViewModel() {

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

        viewModel.myUserId.observe(this) {
            if (args.item.author == it) {
                mBinding.btnMore.visibility = View.VISIBLE
            } else {
                mBinding.btnMore.visibility = View.GONE
            }
            commentAdapter.setMyUserId(it)
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

        viewModel.imgUrls.observe(this) {
            if (!it.isNullOrEmpty()) {
                mBinding.viewpagerFrame.visibility = View.VISIBLE
                val imageAdapter = DetailImageAdapter(it) {}
                mBinding.viewpager.adapter = imageAdapter
                mBinding.wormDotsIndicator.attachTo(mBinding.viewpager)

                mBinding.wormDotsIndicator.visibility = View.VISIBLE
            } else {
                mBinding.viewpagerFrame.visibility = View.GONE
                mBinding.wormDotsIndicator.visibility = View.GONE
            }
        }

        viewModel.createDateTime.observe(this) {
            mBinding.datetime.text = it
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            commentViewModel.commentState.collect { state ->
                Log.d("collectCommentState: ", "collectCommentState: ${state.commentList}")
                commentAdapter.submitList(state.commentList)
                if (state.error == "finish") {
                    getActivity()?.getSupportFragmentManager()?.beginTransaction()?.remove(requireParentFragment())?.commit()
                }
                if (state.error.isNotBlank()) {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hasBottomBar(true)
    }

    override fun deleteComment(commentId: Int) {
        commentViewModel.deleteComment(commentId)
    }

    override fun updateComment(
        commentId: Int,
        content: String,
    ) {
        commentViewModel.updateComment(commentId, content)
    }
}
