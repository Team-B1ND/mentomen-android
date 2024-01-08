package kr.hs.dgsw.mentomenv2.feature.detail

import android.util.Log
import android.view.View
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
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentDetailBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private val commentAdapter =
        CommentAdapter()

    override fun setupViews() {
        collectState()
        (activity as MainActivity).hasBottomBar(false)
        viewModel.userName.value = args.item.userName
        viewModel.content.value = args.item.content
        viewModel.createDateTime.value = args.item.createDateTime
        viewModel.stdInfo.value = args.item.stdInfo
        viewModel.postId.value = args.item.postId
        viewModel.getComment()

        mBinding.datetime.text = args.item.createDateTime
        mBinding.rvComment.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvComment.adapter = commentAdapter

        if (args.item.profileUrl.isNotEmpty()) {
            Glide.with(requireContext())
                .load(args.item.profileUrl)
                .into(mBinding.ivProfile)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.ic_default_user)
                .into(mBinding.ivProfile)
        }

        if (!args.item.imgUrls.isNullOrEmpty()) {
            mBinding.viewpagerFrame.visibility = View.VISIBLE
            val imageAdapter = DetailImageAdapter(args.item.imgUrls ?: emptyList()) {}
            mBinding.viewpager.adapter = imageAdapter
            mBinding.wormDotsIndicator.attachTo(mBinding.viewpager)

            mBinding.wormDotsIndicator.visibility = View.VISIBLE
        } else {
            mBinding.viewpagerFrame.visibility = View.GONE
            mBinding.wormDotsIndicator.visibility = View.GONE
        }

        mBinding.backButton.setOnClickListener {
            (activity as MainActivity).hasBottomBar(true)
            findNavController().navigateUp()
        }

        mBinding.ivSend.setOnClickListener {
            viewModel.postComment()
        }

        // nav arg로 받는 게 아니라 post Id를 받아와서 호출하는 방식으로 바꿔야함, 실시간성 때문에
        // 처음에는 nav arg로 받되 새로고침 시 post Id를 받아와서 호출하는 방식으로 바꿔야함
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.commentState.collect { state ->
                if ((state.commentList ?: emptyList()).isNotEmpty()) {
                    Log.d("collectCommentState: ", "collectCommentState: ${state.commentList}")
                    commentAdapter.submitList(state.commentList)
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
}
